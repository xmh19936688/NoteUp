package com.xmh.noteup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xmh.noteup.utils.DataUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ImportActivity extends AppCompatActivity {


    private RecyclerView rvList;
    private List<Pair<String, Date>> list = new ArrayList<>();
    private String filePath = "";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Intent intent = getIntent();
        filePath = intent.getData().getPath();

        dialog = new ProgressDialog(this);
        dialog.setTitle("正在处理");
        dialog.setCancelable(false);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(filePath);
                list = DataUtil.getInfoFromFile(file);
                list = DataUtil.sortByDate(list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (list.size() > 0) {
                            initView();
                            SharedPreferences preferences = getSharedPreferences(App.PREFRENCE_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString(App.DATA_FILE_PATH, filePath);
                            edit.commit();

                            MainService.start(ImportActivity.this);
                        } else {
                            Toast.makeText(ImportActivity.this, "未解析到数据", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        }).start();
    }

    private void initView() {
        rvList = (RecyclerView) findViewById(R.id.list);
        Adapter adapter = new Adapter();
        adapter.setData(list);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }
}
