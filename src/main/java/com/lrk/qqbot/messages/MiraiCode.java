package com.lrk.qqbot.messages;

import com.google.gson.JsonObject;

public class MiraiCode extends Message {

	public static final String TYPE = "MiraiCode";

	@Override
	public String type() {
		return TYPE;
	}

	private final String code;

	public MiraiCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("code", code);
		return data;
	}
	
}
