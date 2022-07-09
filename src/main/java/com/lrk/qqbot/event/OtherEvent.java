package com.lrk.qqbot.event;

import com.lrk.qqbot.core.BotCore;
import com.google.gson.JsonObject;

public class OtherEvent extends Event {

    public OtherEvent(BotCore botCore, JsonObject object) {
        super(botCore, object);
    }

}