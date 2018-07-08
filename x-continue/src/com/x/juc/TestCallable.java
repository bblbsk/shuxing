package com.x.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

	public static void main(String[] args) {
		CallableDemo c = new CallableDemo();

		FutureTask ft = new FutureTask(c);

		new Thread(ft).start();
		
		try {
			// 会一直阻塞，直到ft的执行完毕，所有Callable也可以实现闭锁的功能
			System.out.println(ft.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	static class CallableDemo implements Callable{

		@Override
		public Integer call() throws Exception {
			return 123;
		}
		
	}
}
