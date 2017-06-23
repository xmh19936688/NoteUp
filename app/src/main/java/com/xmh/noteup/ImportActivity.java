package com.xmh.noteup;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Intent intent = getIntent();
        File file = new File(intent.getData().getPath());
        List<Pair<String, Date>> list = new ArrayList<>();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/M/d");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb2312"));
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
            list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
