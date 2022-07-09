package com.lrk.qqbot.event;

import com.lrk.qqbot.core.BotCore;
import com.lrk.qqbot.messages.MessageType;
import com.lrk.qqbot.messages.Message;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Collection;

public abstract class MessageEvent extends Event {

    // 发送者ID
    protected long senderID;
    // 消息内容
    protected String message;
    // 消息类型
    protected MessageType messageType;

    public MessageEvent(BotCore botCore, JsonObject params) {
        super(botCore, params);
    }

    public abstract JsonObject reply(String messages) throws IOException;
    public abstract JsonObject reply(Message... messages) throws IOException;
    public abstract JsonObject reply(Collection<Message> messages) throws IOException;
    public JsonObject replyIgnoreException(String message) {
        try {
            return reply(message);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public long getSender() {
        return senderID;
    }

    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return "MessageEvent{" +
                "senderID=" + senderID +
                ", message='" + message + '\'' +
                '}';
    }

}
