package com.lrk.qqbot.threads;

import com.lrk.qqbot.event.GroupMessageEvent;

public class GroupMessageThread extends RobotThread {

	protected final GroupMessageEvent event;

	public GroupMessageThread(GroupMessageEvent event) {
		this.event = event;
		init();
	}

	@Override
	protected void process() throws Exception {
	}

}
