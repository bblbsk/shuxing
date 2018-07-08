package com.x.nio;

import java.nio.ByteBuffer;

public class TestBuffer {
	public static void main(String[] args) {
		
		String str = "abc";
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		buffer.put(str.getBytes());
		
		System.out.println(buffer.position());
		// 切换为读数据
		System.out.println("切换为读模式----------------------");
		buffer.flip();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		// 读数据
		byte[] b = new byte[buffer.limit()];
		buffer.get(b);
		System.out.println("------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		System.out.println(new String(b));
		
		System.out.println("重读--------------------");
		buffer.rewind();//重读
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		
		System.out.println("----------------duplicate");
		ByteBuffer duplicate = buffer.duplicate();


		System.out.println("-------------缓冲区数据少，读到一个大的数组的情况");
		byte[] big = new byte[10];
		buffer.get(big, 0, buffer.limit());
		System.out.println(new String(big));
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		System.out.println(duplicate.position());
		System.out.println(duplicate.limit());
		
		buffer.allocateDirect(1024);
	
	}
}
