package edu.javagroup.jcalc.lines;

import edu.javagroup.jcalc.digits.Addition;
import edu.javagroup.jcalc.digits.Division;
import edu.javagroup.jcalc.digits.Multiplication;
import edu.javagroup.jcalc.digits.Subtraction;

/**
 * Класс реализует вычисления математических выражений.
 */
public class LineOperation {

    /**Метод удаляет последний символ из строки.
     *
     * @param source Математическое выражение(String).
     * @return Строка без последнего символа(String).
     */
    public static String removeLastSymbol(String source) {
        return source.substring(0, source.length() - 1);
    }

    /**Метод добавляет символ минуса в начало строки.
     *
     * @param source Математическое выражение(String).
     * @return Строка с символом минуса в начале(String).
     */
    public static String addMinusPrefix(String source) {
        return "-".concat(source);
    }

    /**Метод соединяем между содой две строки.
     *
     * @param firstLine Первая строка(String).
     * @param secondLine Вторая строка(String).
     * @return Результат конкатенации этих строк.(String)
     */
    public static String concatLines(String firstLine, String secondLine) {
        return firstLine.concat(secondLine);
    }

    /**Метод находит результат математического выражения.
     *
     * @param source Математическое выражение(String).
     * @return Результат математического выражения(String).
     */
    public static String getResult(String source) {
        // проверяем входную строку на null
        if (source != null && source.length() != 0) {
            // обрабатываем входное выражение
            source = LinePreparing.linePreparing(source);
            // проверяем строку на лишние символы
            if (LineCheck.isLineCorrect(source)) {
                // проверяем строку на допустимые символы кроме ()
                // если выражение содержит () то находим результат
                // с помощью вызова метода getResultWithBrackets
                // иначе с помощью метода getResultWithoutRoundBrackets
                if (LineParsing.isFinalNumber(source)) {
                    return source;
                } else if (source.contains("(") || source.contains(")")) {
                    return getResultWithBrackets(source);
                } else {
                    return getResultWithoutRoundBrackets(source);
                }
            } else {
                return source;
            }
        } else {
            return source;
        }
    }

    /**метод возвращает результат математического выражения,
     * которое содержит в себе скобки.
     *
     * @param source Математическое выражение(String).
     * @return Результат математического выражения(String).
     */
    public static String getResultWithBrackets(String source) {
        // пока математическое выражение содержит скобки
        while ((source.contains("(") || source.contains(")")) && !LineParsing.isFinalNumber(source)) {
            // координата первой открывающейся скобки
            int firstIndexBracket = source.indexOf("(") + 1;
            // координата закрывающейся скобки, следующей за открывающейся
            int nextIndexBracket = firstIndexBracket + source.substring(firstIndexBracket).indexOf(")");
            // выражение между двумя скобками
            String lineBetweenBrackets = source.substring(firstIndexBracket, nextIndexBracket);
            // обрабатываем выражение между скобками
            lineBetweenBrackets = LinePreparing.linePreparing(lineBetweenBrackets);
            // находим результат выражения в скобках
            String result = getResultWithoutRoundBrackets(lineBetweenBrackets);
            // если результат равен пустой строке, выходим из цикла
            // иначе заменяем выражение между двумя скобками на результат
            // и записываем в исходное математическое выражение
            if (result.equals("")) {
                break;
            } else {
                source = collectLines(source, result, firstIndexBracket, nextIndexBracket);
            }
        }
        // находим результат выражения без скобок
        return getResultWithoutRoundBrackets(source);
    }

    /**Метод вычисляет результат математического выражения,
     * которое несодержит скобок.
     *
     * @param source Математическое выражение(String);
     * @return Результат выражения(String).
     */
    public static String getResultWithoutRoundBrackets(String source) {
        // пока математическое выражение содержит символы математических операций
        while (source.contains("+") || source.contains("-") || source.contains("*")
                || source.contains("/") || LineParsing.isFinalNumber(source)) {
            // ищем символ математической операции
            String symbol = LineParsing.findFirstMathSymbol(source);
            // если символа не находит, то выходим из цикла
            if (symbol.equals("")) {
                break;
            }
            int indexSymbol;
            // если символом мат операции евляемся минус,
            // то мы берем координату следующего минуса,
            // а не того, который может стоять в начале математического выражения
            if (source.startsWith("-")) {
                indexSymbol = source.substring(1).indexOf(symbol) + 1;
            } else {
                indexSymbol = source.indexOf(symbol);
            }
            // в зависимости от символа операции выполняем соответствующую операцию
            // и записываем в result
            String result = "";
            switch (symbol) {
                case "*":
                    result = multiplication(source, indexSymbol);
                    break;
                case "/":
                    result = division(source, indexSymbol);
                    break;
                case "+":
                    result = addition(source, indexSymbol);
                    break;
                case "-":
                    result = subtraction(source, indexSymbol);
            }
            // заменяем в математичеком выражении
            // слагаемые выполненной операции на результат
            source = collectLines(source, result, indexSymbol);
            // если в строке есть -- заменяем на + иначе оставляем без изменения
            source = source.contains("--") ? source.replace("--", "+") : source;
            // если в математическом выражении есть точка, и после нее идет нуль,
            // то мы оставляем только часть левее точки
            if (source.contains(".") && source.charAt(source.indexOf(".") + 1) == '0') {
                source = source.substring(0,source.indexOf("."));
            }
        }
        return source;
    }

