package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

import static edu.javagroup.jcalc.digits.Round.round;

/**
 * Класс реализует сложение двух чисел разных типов.
 */
public class Addition {

    /**
     * Метод складывает два числа разных типов.
     *
     * @param firstNum  Первое число(String).
     * @param secondNum Второе число(String).
     * @return Результат сложения двух чисел(String).
     */
    public static String addition(String firstNum, String secondNum) {
        /*
        Если оба числа не содержат точек, то складываем как int
        если одно из чисел содержит точку, а второ нет, то складываем как double и int
        если два числа содержат точки, то складываем как double
        если в метод пришли не числа, то возвращаем пустую строку
        */
        if (!firstNum.contains(".") && !secondNum.contains(".")) {
            return Integer.toString(addition(LineParsing.getInteger(firstNum), LineParsing.getInteger(secondNum)));
        } else if (!firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(addition(LineParsing.getInteger(firstNum), LineParsing.getDouble(secondNum)));
        } else if (firstNum.contains(".") && !secondNum.contains(".")) {
            return Double.toString(addition(LineParsing.getDouble(firstNum), LineParsing.getInteger(secondNum)));
        } else if (firstNum.contains(".") && secondNum.contains(".")) {
            return Double.toString(addition(LineParsing.getDouble(firstNum), LineParsing.getDouble(secondNum)));
        } else {
            return "";
        }
    }

    /**
     * Метод складывает два числа типа int.
     *
     * @param firstNum  Первое число типа int.
     * @param secondNum Второе число типа int.
     * @return Сумма двух чисел(int).
     */
    public static int addition(int firstNum, int secondNum) {
        return firstNum + secondNum;
    }

    /**
     * Метод складывает два числа типа int и double.
     *
     * @param firstNum  Первое число типа int.
     * @param secondNum Второе число типа double.
     * @return Сумма двух чисел(double), округленная до второго знака после запятой.
     */
    public static double addition(int firstNum, double secondNum) {
        return round(firstNum + secondNum);
    }

    /**
     * Метод складывает два числа типа double и int.
     *
     * @param firstNum  Первое число типа double.
     * @param secondNum Второе число типа int.
     * @return Сумма двух чисел(double), округленная до второго знака после запятой.
     */
    public static double addition(double firstNum, int secondNum) {
        return round(firstNum + secondNum);
    }

    /**
     * Метод скалывает два числа типа double.
     *
     * @param firstNum  Первое число типа double.
     * @param secondNum Второе число типа double.
     * @return Сумма двух чисел(double), округленная до второго знака после запятой.
     */
    public static double addition(double firstNum, double secondNum) {
        return round(firstNum + secondNum);
    }
}
