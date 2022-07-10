package com.tianscar.qqbot.messages;
//应用消息(轻视频等)
import com.google.gson.JsonObject;

public class App extends Message {

	public static final String TYPE = "App";

	@Override
	public String type() {
		return TYPE;
	}

	private final String content;
	private final JsonObject appContent;

	public App(String content) {
		this.content = content;
		this.appContent = new JsonObject();
		appContent.addProperty("content", content);
		System.out.println(appContent);
	}
	
	public String getContent() {
		return content;
	}
	
	public JsonObject getAppContent() {
	    return appContent;
	}
	
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("content", content);
		return data;
	}

}
