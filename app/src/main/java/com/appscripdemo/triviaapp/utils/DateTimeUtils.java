package com.appscripdemo.triviaapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    public static String getCurrentDateTime(){
        String dateFormat = "dd MMM hh:mm a";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return df.format(Calendar.getInstance().getTime());
    }
}
