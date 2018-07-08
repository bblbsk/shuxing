package com.x.others;

public class StaticCodeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A.s="s";
		System.out.println("--");
		A a1 = new A();
		a1.print();
		A a2 = new A();
		a2.print();
//		A.print();
	}
	
	static class A{
		static {
			System.out.println("Static");
		}
		
		{
			System.out.println("common");
		}
		
		static String s = "";
		
		static void print(){
			System.out.println("A");
		}
	}

}
