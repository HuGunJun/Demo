package com.hgj.demo.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.keyword.KeywordsFlow;
import com.hgj.demo.R;

import java.util.Random;

public class KeyWordActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] keywords = {"国贸360", "人民路", "大石桥", "新通桥",
            "紫荆山", "医学院", "二七广场", "碧沙岗", "凯旋门", "居易国际", "百货大楼", "体育馆", "沙门村",
            "刘庄", "陈寨", "科技市场", "火车站"};
    public static final String[] keywords2 = {"火锅", "小吃", "咖啡", "电影院", "KTV",
            "茶馆", "足浴", "按摩", "超市", "银行", "酒店", "超市", "豫菜", "川菜", "蛋糕店", "医院",
            "面包店", "商场", "书店", "烧烤", "海鲜", "清真"};
    private KeywordsFlow keywordsFlow;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_word);
        keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow_Layout);
        keywordsFlow.setDuration(800l);
        keywordsFlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            keywordsFlow.rubKeywords();
            feedKeywordsFlow(keywordsFlow, keywords);
            keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
            return;
        }

        if (v.getId() == R.id.button2) {
            keywordsFlow.rubKeywords();
            feedKeywordsFlow(keywordsFlow, keywords2);
            keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
            return;
        }

        if (v instanceof TextView) {
            String keyword = ((TextView) v).getText().toString();
            Toast.makeText(getApplication(), keyword, Toast.LENGTH_SHORT).show();
            return;
        }

    }


    // 填充关键词
    private void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < KeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }


}
