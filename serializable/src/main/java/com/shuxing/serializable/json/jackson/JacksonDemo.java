package com.shuxing.serializable.json.jackson;

import java.io.File;
import java.io.IOException;
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

import com.shuxing.serializable.json.model.TBillCsvTmp;

public class JacksonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	@Before
	public void before() throws IOException{
		begin = new Date();
		File jsonFile = new File(JacksonDemo.class.getResource("/com/daojia/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
	}
	
	@Test
	public void testJackson() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<TBillCsvTmp> result = mapper.readValue(data, new TypeReference<List<TBillCsvTmp>>(){});
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
