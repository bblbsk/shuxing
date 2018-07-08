package com.x.constr;

public class Parent {

	static {
		System.out.println("parent static");
	}
	
	{
		System.out.println("parent construct");
	}
	
	public Parent(){
		System.out.println("Parent ....");
	}
}
