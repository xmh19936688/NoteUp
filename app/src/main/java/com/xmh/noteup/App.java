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

    @Override
    public void onCreate() {
        super.onCreate();

        // will always start new activity
        alarm2();

    }

    private void alarm2() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0x1, intent, 0);

        int delay = 5 * 1000;
//        int delay = 24 * 60 * 60 * 1000;
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
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 5 * 1000, delay, pendingIntent);
//        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, tomorrow.getTime(), delay, pendingIntent);
    }
}
