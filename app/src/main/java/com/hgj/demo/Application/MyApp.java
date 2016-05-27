package com.hgj.demo.Application;

import android.app.Application;

import com.easemob.chat.EMChat;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.helper.SQLHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/5/18 09:32
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MyApp extends Application {

    private SQLHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        EaseUI.getInstance().init(this);
        EMChat.getInstance().setAutoLogin(true);
        EMChat.getInstance().setAppInited();

    }

}
