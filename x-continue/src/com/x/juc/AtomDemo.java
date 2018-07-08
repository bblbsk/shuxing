package com.x.juc;

public class AtomDemo {

	public static void main(String[] args) {
		int i = 10;
		i = ++i;
		System.out.println(i);
		
		AtomRunnale ar = new AtomRunnale();
		for (int j = 0; j < 10; j++) {
			 new Thread(ar).start();
		}
	}
	
	
	static class AtomRunnale implements Runnable {

		private int i = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(add());
		}
		
		private int add(){
			return ++i;
		}
		
	}
	
}
