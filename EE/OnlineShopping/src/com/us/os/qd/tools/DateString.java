package com.us.os.qd.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateString {
	public static String getNowString() {
		return new SimpleDateFormat("yyyyMMddHHmmssssss").format(new Date());
	}
}
