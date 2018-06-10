package com.example.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test {

	public static void main(String[] args) {
		String test = "张3";
		String encode = new BASE64Encoder().encode(test.getBytes());
		System.out.println("加密后:" + encode);
		
		try {
			byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encode);
			System.out.println("解密后:" + new String(decodeBuffer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
