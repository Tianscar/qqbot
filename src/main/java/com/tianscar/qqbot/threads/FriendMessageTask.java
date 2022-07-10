package com.tianscar.qqbot.threads;

import com.tianscar.qqbot.event.FriendMessageEvent;

public class FriendMessageTask extends RobotTask {

	protected final FriendMessageEvent event;

	public FriendMessageTask(FriendMessageEvent event) {
		this.event = event;
		init();
	}

	@Override
	protected void process() throws Exception {

	}

}