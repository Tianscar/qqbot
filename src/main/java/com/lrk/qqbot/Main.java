package com.lrk.qqbot;

import com.lrk.qqbot.core.BotCore;

public class Main {

	public static void main(String[] args) throws Exception {
		Data.init();
		BotCore core = new BotCore(
				Data.getProp("port").replaceAll(" ", "").split(":")[0],
				Integer.parseInt(Data.getProp("port").replaceAll(" ", "").split(":")[1]),
				Data.getProp("miraiApiHttpVerifyKey"), Long.parseLong(Data.getProp("qq")));
		if (core.addListener(new RobotMain())) {
			RobotNotification.config("添加监听器: " + RobotMain.class.getSimpleName());
		}
	}

}