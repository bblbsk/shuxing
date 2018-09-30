package com.x.parent;

public class Parent {

	static {
		System.out.println("父类的静态代码块");
	}
	
	public Parent(){
		System.out.println("父类的构造方法");
	}
	
	private static String gender = getGender();
	
	private String name = getName();
	
	private String getName(){
		System.out.println("父类的成部变量初始化");
		return "parent";
	}
	
	private static String getGender(){
		System.out.println("父类的静态变量初始化");
		return "parent";
	}
	
	protected void testMethod(){
		System.out.println("父类的成员方法");
	}
}
