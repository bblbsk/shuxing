package com.x.runnable;

public class WaitTest {
	public static void main(String[] args) {
		
		
		WaitTest wt = new WaitTest();
		synchronized (wt) {
				try {
					wt.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
}
