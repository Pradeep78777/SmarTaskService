package com.sas.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheController {
	private static Map<String, String> user256Cache = new HashMap<>();
	private static Map<String, String> userDeviceIp = new HashMap<>();

	public static Map<String, String> getUser256Cache() {
		return user256Cache;
	}

	public static void setUser256Cache(Map<String, String> user256Cache) {
		CacheController.user256Cache = user256Cache;
	}

	public static Map<String, String> getUserDeviceIp() {
		return userDeviceIp;
	}

	public static void setUserDeviceIp(Map<String, String> userDeviceIp) {
		CacheController.userDeviceIp = userDeviceIp;
	}

}
