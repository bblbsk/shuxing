package com.x.runnable;

import java.util.HashMap;

public class RunnableShareTest {

	private static int ticket = 5;
	
	static class TicketShare implements Runnable{

		@Override
		public void run() {
			synchronized (this) {
				while(ticket > 0){
					System.out.println("ʣ�ࣺ" + ticket);
					ticket --;
				}
			}
		}
	}
	
	private static HashMap<Integer,String> map = new HashMap<Integer,String>(2,0.75f);  
	public static void main(String[] args) {
//		TicketShare s = new TicketShare();
//		new Thread(s).start();
//		new Thread(s).start();
//		new Thread(s).start();
		
        map.put(5, "C");  
   	 
        new Thread("Thread1") {  
            public void run() {  
                map.put(7, "B");  
                System.out.println(map);  
            };  
        }.start();  
        new Thread("Thread2") {  
            public void run() {  
                map.put(3, "A");  
                System.out.println(map);  
            };  
        }.start(); 
	}

}
