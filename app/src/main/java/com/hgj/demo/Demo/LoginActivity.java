package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.hgj.demo.R;

/**
 * 作者：HuGuoJun
 * 2016/5/27 13:31
 * 邮箱：www.guojunkuaile@qq.com
 */
public class LoginActivity extends EaseBaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.layout_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Login();
                break;
        }
    }

    /**
     * 登陆
     */
    private void Login() {
        EMChatManager.getInstance().login("huguojun", "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(context, TestDemo.class));
            }

            @Override
            public void onError(int i, String s) {
                
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
