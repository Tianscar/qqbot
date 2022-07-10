package com.tianscar.qqbot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tianscar.qqbot.messages.Message;

import java.util.Collection;
import java.util.Random;

public class Utils {

    public static JsonObject sendMessageToFriend(long qq, Message... messages) {
        JsonObject object = new JsonObject();
        object.addProperty("target", qq);
        JsonArray messageChain = new JsonArray();
        for(Message message : messages){
            messageChain.add(message.toJsonObject());
        }
        object.add("messageChain",messageChain);
        return object;
    }

    public static JsonObject sendMessageToFriend(long qq, Collection<Message> messages) {
        JsonObject object = new JsonObject();
        object.addProperty("target", qq);
        JsonArray messageChain = new JsonArray();
        for(Message message : messages){
            messageChain.add(message.toJsonObject());
        }
        object.add("messageChain",messageChain);
        return object;
    }

    public static JsonObject sendMessageToGroup(long groupID, Message... messages) {
        JsonObject object = new JsonObject();
        object.addProperty("target", groupID);
        JsonArray messageChain = new JsonArray();
        for(Message message : messages) {
            messageChain.add(message.toJsonObject());
        }
        object.add("messageChain", messageChain);
        return object;
    }

    public static JsonObject sendMessageToGroup(long groupID, Collection<Message> messages) {
        JsonObject object = new JsonObject();
        object.addProperty("target", groupID);
        JsonArray messageChain = new JsonArray();
        for(Message message : messages) {
            messageChain.add(message.toJsonObject());
        }
        object.add("messageChain", messageChain);
        return object;
    }

    public static JsonObject sendGroupNudge(long groupID, long target){
        JsonObject data = new JsonObject();
        data.addProperty("target", target);
        data.addProperty("subject", groupID);
        data.addProperty("kind","Group");
        return data;
    }

    public static int randomInt(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
    }

}
