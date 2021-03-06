package com.tianscar.qqbot.messages;

import com.google.gson.JsonObject;

public class Source extends Message {

	private final String TYPE = "Source";

	@Override
	public String type() {
		return TYPE;
	}

	private final int id;//消息id
	private final int time;//消息时间戳

	public Source(int id, int time) {
		this.id = id;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public int getTime() {
		return time;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("id", id);
		data.addProperty("time", time);
		return data;
	}

}
