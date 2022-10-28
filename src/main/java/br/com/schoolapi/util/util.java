package br.com.schoolapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class util {

	static String PATTERN = "MM/dd/yyyy HH:mm:ss";

	public static String stringDate(Date date) {
		DateFormat df = new SimpleDateFormat(PATTERN);
		Date today = Calendar.getInstance().getTime();
		return df.format(today);
	}

}
