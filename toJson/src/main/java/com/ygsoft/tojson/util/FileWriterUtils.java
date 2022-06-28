package com.ygsoft.tojson.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
  * @ClassName: FileWriterUtils
  * @Description: 读写文件
  * @author-csx
  * @date 2017年5月4日 上午9:39:32
  *
  */
public class FileWriterUtils {

	/**
	  *
	  * @Title: writeToFile
	  * @Description: 将输入流中的内容，写入到File中
	  * @param @param is
	  * @return File    返回类型
	  * @throws
	  */
	public static File writeToFile(InputStream is){
		File root = new File("");
		String srcFilePath = root.getAbsolutePath() + File.separator + Constant.EXCEL_FILE_NAME;
		//创建文件
		File srcFile = new File(srcFilePath);
		FileOutputStream os;
		try {
			os = new FileOutputStream(srcFile);
			//IO流写入文件
			int i;
			byte[] buf = new byte[1024];
			try {
				while ((i = is.read(buf)) != -1) {
					os.write(buf, 0, i);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					is.close();
					os.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}
		return srcFile;
	}
}
