package com.x.others;

public class RepeatTest {
	public static void main(String[] args) {
		for (int i = 0, k = 0; i < 10 && k<5; i++,k++) {
			System.out.println("i:" + i + " k:" + k);
		}
		
		String s = new String("1");
		System.out.println(s.hashCode());
		String s1 = s;
		System.out.println(s1.hashCode());
		s = "2";
		System.out.println(s1);
		System.out.println(s.hashCode());
		System.out.println(s1.hashCode());
		s = "1";
		System.out.println(s.hashCode());
	}
}
