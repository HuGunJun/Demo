package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseConversationListFragment;
import com.hgj.demo.R;
import com.lidroid.xutils.ViewUtils;

public class Activity1 extends EaseBaseActivity {

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
                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName());
                        startActivity(intent);
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
