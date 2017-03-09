package com.unidadcoronaria.prestaciones.util;

import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtil {

    private final static String LOG_TAG = "DateUtil";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
    private static final SimpleDateFormat dateFormatUserDay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private static final SimpleDateFormat dateFormatUserHour = new SimpleDateFormat("HH:mm", Locale.getDefault());


    public static Date getConvertedDate(String dateString) {
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error converting date.");
        }
        return convertedDate;
    }

    public static String getConvertedString(Date date) {
        String convertedString = "";
        try {
            convertedString = dateFormat.format(date);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error converting date.");
        }
        return convertedString;
    }

    public static String getConvertedDayString(Date date) {
        String convertedString = "";
        try {
            convertedString = dateFormatUserDay.format(date);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error converting date.");
        }
        return convertedString;
    }

    public static String getConvertedHourString(Date date) {
        Log.d("DateUtil", "Time - "+String.valueOf(date.getTime()));
        String convertedString = "";
        try {
            convertedString = dateFormatUserHour.format(date);
            Log.d("DateUtil", "Hour - "+convertedString);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error converting date.");
        }
        return convertedString;
    }


    public static boolean isToday(long timeInMilis) {
        Calendar refTime = Calendar.getInstance();
        refTime.setTimeInMillis(timeInMilis);
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DATE) == refTime.get(Calendar.DATE);
    }

    public static boolean isTomorrow(long timeInMilis) {
        Calendar refTime = Calendar.getInstance();
        refTime.setTimeInMillis(timeInMilis);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 1);
        return now.get(Calendar.DATE) == refTime.get(Calendar.DATE);
    }

}
