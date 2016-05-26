package com.hgj.demo.Act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.EaseUserUtils;
import com.easemob.easeui.widget.progress.CustomView;
import com.easemob.easeui.widget.progress.NumberProgressBar;
import com.easemob.easeui.widget.progress.NumberProgressBar.OnProgressBarListener;
import com.hgj.demo.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoaddingActivity extends EaseBaseActivity implements OnProgressBarListener {
    private Timer timer;
    private NumberProgressBar bnp;
    private CustomView customView = null;
    private int porcess = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding);
        bnp = (NumberProgressBar) findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        customView = (CustomView) findViewById(R.id.customView);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                        ++porcess;
                        customView.setProgress(porcess);
                    }
                });
            }
        }, 1000, 100);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onProgressChange(int current, int max) {
        if (current == max) {
            Toast.makeText(context, "加载完成", 30).show();
            timer.cancel();
            // bnp.setProgress(0);
        }
    }

}
