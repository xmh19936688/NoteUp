package com.xmh.noteup;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by XMH on 2017/6/24 024.
 */

public class App extends Application {

    public static final String PREFRENCE_NAME = "NoteupPreference";
    public static final String DATA_FILE_PATH = "DataFilePath";

    public static final int NOTIFY_ID = 0x1;


    @Override
    public void onCreate() {
        super.onCreate();

        initAlarm();

    }

    private void initAlarm() {
        Intent intent = new Intent(this, MainService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0x1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        int delay = 60 * 60 * 1000;
        int delay = 24 * 60 * 60 * 1000;
        Date tomorrow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tomorrow);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tomorrow = calendar.getTime();


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), delay, pendingIntent);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, tomorrow.getTime(), delay, pendingIntent);
    }
}
