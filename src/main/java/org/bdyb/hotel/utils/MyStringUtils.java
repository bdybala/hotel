package org.bdyb.hotel.utils;

public class MyStringUtils {
    public static String insertPercentageChars(String value) {
        if (!value.startsWith("%")) {
            value = "%" + value;
        }
        if (!value.endsWith("%")) {
            value = value + "%";
        }
        return value;
    }
}
