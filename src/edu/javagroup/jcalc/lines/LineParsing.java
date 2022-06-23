package edu.javagroup.jcalc.lines;

/**
 * Класс реализует методы для разбора математического выражения на отдельные элементы.
 */
public class LineParsing {

    /**
     * Ищет первый математический символ,
     * т.е. символ высшего приоритета.
     *
     * @param source Математическое выражение.
     * @return Возвращает строку, содержащую символ математической операции.
     */
    public static String findFirstMathSymbol(String source) {
        //если строка начинается на минус убираем его
        source = source.startsWith("-") ? source.substring(1) : source;
        /* если строка содержит символы 1 приоритета,
         то ищем этот символ;
         если символов 1ого приоритета нет,
         то ищем символы 2ого приоритета;
         если символов 2ого приоритета нету,
         то возращаем пустую строку
        */
        if (source.contains("*") || source.contains("/")) {
            return findFirstMathSymbol(source, 1);
        } else if (source.contains("+") || source.contains("-")) {
            return findFirstMathSymbol(source, 2);
        } else {
            return "";
        }
    }

    /**
     * Этот метод возвращает число, в строчном представлении, левее
     * от полученного математического символа.
     *
     * @param source Математическое выражение.
     * @param pos    Координата математического символа.
     * @return Число левее математического символа.
     */
    public static String getNumberFromLeftPart(String source, int pos) {
        StringBuilder leftNumber = new StringBuilder();
        //получаем строку левее символа
        for (int i = pos - 1; i >= 0; i--) {
            // если символ число точка или минсу то записываем в левое число
            if (Character.isDigit(source.charAt(i)) || source.charAt(i) == '.' || source.charAt(i) == '-') {
                leftNumber.append(source.charAt(i));
            } else if (source.charAt(i) == ')') { // если есть скобка, то скипаем ее
                continue;
            } else { // встречаем другой символ то выходим из цикла
                break;
            }
            // если мы записали один мину, то второго быть не должно, поэтому выходим из цикла
            if (leftNumber.toString().contains("-")) {
                break;
            }
        }
        //реверсный ответ
        return leftNumber.reverse().toString();
    }

    /**
     * В случае приоритета = 1, ищет * или / и возвращает их,
     * в случае приоритета = 2, ищет + или - и возвращает их.
     *
     * @param source   Математическое выражение.
     * @param priority Приоритет математического символа.
     * @return Полученный символ заданного приоритета.
     */
    public static String findFirstMathSymbol(String source, int priority) {
        // в зависимости от приоритета мы ищем математический символ в строке
        if (priority == 1) {
            for (int i = 0; i < source.length(); i++) {
                // возвращаем первый найденный символ данного приоритета
                if (source.charAt(i) == '*' || source.charAt(i) == '/') {
                    return Character.toString(source.charAt(i));
                }
            }
        } else if (priority == 2) {
            for (int i = 0; i < source.length(); i++) {
                // возвращаем первый символ заданного приоритета
                if (source.charAt(i) == '+' || source.charAt(i) == '-') {
                    return Character.toString(source.charAt(i));
                }
            }
        }
        // если приоритет/строка были введены неверно
        return "";
    }

    /**
     * Этот метод возвращает число, в строчном представлении, правее
     * от полученного математического символа.
     *
     * @param source Математическое выражение.
     * @param pos    Координата математического символа.
     * @return Число правее математического символа.
     */
    public static String getNumberFromRightPart(String source, int pos) {
        StringBuilder rightNumber = new StringBuilder();
        //проход в правую часть от мат символа
        for (int i = pos + 1; i < source.length(); i++) {
            //если это число, точка или минус то записываем
            if (Character.isDigit(source.charAt(i)) || source.charAt(i) == '.' || source.charAt(i) == '-') {
                rightNumber.append(source.charAt(i));
            } else if (source.charAt(i) == '(') { // если это ( то скипаем
                continue;
            } else {
                break;
            }
            // если у нас число закнчивается на - и его длина больше 1 то, тогда удаляем минус и break аемся
            if (rightNumber.toString().endsWith("-") && rightNumber.length() > 1) {
                rightNumber.deleteCharAt(rightNumber.length() - 1);
                break;
            }
        }
        return rightNumber.toString();
    }

    /**
     * Метод получает строку и возвращает
     * целое число.
     *
     * @param source Число в виде строки.
     * @return Число типа Integer.
     */
    public static int getInteger(String source) {
        return Integer.parseInt(source);
    }

    /**
     * Метод получает строку и возвращает
     * дробное число
     *
     * @param source Число в виде строки.
     * @return Число типа Double.
     */
    public static double getDouble(String source) {
        return Double.parseDouble(source);
    }

    /**
     * Проверяет строку на допустимые символы.
     *
     * @param source Число в виде строки.
     * @return true если строка соответствует числу, иначе false.
     */
    public static boolean isFinalNumber(String source) {
        //если число начинается на - убираем его
        source = source.startsWith("-") ? source.substring(1) : source;
        //количество точек в числе
        int dotCount = source.length() - source.replace(".", "").length();
        //допустимые символы
        String acceptSymbol = "0123456789.";
        for (int i = 0; i < source.length(); i++) {
            // проверяем строку на цифры и на точку
            if (!acceptSymbol.contains(Character.toString(source.charAt(i)))) {
                return false;
            }
        }
        // возвращаем true если в чиле нету точек или она одна
        return dotCount <= 1 && !source.startsWith(".");
    }

}

