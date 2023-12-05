package com.data.store;

import java.util.LinkedHashMap;

public class UserData {
	private static LinkedHashMap<String, Object>  dataStore = new LinkedHashMap<>();
	
	public static void setUserData(String key, Object value) {
		dataStore.put(key, value);
		
	}
	public static Object getUserData(String key) {
		return dataStore.get(key);
	}


}
