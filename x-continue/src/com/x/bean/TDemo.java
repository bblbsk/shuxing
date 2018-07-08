package com.x.bean;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TDemo {

	public static void main(String[] args) {
////		Map m = new HashMap<String,String>();
////		m.put("a", "a");
////		m.put("b", "a");
//		Animal a = new Animal();
//		XMLUtils utls = new XMLUtils();
//		try {
//			InputStream obj2Xml = utls.Obj2Xml(a);
//			
//		} catch (FileNotFoundException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Map m = new HashMap<String,String>();
		m.put("name", "123");
		m.put("height", "12");
		try {
			Animal convertMap = (Animal) Map2Bean.convertMap(Animal.class, m);
		} catch (IllegalAccessException | InstantiationException
				| InvocationTargetException | IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
