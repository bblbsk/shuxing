package com.x.dump;

import java.util.Random;

public class DumpTest {

	public static void main(String[] args){
		long maxMemory = Runtime.getRuntime().maxMemory() ;//���� Java �������ͼʹ�õ�����ڴ�����
		long totalMemory = Runtime.getRuntime().totalMemory() ;//���� Java ������е��ڴ�������
		System.out.println("MAX_MEMORY = " + maxMemory + "���ֽڣ���" + (maxMemory / (double)1024 / 1024) + "MB");
		System.out.println("TOTAL_MEMORY = " + totalMemory + "���ֽڣ���" + (totalMemory / (double)1024 / 1024) + "MB");
		
		String str = "www.atguigu.com" ;
		while(true) 
		{
			str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("1");
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					System.out.println("2");
////					System.out.println(str);
//				}
//			}).start();
		}
		

		
	
	}

}
