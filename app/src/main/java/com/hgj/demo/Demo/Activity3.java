package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.hgj.demo.Act.MainActivity;
import com.hgj.demo.R;

public class Activity3 extends EaseBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                startActivity(new Intent(context, MainActivity.class));
                break;
        }
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
