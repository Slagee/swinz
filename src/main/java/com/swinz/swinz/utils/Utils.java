package com.swinz.swinz.utils;

public class Utils {

    public static boolean compareWithThreshold(double valueA, double valueB, double threshold) {
        return Math.abs(valueA - valueB) <= threshold;
    }
}
