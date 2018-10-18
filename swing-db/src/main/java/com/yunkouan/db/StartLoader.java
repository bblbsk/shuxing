package com.yunkouan.db;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.yunkouan.db.jframe.DBJFrame;

public class StartLoader {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
					DBJFrame frame = new DBJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
