package com.example.tsnt.utils;

import java.text.DecimalFormat;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-14 12:05
 * @Description: 数字格式化的工具类
 */

public class NumberFormatUtil {
    public static String reserveDecimalNotEndWithZero(double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d);
        while ((format.endsWith("0") && format.contains(".")) || format.endsWith(".")) {
            format = format.substring(0, format.length() - 1);
        }
        return format;
    }

    public static String notReserveDecimal(double d) {
        DecimalFormat df = new DecimalFormat("#0");
        String format = df.format(d);
        return format;
    }
}
