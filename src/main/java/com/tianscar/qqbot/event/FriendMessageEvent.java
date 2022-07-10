package com.tianscar.qqbot.event;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tianscar.qqbot.Utils;
import com.tianscar.qqbot.core.BotCore;
import com.tianscar.qqbot.messages.MessageType;
import com.tianscar.qqbot.messages.Image;
import com.tianscar.qqbot.messages.Message;
import com.tianscar.qqbot.messages.Plain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FriendMessageEvent extends MessageEvent {

    // 消息id
    private long messageID;
    //消息中的文本
    private String message;
    //消息中的图片或大表情(PicMsg, BigFaceMsg)
    private final ArrayList<Image> pictures = new ArrayList<>();
    //非文本消息的表示形式(PicMsg, BigFaceMsg)
    private String tips;


    public FriendMessageEvent(BotCore botCore, JsonObject data) {
        super(botCore, data);
        
        senderID = data.getAsJsonObject("sender").get("id").getAsLong();
        JsonArray messageChain = data.getAsJsonArray("messageChain");
        for(int i = 0; i < messageChain.size(); i++){
        	JsonObject messagedata = messageChain.get(i).getAsJsonObject();
        	MessageType messageType= MessageType.valueOf(messagedata.get("type").getAsString());
        	switch(messageType){
                case Source:
        			messageID = messagedata.get("id").getAsLong();
        			break;
                case Plain:
        			message = messagedata.get("text").getAsString();
        			break;
        	}
        }

    }

    @Override
    public JsonObject reply(String message) throws IOException {
        return getBotCore().sendFriendMessage(
                Utils.sendMessageToFriend(senderID, new Plain(message))
        );
    }
    
    @Override
    public JsonObject reply(Message... messages) throws IOException {
    	return getBotCore().sendFriendMessage(Utils.sendMessageToFriend(senderID, messages));
    }

    @Override
    public JsonObject reply(Collection<Message> messages) throws IOException {
        return getBotCore().sendFriendMessage(Utils.sendMessageToFriend(senderID, messages.toArray(new Message[0])));
    }

    public String getMessage() {
        return message;
    }

    public long getMessageID() {
        return messageID;
    }

    public ArrayList<Image> getPictures() {
        return pictures;
    }

    public String getTips() {
        return tips;
    }

}