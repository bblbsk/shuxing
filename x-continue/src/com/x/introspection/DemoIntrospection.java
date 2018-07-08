package com.x.introspection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import com.x.bean.Animal;

public class DemoIntrospection {

	public static void main(String[] args) throws IntrospectionException {
		
		BeanInfo beanInfo = Introspector.getBeanInfo(Animal.class);
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			String name = propertyDescriptor.getName();
			System.out.println(name);
		}
		MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
		for (MethodDescriptor methodDescriptor : methodDescriptors) {
			System.out.println(methodDescriptor.getName());
		}
	}
}
