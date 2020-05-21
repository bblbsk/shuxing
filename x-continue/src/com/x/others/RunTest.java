package com.x.others;

public class RunTest {
	public static void main(String args[]) {

		Thread t = new Thread() {

			public void run() {
				pong();
			}
		};

		t.run();
		System.out.print("ping");

	}

	static void pong() {

		System.out.print("pong");

	}
}
