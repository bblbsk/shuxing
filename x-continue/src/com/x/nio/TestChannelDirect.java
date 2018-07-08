package com.x.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannelDirect {

	public static void main(String[] args) throws IOException {
		
        FileChannel inChannel2 = FileChannel.open(Paths.get("C:\\Users\\csx\\Desktop\\123.jpg"), StandardOpenOption.READ);
        FileChannel outChannel2 = FileChannel.open(Paths.get("C:\\Users\\csx\\Desktop\\234.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //�ڴ�ӳ���ļ�
        MappedByteBuffer inMappedBuf = inChannel2.map(FileChannel.MapMode.READ_ONLY, 0, inChannel2.size());
        MappedByteBuffer outMappedBuf = outChannel2.map(FileChannel.MapMode.READ_WRITE, 0, inChannel2.size());

        //ֱ�ӶԻ������������ݶ�д����
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel2.close();
        outChannel2.close();

        /*
         * ͨ��֮������ݴ��䣨ֱ�ӻ�������
         */
        FileChannel inChannel3 = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel3 = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel3.transferTo(0, inChannel3.size(), outChannel3);
        //�ȼ���
//        outChannel3.transferFrom(inChannel3, 0, inChannel3.size());

        inChannel3.close();
        outChannel3.close();
	}

}
