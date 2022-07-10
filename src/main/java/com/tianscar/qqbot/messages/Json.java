package com.tianscar.qqbot.messages;

import com.google.gson.JsonObject;

public class Json extends Message {

	public final static String TYPE = "Json";

	@Override
	public String type() {
		return TYPE;
	}

	private final String json;

	public Json(String json) {
		this.json = json;
	}
	
	public String getJson() {
		return json;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("json", json);
		return data;
	}
	
}
