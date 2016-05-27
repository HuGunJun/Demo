package com.hgj.demo.Demo;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseConversationListFragment;
import com.hgj.demo.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class Activity1 extends EaseBaseActivity {
    @ViewInject(R.id.fragment_container)
    RelativeLayout fragment_container;

    public static EaseConversationListFragment conversationListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        ViewUtils.inject(this);
        InitView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {
        conversationListFragment = new EaseConversationListFragment();
        conversationListFragment
                .setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

                    @Override
                    public void onListItemClicked(EMConversation conversation) {


                    }
                });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationListFragment)
                .show(conversationListFragment).commit();
    }

    @Override
    public void InitData() {

    }
}
