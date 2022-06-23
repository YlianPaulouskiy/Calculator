package edu.javagroup.jcalc.lines;

/**
 * Класс реализует проверку математического выражения на допустимые символы.
 */
public class LineCheck {

    /**метод проверяет строку на лишние(недастающие) скобки или
     * символы математических операций.
     *
     * @param source Математическое выражение.
     * @return Результат методов isRoundBracketsCorrect И isMathSymbolsCorrect.
     */
    public static boolean isLineCorrect(String source) {
        return isRoundBracketsCorrect(source) && isMathSymbolsCorrect(source);
    }

    /**Метод проверяет содержит ли наше математическое
     * выражение скобки или нет.
     *
     * @param source Математическое выражение.
     * @return true если скобок нет, иначе результат метода isRoundBracketsCountCorrect.
     */
    public static boolean isRoundBracketsCorrect(String source) {
        //содержит ли строка скобки
        if (source.contains("(") || source.contains(")")) {
            return isRoundBracketsCountCorrect(source);
        } else {
            return true;
        }
    }

    /**Метод проверяем содержит ли строка равное количество
     * открывающихся и закрывающихся скобок.
     *
     * @param source Математическое выражение.
     * @return true елси колво открывающихся скобок равно колву закрывающихся
     */
    public static boolean isRoundBracketsCountCorrect(String source) {
        //счетчики для открвающихся и закрывающихся скобок
        int openBracketCounter = 0;
        int closeBracketCounter = 0;
        // подсчет кол-ва скобок
        for (int i = 0; i < source.length(); i++) {
            if ("(".contains(Character.toString(source.charAt(i)))) {
                openBracketCounter++;
            } else if (")".contains(Character.toString(source.charAt(i)))) {
                closeBracketCounter++;
            }
        }
        // если кол-ва открывающихся равно кол-ву закрывающихся возращаем true
        return openBracketCounter == closeBracketCounter && isRoundBracketsPositionCorrect(source);
    }

    /**Метод проверяет позиции открывающихся и
     * закрывающихся скобок.
     *
     * @param source Математическое выражение.
     * @return false если в начале строки встречается закрывающаяся скобка
     * или в конце строки - открывающаяся
     */
    public static boolean isRoundBracketsPositionCorrect(String source) {
        // если первая позиция ) меньше позиции (
        // значит открытой скобки не было --> возращаем false
        // если поледняя позиция ( больше ) значит
        // скобки не закрыты --> возвращаем false
        return !(source.indexOf(")") < source.indexOf(")")) && !(source.lastIndexOf('(') > source.lastIndexOf(')'));
    }

    /**Метод проверяет начало и конец строки на наличие
     * недопустимых символов математических опреаций.
     *
     * @param source Математическое выражение.
     * @return false если строка начинается или заканчивается на *+/ или заканчивается на -
     */
    public static boolean isMathSymbolsCorrect(String source) {
        return isFirstMathSymbol(source, "+") && isFirstMathSymbol(source,"*") && isFirstMathSymbol(source,"/")
                && isLastMathSymbol(source, "+") && isLastMathSymbol(source, "*") && isLastMathSymbol(source, "/")
                && isLastMathSymbol(source, "-");
    }

    /**Метод проверяет начинается ли строка с определенного символа.
     *
     * @param source Математическое выражение.
     * @param symbol Символ на наличие которого необхожимо проверить начало строки.
     * @return Инвертированный boolean.
     */
    public static boolean isFirstMathSymbol(String source, String symbol) {
        return !source.startsWith(symbol);
    }

    /**Метод проверяет заканчивается ли строка определенным символом.
     *
     * @param source Математическое выражение.
     * @param symbol Символ на наличие которого проверяется конец строки.
     * @return Инвертированный boolean.
     */
    public static boolean isLastMathSymbol(String source, String symbol) {
        return !source.endsWith(symbol);
    }

}
