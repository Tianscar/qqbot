package com.tianscar.qqbot.messages;

import com.google.gson.JsonObject;

public class Xml extends Message {

	public static final String TYPE = "Xml";

	@Override
	public String type() {
		return TYPE;
	}

	private final String xml;//XML消息内容

	public Xml(String xml) {
		this.xml = xml;
	}

	public String getXml() {
		return xml;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("xml", xml);
		return data;
	}
}
