package com.xmh.noteup.utils;

import android.support.v4.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by void on 2017/6/26 026.
 */

public class DataUtil {

    public static List<Pair<String, Date>> getInfoFromFile(File file) {
        List<Pair<String, Date>> list = new ArrayList<>();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/M/d");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb2312"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.split(",").length == 27) {
                    String name = line.split(",")[1];
                    String d = line.split(",")[20];
                    if (!StringUtil.isEmpty(name) && !StringUtil.isEmpty(d)) {
                        Date date = dateFormater.parse(d);
                        list.add(new Pair(name, date));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<Pair<String, Date>> sortByDate(List<Pair<String, Date>> list) {
        Collections.sort(list, new Comparator<Pair<String, Date>>() {
            @Override
            public int compare(Pair<String, Date> o1, Pair<String, Date> o2) {
                Calendar c1 = Calendar.getInstance();
                c1.setTime(o1.second);
                c1.set(Calendar.YEAR, 0);

                Calendar c2 = Calendar.getInstance();
                c2.setTime(o2.second);
                c2.set(Calendar.YEAR, 0);
                return c1.compareTo(c2);
            }
        });
        return list;
    }
}
