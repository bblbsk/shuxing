package com.shuxing.serializable.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

/**
 * @Description:转换Bean时跳过原对象的空值字段
 * @Author：daojia
 * @CreateTime：2018年8月15日下午4:57:05
 * @version v1.0
 */
public class BeanUtilsBeanDemo {

	@Test
	public void testBeanCopy() throws IllegalAccessException, InvocationTargetException{
		
		A a = new A();
		B b = new B();
		
		a.setName("123");
		b.setPhone("112233");
		
		BeanUtilsBeanDefine  d = new BeanUtilsBeanDefine();
		d.copyProperties(b, a);
		
		System.out.println(b);
	}
	
	public class A {
		private String name;
		private String phone;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
	}
	
	public class B {
		private String name;
		private String phone;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		@Override
		public String toString() {
			return "B [name=" + name + ", phone=" + phone + "]";
		}
		
	}
}
