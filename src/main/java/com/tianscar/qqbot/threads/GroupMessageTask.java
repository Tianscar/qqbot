package com.tianscar.qqbot.threads;

import com.tianscar.qqbot.Data;
import com.tianscar.qqbot.Utils;
import com.tianscar.qqbot.event.GroupMessageEvent;
import com.tianscar.qqbot.image.MeiTu;
import com.tianscar.qqbot.image.Yunshi;
import com.tianscar.qqbot.messages.At;
import com.tianscar.qqbot.messages.Image;
import com.tianscar.qqbot.messages.Plain;
import com.tianscar.qqbot.wordlib.WordlibParser;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.logging.Logger;

public class GroupMessageTask extends RobotTask {

	public static final String ACTIVE_CHAT_GROUPS = "activeChatGroups";
	public static final String ADMIN_LIST = "adminList";
	public static final String SUPERUSER_LIST = "superuserList";
	public static final String ROBOT_NAME = "窗边冕";
	public static final String ROBOT_KEYWORD = ".";

	protected final GroupMessageEvent event;
	private static final HashMap<String, String> wordlib = WordlibParser.parse(
			GroupMessageTask.class.getResourceAsStream("/wordlib/words.txt"),
			ROBOT_NAME);

	private static final Random random = new Random();

	public GroupMessageTask(GroupMessageEvent event) {
		this.event = event;
		init();
	}

	@Override
	protected void process() throws Exception {

		String message = event.getMessage();
		long sender = event.getSender();
		long groupId = event.getGroupID();
		long messageId = event.getMessageId();
		ArrayList<Long> atUsers = event.getAtUsers();

		if (!message.toLowerCase().contains(ROBOT_KEYWORD)) {
			return;
		}

		if (message.toLowerCase().contains("bot")) {
			if (message.toLowerCase().contains("on")) {
				if (Data.propArrayContainsNumber(ADMIN_LIST, sender) || Data.propArrayContainsNumber(SUPERUSER_LIST, sender)) {
					if (!Data.propArrayContainsNumber(ACTIVE_CHAT_GROUPS, groupId)) {
						Data.propArrayAppendNumber(ACTIVE_CHAT_GROUPS, groupId);
						Data.refreshProp();
						event.reply(new Plain("[CONTROL] " + ROBOT_NAME + "已就绪"));
						return;
					}
				}
			}
			else if (message.toLowerCase().contains("off")) {
				if (Data.propArrayContainsNumber(ADMIN_LIST, sender) || Data.propArrayContainsNumber(SUPERUSER_LIST, sender)) {
					if (Data.propArrayContainsNumber(ACTIVE_CHAT_GROUPS, groupId)) {
						Data.propArrayRemoveNumber(ACTIVE_CHAT_GROUPS, groupId);
						Data.refreshProp();
						event.reply(new Plain("[CONTROL] " + ROBOT_NAME + "已关机"));
						return;
					}
				}
			}
		}

		if (Data.propArrayContainsNumber(ACTIVE_CHAT_GROUPS, groupId)) {

			if (message.toLowerCase().contains("bot")) {
				if (message.toLowerCase().contains("admin") && message.toLowerCase().contains("add") && atUsers.size() > 0) {
					if (Data.propArrayContainsNumber(SUPERUSER_LIST, sender)) {
						for (long atUser : atUsers) {
							if (!Data.propArrayContainsNumber(ADMIN_LIST, atUser)) {
								Data.propArrayAppendNumber(ADMIN_LIST, atUser);
								Data.refreshProp();
								event.reply(new Plain("[CONTROL] " + ROBOT_NAME + "已将 "), new At(atUser, ""), new Plain(" 添加到管理员列表"));
							}
						}
					}
					return;
				}
				else if (message.toLowerCase().contains("admin") && message.toLowerCase().contains("remove") && atUsers.size() > 0) {
					if (Data.propArrayContainsNumber(SUPERUSER_LIST, sender)) {
						for (long atUser : atUsers) {
							if (Data.propArrayContainsNumber(ADMIN_LIST, atUser)) {
								Data.propArrayRemoveNumber(ADMIN_LIST, atUser);
								Data.refreshProp();
								event.reply(new Plain("[CONTROL] " + ROBOT_NAME + "已将 "), new At(atUser, ""), new Plain(" 从管理员列表移除"));
							}
						}
					}
					return;
				}
			}

			boolean chat = true;

			if (message.contains("戳")) {
				event.getBotCore().nudge(Utils.sendGroupNudge(groupId, sender));
			}

			if (message.contains("运势")) {
				event.reply(new At(sender, ""), new Image(null, null, null, Data.processImage(new Yunshi())));
				chat = false;
			}

			if (message.contains("美图")) {
				String image = Data.processImage(new MeiTu());
				if (image == null) {
					event.reply(new At(sender, ""), new Plain("获取失败，请重试"));
				}
				else event.reply(new At(sender, ""), new Image(null, null, null, image));
				chat = false;
			}

			if (!chat) return;
			LinkedList<String> replyList = new LinkedList<>();
			for (String key : wordlib.keySet()) {
				if (message.contains(key)) {
					replyList.add(wordlib.get(key)
							.replaceAll("XXX", event.getSenderNickName())
							.replaceAll("【换行】", "\n"));
				}
			}
			int size = replyList.size();
			if (size > 0) {
				String reply = replyList.get(random.nextInt(size));
				event.reply(new At(sender, ""), new Plain(" " + reply));
			}

		}
	}

}
