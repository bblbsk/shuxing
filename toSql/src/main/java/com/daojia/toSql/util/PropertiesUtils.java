package com.daojia.toSql.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * function:Get properties file attributes value author：shaojiang date：2011.7.20
 */
public class PropertiesUtils {

	private static Properties pros;// Properties Object

	static {
		load("system.properties");
	}

	/**
	 * Loading files to Properties object
	 * 
	 * @param fileName
	 */
	public static void load(String fileName) {
		pros = new Properties();// Create a Properties object
		/**
		 * Get properties file attributes for the current value of the
		 * inter-class compiled bytecode in file list of files of the documents
		 * called fileName input stream
		 */
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
		try {
			pros.load(in);// Load the file Properties to flow to the object
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Through the attribute name to get attribute values
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return pros.getProperty(key);
	}
	
	public static int getIntegerValue(String key) {
		return Integer.valueOf(pros.getProperty(key));
	}

	public static void main(String[] args) {
		String string = get("filedName");
		System.out.println(string);
	}

}