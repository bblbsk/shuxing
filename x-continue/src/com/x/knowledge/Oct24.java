package com.x.knowledge;

import java.util.ArrayList;

public class Oct24 {

	public static void main(String[] args) {
		// 1数字缓冲区
		System.out.println("-------------------1-----------------");
		Integer a = 1;
		Integer b = 1;
		Integer c = 129;
		Integer d = 129;
		System.out.println(a == b);
		System.out.println(c == d);
		// 2自动拆箱装箱
		System.out.println("-------------------2-----------------");
		int i1 = 1;
		Integer i2 = new Integer(1);
		System.out.println(i1 == i2);
		// 3奇偶判断
		System.out.println("--------------------3----------------");
		System.out.println((-1 % 2) == 1);	// 期望true ,实际false
		System.out.println((-1 % 2) == 0);	// 正确false
		// 4集合框架的clear方法，不是将重建一个list，而是遍历将每一个对象设置为null, 不使用新建方式，为了尽量避免堆内存溢出问题
		System.out.println("-------------------4----------------");
		// 5 arraylist trimToSize
		System.out.println("--------------------5----------------");
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		l2.add(1);
		System.out.println(l2.equals(l));
		l2.add(null);
		System.out.println(l2.equals(l));
//		l2.trimToSize();
//		System.out.println(l2.equals(l));
	}
}
