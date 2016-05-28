package com.hgj.demo.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;
import com.easemob.easeui.model.InviteManager;
import com.easemob.easeui.model.UserManager;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseContactListFragment;
import com.hgj.demo.R;
import com.lidroid.xutils.ViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity2 extends EaseBaseActivity {

    private EaseContactListFragment contactListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        ViewUtils.inject(this);
        InitView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {
        contactListFragment = new EaseContactListFragment();
        contactListFragment.setContactsMap(getContacts());
        contactListFragment
                .setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

                    @Override
                    public void onListItemClicked(EaseUser user) {
                    }
                });
        contactListFragment
                .setContactListItemLongClickListener(new EaseContactListFragment.EaseContactListItemLongClickListener() {

                    @Override
                    public void onListItemLongClicked(EaseUser user) {
                        Toast.makeText(context, user.getUsername(),
                                Toast.LENGTH_SHORT).show();

                    }
                });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, contactListFragment)
                .show(contactListFragment).commit();


        contactListFragment.setContactListItemLongClickListener(new EaseContactListFragment.EaseContactListItemLongClickListener() {
            @Override
            public void onListItemLongClicked(EaseUser user) {
                EaseUser user1 = EaseUI.getInstance().getUserProfileProvider().getUser(user.getUsername());
                if (user1 != null) {
                    Toast.makeText(context, "存在用户", Toast.LENGTH_SHORT).show();

                }
            }
        });
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername());
                startActivity(intent);
            }
        });
    }

    @Override
    public void InitData() {

    }

    /**
     * 填充好友列表
     *
     * @return
     */
    private Map<String, EaseUser> getContacts() {
        Map<String, EaseUser> contacts = new HashMap<String, EaseUser>();
        List<EaseUser> user_contract = UserManager.getManage(EaseUI.getInstance().getSQLHelper()).getUser_Contract();
        for (int i = 0; i < user_contract.size(); i++) {
            EaseUser user = new EaseUser();
            user.setUsername(user_contract.get(i).getUsername());
            contacts.put(i + "", user);
        }
        return contacts;

    }

}
