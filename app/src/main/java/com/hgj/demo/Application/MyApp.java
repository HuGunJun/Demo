package com.hgj.demo.Application;

import android.app.Application;

import com.hgj.demo.ChatHelper.ChatHelper;

/**
 * 作者：HuGuoJun
 * 2016/5/18 09:32
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ChatHelper.getInstance().init(this);
    }
}
