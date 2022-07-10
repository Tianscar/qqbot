package com.tianscar.qqbot;

import com.tianscar.qqbot.event.Event;
import com.tianscar.qqbot.event.FriendMessageEvent;
import com.tianscar.qqbot.event.GroupMessageEvent;
import com.tianscar.qqbot.listener.EventHandler;
import com.tianscar.qqbot.listener.Listener;
import com.tianscar.qqbot.threads.FriendMessageTask;
import com.tianscar.qqbot.threads.GroupMessageTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RobotMain implements Listener {

    private final ExecutorService executor;

    public RobotMain() {
        executor = Executors.newFixedThreadPool(20);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executor.shutdown();
            }
        }));
    }

    @EventHandler
    public void onEvent(Event event) {
        RobotNotification.info("新事件: (" + event.getParams() + ")");
    }

    @EventHandler
    public void onFriendMessage(FriendMessageEvent event) throws Exception {
        executor.execute(new FriendMessageTask(event));
    }

    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) throws Exception {
    	executor.execute(new GroupMessageTask(event));
	}
	
}
