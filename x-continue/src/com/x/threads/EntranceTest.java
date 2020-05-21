package com.x.threads;

public class EntranceTest {

	public static void main(String[] args) throws InterruptedException{
		System.out.println("1");
		
		Thread t = new ThreadTest();
//		try {
//			t.start();
//			t.join();
//		} catch (InterruptedException e) {
//			throw e;
//		}
		
		System.out.println("2");
		
		char[] c = new char[]{'c'};
		int i = c[0];
		System.out.println(i);
		
	}
}
