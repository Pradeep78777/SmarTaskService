/**
 * 
 */
package com.sas.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotAuthorizedException;

import com.sas.Cache.CacheController;

/**
 * @author Pradeep K
 *
 */
public class TokenAuthModule {

	public static Map<String, TokenObject> tokenKeyCache = new HashMap<>();
	public static Map<String, String> userIdKeyCache = new HashMap<>();
	public static Map<String, Long> requestCountMap = new HashMap<>();

	public static Map<String, TokenObject> getTokenKeyCache() {
		return tokenKeyCache;
	}

	public static void setTokenKeyCache(Map<String, TokenObject> tokenKeyCache) {
		TokenAuthModule.tokenKeyCache = tokenKeyCache;
	}

	public static Map<String, String> getUserIdKeyCache() {
		return userIdKeyCache;
	}

	public static void setUserIdKeyCache(Map<String, String> userIdKeyCache) {
		TokenAuthModule.userIdKeyCache = userIdKeyCache;
	}

	public static Map<String, Long> getRequestCountMap() {
		return requestCountMap;
	}

	public static void setRequestCountMap(Map<String, Long> requestCountMap) {
		TokenAuthModule.requestCountMap = requestCountMap;
	}

	public static boolean storeTokenCache(String token, String custId) {
		final String storeToken = userIdKeyCache.get(custId);
		final TokenObject user = tokenKeyCache.get(storeToken);

		/*
		 * User token already exists but user logged in again. This will
		 * invalidate the existing login from any other device Comment out the
		 * following code part IF MULTIPLE USER LOGINS ARE ALLOWED
		 */
		// System.out.println(tokenKeyCache.get(storeToken));
		// if (storeToken != null &&
		// tokenKeyCache.get(storeToken).getUser().equalsIgnoreCase(custId)) {
		// tokenKeyCache.remove(storeToken);
		// }
		if (storeToken != null && user != null) {
			if (user.getUser().equalsIgnoreCase(custId)) {
				tokenKeyCache.remove(storeToken);
			}
		}

		/* Store the token with object as value. Set expiry till midnight */
		final TokenObject tokenObj = new TokenObject();
		tokenObj.setExpiry(CommonMethod.getExpiryInSeconds() * 1000 + Calendar.getInstance().getTimeInMillis());
		tokenObj.setUser(custId);
		tokenKeyCache.put(token, tokenObj);
		userIdKeyCache.put(custId, token);

		return true;
	}

	/**
	 * Method validates the provided token and returns the user id for
	 * processing
	 * 
	 * @param pToken
	 */
	public static void validateToken(String pToken, String userId) throws Exception {
		TokenObject tokenObj = tokenKeyCache.get(pToken);
		String userToken = CacheController.getUser256Cache().get(userId);
		// System.out.println(tokenObj.getUser().equalsIgnoreCase(userId));
		if (!pToken.equalsIgnoreCase(userToken)
				|| (tokenObj == null || tokenObj.getExpiry() < Calendar.getInstance().getTimeInMillis()
						|| !tokenObj.getUser().equalsIgnoreCase(userId))) {
			throw new NotAuthorizedException("Not Authorized");
		} else {
			// if () {
			// throw new NotAuthorizedException("Not Authorized");
			// }
			/* Set expiry till midnight */
			tokenObj.setExpiry(CommonMethod.getExpiryInSeconds() * 1000 + Calendar.getInstance().getTimeInMillis());
			tokenKeyCache.put(pToken, tokenObj);
		}
	}
}
