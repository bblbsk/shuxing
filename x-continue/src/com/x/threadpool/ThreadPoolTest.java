package com.x.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		});
		
	}
}
