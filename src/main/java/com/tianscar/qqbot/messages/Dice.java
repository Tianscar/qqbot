package com.tianscar.qqbot.messages;
// 骰子
import com.google.gson.JsonObject;

public class Dice extends Message {

	public static final String TYPE = "Dice";

	@Override
	public String type() {
		return TYPE;
	}

	private final int value;
	
	public Dice(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("value", value);
		return data;
	}
}
