package com.hgj.demo.Demo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.hgj.demo.R;

/**
 * 作者：HuGuoJun
 * 2016/5/26 17:26
 * 邮箱：www.guojunkuaile@qq.com
 */
public class TestDemo extends TabActivity {
    private TabHost tabHost;
    private RadioGroup radioderGroup;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_test_demo);
        InitView();
        InitData();
    }

    public void onClick(View view) {

    }

    public void InitView() {
        tabHost = getTabHost();
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
    }

    public void InitData() {
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1").setContent(new Intent(this, Activity1.class)));
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2").setContent(new Intent(this, Activity2.class)));
        tabHost.addTab(tabHost.newTabSpec("3").setIndicator("3").setContent(new Intent(this, Activity3.class)));
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        radioderGroup.check(R.id.mainTabs_radio_home);//默认第一个按钮
        radioderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mainTabs_radio_home:
                        tabHost.setCurrentTabByTag("1");
                        break;
                    case R.id.mainTabs_radio_msg:
                        tabHost.setCurrentTabByTag("2");
                        break;
                    case R.id.mainTabs_radio_selfInfo:
                        tabHost.setCurrentTabByTag("3");
                        break;
                }
            }
        });

    }
}
