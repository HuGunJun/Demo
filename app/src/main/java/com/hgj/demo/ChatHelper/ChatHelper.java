package com.hgj.demo.ChatHelper;

import android.content.Context;
import android.content.Intent;

import com.easemob.EMConnectionListener;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.ConnectionListener;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;
import com.easemob.easeui.model.EaseNotifier;
import com.easemob.easeui.model.UserManager;
import com.hgj.demo.Demo.Activity1;
import com.hgj.demo.Demo.ChatActivity;

import java.util.EventListener;
import java.util.List;

/**
 * User: HuGuoJun
 * Date: 2016-05-28
 * Time: 20:35
 * Email: www.guojunkuaile@qq.com
 * QQ: 1397883456
 */
public class ChatHelper {


    private Context context;
    private static ChatHelper helper;

    public static synchronized ChatHelper getInstance() {
        if (helper == null) {
            helper = new ChatHelper();
        }
        return helper;
    }

    public synchronized void init(Context context) {
        this.context = context;
        EaseUI.getInstance().init(context);
        EMChat.getInstance().setAutoLogin(true);
        EMChat.getInstance().setAppInited();

        //注册一个新消息到达的监听
        EMChatManager.getInstance().registerEventListener(
                new MyNewMessageListener());
        // 设置提供者
        EaseUI.getInstance().getNotifier()
                .setNotificationInfoProvider(new MyNotificationProvider());
        // 注册一个监听连接状态的listener
        EMChatManager.getInstance().addConnectionListener(
                new MyConnectionListener());
        // 设置联系人提供者
        EaseUI.getInstance().setUserProfileProvider(new MyUserProvider());
    }

    /**
     * 新消息到达监听事件
     */
    public class MyNewMessageListener implements EMEventListener {

        @Override
        public void onEvent(EMNotifierEvent emNotifierEvent) {
            switch (emNotifierEvent.getEvent()) {
                case EventNewMessage: // 接收新消息
                {
                    EMMessage message = (EMMessage) emNotifierEvent.getData();
                    EaseUI.getInstance().getNotifier().onNewMsg(message);
                    // 刷新聊天历史页面
                    Activity1.conversationListFragment.refresh();
                    break;
                }
                default:
                    break;
            }
        }
    }

    /**
     * 注册通知到达监听
     */
    public class MyNotificationProvider implements EaseNotifier.EaseNotificationInfoProvider {

        @Override
        public String getDisplayedText(EMMessage message) {
            return null;
        }

        @Override
        public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
            return null;
        }

        @Override
        public String getTitle(EMMessage message) {
            return null;
        }

        @Override
        public int getSmallIcon(EMMessage message) {
            return 0;
        }

        @Override
        public Intent getLaunchIntent(EMMessage message) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra(EaseConstant.EXTRA_USER_ID, message.getFrom());
            return intent;
        }
    }

    /**
     * 注册链接状态的监听
     */
    public class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(int i) {

        }
    }

    /**
     * 设置用户查找提供者
     */
    public class MyUserProvider implements EaseUI.EaseUserProfileProvider {

        @Override
        public EaseUser getUser(String username) {
            List<EaseUser> userChannel = UserManager.getManage(
                    EaseUI.getInstance().getSQLHelper()).getUser_Contract();
            EaseUser user = null;
            for (int i = 0; i < userChannel.size(); i++) {
                if (userChannel.get(i).getUsername().equals(username)) {
                    user = new EaseUser();
                    user.setUsername(username);
                    break;
                }
            }
            return user;
        }
    }
}
