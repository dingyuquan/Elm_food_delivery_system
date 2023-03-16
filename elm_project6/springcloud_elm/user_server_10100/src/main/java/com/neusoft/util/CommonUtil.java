package com.neusoft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtil {
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	public static String getNextData(int time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, time);
		return sdf.format(calendar.getTime());
	}
}
