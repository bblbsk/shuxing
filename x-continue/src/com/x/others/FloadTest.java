package com.x.others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.filechooser.FileSystemView;

public class FloadTest {

	@SuppressWarnings(value = { "finally" })
	public static void main(String[] args) throws Exception {//throws FileNotFoundException {
	
		File f = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\a.txt");
//		try {
//			InputStream is = new FileInputStream(f);
//		} finally{
//			System.out.println("finally");
//		}
		try {
			InputStream is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			throw new Exception("我将被忽略，因为下面的finally中使用了return");
		} catch (IOException e) {
			System.out.println("IOException");
		} finally {
			System.out.println("finally");
		}
	}
}
