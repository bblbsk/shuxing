package com.shuxing.lucene;

public class StaticTest {
	
	public static String print(StringFunc sf, String s) {
		return sf.func(s);
	}

	private static String printMessage(String content){
		System.out.println(content);
		return content;
	}

	public static void main(String[] args) {
		print(StaticTest::printMessage, "123");
	}
	

}
