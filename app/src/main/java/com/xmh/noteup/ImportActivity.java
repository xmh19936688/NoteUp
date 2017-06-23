package com.xmh.noteup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;

import com.xmh.noteup.utils.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportActivity extends AppCompatActivity {

    private static final String TAG_CSV_FILE_PATH = "path";

    private String filePath = "";
    private List<Pair<String, Date>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Intent intent = getIntent();
        filePath = intent.getData().getPath();

        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(filePath);
                list = getInfoFromFile(file);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (list.size() > 0) {
                            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString(TAG_CSV_FILE_PATH, filePath);
                        }
                    }
                });

            }
        }).start();
    }

    private List<Pair<String, Date>> getInfoFromFile(File file) {
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
}
