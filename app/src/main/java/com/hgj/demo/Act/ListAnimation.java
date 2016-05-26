package com.hgj.demo.Act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.listviewanimation.ScaleInAnimationAdapter;
import com.google.android.gms.internal.ad;
import com.hgj.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListAnimation extends EaseBaseActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_animation);
        listView = (ListView) findViewById(R.id.listView1);
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", i + "盛开的地方");
            list.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(), list,
                android.R.layout.simple_list_item_1, new String[]{"name"},
                new int[]{android.R.id.text1});

        ScaleInAnimationAdapter adapter = new ScaleInAnimationAdapter(
                simpleAdapter, 0f);
        adapter.setListView(listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
