package com.shuxing.serializable.json.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shuxing.serializable.common.model.PerformanceModel;

public class JacksonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	private List<PerformanceModel> models = new ArrayList<PerformanceModel>();
	
	@Before
	public void before() throws IOException{
		File jsonFile = new File(JacksonDemo.class.getResource("/com/shuxing/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
		
		// 创建对象
		for (int i = 0; i < 101234; i++) {
			PerformanceModel model = new PerformanceModel("shuxing", i, i * 1.1);
			models.add(model);
		}
		
		begin = new Date();
	}
	
	@Test
	public void testJackson() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		// 序列化
		String seriaString = mapper.writeValueAsString(models);
		
		// 反序列化
		List<PerformanceModel> result = mapper.readValue(seriaString, new TypeReference<List<PerformanceModel>>(){});
//		for (TBillCsvTmp tBillCsvTmp : result) {
//			System.out.println(tBillCsvTmp);
//		}
		System.out.println(result.size());
	}
	
	@After
	public void after(){
		end = new Date();
		System.out.println("耗时.........:" + (end.getTime() - begin.getTime()));
	}
}
