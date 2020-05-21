package com.x.others;

public class PrecisionTest {
public static void main(String[] args) {
	byte a = 127;
	byte b = 127;
	
	a += b;
	System.out.println(a);
	int i = 0x7fffffff;
	System.out.println(i + 1);
}
}
