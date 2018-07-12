package com.shuxing.serializable.kryo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoDemo {

	Kryo kryo = new Kryo();
	private UserVo vo;
	private File file;
	
	@Before
	public void init(){
		// 创建对象
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("socer");
		vo = new UserVo("daojia", "20", 111, "1300000000", hobbies);
		// 在桌面已类名新建文件
		File deskTop = FileSystemView.getFileSystemView().getHomeDirectory();
		file = new File(deskTop, this.getClass().getSimpleName());
	}

	@Test
	public void testSerializable() throws FileNotFoundException{
		Output output = new Output(new FileOutputStream(file));
		System.out.println(vo);
		kryo.writeObject(output, vo);
		output.close();
	}
	
	
	@Test
	public void testDeserialization() throws FileNotFoundException{
		Input input = new Input(new FileInputStream(file));
		UserVo deSeriaVo = (UserVo) kryo.readObject(input, UserVo.class);
		input.close();
		System.out.println(deSeriaVo);
		Assert.assertEquals(vo.getName(), deSeriaVo.getName());
	}
	
}
