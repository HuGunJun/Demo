package com.easemob.easeui.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.easemob.easeui.controller.EaseUI;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/5/27 15:14
 * 邮箱：www.guojunkuaile@qq.com
 */
public class SQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;
    public static final String TABLE_USER = "users_list";// 联系人列表
    public static final String TYPE = "type";//用户传入信息的类型
    public static final String INFO = "info";//用户传入信息的字段

    private Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 联系人
        List<HashMap<String, String>> sqlHashMap = EaseUI.getInstance().getSqlHashMap();
        String sql = "";
        for (int i = 0; i < sqlHashMap.size(); i++) {
            sql += "," + sqlHashMap.get(i).get(INFO) + " " + sqlHashMap.get(i).get(TYPE) + " ";
        }
        String sql_contract_list = "create table if not exists " + TABLE_USER
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT " + sql + ")";
        Log.i("main", sql);
        db.execSQL(sql_contract_list);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DELETE FROM " + SQLHelper.TABLE_USER + ";";
        db.execSQL(sql);
        String sqls = "update sqlite_sequence set seq=0 where name='"
                + SQLHelper.TABLE_USER + "'";
        db.execSQL(sqls);
    }
}
