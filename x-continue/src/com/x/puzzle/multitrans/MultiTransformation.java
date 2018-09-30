package com.x.puzzle.multitrans;

/**
 * @Description:多重转换
 * @Author：daojia
 * @CreateTime：2018年9月25日下午8:20:11
 * @version v1.0
 */
public class MultiTransformation {
	public static void main(String[] args) {
		System.out.println((int) (char) (byte) 1);
		System.out.println((byte) -1);
		System.out.println((char) (byte) -1);
		System.out.println((int) (char) (byte) -1);
	}
}
