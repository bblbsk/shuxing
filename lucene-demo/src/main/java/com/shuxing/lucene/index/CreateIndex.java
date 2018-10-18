package com.shuxing.lucene.index;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.shuxing.lucene.ik.IKAnalyzer6x;


/**
 * @Description:创建索引
 * @Author：daojia
 * @CreateTime：2018年10月17日上午10:34:19
 * @version v1.0
 */
public class CreateIndex {

	public static void main(String[] args) throws URISyntaxException, IOException {
		
		News news1 = new News();
		news1.setId(1);
		news1.setTitle("习近平会见美国总统奥巴马，学习国外经验");
		news1.setContent("国家主席习近平 9 月 3 日在杭州西湖国宾馆会见前米出席二十国集团领导人杭州峰会的美国总统奥巴马...");
		news1.setReply(672);
		
		News news2 = new News();
		news2.setId(2);
		news2.setTitle("北大迎 4380 名新生农村学生 700 多人近年最多");
		news2.setContent("昨天，北京大学迎来 4380 名来自全国各地及数卡个同家的本科新生。其中，农村学生共 700 余名，为近年最多...");
		news2.setReply(995);
	
		News news3 = new News();
		news3.setId(3);
		news3.setTitle("特朗普宣誓（Donald Trump ）就任美国第 45 任总统");
		news3.setContent("当地时间 1 月 20 日，唐纳德·特朗普在美国国会宣誓就职，正式成为美国第 45 任总统。");
		news3.setReply(1872);
		
		List<News> news = new ArrayList<News>();
		news.add(news1);
		news.add(news2);
		news.add(news3);
		
		// 创建分词器
		Analyzer analyzer = new IKAnalyzer6x(true);
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		Directory directory = null;
		IndexWriter indexWriter = null;
		
		// 索引目录
		URL url = CreateIndex.class.getClassLoader().getResource("indexdir");
		Path path = Paths.get(url.toURI());
//		Path path1 = Paths.get("C:\\Users\\daojia\\Desktop\\recruit.log.2018-10-15.log");
		if (Files.isReadable(path)) {
			directory = FSDirectory.open(path);
			indexWriter = new IndexWriter(directory, iwc);
			
			// 创建域数据
			FieldType idType = new FieldType();
			idType.setIndexOptions(IndexOptions.DOCS);
			idType.setStored(true);

			FieldType titleType = new FieldType();
			titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			titleType.setStored(true);
			titleType.setTokenized(true);

			FieldType contentType = new FieldType();
			contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			contentType.setStored(true);
			contentType.setTokenized(true);
			contentType.setStoreTermVectors(true);
			contentType.setStoreTermVectorPositions(true);
			contentType.setStoreTermVectorOffsets(true);
			
			for (News newData : news) {
				Document doc = new Document();
				doc.add(new Field("id", String.valueOf(newData.getId()), idType));
				doc.add(new Field("title", newData.getTitle(), titleType));
				doc.add(new Field("content", newData.getContent(), contentType));
				doc.add(new IntPoint("reply", newData.getReply()));
				doc.add(new StoredField("reply_display", newData.getReply()));
				
				indexWriter.addDocument(doc);
			}
			
			indexWriter.commit();
			indexWriter.close();
			directory.close();
		}
		
		System.out.println("创建索引完毕!");
	}
}
