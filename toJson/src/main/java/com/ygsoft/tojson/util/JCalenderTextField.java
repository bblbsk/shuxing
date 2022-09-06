package com.ygsoft.tojson.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;


/**
  * @ClassName: JCalenderTextField
  * @Description: 格式化传入的时间值
  * @author-csx
  * @date 2022年6月8日 下午10:11:28
  *
  */
@SuppressWarnings("serial")
public class JCalenderTextField extends JTextField implements Observer {
	
	public void update(Observable o, Object arg) {
		Calendar calendar = (Calendar) arg;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		setText(sdf.format(calendar.getTime()));
	}
}