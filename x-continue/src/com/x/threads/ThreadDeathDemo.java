package com.x.threads;


/**
  * @ClassName: ThreadDeathDemo
  * @Description: 没找到线程结束时通知的方法
  * @author-csx
  * @date 2018年5月13日 下午5:00:42
  *
  */
public class ThreadDeathDemo {

	
	public static void main(String[] args) {
		
		
	}
	
	static class A extends Thread {

		@Override
		public void run() {
			System.out.println("starting..........");
			System.out.println("running..........");
			System.out.println("end..........");
		}
	} 
}
