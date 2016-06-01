package com.hgj.demo.Demo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.DensityUtil;
import com.easemob.easeui.widget.videoview.MediaController;
import com.easemob.easeui.widget.videoview.SuperVideoPlayer;
import com.easemob.easeui.widget.videoview.Video;
import com.easemob.easeui.widget.videoview.VideoUrl;
import com.hgj.demo.R;

import java.util.ArrayList;

/**
 * 作者：HuGuoJun
 * 2016/6/1 09:05
 * 邮箱：www.guojunkuaile@qq.com
 */
public class VideoAvtivity extends EaseBaseActivity {

    private String TEST_URL = "http://172.16.0.156:8080/com.nkbh.pro/a.mp4";
    private SuperVideoPlayer mSuperVideoPlayer;
    private int time;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_second);
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        Video video = new Video();
        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl(TEST_URL);
        VideoUrl videoUrl2 = new VideoUrl();
        videoUrl2.setFormatName("480P");
        videoUrl2.setFormatUrl(TEST_URL);
        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();
        arrayList1.add(videoUrl1);
        arrayList1.add(videoUrl2);
        video.setVideoName("房源视频");
        video.setVideoUrl(arrayList1);

        Video video2 = new Video();
        VideoUrl videoUrl3 = new VideoUrl();
        videoUrl3.setFormatName("720P");
        videoUrl3.setFormatUrl(TEST_URL);
        VideoUrl videoUrl4 = new VideoUrl();
        videoUrl4.setFormatName("480P");
        videoUrl4.setFormatUrl(TEST_URL);
        ArrayList<VideoUrl> arrayList2 = new ArrayList<>();
        arrayList2.add(videoUrl3);
        arrayList2.add(videoUrl4);
        video2.setVideoName("小区视频");
        video2.setVideoUrl(arrayList2);

        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video);
        videoArrayList.add(video2);

        mSuperVideoPlayer.loadMultipleVideo(videoArrayList);
        mSuperVideoPlayer.setVideoPlayCallback(new SuperVideoPlayer.VideoPlayCallbackImpl() {
            @Override
            public void onCloseVideo() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer
                        .setPageType(MediaController.PageType.SHRINK);
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
