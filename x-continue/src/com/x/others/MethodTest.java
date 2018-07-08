package com.x.others;

import java.lang.reflect.Method;

public class MethodTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = MethodTest.class.getMethod("addInt",Integer.class);
		
		System.out.println(cal(method));
		
	}
	
	public static int cal(Method add){
		
		try {
			return (int) add.invoke(new MethodTest() , 5);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int addInt(Integer a){
		return 5 + a;
	}
}
