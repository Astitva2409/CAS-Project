package com.utils;

import java.util.Date;

public class DateUtils {
    public static String getTime() {
        Date date = new Date();
        return date.toString().replaceAll(":", "_");
    }
}
