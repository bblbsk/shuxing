package com.x.others;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderTest {

	public static void main(String[] args) {
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		Calendar c= Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		
		System.out.println(sdf.format(c.getTime()));
		
	}
}
