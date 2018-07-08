package com.x.nio;

import java.nio.ByteBuffer;

public class TestBuffer {
	public static void main(String[] args) {
		
		String str = "abc";
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		buffer.put(str.getBytes());
		
		System.out.println(buffer.position());
		// �л�Ϊ������
		System.out.println("�л�Ϊ��ģʽ----------------------");
		buffer.flip();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		// ������
		byte[] b = new byte[buffer.limit()];
		buffer.get(b);
		System.out.println("------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		System.out.println(new String(b));
		
		System.out.println("�ض�--------------------");
		buffer.rewind();//�ض�
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		
		
		System.out.println("----------------duplicate");
		ByteBuffer duplicate = buffer.duplicate();


		System.out.println("-------------�����������٣�����һ�������������");
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
