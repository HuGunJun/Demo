/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.easemob.easeui.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.widget.loadding.ZProgressHUD;

public abstract class EaseBaseActivity extends AppCompatActivity {
    public Context context;
    private ZProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        context = this;
        // http://stackoverflow.com/questions/4341600/how-to-prevent-multiple-instances-of-an-activity-when-it-is-launched-with-differ/
        // 理论上应该放在launcher activity,放在基类中所有集成此库的app都可以避免此问题
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onresume时，取消notification显示
        // EaseUI.getInstance().getNotifier().reset();

    }

    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public abstract void onClick(View view);

    /**
     * 初始化控件UI
     */
    public abstract void InitView();

    /**
     * 初始化数据
     */
    public abstract void InitData();

    /**
     * 显示加载对话框
     */
    public void ShowLoadingDialog() {
        progressHUD = ZProgressHUD.getInstance(this);
        progressHUD.setMessage("加载中");
        progressHUD.setCancelable(true);
        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
        progressHUD.show();
    }

    /**
     * 关闭加载对话框
     */
    public void CloseLoadingDialog() {
        progressHUD = ZProgressHUD.getInstance(this);
        progressHUD.dismiss();
    }

}
