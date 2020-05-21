package com.x.others;

public class EqualsDemo {
	public static void main(String[] args) {
		
		System.out.println("--------------int|Integer---------");
		int i1 = 10;
		Integer i2 = 10;
		Integer i3 = new Integer(10);
		Integer i4 = new Integer(10);
		Integer i5 = 600;
		Integer i6 = 600;
		Integer i7 = 100;
		Integer i8 = 100;
		int i9 = 500;
		int i10 = 500;
		
		System.out.println(i1 == i2);
		System.out.println(i1 == i3);
		System.out.println(i2 == i3);
		System.out.println(i3 == i4);
		System.out.println(i5 == i6);
		System.out.println(i7 == i8);
		System.out.println(i5.equals(i6));
		System.out.println(i9 == i10);
		
		System.out.println("----------double-----");
		Double d1 = 1.2;
		Double d2 = 1.2;
		double d3 = 1.2;
		System.out.println(d1 == d2);
		System.out.println(d1 == d3);
		
		System.out.println("----------String-----");
		String s1 = "1";
		String s2 = "1";
		String s3 = new String("1");
		String s4 = new StringBuffer("1").toString();
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s4);
	}
}
