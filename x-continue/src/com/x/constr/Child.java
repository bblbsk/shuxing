package com.x.constr;

public class Child extends Parent{

	static {
		System.out.println("Chiled stataic");
	}
	
	{
		System.out.println("Child construct");
	}
	
	private int i = getIValue();
	
	public Child(){
		System.out.println("Child..........");
	}
	
	public static void main(String[] args) {
		
		Child c = new Child();
		System.out.println("------" + c.i);
	}

	private int getIValue() {
		System.out.println(4);
		return 4;
	}
	
	
}
