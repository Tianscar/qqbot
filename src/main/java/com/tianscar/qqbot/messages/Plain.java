package com.tianscar.qqbot.messages;

import com.google.gson.JsonObject;

public class Plain extends Message {

	public static final String TYPE = "Plain";

	@Override
	public String type() {
		return TYPE;
	}

	private final String text;//文本内容
	
	public Plain(String text) {
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public JsonObject toJsonObject(){
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("text", text);
		return data;
	}

}