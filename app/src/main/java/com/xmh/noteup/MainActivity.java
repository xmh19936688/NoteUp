package com.xmh.noteup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xmh.noteup.utils.DataUtil;
import com.xmh.noteup.utils.DateUtil;
import com.xmh.noteup.utils.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListToday;
    private RecyclerView rvListWeek;
    private RecyclerView rvListAll;
    private Adapter adapterToday = new Adapter();
    private Adapter adapterWeek = new Adapter();
    private Adapter adapterAll = new Adapter();
    private List<Pair<String, Date>> listToday = new ArrayList<>();
    private List<Pair<String, Date>> listWeek = new ArrayList<>();
    private List<Pair<String, Date>> listAll = new ArrayList<>();
    private View vToday;
    private View vWeek;
    private View vAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        SharedPreferences preferences = getSharedPreferences(App.PREFRENCE_NAME, MODE_PRIVATE);
        String path = preferences.getString(App.DATA_FILE_PATH, "");
        if (StringUtil.isEmpty(path)) {
            return;
        }

        File file = new File(path);
        if (file == null || !file.exists()) {
            return;
        }

        listAll = DataUtil.getInfoFromFile(file);
        listAll = DataUtil.sortByDate(listAll);
        for (Pair<String, Date> p : listAll) {
            if (DateUtil.isToday(p.second)) {
                listToday.add(p);
            } else if (DateUtil.inWeek(p.second)) {
                listWeek.add(p);
            }
        }

        adapterToday.setData(listToday);
        adapterWeek.setData(listWeek);
        adapterAll.setData(listAll);
    }

    private void initView() {
        vToday = findViewById(R.id.ll_today);
        vWeek = findViewById(R.id.ll_week);
        vAll = findViewById(R.id.ll_all);

        rvListToday = (RecyclerView) findViewById(R.id.list_today);
        rvListWeek = (RecyclerView) findViewById(R.id.list_week);
        rvListAll = (RecyclerView) findViewById(R.id.list_all);

        adapterToday.setData(listToday);
        adapterWeek.setData(listWeek);
        adapterAll.setData(listAll);

        rvListToday.setAdapter(adapterToday);
        rvListWeek.setAdapter(adapterWeek);
        rvListAll.setAdapter(adapterAll);

        rvListToday.setLayoutManager(new LinearLayoutManager(this));
        rvListWeek.setLayoutManager(new LinearLayoutManager(this));
        rvListAll.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onTodayClick(View v) {
        vToday.setVisibility(View.VISIBLE);
        vWeek.setVisibility(View.GONE);
        vAll.setVisibility(View.GONE);
    }

    public void onWeekClick(View v) {
        vToday.setVisibility(View.GONE);
        vWeek.setVisibility(View.VISIBLE);
        vAll.setVisibility(View.GONE);
    }

    public void onAllClick(View v) {
        vToday.setVisibility(View.GONE);
        vWeek.setVisibility(View.GONE);
        vAll.setVisibility(View.VISIBLE);
    }
}