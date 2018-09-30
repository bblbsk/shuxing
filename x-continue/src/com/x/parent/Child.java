package com.x.parent;

public class Child extends Parent{

	static {
		System.out.println("子类的静态代码块");
	}
	
	public Child(){
		System.out.println("子类的构造方法");
	}
	
	// 类变量
	private static String gender = getGender();
	
	// 成员变量
	private String name = getName();
	
	protected void testMethod(){
		System.out.println("子类的成员方法");
	}
	
	private static String getGender(){
		System.out.println("子类的静态变量初始化");
		return "child";
	}
	
	private String getName(){
		System.out.println("子类的成部变量初始化");
		return "child";
	}
	
	public static void main(String[] args) {
		new Child().testMethod();
	}
}
