package com.sas.util;

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
	 * @param accessLog
	 * @return
	 * @author Dinesh
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
}
