package com.xmh.noteup.utils;

import android.support.compat.BuildConfig;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by void on 2017/6/27 027.
 */

public class LogUtil {
    public static void e(String tag, String msg, boolean toFile) {
        Log.e(tag, msg);
        if (!toFile || !BuildConfig.DEBUG) {
            return;
        }
        String logFilePath = FileUtil.getLogFilePath();
        String logFileName = logFilePath + tag + ".txt";
        File logFile = new File(logFileName);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(logFileName, true);
            fileWriter.write(new Date().toString() + "::" + tag + "::" + msg + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
