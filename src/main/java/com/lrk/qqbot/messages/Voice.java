package com.lrk.qqbot.messages;

import com.google.gson.JsonObject;

public class Voice extends Message {

	public static final String TYPE = "Voice";

	@Override
	public String type() {
		return TYPE;
	}

	private final String voiceId;//语音消息识别id
	private final String url;//语音URL
	private final String path;//语音本地路径
	private final String base64;//语音的Base64编码
	private final long length;//语音长度(发送时可以不写)

	public Voice(String voiceId, String url, String path, String base64, long length) {
		this.voiceId = voiceId;
		this.url = url;
		this.path = path;
		this.base64 = base64;
		this.length = length;
	}
	
	public Voice(String voiceId, String url, String path, String base64) {
		this(voiceId, url, path, base64, 0);
	}

	public String getVoiceId() {
		return voiceId;
	}

	public String getUrl() {
		return url;
	}

	public String getPath() {
		return path;
	}

	public String getBase64() {
		return base64;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("voiceId", voiceId);
		data.addProperty("url", url);
		data.addProperty("path", path);
		data.addProperty("base64", base64);
		data.addProperty("length", length);
		return data;
	}

}