    /**Метод находит результат умножения двух чисел, стоящих
     * около символа математической операции.
     *
     * @param source Математическое выражение(String).
     * @param indexMathSymbol Координата математического символа(int).
     * @return Результат умножения двух чисел, стоящих около символа мат операции(String).
     */
    public static String multiplication(String source, int indexMathSymbol) {
        //число левее математического символа
        String leftNumber = LineParsing.getNumberFromLeftPart(source, indexMathSymbol);
        //число правее математического символа
        String rightNumber = LineParsing.getNumberFromRightPart(source, indexMathSymbol);
        // возвращаем результат умножения двух чисел
        return Multiplication.multiplication(leftNumber, rightNumber);
    }

    /**Метод находит результат деления двух чисел, стоящих
     * около символа математической операции.
     *
     * @param source Математеческое выражение(String).
     * @param indexMathSymbol Координата математического символа(int).
     * @return Результат деления двух чисел, стоящих около символа мат операции(String).
     */
    public static String division(String source, int indexMathSymbol) {
        //число левее математического символа
        String leftNumber = LineParsing.getNumberFromLeftPart(source, indexMathSymbol);
        //число правее математического символа
        String rightNumber = LineParsing.getNumberFromRightPart(source, indexMathSymbol);
        // возвращаем результат деления двух чисел
        return Division.division(leftNumber, rightNumber);
    }

    /**Метод находит сумму двух чисел, стоящих
     * около символа математической операции.
     *
     * @param source Математическое выражение(String).
     * @param indexMathSymbol Координата математического символа(int).
     * @return Результат сложения двух чисел, стоящих около символа мат операции(String).
     */
    public static String addition(String source, int indexMathSymbol) {
        //число левее математического символа
        String leftNumber = LineParsing.getNumberFromLeftPart(source, indexMathSymbol);
        //число правее математического символа
        String rightNumber = LineParsing.getNumberFromRightPart(source, indexMathSymbol);
        // возвращаем результат сложения двух чисел
        return Addition.addition(leftNumber, rightNumber);
    }

    /**Метод находит разность двух чисел, стоящих
     * по обе стороны от символа мат операции.
     *
     * @param source Математическое выражение(String).
     * @param indexMathSymbol Координата математического символа(int).
     * @return Результат вычитания чисел, стоящих около символа мат операции(String).
     */
    public static String subtraction(String source, int indexMathSymbol) {
        //число левее математического символа
        String leftNumber = LineParsing.getNumberFromLeftPart(source, indexMathSymbol);
        //число правее математического символа
        String rightNumber = LineParsing.getNumberFromRightPart(source, indexMathSymbol);
        // возвращаем результат вычитания
        return Subtraction.subtraction(leftNumber, rightNumber);
    }

    /**Метод собирает строку, заменяя два числа
     * и математический смвол между ними на результат вычисления.
     *
     * @param source Математичекое выражение(String).
     * @param result Результат вычисления(String).
     * @param indexResult Координа символа математической операции(int).
     * @return Математическое выражение с заменой двух чисел и мат символа на результат выражения(String).
     */
    public static String collectLines(String source, String result, int indexResult) {
        // число левее символа математической операции
        String leftNumber = LineParsing.getNumberFromLeftPart(source, indexResult);
        // число правее символа математической операции
        String rightNumber = LineParsing.getNumberFromRightPart(source, indexResult);
        // левая часть выражения до левого числа
        String leftPart = source.substring(0, indexResult - leftNumber.length());
        // правая часть выражения после правого числа
        String rightPart = source.substring((indexResult + 1) + rightNumber.length());
        //складываем левую часть + результат + правая часть
        return leftPart.concat(result).concat(rightPart);
    }

    /**Метод собирает строку, заменяя выражение в
     * скобках их результатом.
     *
     * @param source Математическое выражение(String).
     * @param result Результат в скобках(String).
     * @param indexOpenBracket Координата открывающейся скобки(int).
     * @param indexCloseBracket Координата закрывающейся скобки(int).
     * @return Математическое выражение с заменой выражения в скобках на результат(String).
     */
    public static String collectLines(String source, String result, int indexOpenBracket, int indexCloseBracket) {
        //левая часть выражения от открывающейся скобки
        String leftPart = source.substring(0, indexOpenBracket - 1);
        //правая часть выражения от закрывающейся скобки
        String rightPart = source.substring(indexCloseBracket + 1);
        // соединяем левую часть результат и правую часть выражения
        return leftPart.concat(result).concat(rightPart);
    }
}
