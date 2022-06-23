package edu.javagroup.jcalc.digits;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс округляет число до двух знаков после запятой
 */
public class Round {

    private static final int EXACTNESS = 2;

    /**
     * Метод округляет число с плавающей точкей
     * до двух знаков после запятой.
     *
     * @param number Число типа double.
     * @return Число типа double округленное до двух знаков после запятой.
     */
    public static double round(double number) {
        return new BigDecimal(number).setScale(EXACTNESS, RoundingMode.HALF_UP).doubleValue();
    }
}
