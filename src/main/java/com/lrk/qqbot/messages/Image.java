package com.lrk.qqbot.messages;

import com.google.gson.JsonObject;

/**
 * 消息中所携带的图片
 */
public class Image extends Message {

	public static final String TYPE = "Image";

	@Override
	public String type() {
		return TYPE;
	}

	private final String imageId;//图片ID
	private final String url;//图片URL
	private final String path;//图片本地路径
	private final String base64;//图片的Base64编码
	
	public Image(String imageId, String url, String path, String base64) {
		this.imageId = imageId;
		this.url = url;
		this.path = path;
		this.base64 = base64;
	}

	public String getImageId(){
		return imageId;
	}
	public String getUrl(){
		return url;
	}
	public String getPath(){
		return path;
	}
	public String getBase64(){
		return base64;
	}

	public JsonObject toJsonObject() {
		JsonObject data = new JsonObject();
		data.addProperty("type", TYPE);
		data.addProperty("imageId", imageId);
		data.addProperty("url", url);
		data.addProperty("path", path);
		data.addProperty("base64", base64);
		return data;
	}

}
