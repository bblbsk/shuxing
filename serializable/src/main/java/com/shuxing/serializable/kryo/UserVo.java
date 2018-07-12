package com.shuxing.serializable.kryo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：daojia
 * @CreateTime：2018年7月8日上午11:02:05
 * @version v1.0
 */
public class UserVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String age;
	public int height;
	private List<String> hobbies;
	
	public UserVo(String name, String age, int height, String phone, List<String> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.hobbies = hobbies;
	}
	
	public UserVo() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		setName("58daojia");
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		setAge("25");
	}
	
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", age=" + age + ", height=" + height
				+ ", hobbies=" + hobbies + "]";
	}

}