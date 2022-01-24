package com.sas.util;

import java.util.Calendar;

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
}
