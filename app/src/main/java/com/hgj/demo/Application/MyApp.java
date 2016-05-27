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

    @Override
    public void onCreate() {
        super.onCreate();
        EaseUI.getInstance().init(this);
        EMChat.getInstance().setAutoLogin(true);
        EMChat.getInstance().setAppInited();
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(SQLHelper.INFO, "name");
        hashMap.put(SQLHelper.TYPE, "TEXT");
        list.add(hashMap);

        HashMap<String, String> hashMap1 = new HashMap<String, String>();
        hashMap1.put(SQLHelper.INFO, "sex");
        hashMap1.put(SQLHelper.TYPE, "INT");
        list.add(hashMap1);

        EaseUI.getInstance().setSqlHashMap(list);
        EaseUI.getInstance().getSQLHelper().getWritableDatabase();

    }
}
