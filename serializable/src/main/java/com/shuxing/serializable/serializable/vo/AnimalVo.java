package com.shuxing.serializable.serializable.vo;

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
public class AnimalVo  {//implements Serializable
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String age;
	public int height;
	private List<String> hobbies;
//	public int weight;
	private transient String phone;
	public static int gender;	// 性别，默认男，1为女
	
	public AnimalVo(String name, String age, int height, String phone, List<String> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.hobbies = hobbies;
		this.phone = phone;
	}
	
	public AnimalVo() {
		super();
	}
	
//	public int getWeight() {
//		return weight;
//	}
//	public void setWeight(int weight) {
//		this.weight = weight;
//	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public static int getGender() {
		return gender;
	}
	public static void setGender(int gender) {
		AnimalVo.gender = gender;
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