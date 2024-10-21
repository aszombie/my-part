package com.finalproject.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatetimeConverter {

	public static String toString(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				result = new SimpleDateFormat(format).format(date);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}

	public static Date parse(String datetime, String format) {
		Date result = null;
		System.out.println(datetime);
		System.out.println(format);
		try {
			result = new SimpleDateFormat(format).parse(datetime);
		} catch (Exception e) {
			result = new Date();
			//e.printStackTrace();

		}
		return result;
	}
}
