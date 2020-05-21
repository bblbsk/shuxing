package com.x.threads;

import java.util.Map;

public class ThreadTest extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.currentThread().sleep(100);
				System.out.println("thread:" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
