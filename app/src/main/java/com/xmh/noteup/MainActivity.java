package com.xmh.noteup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // will start activity if none activity started
//        alarm2();

    }

    private void alarm1(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0x1, intent, 0);

        int alermType = AlarmManager.ELAPSED_REALTIME;
        final int delay = 15000;

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        manager.setRepeating(alermType, SystemClock.elapsedRealtime() + delay, delay, pendingIntent);

//        manager.set(alermType, delay, pendingIntent);
    }

    private void alarm2(){
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

/**
 * https://developer.android.com/reference/android/app/AlarmManager.html#set(int, long, android.app.PendingIntent)
 *
 * Schedule an alarm.
 调度闹钟。

 Note: for timing operations (ticks, timeouts, etc) it is easier and much more efficient to use Handler.
 注意：大多数延时操作用Handler更合适。

 If there is already an alarm scheduled for the same IntentSender, that previous alarm will first be canceled.
 如果该IntentSender已经有一个在调度的alarm，则正在调度的将被取消。


 If the stated trigger time is in the past, the alarm will be triggered immediately.
 如果设置的触发时间是过去，则将被立即出发。

 If there is already an alarm for this Intent scheduled (with the equality of two intents being defined by filterEquals(Intent)), then it will be removed and replaced by this one.
 如果该Intent已经有一个正在调度的alarm，（通过filterEquals()进行判断Intent是否相同），则正在调度的将被新的替换。

 The alarm is an Intent broadcast that goes to a broadcast receiver that you registered with registerReceiver(BroadcastReceiver, IntentFilter) or through the <receiver> tag in an AndroidManifest.xml file.
 闹钟是一个广播，被发送到注册的接收器上。

 Alarm intents are delivered with a data extra of type int called Intent.EXTRA_ALARM_COUNT that indicates how many past alarm events have been accumulated into this intent broadcast. Recurring alarms that have gone undelivered because the phone was asleep may have a count greater than one when delivered.

 */
