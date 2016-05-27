package com.hgj.demo.Demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easemob.easeui.domain.EaseUser;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseContactListFragment;
import com.hgj.demo.R;
import com.lidroid.xutils.ViewUtils;

import java.util.HashMap;
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
        String chasr = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 100; i++) {
            EaseUser user = new EaseUser();
            user.setUsername("测试");
            user.setNick(chasr.charAt((int) (Math.random() * 26)) + "");
            contacts.put(i + "", user);
        }

        return contacts;
    }

}
