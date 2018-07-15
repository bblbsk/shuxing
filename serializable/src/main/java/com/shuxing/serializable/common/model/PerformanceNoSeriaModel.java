package com.shuxing.serializable.common.model;


/**
 * @Author：daojia
 * @CreateTime：2018年7月8日上午11:02:05
 * @version v1.0
 */
public class PerformanceNoSeriaModel{
	
	private String name;
	private int age;
	public double height;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public PerformanceNoSeriaModel() {
		super();
	}
	
	public PerformanceNoSeriaModel(String name, int age, double height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}
	@Override
	public String toString() {
		return "PerformanceModel [name=" + name + ", age=" + age + ", height="
				+ height + "]";
	}
	
}