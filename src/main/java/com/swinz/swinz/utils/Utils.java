package com.swinz.swinz.utils;

import java.math.BigDecimal;

public class Utils {

    public static boolean compareWithThreshold(double valueA, double valueB, double threshold) {
        return BigDecimal.valueOf(valueA).subtract(BigDecimal.valueOf(valueB)).abs().doubleValue() <= BigDecimal.valueOf(threshold).doubleValue();
    }
}
