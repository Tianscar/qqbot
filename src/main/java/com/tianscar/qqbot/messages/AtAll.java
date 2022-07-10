package com.tianscar.qqbot.messages;
//@全体成员
import com.google.gson.JsonObject;

public class AtAll extends Message {

	public static final String TYPE = "AtAll";

	@Override
	public String type() {
		return TYPE;
	}
	
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		return data;
	}
}