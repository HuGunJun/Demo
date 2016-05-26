package com.hgj.demo.Act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.synchorizontalscrollview.BaseSynHorizontalScrollView;
import com.easemob.easeui.widget.synchorizontalscrollview.SyncHorizontalScrollView;
import com.hgj.demo.R;
import com.hgj.demo.adapter.FragmntAdapter;
import com.hgj.demo.frag.BFrag;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class Act_SychronizeHorizontalView extends EaseBaseActivity {
    @ViewInject(R.id.hr)
    SyncHorizontalScrollView hr;
    @ViewInject(R.id.vp_home)
    ViewPager vp_home;
    FragmntAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sychronizehorizontalview);
        ViewUtils.inject(this);
        InitView();
        SetOnClickListener();
    }

    @Override
    public void onClick(View view) {

    }

    private void SetOnClickListener() {
        vp_home.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hr.setCurrentIndicator(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void InitView() {
        ArrayList<String> list =
                new ArrayList<String>();

        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        list.add("asdfas");
        hr.Init(this, list, "修改", vp_home, R.drawable.horizental_top_menu_selector, new BaseSynHorizontalScrollView.SynHorizontalScrollViewChangeListener() {
            @Override
            public void ChangeListener() {
                Toast.makeText(context, "修改", Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            BFrag F = new BFrag();
            fragmentList.add(F);

        }
        adapter = new FragmntAdapter(getSupportFragmentManager(), fragmentList);
        vp_home.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void InitData() {

    }
}
