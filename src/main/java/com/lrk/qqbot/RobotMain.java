package com.lrk.qqbot;

import com.lrk.qqbot.event.Event;
import com.lrk.qqbot.event.FriendMessageEvent;
import com.lrk.qqbot.event.GroupMessageEvent;
import com.lrk.qqbot.listener.EventHandler;
import com.lrk.qqbot.listener.Listener;
import com.lrk.qqbot.threads.FriendMessageThread;
import com.lrk.qqbot.threads.GroupMessageThread;

import java.io.IOException;

public class RobotMain implements Listener {

    @EventHandler
    public void onEvent(Event event) {
        RobotNotification.info("新事件: (" + event.getParams() + ")");
    }

    @EventHandler
    public void onFriendMessage(FriendMessageEvent event) throws Exception {
    	new FriendMessageThread(event).start();
    }

    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) throws Exception {
    	new GroupMessageThread(event).start();
	}
	
}
