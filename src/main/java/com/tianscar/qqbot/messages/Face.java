package com.tianscar.qqbot.messages;
//表情消息
import com.google.gson.JsonObject;

public class Face extends Message {

	public static final String TYPE = "Face";

	@Override
	public String type() {
		return TYPE;
	}

	private final int faceId;//表情编号
	private final String name;//表情拼音
	
	public Face(int faceId, String name) {
		this.faceId = faceId;
		this.name = name;
	}
	
	public int getFaceId(){
		return faceId;
	}
	
	public String getName(){
		return name;
	}
	
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("faceId", faceId);
		data.addProperty("name", name);
		return data;
	}
}