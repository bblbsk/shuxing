package com.x.threads;


/**
  * @ClassName: ThreadDeathDemo
  * @Description: û�ҵ��߳̽���ʱ֪ͨ�ķ���
  * @author-csx
  * @date 2018��5��13�� ����5:00:42
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
