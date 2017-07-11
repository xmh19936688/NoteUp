package com.xmh.noteup;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.Pair;
import android.widget.RemoteViews;

import com.xmh.noteup.utils.DataUtil;
import com.xmh.noteup.utils.DateUtil;
import com.xmh.noteup.utils.LogUtil;
import com.xmh.noteup.utils.StringUtil;

import java.io.File;
import java.util.Date;
import java.util.List;

public class MainService extends IntentService {

    public MainService() {
        super("MainService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock lock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "lock");
        lock.acquire();

        LogUtil.e("xmh", "run", true);

        SharedPreferences preferences = getSharedPreferences(App.PREFRENCE_NAME, MODE_PRIVATE);
        String path = preferences.getString(App.DATA_FILE_PATH, "");
        if (StringUtil.isEmpty(path)) {
            return;
        }

        File file = new File(path);
        List<Pair<String, Date>> list = DataUtil.getInfoFromFile(file);
        if (list == null || list.isEmpty()) {
            return;
        }

        LogUtil.e("xmh", "size:" + list.size(), true);

        int today = 0, week = 0;
        for (Pair<String, Date> p : list) {
            if (DateUtil.isToday(p.second)) {
                today++;
            } else if (DateUtil.inWeek(p.second)) {
                week++;
            }
        }

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.layout_notify);
        view.setTextViewText(R.id.tv_today, String.valueOf(today));
        view.setTextViewText(R.id.tv_week, String.valueOf(week));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0x1, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContent(view)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("生日提醒")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(App.NOTIFY_ID, notification);

        lock.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
