package com.hgj.demo.Act;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.WindowManager;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.DensityUtil;
import com.easemob.easeui.widget.barrageview.BarrageView;
import com.easemob.easeui.widget.barrageview.BarrageViewItem;
import com.easemob.easeui.widget.barrageview.IBarrageViewItem;
import com.easemob.easeui.widget.videoview.MediaController;
import com.easemob.easeui.widget.videoview.SuperVideoPlayer;
import com.easemob.easeui.widget.videoview.Video;
import com.easemob.easeui.widget.videoview.VideoUrl;
import com.hgj.demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/1 09:05
 * 邮箱：www.guojunkuaile@qq.com
 */
public class VideoAvtivity extends EaseBaseActivity {

    private String TEST_URL = "http://172.16.0.156:8080/com.nkbh.pro/a.mp4";
    private SuperVideoPlayer mSuperVideoPlayer;
    private BarrageView mBarrageView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("time", mSuperVideoPlayer.getSuperVideoView()
                .getCurrentPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null == mSuperVideoPlayer)
            return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            final WindowManager.LayoutParams attrs = getWindow()
                    .getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.0f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
        mSuperVideoPlayer.loadAndPlay(TEST_URL,
                savedInstanceState.getInt("time"));
    }


    private List<IBarrageViewItem> initItems() {
        List<IBarrageViewItem> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            IBarrageViewItem item = new BarrageViewItem(this, i + " : plain text danmuku", mBarrageView.getWidth());
            list.add(item);
        }

        String msg = " : text with image   ";
        for (int i = 0; i < 100; i++) {
            ImageSpan imageSpan = new ImageSpan(this, R.drawable.ic_launcher);
            SpannableString spannableString = new SpannableString(i + msg);
            spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            IBarrageViewItem item = new BarrageViewItem(this, spannableString, mBarrageView.getWidth(), 0, 0, 0, 1.5f);
            list.add(item);
        }
        return list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_second);
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        ArrayList<Video> videoArrayList = new ArrayList<>();
        Video video = new Video();
        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();

        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl(TEST_URL);
        VideoUrl videoUrl2 = new VideoUrl();
        videoUrl2.setFormatName("480P");
        videoUrl2.setFormatUrl(TEST_URL);

        arrayList1.add(videoUrl1);
        arrayList1.add(videoUrl2);
        video.setVideoName("房源视频");
        video.setVideoUrl(arrayList1);
        videoArrayList.add(video);

        mSuperVideoPlayer.loadMultipleVideo(videoArrayList);
        mSuperVideoPlayer.setVideoPlayCallback(new SuperVideoPlayer.VideoPlayCallbackImpl() {
            @Override
            public void onCloseVideo() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer
                        .setPageType(MediaController.PageType.SHRINK);
                mSuperVideoPlayer.stopPlay();
            }

            @Override
            public void onSwitchPageType() {
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mSuperVideoPlayer
                            .setPageType(MediaController.PageType.SHRINK);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    mSuperVideoPlayer
                            .setPageType(MediaController.PageType.EXPAND);
                }
            }

            @Override
            public void onPlayFinish() {
            }
        });

        mBarrageView = (BarrageView) findViewById(R.id.danmakuView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<IBarrageViewItem> list = initItems();
                Collections.shuffle(list);
                mBarrageView.addItem(list, true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBarrageView.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBarrageView.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBarrageView.clear();
    }

    @Override
    public void onClick(View view) {
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
