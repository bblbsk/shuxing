package com.x.threads;

import java.util.concurrent.atomic.AtomicInteger;


public class Test2 {

	private static AtomicInteger count = new AtomicInteger();

	private void add10K() {

		int idx = 0;

		while (idx++ < 10000) {

			count.getAndAdd(1);

		}

	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(calc().intValue());
	}
	
	public static AtomicInteger calc() throws InterruptedException {

		final Test2 test = new Test2();

		// 创建两个线程，执行 add() 操作
		Thread th1 = new Thread(() -> {
			test.add10K();
		});

		Thread th2 = new Thread(() -> {
			test.add10K();
		});

		// 启动两个线程

		th1.start();

		th2.start();

		// 等待两个线程执行结束
		System.out.println("-----------: " + count);
		th1.join();
		System.out.println("-----------: " + count);
		th2.join();
		System.out.println("-----------: " + count);

		return count;
	}

}