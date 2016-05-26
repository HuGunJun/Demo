package com.hgj.demo.Act;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.cycleview.CycleViewPager;
import com.easemob.easeui.widget.cycleview.CycleVpEntity;
import com.easemob.easeui.widget.cycleview.ViewFactory;
import com.hgj.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/5/18 10:53
 * 邮箱：www.guojunkuaile@qq.com
 */
public class ImageTurn extends EaseBaseActivity {
    LinearLayout lv_imageviewturn;
    private CycleViewPager cycleViewPager;
    private View vhdf;
    String[] imageUrls = {
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.list_listview_turn);
        InitView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {
        lv_imageviewturn = (LinearLayout) findViewById(R.id.lv_imageviewturn);
        vhdf = getLayoutInflater().inflate(R.layout.ease_viewpage, null);
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        ViewFactory.initialize(this, vhdf, cycleViewPager, cycData(imageUrls),
                new CycleViewPager.ImageCycleViewListener() {

                    @Override
                    public void onImageClick(CycleVpEntity info, int postion,
                                             View imageView) {

                    }
                });
        cycleViewPager.SetIndicatorResouse(R.drawable.ic_svstatus_error, R.drawable.ic_svstatus_success);
        lv_imageviewturn.addView(vhdf);
    }

    @Override
    public void InitData() {

    }

    /**
     * 返回广告数据
     *
     * @return
     */
    public static List<CycleVpEntity> cycData(String[] ImageS) {
        List<CycleVpEntity> list = new ArrayList<CycleVpEntity>();
        for (int i = 0; i < ImageS.length; i++) {
            CycleVpEntity cyc = new CycleVpEntity();
            cyc.setIurl(ImageS[i]);
            cyc.setCurl("www.baidu.com");
            cyc.setTitle("奇怪的标题"+i);
            list.add(cyc);
        }
        return list;
    }

}
