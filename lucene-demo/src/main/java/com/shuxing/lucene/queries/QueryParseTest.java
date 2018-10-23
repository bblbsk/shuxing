package com.shuxing.lucene.queries;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PhraseQuery.Builder;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.shuxing.lucene.ik.IKAnalyzer6x;
import com.shuxing.lucene.index.CreateIndex;


/**
 * @Description:搜索入门
 * @Author：daojia
 * @CreateTime：2018年10月22日下午3:53:09
 * @version v1.0
 */
public class QueryParseTest {

	static IndexSearcher searcher;
	
	public static void main(String[] args) throws Throwable {
		URL url = CreateIndex.class.getClassLoader().getResource("indexdir");
		Path indexPath = Paths.get(url.toURI());
		Directory dir = FSDirectory.open(indexPath);
		DirectoryReader reader = DirectoryReader.open(dir);
		searcher = new IndexSearcher(reader);
		
		// 单域查询
		query();
		// 多域查询 MultiFieldQueryParser 
		multiFieldQuery();
		// 词项查询 TermQuery
		termQuery();
		// 布尔查询 BooleanQuery
		booleanQuery();
		// 范围查询 RangeQuery
		rangeQuery();
		// 前缀查询 PrefixQuery
		prefixQuery();
		// 句查询 PhrazeQuery
		phrazeQuery();
		// 模糊查询 FuzzyQuery
		fuzzyQuery();
		// 通配查询 WildcardQuery
		wildcardQuery();
		
	}
	
	/**
	 * @Description:搜索入门
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午4:16:53
	 * @throws
	 */
	private static void query() throws Exception{
		QueryParser parser = new QueryParser("title", new IKAnalyzer6x(true));
		parser.setDefaultOperator(Operator.AND);
		Query query = parser.parse("农村学生");
		System.out.println("query:" + query.toString());
		
		System.out.println(searcher.count(query));
		
		TopDocs topDocs = searcher.search(query, 5);
		
		printDoc(topDocs);
	}
	
	
	/**
	 * @Description:多域查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午4:17:07
	 * @throws
	 */
	private static void multiFieldQuery() throws Throwable{
		MultiFieldQueryParser multiParse = new MultiFieldQueryParser(new String[]{"title", "contend"}, new IKAnalyzer6x(true));
		multiParse.setDefaultOperator(Operator.OR);
		Query query = multiParse.parse("特朗普,奥巴马");
		System.out.println("query:" + query.toString());
		
		System.out.println(searcher.count(query));
		
		TopDocs topDocs = searcher.search(query, 5);
		printDoc(topDocs);
		
	}
	
	
	/**
	 * @throws IOException 
	 * @Description:词项查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午4:22:05
	 * @throws
	 */
	public static void termQuery() throws Exception{
		Query query = new TermQuery(new Term("title", "北"));
		
		System.out.println("query:" + query.toString());
		System.out.println(searcher.count(query));
		
		TopDocs topDocs = searcher.search(query, 5);

		printDoc(topDocs);
	}
	
	private static void booleanQuery() throws Throwable{
		Query query1 = new TermQuery(new Term("title", "美国"));
		Query query2 = new TermQuery(new Term("title", "日本"));
		BooleanClause bc1 = new BooleanClause(query1, Occur.MUST);
		BooleanClause bc2 = new BooleanClause(query2, Occur.MUST_NOT);
		
		BooleanQuery query = new BooleanQuery.Builder().add(bc1).add(bc2).build();
		
		TopDocs topDocs = searcher.search(query, 5);
		
		printDoc(topDocs);
	}
	
	
	/**
	 * @Description:范围查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午5:27:40
	 * @throws
	 */
	private static void rangeQuery() throws Exception{
		Query query = IntPoint.newRangeQuery("reply", 100, 1000);
		
		TopDocs topDocs = searcher.search(query, 5);
		
		printDoc(topDocs);
	}
	
	/**
	 * @Description:前缀查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午5:36:33
	 * @throws
	 */
	private static void prefixQuery() throws Exception{
		Term term = new Term("title", "学");
		Query query = new PrefixQuery(term);
		
		printDoc(searcher.search(query, 5));
	}
	
	/**
	 * @Description:句查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午5:44:52
	 * @throws
	 */
	private static void phrazeQuery() throws Exception{
		// 习近平|会见|美国总统|奥|巴马|学习|国外经验|
		Builder builder = new PhraseQuery.Builder();
		builder.add(new Term("title", "会见"));
		builder.add(new Term("title", "国外经验"));
		builder.setSlop(4);
		PhraseQuery query = builder.build();
		printDoc(searcher.search(query, 5));
		
		System.out.println("------------------");
		
		Builder builder2 = new PhraseQuery.Builder();
		builder2.add(new Term("title", "巴马"));
		builder2.add(new Term("title", "美国总统"));
		builder2.setSlop(3);
		PhraseQuery query2 = builder2.build();
		printDoc(searcher.search(query2, 5));
	}
	
	/**
	 * @Description:模糊查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午7:30:44
	 * @throws
	 */
	private static void fuzzyQuery() throws Exception{
		FuzzyQuery query = new FuzzyQuery(new Term("title", "国外经验"));
		TopDocs topDocs = searcher.search(query, 5);
		printDoc(topDocs);
	}
	
	/**
	 * @Description:通配符查询
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午7:38:43
	 * @throws
	 */
	private static void wildcardQuery() throws Exception{
		WildcardQuery query = new WildcardQuery(new Term("title", "美国*"));
		TopDocs topDocs = searcher.search(query, 5);
		printDoc(topDocs);
	}
	
	/**
	 * @Description:打印匹配的文档结果
	 * @Author daojia
	 * @CreateTime 2018年10月22日下午4:26:12
	 * @throws
	 */
	private static void printDoc(TopDocs topDocs) throws Exception{
		for (ScoreDoc sd : topDocs.scoreDocs) {
			Document doc = searcher.doc(sd.doc);
			System.out.println("DocID:" + sd.doc);
			System.out.println("id:" + doc.get("id"));
			System.out.println("title:" + doc.get("title"));
			System.out.println("文档内容：" + doc.get("content"));
			System.out.println("文档评分:" + sd.score);
		}
	}
}
