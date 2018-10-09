package com.x.puzzle.odevity;


/**
 * @Description:奇偶性
 * @Author：daojia
 * @CreateTime：2018年10月9日下午2:48:06
 * @version v1.0
 */
public class OdevityDemo {

	private static int compare_error = 1;
	private static int compare_correct = 0;
	
	public static void main(String[] args) {
		
		int a = -1;
		int b = -2;
		int c = 1;
		int d = 2;
		
		// 四分之一的情况会判断出错
		System.out.println(a % 2 == compare_error);
		System.out.println(b % 2 == compare_error);
		System.out.println(c % 2 == compare_error);
		System.out.println(d % 2 == compare_error);
		System.out.println("---------------");
		System.out.println(a % 2 != compare_correct);
		System.out.println(b % 2 != compare_correct);
		System.out.println(c % 2 != compare_correct);
		System.out.println(d % 2 != compare_correct);
	}
}
