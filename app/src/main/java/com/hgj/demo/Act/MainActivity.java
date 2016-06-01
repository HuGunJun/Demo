package com.hgj.demo.Act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.EaseUserUtils;
import com.easemob.easeui.widget.actionsheetdialog.ActionSheetDialog;
import com.easemob.easeui.widget.actionsheetdialog.ActionSheetDialog.OnSheetItemClickListener;
import com.easemob.easeui.widget.actionsheetdialog.ActionSheetDialog.SheetItemColor;
import com.easemob.easeui.widget.alertdialog.EaseAlertDialog_Ios;
import com.easemob.easeui.widget.progress.SVProgressHUD;
import com.easemob.easeui.widget.switchview.SegmentView;
import com.hgj.demo.Demo.VideoAvtivity;
import com.hgj.demo.Demo.XlistViewActivity;
import com.hgj.demo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zbar.lib.CaptureActivity;


public class MainActivity extends EaseBaseActivity {
    int progress = 0;
    // 侧滑菜单
    private SlidingMenu menu;
    @ViewInject(R.id.segmentView)
    private SegmentView mSegmentView;
    @ViewInject(R.id.QR)
    ImageView QR;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (SVProgressHUD.getProgressBar(context).getMax() != SVProgressHUD
                    .getProgressBar(context).getProgress()) {
                SVProgressHUD.getProgressBar(context).setProgress(progress);
                SVProgressHUD.setText(context, "进度 " + progress + "%");
                mHandler.sendEmptyMessageDelayed(0, 500);
            } else {
                SVProgressHUD.dismiss(context);
                SVProgressHUD.getProgressBar(context).setProgress(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        //支持中文
        QR.setImageBitmap(EaseUserUtils.createQRImage("http://www.baidu.com", 200, 200));
        menu = new SlidingMenu(getApplicationContext());
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.height_top_bar);
        // 设置背景图
        // menu.setBackgroundResource(R.drawable.left_new_background);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 为侧滑菜单设置布局
        menu.setMenu(R.layout.slidingmenu);
        Button btn = (Button) menu.findViewById(R.id.slibtn1);
        btn.setText("打开右侧菜单");
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                menu.toggle();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ShowLoadingDialog();

        SetOnClickListener();

    }

    private void SetOnClickListener() {
        mSegmentView.setOnIndexChangedListener(new SegmentView.OnIndexChangedListener() {

            @Override
            public void onChanged(SegmentView view, int index) {
                Toast.makeText(context, "segment" + index,
                        Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new ActionSheetDialog(context)
                        .builder()
                        .setTitle("清空消息列表后，聊天记录依然保留，确定要清空消息列表？")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("清空消息列表", SheetItemColor.Red,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                }).show();
                break;
            case R.id.btn2:
                new ActionSheetDialog(context)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("发送给好友", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("转载到空间相册", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("上传到群相册", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("保存到手机", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("收藏", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("查看聊天图片", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                }).show();
                break;
            case R.id.btn3:
                new ActionSheetDialog(context)
                        .builder()
                        .setTitle("请选择操作")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("条目一", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目二", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目三", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目四", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目五", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目六", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目七", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目八", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目九", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("条目十", SheetItemColor.Blue,
                                new OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(context, "item" + which,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                break;
            case R.id.btn4:
                new EaseAlertDialog_Ios(context).builder().setTitle("退出当前账号")
                        .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                        .setPositiveButton("确认退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
            case R.id.btn5:
                new EaseAlertDialog_Ios(context).builder()
                        .setMsg("你现在无法接收到新消息提醒。请到系统-设置-通知中开启消息提醒")
                        .setNegativeButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                break;
            case R.id.btn6:
                SVProgressHUD.show(this);
                break;
            case R.id.btn7:
                SVProgressHUD.showWithMaskType(this,
                        SVProgressHUD.SVProgressHUDMaskType.None);
                break;
            case R.id.btn8:
                SVProgressHUD.showWithStatus(this, "加载中...");
                break;
            case R.id.btn9:
                SVProgressHUD.showInfoWithStatus(this, "这是提示",
                        SVProgressHUD.SVProgressHUDMaskType.None);
                break;
            case R.id.btn10:
                SVProgressHUD.showSuccessWithStatus(this, "恭喜，提交成功！");
                break;
            case R.id.btn11:
                SVProgressHUD.showErrorWithStatus(this, "不约，叔叔我们不约～",
                        SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
                break;
            case R.id.btn12:
                SVProgressHUD.showWithProgress(this, "进度 " + progress + "%",
                        SVProgressHUD.SVProgressHUDMaskType.Black);
                progress = 0;
                mHandler.sendEmptyMessageDelayed(0, 500);
                break;
            case R.id.btn13:
                startActivity(new Intent(context, ImageTurn.class));
                break;
            case R.id.btn16:
                startActivity(new Intent(context, Act_SychronizeHorizontalView.class));
                break;
            case R.id.btn20:
                startActivity(new Intent(context, LoaddingActivity.class));
                break;
            case R.id.btn21:
                startActivity(new Intent(context, ListAnimation.class));
                break;
            case R.id.btn22:
                startActivityForResult(new Intent(context, CaptureActivity.class),
                        100);
                break;
            case R.id.btn23:
                startActivity(new Intent(context, VideoAvtivity.class));
                break;
            case R.id.btn24:
                startActivity(new Intent(context, XlistViewActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (arg2 != null) {
            Bundle bundle = arg2.getExtras();
            String mm = bundle.getString("data");
            Log.i("main", mm);
        }
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }
}
