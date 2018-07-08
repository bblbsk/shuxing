package com.x.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestChannel {

	public static void main(String[] args) throws IOException {
		/*
		 * 1.利用通道完成文件的复制（非直接缓冲区）
		 */
		FileInputStream fis = new FileInputStream("C:\\Users\\csx\\Desktop\\123.jpg");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\csx\\Desktop\\234.jpg");

		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}

		if (outChannel != null) {
			try {
				outChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (inChannel != null) {
			try {
				inChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
