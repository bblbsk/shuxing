package com.shuxing.serializable.kryo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.shuxing.serializable.common.model.PerformanceModel;
import com.shuxing.serializable.common.model.PerformanceNoSeriaModel;

public class KryoDemo {

	Kryo kryo = new Kryo();
	private List<PerformanceNoSeriaModel> models = new ArrayList<PerformanceNoSeriaModel>();
	private File file;
	
	private Date start;
	private Date end;
	
	@Before
	public void init(){
		// 创建对象
		for (int i = 0; i < 101234; i++) {
			PerformanceNoSeriaModel model = new PerformanceNoSeriaModel("shuxing", i, i * 1.1);
			models.add(model);
		}
		// 在桌面已类名新建文件
		File deskTop = FileSystemView.getFileSystemView().getHomeDirectory();
		file = new File(deskTop, this.getClass().getSimpleName());
		System.out.println("------ method start -----------------");
		start = new Date();
	}
	
	@After
	public void afterMethod(){
		end = new Date();
		long holding = end.getTime() - start.getTime();
		System.out.println("------ method holding ------------ " + holding);
		System.out.println("------ method end -------------------");
	}

	@Test
	public void testSerializableList() throws FileNotFoundException{
		// 序列化
		Output output = new Output(new FileOutputStream(file));
		for (PerformanceNoSeriaModel performanceNoSeriaModel : models) {
			kryo.writeObject(output, performanceNoSeriaModel);
		}
		output.close();
		
		// 反序列化
		Input input = new Input(new FileInputStream(file));
		PerformanceModel performanceNoSeriaModel;
		
		for (int i = 0; i < models.size(); i++) {
			performanceNoSeriaModel = kryo.readObject(input, PerformanceModel.class);
//			System.out.println(performanceNoSeriaModel.getAge());
		}
		input.close();
		
	}
	
}
