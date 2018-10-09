package com.x.puzzle.longdivision;


/**
 * @Description:长整除
 * @Author：daojia
 * @CreateTime：2018年10月9日下午2:43:55
 * @version v1.0
 */
public class LongDivisionDemo {

	public static void main(String[] args) {
		
		/***
		 * Java不具有目标类型确定, int类型值溢出后，进行计算会出错, 
		 * 因此需要在计算之前对, 对计算因子的类型进行
		 */
		
		long a = 24 * 60 * 60 * 1000 * 24;
		long b = 24 * 60 * 60 * 1000;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(a / b);
		
		long c = 24l * 60 * 60 * 1000 * 1000;
		long d = 24l * 60 * 60 * 1000;
	
		System.out.println(c / d);
	}

}
