package com.x.others;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class FileNameUtilsTest {

	public static void main(String[] args) {
		
		File file = new File("D:\\workspaces_x\\x-continue\\src\\com\\x\\others\\FileNameUtilsTest.java");
	
		System.out.println(file.getAbsolutePath());
		System.out.println(FilenameUtils.separatorsToUnix(file.getAbsolutePath()));
		System.out.println(FilenameUtils.getPathNoEndSeparator(file.getName()));
		System.out.println(file.getName().substring(FilenameUtils.indexOfExtension(file.getName())));
		
	}
}
