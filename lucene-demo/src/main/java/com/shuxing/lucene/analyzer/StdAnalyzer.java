package com.shuxing.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


/**
 * @Description:标准分词器
 * @Author：daojia
 * @CreateTime：2018年10月16日下午3:10:13
 * @version v1.0
 */
public class StdAnalyzer {

	private static String strCh = "中华人民共和国简称中国，是一个有 13 亿人口的国家";
	private static String strEn  ="Dogs  can  not  achieve  a  place, eyes  can  reach:";
	
	public static void main(String[] args) throws IOException {
		System.out.println("StandardAnalyzer 对中文分词：");
		stdAnalyzer(strCh); 
		System.out.println("StandardAnalyzer 对英文分词：");
		stdAnalyzer (strEn); 
	}
	
	private static void stdAnalyzer(String str) throws IOException{
		Analyzer analyzer = new StandardAnalyzer();
		
		StringReader reader = new StringReader(str);
		
		TokenStream stream = analyzer.tokenStream(str, reader);
		stream.reset();
		CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);
		while(stream.incrementToken()){
			System.out.print(attribute.toString() + "|");
		}
		System.out.println(System.lineSeparator());
	}
}
