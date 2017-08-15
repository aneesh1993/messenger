package com.chatterbox.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.chatterbox.messenger.model.Message;
import com.chatterbox.messenger.model.Profile;

public class Database {
	
	private static Map<Long, Message> messageMap = new HashMap<>();
	private static Map<String, Profile> profileMap = new HashMap<>();
	
	public static Map<Long, Message> getMessages(){
		return messageMap;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profileMap;
	}
}
