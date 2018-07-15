package com.shuxing.serializable.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shuxing.serializable.common.model.PerformanceModel;

public class SerializableListDemo {

	private List<PerformanceModel> models = new ArrayList<PerformanceModel>();
	private File file;
	
	private Date start;
	private Date end;
	
	@Before
	public void init(){
		// 创建对象
		for (int i = 0; i < 101234; i++) {
			PerformanceModel model = new PerformanceModel("shuxing", i, i * 1.1);
			models.add(model);
		}
		// 在桌面已类名新建文件
		File deskTop = FileSystemView.getFileSystemView().getHomeDirectory();
		file = new File(deskTop, this.getClass().getSimpleName());
		System.out.println("------ method start ----------------");
		start = new Date();
	}
	
	@After
	public void afterMethod(){
		end = new Date();
		long holding = end.getTime() - start.getTime();
		System.out.println("------ method holding----------" + holding);
		System.out.println("------ method end ------------------");
	}

	@Test
	public void testSerializableList() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 序列化
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		for (PerformanceModel performanceModel : models) {
			oos.writeObject(performanceModel);
		}
		oos.close();
		
		// 反序列
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		PerformanceModel readObject;
		for (int i = 0; i < models.size(); i++) {
			readObject = (PerformanceModel) in.readObject();
//			System.out.println(readObject);
		}
		
	}

}
