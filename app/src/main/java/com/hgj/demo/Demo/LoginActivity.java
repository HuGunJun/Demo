package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.helper.SQLHelper;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.exceptions.EaseMobException;
import com.hgj.demo.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/5/27 13:31
 * 邮箱：www.guojunkuaile@qq.com
 */
public class LoginActivity extends EaseBaseActivity {
    @ViewInject(R.id._et_username)
    EditText et_username;
    @ViewInject(R.id._et_pass)
    EditText et_pass;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.layout_login);
        ViewUtils.inject(this);
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
        String username = et_username.getText().toString();
        String pass = et_pass.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        EMChatManager.getInstance().login(username, pass, new EMCallBack() {
            @Override
            public void onSuccess() {
                try {
                    List<String> contactUserNames = EMContactManager
                            .getInstance().getContactUserNames();

                    List<HashMap<String, String>> list_friend = new ArrayList<HashMap<String, String>>();


                    for (int i = 0; i < contactUserNames.size(); i++) {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put(SQLHelper.NAME, contactUserNames.get(i).toString());
                        list_friend.add(hashMap);
                    }
                    EaseUI.getInstance().setFriendlist(list_friend);

                    List<String> blackListUsernames = EMContactManager
                            .getInstance().getBlackListUsernames();
                    List<HashMap<String, String>> list_black = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < blackListUsernames.size(); i++) {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put(SQLHelper.NAME, blackListUsernames.get(i).toString());
                        list_black.add(hashMap);
                    }
                    EaseUI.getInstance().setBlacklist(list_black);

                    startActivity(new Intent(context, TestDemo.class));
                    finish();
                } catch (EaseMobException e) {
                    e.printStackTrace();
                }
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
