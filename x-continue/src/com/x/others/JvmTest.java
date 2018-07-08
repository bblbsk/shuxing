package com.x.others;

public class JvmTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			
			Thread.currentThread().sleep(5);
			String s = String.valueOf("current-value:" + i);
			System.out.println(s);
		}

	}

}
