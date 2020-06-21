package com.swinz.swinz.utils;

import java.math.BigDecimal;

public class Utils {

    public static boolean compareWithThreshold(double valueA, double valueB, double threshold) {
        return BigDecimal.valueOf(Math.abs(valueA - valueB)).compareTo(BigDecimal.valueOf(threshold)) == -1;
    }
}
