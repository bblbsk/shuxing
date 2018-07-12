package com.shuxing.serializable.externalizable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExternalizableDemo {

	private UserExternlizableVO externlizableVO;
	private File file;

	@Before
	public void before() {
		externlizableVO = new UserExternlizableVO("daojia", 20, 170);
		// 在桌面已类名新建文件
		File deskTop = FileSystemView.getFileSystemView().getHomeDirectory();
		file = new File(deskTop, this.getClass().getSimpleName());
	}

	@Test
	public void testExternlizable() throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		System.out.println(externlizableVO);
		oos.writeObject(externlizableVO);
		oos.close();
	}
	
	@Test
	public void testDeExternlizable() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		UserExternlizableVO vo = (UserExternlizableVO) ois.readObject();
		ois.close();
		System.out.println(vo);
		Assert.assertEquals(externlizableVO.getName(), vo.getName());
	}
}
