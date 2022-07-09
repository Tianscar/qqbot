package com.lrk.qqbot.threads;

import com.lrk.qqbot.event.FriendMessageEvent;

public class FriendMessageThread extends RobotThread {

	protected final FriendMessageEvent event;

	public FriendMessageThread(FriendMessageEvent event) {
		this.event = event;
		init();
	}

	@Override
	protected void process() throws Exception {

	}

}