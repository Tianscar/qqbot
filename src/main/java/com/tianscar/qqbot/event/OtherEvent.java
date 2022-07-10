package com.tianscar.qqbot.event;

import com.tianscar.qqbot.core.BotCore;
import com.google.gson.JsonObject;

public class OtherEvent extends Event {

    public OtherEvent(BotCore botCore, JsonObject object) {
        super(botCore, object);
    }

}