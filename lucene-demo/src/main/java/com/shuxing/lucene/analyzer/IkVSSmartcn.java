package com.shuxing.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.shuxing.lucene.ik.IKAnalyzer6x;


/**
 * @Description:IK和Smart智能中文分词器的比较
 * @Author：daojia
 * @CreateTime：2018年10月16日下午4:39:26
 * @version v1.0
 */
public class IkVSSmartcn {

	private static String str1 = "公路局正在治理解放大道路面积水问题。厉害了我的国。";
	
	private static String str2 = "IKAnalyzer 是一个开源的，基于 java 语言开发的轻量级的中文分词工具包。";
	
	public static void main(String[] args) throws IOException {
		
		// IK
		Analyzer analyzer = new IKAnalyzer6x(true);
		analyzerCh(analyzer, str1);
		analyzerCh(analyzer, str2);
		
		// smart;
		analyzer = new SmartChineseAnalyzer();
		analyzerCh(analyzer, str1);
		analyzerCh(analyzer, str2);
	}
	
	
	private static void analyzerCh(Analyzer analyzer, String str) throws IOException{
		StringReader reader = new StringReader(str);
		
		TokenStream stream = analyzer.tokenStream(str, reader);
		stream.reset();
		CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);
		while(stream.incrementToken()){
			System.out.print(attribute.toString() + "|");
		}
		System.out.println(System.lineSeparator());
		
		stream.close();
	}
}
