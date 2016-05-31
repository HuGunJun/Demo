package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.helper.SQLHelper;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.exceptions.EaseMobException;
import com.hgj.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by huguojun on 16/5/31.
 */
public class Splash extends EaseBaseActivity{

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(EMChatManager.getInstance().getCurrentUser()!=null){
                    Login();

                }
            }
        },3000);

    }

    public void Login(){
        String username =  EMChatManager.getInstance().getCurrentUser().toString();
        String pass = "123456";
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, EMChatManager.getInstance().getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {

    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
