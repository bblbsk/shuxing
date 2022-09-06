package com.ygsoft.tojson.jframe.adapter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
  * @ClassName: FrameWindowAdapter
  * @Description: Frame窗口监听
  * @author: liyongmei
  * @date 2022年6月11日 下午12:30:16
  *
  */
public class JFrameWindowAdapter extends WindowAdapter{

	@Override
	public void windowClosing(WindowEvent we) {
		int result = JOptionPane.showConfirmDialog(null, "确定关闭?","关闭确认", JOptionPane.OK_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
