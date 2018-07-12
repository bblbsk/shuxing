package com.shuxing.serializable.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserExternlizableVO implements Externalizable{
	
//	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	public int height;
	
//	public UserExternlizableVO() {
//		super();
//	}
	
	public UserExternlizableVO(String name, int age, int height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}

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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getName());
		out.writeObject(getAge());
		out.writeObject(getHeight());
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setName(in.readObject().toString());
		setAge(Integer.valueOf(in.readObject().toString()));
	}

	@Override
	public String toString() {
		return "UserExternlizableVO [name=" + name + ", age=" + age
				+ ", height=" + height + "]";
	}
}
