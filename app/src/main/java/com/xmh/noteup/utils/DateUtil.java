package com.xmh.noteup.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by void on 2017/6/26 026.
 */

public class DateUtil {

    private static String LastDateFormat = "yyyy年M月d";

    public static boolean isToday(Date date) {

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar other = Calendar.getInstance();
        other.setTime(date);
        other.set(Calendar.YEAR, today.get(Calendar.YEAR));

        return 0 == today.compareTo(other);
    }

    public static boolean inWeek(Date date) {

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar week = Calendar.getInstance();
        week.setTime(new Date());
        week.add(Calendar.DAY_OF_MONTH, 7);
        week.set(Calendar.HOUR, 0);
        week.set(Calendar.MINUTE, 0);
        week.set(Calendar.SECOND, 0);
        week.set(Calendar.MILLISECOND, 0);

        Calendar other = Calendar.getInstance();
        other.setTime(date);
        other.set(Calendar.YEAR, today.get(Calendar.YEAR));

        return 0 > today.compareTo(other) && 0 < week.compareTo(other);
    }

    public static Date format(String s) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(LastDateFormat);
            Date date = formater.parse(s);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/M/d");
            Date date = formater.parse(s);
            LastDateFormat = "yyyy/M/d";
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-M-d");
            Date date = formater.parse(s);
            LastDateFormat = "yyyy-M-d";
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy.M.d");
            Date date = formater.parse(s);
            LastDateFormat = "yyyy.M.d";
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy年M月d日");
            Date date = formater.parse(s);
            LastDateFormat = "yyyy年M月d日";
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy年M月d");
            LastDateFormat = "yyyy年M月d";
            Date date = formater.parse(s);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
