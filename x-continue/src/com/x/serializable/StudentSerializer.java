package com.x.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.filechooser.FileSystemView;

class StudentSerializer {
	public static void main(String[] args) throws Exception {
		String desktop = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		String fileName = desktop + "\\jason.se";
		
		// create a Student object
		Student st = new Student();
		st.setName("LL");
		st.setAge(15);
		Student.setGrade("grade");
		st.setIdCard("111111111232131");
		st.setCorse(new ArrayList<String>(Arrays.asList("math","chinese","english")));

		// serialize the st to jason.se file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(st);
		oos.close();

		// deserialize the object from jason.se
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		Student jason = (Student) ois.readObject();
		ois.close();

		// verify the name field of jason object
		assert "jason".equals(jason.getName());
	}
}