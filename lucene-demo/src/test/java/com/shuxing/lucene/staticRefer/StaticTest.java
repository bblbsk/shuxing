package com.shuxing.lucene.staticRefer;

public class StaticTest {
	
	public String print(StringFunc sf, String s) {
		return sf.func(s);
	}

	private static String printMessage(String content){
		System.out.println(content);
		return content;
	}

	public static void main(String[] args) {
		
		new StaticTest().print(StaticTest::printMessage, "123");
//		print(StaticTest::printMessage, "123");
	}
	

}
