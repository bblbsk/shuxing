package com.x.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

class Student implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private static String grade;
	private transient String idCard;
	private List<String> corse;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getGrade() {
		return grade;
	}

	public static void setGrade(String grade) {
		Student.grade = grade;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public List<String> getCorse() {
		return corse;
	}

	public void setCorse(List<String> corse) {
		this.corse = corse;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		// 可以执行其他的操作, 比如对反列化的文件进行加密等等
		setName("new");
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
		// 可以调用其他方法来进行额外的初始化操作
		setAge(16);
		setGrade("new-grage");
	}
}