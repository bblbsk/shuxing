package com.shuxing.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.shuxing.lucene.ik.IKAnalyzer6x;

/**
 * @Description:多种分词器的比较
 * @Author：daojia
 * @CreateTime：2018年10月16日下午3:45:50
 * @version v1.0
 */
public class VariousAnalyzers {

	private static String str = "中华人民共和国简称中国，是一个有 13 亿人口的国家";

	private static Map<String, Analyzer> analyzers = new HashMap<String, Analyzer>();
	
	static {
		analyzers.put("标准分词", new StandardAnalyzer());
		analyzers.put("空格分词", new WhitespaceAnalyzer());
		analyzers.put("简单分词", new SimpleAnalyzer());
		analyzers.put("二分分词 ", new CJKAnalyzer());
		analyzers.put("关键词分词", new KeywordAnalyzer());
		analyzers.put("停用词分词", new StopAnalyzer());
		analyzers.put("中文智能分词", new SmartChineseAnalyzer());
		analyzers.put("IK分词", new IKAnalyzer6x(true));
	}
	
	public static void main(String[] args) throws IOException {
		
		Set<Entry<String,Analyzer>> entrySet = analyzers.entrySet();
		
		for (Entry<String, Analyzer> entry : entrySet) {
			System.out.print(entry.getKey() + " : ");
			// 打印分词结果
			printAnalyzer(entry.getValue());
		}
	}

	/**
	 * @Description:printAnalyzer
	 * @Author daojia
	 * @CreateTime 2018年10月16日下午3:55:17
	 * @throws
	 */
	private static void printAnalyzer(Analyzer analyzer) throws IOException {
		StringReader reader = new StringReader(str);
		TokenStream tokenStream = analyzer.tokenStream("demo", reader);
		CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			System.out.print(attribute.toString() + "|");
		}
		System.out.println(System.lineSeparator());
		analyzer.close();
	}
}
