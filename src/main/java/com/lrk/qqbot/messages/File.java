package com.lrk.qqbot.messages;

import com.google.gson.JsonObject;

public class File extends Message {

	public static final String TYPE = "File";

	@Override
	public String type() {
		return TYPE;
	}

	private final int id;
	private final String name;
	private final int size;
	
	public File(int id, String name, int size) {
		this.id = id;
		this.name = name;
		this.size = size;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("id", id);
		data.addProperty("name", name);
		data.addProperty("size", size);
		return data;
	}
	
}
