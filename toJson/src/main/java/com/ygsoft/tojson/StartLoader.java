package com.ygsoft.tojson;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.ygsoft.tojson.jframe.SqlJFrame;


/**
 * @Title: SqlStartLoader.java
 * @Description:启动类
 * @Author：liyongmei
 * @CreateTime：2022年6月10日上午9:23:33
 * @version v1.0
 */
public class StartLoader {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
					SqlJFrame frame = new SqlJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
