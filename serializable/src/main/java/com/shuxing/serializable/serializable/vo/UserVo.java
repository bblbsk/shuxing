package com.shuxing.serializable.serializable.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：daojia
 * @CreateTime：2018年7月8日上午11:02:05
 * @version v1.0
 */
public class UserVo extends UserParentVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String age;
	public int height;
	private List<String> hobbies;
	private transient AnimalVo animal;
//	public int weight;
	private transient String phone;
	public static Integer gender;	// 性别，默认男，1为女
	
//	private static final ObjectStreamField[] serialPersistentFields = {
//		new ObjectStreamField("name", String.class),
//		new ObjectStreamField("age", String.class)
//	};
	
	
	public UserVo(String name, String age, int height, String phone, List<String> hobbies, AnimalVo animal, String race) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.hobbies = hobbies;
		this.phone = phone;
		this.animal = animal;
		this.race = race;
	}
	
//	public UserVo() {
//		super();
//	}
	
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
		UserVo.gender = gender;
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
	
//	private Object writeReplace(){
//		ArrayList<Object> list = new ArrayList<>();
//		list.add(name); 
//		list.add(age);
//		return list;
//	}


	@Override
	public String toString() {
		return "UserVo [name=" + name + ", age=" + age + ", height=" + height
				+ ", hobbies=" + hobbies + ", animal=" + animal + ", race="
				+ race + "]";
	}

}