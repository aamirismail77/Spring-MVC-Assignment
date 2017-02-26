package com.bellinf.batch4.mvc.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class DateAndTime {
	
	public String currentdatetime() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd  HH:mm:ss");
		String sdt = df.format(new Date(System.currentTimeMillis()));
		return sdt;

	}
}

