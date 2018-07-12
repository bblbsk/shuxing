package com.shuxing.serializable.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import javax.swing.filechooser.FileSystemView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializableDemo {

	private UserVo vo;
	private File file;
	
	@Before
	public void init(){
		// 创建对象
		AnimalVo animal = new AnimalVo("daojia", "20", 111, "1300000000", Arrays.asList("socer"));
		vo = new UserVo("daojia", "20", 111, "1300000000", Arrays.asList("socer"), animal, "yellow");
		// 在桌面已类名新建文件
		File deskTop = FileSystemView.getFileSystemView().getHomeDirectory();
		file = new File(deskTop, this.getClass().getSimpleName());
	}

	@Test
	public void testSerializable() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		System.out.println(vo);
		oos.writeObject(vo);
		oos.close();
	}
	
	
	@Test
	public void testDeserialization() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		UserVo deSeriaVo = (UserVo) in.readObject();
		in.close();
		System.out.println(deSeriaVo);
		Assert.assertEquals(vo, deSeriaVo);
	}
	
	@Test
	public void testSerializableSingle() throws ClassNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		System.out.println("==========orin:" + SingletonInstance.getInstance());
		oos.writeObject(SingletonInstance.getInstance());
		oos.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		SingletonInstance deSeriaVo = (SingletonInstance) in.readObject();
		System.out.println("==========de:" + deSeriaVo);
	}
}
