package com.shuxing.serializable.serializable;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SingletonInstance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static class SingletonInstanceHolder {

		private static SingletonInstance instance = new SingletonInstance();
		
	}
	
	private SingletonInstance() {
		
	}
	
	public static SingletonInstance getInstance(){
		return SingletonInstanceHolder.instance;
	}
	
	private Object readResolve() throws ObjectStreamException {
		return SingletonInstanceHolder.instance;
	}
	
	public static void main(String[] args) {
		System.out.println("=====================1" + getInstance());
		System.out.println("=====================2" + getInstance());
		System.out.println("=====================3" + getInstance());
	}
}
