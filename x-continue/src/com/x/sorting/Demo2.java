package com.x.sorting;

import java.math.BigDecimal;

public class Demo2 {

	public static void main(String[] args) {
		
//		Double d = -1.2d;
//		System.out.println(d<0);
		
//		System.out.println("1".equals(1));
		String s= "0.00";
		System.out.println(new BigDecimal(s).compareTo(BigDecimal.ZERO) == 0);
		s = "0.01";
		System.out.println(new BigDecimal(s).compareTo(BigDecimal.ZERO) > 0);
	}
}
