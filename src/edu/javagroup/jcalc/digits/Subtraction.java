package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

import static edu.javagroup.jcalc.digits.Round.round;

/**
 * Класс реализует разность двух чисел разных типов.
 */
public class Subtraction {

    /**Метод находит разность двух чисел разных типов.
     *
     * @param firstNum Перове число(String).
     * @param secondNum Второе число(String).
     * @return Результат вычитания двух чисел(String).
     */
    public static String subtraction(String firstNum, String secondNum) {
        /*
        Если оба числа не содержат точек, то находим разность как два числа int
        если одно из чисел содержит точку, а второе нет, то находим разность как double и int
        если два числа содержат точки, то находим разность как два числа double
        если в метод пришли не числа, то возвращаем пустую строку
        */
        if (!firstNum.contains(".") && !secondNum.contains(".")) {
            return Integer.toString(subtraction(LineParsing.getInteger(firstNum), LineParsing.getInteger(secondNum)));
        } else if (!firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(subtraction(LineParsing.getInteger(firstNum), LineParsing.getDouble(secondNum)));
        } else if (firstNum.contains(".") && !secondNum.contains(".")) {
            return Double.toString(subtraction(LineParsing.getDouble(firstNum), LineParsing.getInteger(secondNum)));
        } else if (firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(subtraction(LineParsing.getDouble(firstNum), LineParsing.getDouble(secondNum)));
        } else {
            return "";
        }
    }

    /**Метод отнимает два числа типа int.
     *
     * @param firstNum Первое число(int).
     * @param secondNum Второе число(int).
     * @return Результат вычитания двух чисел(int).
     */
    public static int subtraction(int firstNum, int secondNum) {
        return firstNum - secondNum;
    }

    /**Метод отнимает два числа типа int и double.
     *
     * @param firstNum Перове число(int).
     * @param secondNum Второе число(double).
     * @return Результат вычитания двух чисел(double), округленный до двух знаков после запятой.
     */
    public static double subtraction(int firstNum, double secondNum) {
        return round(firstNum - secondNum);
    }

    /**Метод отнимает два числа типа double и int.
     *
     * @param firstNum Первое число(double).
     * @param secondNum Второе число(int).
     * @return Результат вычитания двух чисел(double), округленный до двух знаков после запятой.
     */
    public static double subtraction(double firstNum, int secondNum) {
        return round(firstNum - secondNum);
    }

    /**Метод отнимает два числа типа double.
     *
     * @param firstNum Первое число(double).
     * @param secondNum Второе число(double).
     * @return Результат вычитания двух чисел(double), округленный до двух знаков после запятой.
     */
    public static double subtraction(double firstNum, double secondNum) {
        return round(firstNum - secondNum);
    }
}
