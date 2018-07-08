package com.x.others;

import java.util.HashMap;
import java.util.Map;


public class EqualsTest {
	public static void main(String[] args) {
		intTest();
		stringTest();
//		System.out.println("1".hashCode());
		
		Map<StringBuffer,String> m = new HashMap<StringBuffer,String>();
		StringBuffer sb1 = new StringBuffer("1");
		StringBuffer sb2 = new StringBuffer("123");
		m.put(sb1, "sb1");
		m.put(sb2, "sb2");
		sb1 = sb2;
		m.put(sb1, "sb1");
		System.out.println(m);
	}
	
	public static void intTest(){
		int a = 1;
		int b = a;
		a = 2;
		System.out.println(b);
	}
	
	public static void stringTest(){
		String s1 = new String("s");
		String s2 = s1;
//		s1 = "s1";
		s1 = new String("s1");
		System.out.println(s2);
	}
}
