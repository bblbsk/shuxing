package com.x.lang;

public class StringDemo {

	private final int a;
	
//	static {
//		a = 1;
//	}
	
	public StringDemo() {
		super();
		this.a = 1;
	}

	public static void main(String[] args) {
		
//		new StringDemo().aaa();
		
		String s3 = new String("1") + new String("1");
		s3.intern();
		String s4 = "11";
		System.out.println(s3 == s4);
		
		Student st = new Student(s3);
		change(s3, st);
		
		String s2 = "1";
		String s = new String("1");
//		s.intern();
		System.out.println(s == s2);

		
	}
	
	private void aaa(){
		System.out.println("a:" + a);
	}
	
	public static void change(String c, Student s) {
		c = new String("c");
		s.name = "name";
	}

	static class Student{
		
		public Student(String name) {
			super();
			this.name = name;
		}

		public String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
