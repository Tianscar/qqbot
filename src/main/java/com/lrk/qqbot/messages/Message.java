package com.lrk.qqbot.messages;
import com.google.gson.JsonObject;

public abstract class Message {

	@Override
	public String toString() {
		return toJsonObject().toString();
	}
	
	public abstract JsonObject toJsonObject();

	public abstract String type();

}
