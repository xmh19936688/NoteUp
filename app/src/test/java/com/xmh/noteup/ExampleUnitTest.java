package com.xmh.noteup;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCalendar() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/d");
        Date date = simpleDateFormat.parse("1993/08/05");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DAY_OF_MONTH);
        calendar.get(Calendar.HOUR_OF_DAY);
        calendar.get(Calendar.HOUR);
        calendar.get(Calendar.DATE);
        calendar.get(Calendar.MINUTE);
        calendar.get(Calendar.SECOND);
        calendar.get(Calendar.MILLISECOND);
    }

    @Test
    public void testDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DAY_OF_MONTH);
        calendar.get(Calendar.HOUR_OF_DAY);
        calendar.get(Calendar.HOUR);
        calendar.get(Calendar.DATE);
        calendar.get(Calendar.MINUTE);
        calendar.get(Calendar.SECOND);
        calendar.get(Calendar.MILLISECOND);

    }

    @Test
    public void testFormat() {
        try {
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/M/d");
            Date a = dateFormater.parse("1991/1/1");
            Date b = dateFormater.parse("1991-1-1");
            Date c = dateFormater.parse("1991年1月1日");
            Date d = dateFormater.parse("1991.1.1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}