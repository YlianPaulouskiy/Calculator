package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

import static edu.javagroup.jcalc.digits.Round.round;

/**
 * Класс реализует умножение двух чисел разных типов.
 */
public class Multiplication {

    /**Метод умножает два числа разных типов.
     *
     * @param firstNum Первое число(String).
     * @param secondNum Второе число(String).
     * @return Результат умножения двух чиел(String).
     */
    public static String multiplication(String firstNum, String secondNum) {
        /*
        Если оба числа не содержат точек, то умножаем как два числа int
        если одно из чисел содержит точку, а второе нет, то умножаем как double и int
        если два числа содержат точки, то умножаем как два числа double
        если в метод пришли не числа, то возвращаем пустую строку
        */
        if (!firstNum.contains(".") && !secondNum.contains(".")) {
            return Integer.toString(multiplication(LineParsing.getInteger(firstNum), LineParsing.getInteger(secondNum)));
        } else if (!firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(multiplication(LineParsing.getInteger(firstNum), LineParsing.getDouble(secondNum)));
        } else if (firstNum.contains(".") && !secondNum.contains(".")) {
            return Double.toString(multiplication(LineParsing.getDouble(firstNum), LineParsing.getInteger(secondNum)));
        } else if (firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(multiplication(LineParsing.getDouble(firstNum), LineParsing.getDouble(secondNum)));
        } else {
            return "";
        }
    }

    /**Метод умножает два числа типа int.
     *
     * @param firstNum Первое число(int).
     * @param secondNum Второе число(int).
     * @return Результат умножения двух чисел(int).
     */
    public static int multiplication(int firstNum, int secondNum) {
        return firstNum * secondNum;
    }

    /**Метод умножает два числа int и double.
     *
     * @param firstNum Первое число(int).
     * @param secondNum Второе число(double).
     * @return Результат умножения двух чисел(double), округленный до второго знака после запятой.
     */
    public static double multiplication(int firstNum, double secondNum) {
        return round(firstNum * secondNum);
    }

    /**Метод умножает два числа типа double и int.
     *
     * @param firstNum Певое число(double).
     * @param secondNum Второе число(int).
     * @return Результат умножения двух чисел(double), округленный до второго знака после запятой.
     */
    public static double multiplication(double firstNum, int secondNum) {
        return round(firstNum * secondNum);
    }

    /**Метод умножает два числа типа double.
     *
     * @param firstNum Первое число(double).
     * @param secondNum Второе число(double).
     * @return Результат умножения двух чисел(double), округленный до второго знака после запятой.
     */
    public static double multiplication(double firstNum, double secondNum) {
        return round(firstNum * secondNum);
    }
}
