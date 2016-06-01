package com.hgj.demo.Demo;

import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.xlistview.XListView;
import com.hgj.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/1 15:18
 * 邮箱：www.guojunkuaile@qq.com
 */
public class XlistViewActivity extends EaseBaseActivity {
    XListView xlistview;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.xlistview_activity);
        xlistview = (XListView) findViewById(R.id.xlistview);
        xlistview.setPullLoadEnable(true);
        xlistview.setPullRefreshEnable(true);
        xlistview.IsShowGifView(true);
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 100; i++) {
            HashMap<String, String> keyValuePair = new HashMap<String, String>();
            keyValuePair.put("name", "哈哈");
            list.add(keyValuePair);
        }
        xlistview.setAdapter(new SimpleAdapter(context, list,
                android.R.layout.simple_list_item_1, new String[]{"name"},
                new int[]{android.R.id.text1}));

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
