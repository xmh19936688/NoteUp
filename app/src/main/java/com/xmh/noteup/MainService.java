package com.xmh.noteup;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;

import com.xmh.noteup.utils.DataUtil;
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

        for (Pair<String, Date> p : list) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
