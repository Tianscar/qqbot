package com.lrk.qqbot.event;

import com.lrk.qqbot.Utils;
import com.lrk.qqbot.core.BotCore;
import com.lrk.qqbot.messages.MessageType;
import com.lrk.qqbot.messages.App;
import com.lrk.qqbot.messages.Image;
import com.lrk.qqbot.messages.Message;
import com.lrk.qqbot.messages.Plain;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GroupMessageEvent extends MessageEvent {
    // 群号
    private final long groupID;
    // 群名称
    private final String groupNickName;
    // 发送者QQ号
    private final long senderID;
    // 发送者群名片
    private final String senderNickName;
    // 消息顺序号(所有消息类型均有)
    private long messageId;
    // 消息发送时间(所有消息类型均有)
    private long messageTime;

    //@ 到的用户ID
    private final ArrayList<Long> atUsers = new ArrayList<>();
    //消息中的图片
    private final ArrayList<Image> pictures = new ArrayList<>();
    //App消息
    private App app;
    //文字消息
    private String message = "";
    
    public GroupMessageEvent(BotCore botCore, JsonObject data) {
        super(botCore,data);
        groupID = data.getAsJsonObject("sender").get("group").getAsJsonObject().get("id").getAsLong();
        groupNickName = data.getAsJsonObject("sender").get("group").getAsJsonObject().get("name").getAsString();
        senderID = data.getAsJsonObject("sender").get("id").getAsLong();
        senderNickName = data.getAsJsonObject("sender").get("memberName").getAsString();
        JsonArray messageChain = data.getAsJsonArray("messageChain");
        for(int i = 0; i < messageChain.size(); i++){
        	JsonObject messageData = messageChain.get(i).getAsJsonObject();
        	MessageType messageType= MessageType.valueOf(messageData.get("type").getAsString());
        	switch(messageType) {
                case Source:
        			messageId = messageData.get("id").getAsLong();
        			messageTime  = messageData.get("time").getAsLong();
        			break;
                case Plain:
        			message += messageData.get("text").getAsString();
        			break;
                case At:
        			atUsers.add(messageData.get("target").getAsLong());
        			break;
                case Image:
        			pictures.add(new Image(messageData.get("imageId").getAsString(),
                            messageData.get("url").getAsString(),
                            messageData.get("path").getAsString(),
                            messageData.get("base64").getAsString()));
        			break;
                case App:
        		    app = new App(messageData.get("content").getAsString());
                    try {
                        reply(app);
                    }
                    catch (IOException ignored) {
                    }
                    break;
        	}
        }
    }

    @Override
    public JsonObject reply(String message) throws IOException {
        return getBotCore().sendGroupMessage(
                Utils.sendMessageToGroup(groupID, new Plain(message))
        );
    }

	@Override
    public JsonObject reply(Message... messages) throws IOException {
    	return getBotCore().sendGroupMessage(Utils.sendMessageToGroup(groupID, messages));
    }

    @Override
    public JsonObject reply(Collection<Message> messages) throws IOException {
        return getBotCore().sendGroupMessage(Utils.sendMessageToGroup(groupID, messages));
    }
    
    public long getGroupID() {
        return groupID;
    }

    public String getGroupNickName() {
        return groupNickName;
    }
    
    public long getSender(){
    	return senderID;
    }
    
    public String getSenderNickName() {
        return senderNickName;
    }

    public String getMessage() {
        return message;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public ArrayList<Long> getAtUsers() {
        return atUsers;
    }

    public ArrayList<Image> getPictures() {
        return pictures;
    }

}