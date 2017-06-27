package com.xmh.noteup.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by void on 2017/6/27 027.
 */

public class FileUtil {

    private static final String FILE_ROOT_PATH = "/noteup/";
    private static final String LOG_FILE_PATH = "logfile/";

    public static boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取log保存路径
     */
    public static String getLogFilePath() {
        String path = null;
        if (checkSDCardAvailable()) {
            path = Environment.getExternalStorageDirectory() + FILE_ROOT_PATH + LOG_FILE_PATH;
        } else {
            path = Environment.getDataDirectory() + FILE_ROOT_PATH + LOG_FILE_PATH;
        }
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }
}
