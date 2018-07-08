package com.x.others;

public class StringDemo {

	public static final String s1;
	public static final String s2;
	public static final String demo1;
	public static final String demo2;
	public static final String demo3;
	
	static{
		s1="abc";
		s2="def";
		demo1="a";
		demo2="b";
		demo3="c";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = new String("abc");
		System.out.println(s.hashCode());
		Character a = 'a';
		Character b = 'b';
		Character c = 'c';
		int i = a;
		System.out.println(i);
		System.out.println(new Integer(b));
		System.out.println(new Integer(c));
		System.out.println(97*31*31 + 98*31 + 99);
		
		System.out.println("---------------------------");
		
		String s3=s1+s2;
		String s4="abcdef";
		System.out.println(s3==s4);
		
		System.out.println("---------------------------");
		
		String sa = "a";
		String sb = "b";
		sb = sa + sb;
		String ab = "a" + "b";
		String ss = "ab";
		System.out.println(ab == sb);
		System.out.println(ab == ss);
		
		System.out.println(String.class.getSimpleName());
		
		String k0="kvill";
		String k1="kvill";
		String k2="kv" + "ill";
		System.out.println( k0==k1 );
		System.out.println( k0==k2 );
		
	}

}
