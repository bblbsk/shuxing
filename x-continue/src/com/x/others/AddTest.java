package com.x.others;


import java.util.ArrayList;
import java.util.List;

public class AddTest {
	
	public static void main(String[] args) {
		float f1 = 0.1f;
		float f2 = 0.2f;
		float f3 = 0.3f;
		System.out.println((1 - 0.9f) == f1);
		System.out.println((f1 + f2) == 0.3f);
		System.out.println((f2 + f2) == 0.4);
		System.out.println((f2 + f3) == 0.5f);

		for (int i = 0; i < 10; i++) {
			System.out.println(1 - 0.1f * i);
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
	}
}
