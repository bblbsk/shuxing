package com.shuxing.lucene.highlighter;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.shuxing.lucene.ik.IKAnalyzer6x;
import com.shuxing.lucene.index.CreateIndex;

public class HighlighterTest {
	public static void main(String[] args) throws Exception {
		URL url = CreateIndex.class.getClassLoader().getResource("indexdir");
		Path indexPath = Paths.get(url.toURI());
		Directory dir = FSDirectory.open(indexPath);
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		IKAnalyzer6x analyzer6x = new IKAnalyzer6x(true);
		String field = new String("title");
		QueryParser parser = new QueryParser(field, analyzer6x);
		Query query = parser.parse("学生");
		System.out.println("Query:" + query);

		// 查询高亮
		QueryScorer score = new QueryScorer(query, field);
		SimpleHTMLFormatter fors = new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");// 定制高亮标签
		Highlighter highlighter = new Highlighter(fors, score);// 高亮分析器

		// 返回前10条
		TopDocs tds = searcher.search(query, 10);
		for (ScoreDoc sd : tds.scoreDocs) {
			Document doc = searcher.doc(sd.doc);
			System.out.println("id:" + doc.get("id"));
			System.out.println("title:" + doc.get("title"));
			TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), sd.doc, field, analyzer6x);// 获取tokenstream
			Fragmenter fragment = new SimpleSpanFragmenter(score);
			highlighter.setTextFragmenter(fragment);
			String str = highlighter.getBestFragment(tokenStream, doc.get(field));// 获取高亮的片段
			System.out.println("高亮的片段:" + str);
		}
		dir.close();
		reader.close();
	}
}
