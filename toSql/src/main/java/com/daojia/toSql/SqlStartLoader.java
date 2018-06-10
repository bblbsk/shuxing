package com.daojia.toSql;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.daojia.toSql.jframe.SqlJFrame;


/**
 * @Title: SqlStartLoader.java
 * @Description:启动类
 * @Author：daojia
 * @CreateTime：2018年6月10日上午9:23:33
 * @version v1.0
 */
public class SqlStartLoader {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
