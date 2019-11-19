package com.x.elm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Demo {

	public static void main(String[] args) {
		
		int i = 3;
		if (i>0) {
			System.out.println(1);
		} else if(i>1){
			System.out.println(2);
		} else{
			System.out.println(3);
		}
		
		System.out.println(LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.of("+8")));
		
		Integer a = new Integer(1);
		Integer c = new Integer(1);
		int b = 1;
		System.out.println(a == c);
		System.out.println(a == b);
		
		
	}
}
