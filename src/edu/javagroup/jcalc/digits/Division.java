package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

import static edu.javagroup.jcalc.digits.Round.round;

/**
 * Класс реализует деление двух чисел разных типов.
 */
public class Division {

    /**Метод делит два числа разных типов.
     *
     * @param firstNum Первое число(String).
     * @param secondNum Второе число(Sting).
     * @return Результат деления двух чисел(String).
     */
    public static String division(String firstNum, String secondNum) {
        /*
        Если оба числа не содержат точек, то делим как два числа int
        если одно из чисел содержит точку, а второе нет, то делим как double и int
        если два числа содержат точки, то делим как два числа double
        если в метод пришли не числа, то возвращаем пустую строку
        */
        if (!firstNum.contains(".") && !secondNum.contains(".")) {
            return Double.toString(division(LineParsing.getInteger(firstNum), LineParsing.getInteger(secondNum)));
        } else if (!firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(division(LineParsing.getInteger(firstNum), LineParsing.getDouble(secondNum)));
        } else if (firstNum.contains(".") && !secondNum.contains(".")) {
            return Double.toString(division(LineParsing.getDouble(firstNum), LineParsing.getInteger(secondNum)));
        } else if (firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(division(LineParsing.getDouble(firstNum), LineParsing.getDouble(secondNum)));
        } else {
            return "";
        }
    }

    /**Метод делит два числа типа int.
     *
     * @param firstNum Первое число(int).
     * @param secondNum Второе число(int).
     * @return Результат деления двух чисел(double), округленный до второго знака после запятой.
     */
    public static double division(int firstNum, int secondNum) {
        return round((double) firstNum / secondNum);
    }

    /**Метод делит два числа типа int и double.
     *
     * @param firstNum Первое число(int).
     * @param secondNum Второе число(double).
     * @return Результат деления двух чисел(double), округленный до второго знака после запятой.
     */
    public static double division(int firstNum, double secondNum) {
        return round(firstNum / secondNum);
    }

    /**Метод делит два числа типа double и int.
     *
     * @param firstNum Первое число(double).
     * @param secondNum Второе число(int).
     * @return Результат деления двух чисел(double), округленный до второго знака после запятой.
     */
    public static double division(double firstNum, int secondNum) {
        return round(firstNum / secondNum);
    }

    /**Метод делит два числа типа double.
     *
     * @param firstNum Первое число(double).
     * @param secondNum Второе число(double).
     * @return Результат деления двух чисел(double), округленный до второго знака после запятой.
     */
    public static double division(double firstNum, double secondNum) {
        return round(firstNum / secondNum);
    }
}
