package com.x.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

class Student implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO����һ�仰�������������ʾʲô��
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
		// ����ִ�������Ĳ���, ����Է��л����ļ����м��ܵȵ�
		setName("new");
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
		// ���Ե����������������ж���ĳ�ʼ������
		setAge(16);
		setGrade("new-grage");
	}
}