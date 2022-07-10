package com.tianscar.qqbot.messages;
//@消息
import com.google.gson.JsonObject;

public class At extends Message {

	public static final String TYPE = "At";

	@Override
	public String type() {
		return TYPE;
	}

	private final long target; //要@的目标
	private final String display; //@的文本(名字),做为发送消息时无效
	
	public At (long target, String display) {
		this.target = target;
		this.display = display;
	}
	
	public long getTarget(){
		return target;
	}
	
	public String getDisplay(){
		return display;
	}
	
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("target",target);
		data.addProperty("display",display);
		return data;
	}
	
}