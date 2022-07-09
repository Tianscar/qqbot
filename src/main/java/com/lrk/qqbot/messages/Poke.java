package com.lrk.qqbot.messages;

import com.google.gson.JsonObject;

public class Poke extends Message {

	public static final String TYPE = "Poke";

	@Override
	public String type() {
		return TYPE;
	}

	private final Name name;

	public Poke(Name name) {
		this.name = name;
	}
	
	public String getName() {
		return name.name();
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("name", name.name().charAt(0) + name.name().substring(1).toLowerCase());
		return data;
	}
	
	public enum Name {
		Poke,//戳一戳
		ShowLove,//比心
		Like,//点赞
		HeartBroken,//心碎
		SixSixSix,//666
		FangDaZhao//放大招
	}

}
