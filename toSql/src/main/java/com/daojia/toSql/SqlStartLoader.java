package com.daojia.toSql;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.daojia.toSql.jframe.SqlJFrame;

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
