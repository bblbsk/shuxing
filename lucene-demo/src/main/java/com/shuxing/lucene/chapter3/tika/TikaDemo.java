package com.shuxing.lucene.chapter3.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * @Description: Tika测试
 * @Author：daojia
 * @CreateTime：2018年10月26日上午10:37:53
 * @version v1.0
 */
public class TikaDemo {

	public static void main(String[] args) throws IOException, SAXException, TikaException {
		
		
		// File
		File file = new File("E:\\4000.项目信息\\4003.招聘系统（2018-09-17~2018-09-21）\\开放平台接口说明文档(v1.0.1)20180829.pdf");
		FileInputStream fis = new FileInputStream(file);
//		TikaInputStream inputStream = TikaInputStream.get(file.toURI());
		
		// 创建内容处理器对象
		BodyContentHandler handler = new BodyContentHandler();
		// 创建元数据对象
		Metadata metadata = new Metadata();
		// 创建内容解析器对象
		ParseContext parseContext = new ParseContext();
		// 实例PDFParser
		PDFParser parser = new PDFParser();
		// 调用parser解析
		parser.parse(fis, handler, metadata, parseContext);
		// 遍历元数据
		for (String name : metadata.names()) {
			System.out.println(name + "| " + metadata.get(name));
		}
		
		// 打印pdf中的内容
		System.out.println(handler.toString());
		
		
		
	}
}
