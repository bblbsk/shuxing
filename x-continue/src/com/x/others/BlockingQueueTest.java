package com.x.others;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueTest {

	public static void main(String[] args) {
		
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(5, true);
		for (int i = 0; i < 15; i++) {
			try {
				queue.offer(i);
			} catch (Exception e) {
				if (e instanceof IllegalStateException) {
					int r = queue.remove();
					System.out.println(r);
				}
				// TODO: handle exception
			}
		}
		
		AtomicInteger ai = new AtomicInteger();
		System.out.println(ai.intValue());
		System.out.println(ai.compareAndSet(0, 5));
	}
}
