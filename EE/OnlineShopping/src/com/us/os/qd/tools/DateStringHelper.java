package com.us.os.qd.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringHelper {
	public static String getNowDateString() {
		return new SimpleDateFormat("yyyyMMddhhmmssssss").format(new Date());
	}
}
