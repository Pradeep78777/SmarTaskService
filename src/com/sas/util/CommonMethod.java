package com.sas.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.dto.AccessLogDTO;
import com.sas.service.AccessLogService;

public class CommonMethod {
	/**
	 * to get seconds between current time and midnight
	 * 
	 * @author GOWRI SANKAR R
	 * @return
	 */
	public static int getExpiryInSeconds() {
		Calendar now = Calendar.getInstance();
		Calendar midnight = Calendar.getInstance();
		midnight.add(Calendar.DATE, 1);
		midnight.set(Calendar.HOUR_OF_DAY, 0);
		midnight.set(Calendar.MINUTE, 0);
		midnight.set(Calendar.SECOND, 0);
		midnight.set(Calendar.MILLISECOND, 0);
		long millisecond = midnight.getTime().getTime() - now.getTime().getTime();
		int second = (int) (millisecond / 1000);
		return second;
	}

	/**
	 * Method to insert access log input details
	 * 
	 * @author GOWRI SANKAR R
	 * @param accessLog
	 * @return
	 */
	public static void inputAccessLogDetails(AccessLogDTO accessLogDto, Object pObj, String userId) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		executor.execute(new Runnable() {
			@Override
			public void run() {
				AccessLogDTO accessLog = new AccessLogDTO();
				ObjectMapper mapper = new ObjectMapper();
				String convert = "";
				try {
					convert = mapper.writeValueAsString(pObj);
					accessLog.setInput(convert);
					accessLog.setUrl(accessLogDto.getUrl());
					accessLog.setDeviceIp(accessLogDto.getDeviceIp());
					accessLog.setUserId(userId);
					accessLog.setCreatedOn(accessLogDto.getCreatedOn());
					accessLog.setUserAgent(accessLogDto.getUserAgent());
					accessLog.setDomain(accessLogDto.getDomain());
					AccessLogService logService = new AccessLogService();
					logService.insertAccessLogInputRecords(accessLog);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					executor.shutdown();
				}
			}
		});
	}

	/**
	 * Encrypt the String to MD5
	 * 
	 * @author GOWRI SANKAR R
	 * @param passkey
	 * @return
	 */
	public static String PasswordEncryption(String passkey) {
		String MD5pass = "";
		final byte[] defaultBytes = passkey.getBytes();
		try {
			final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
			md5MsgDigest.reset();
			md5MsgDigest.update(defaultBytes);
			final byte messageDigest[] = md5MsgDigest.digest();

			final StringBuffer hexString = new StringBuffer();
			for (final byte element : messageDigest) {
				final String hex = Integer.toHexString(0xFF & element);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			passkey = hexString + "";
		} catch (final NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		MD5pass = passkey;
		return MD5pass;
	}

	/**
	 * Method to generate the random 256 key
	 * 
	 * @author GOWRI SANKAR R
	 * @return
	 */
	public static String random256Key() {
		int size = 256;
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		String num = sb.toString();
		return num;
	}
}
