package com.shuxing.serializable.json.gson;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shuxing.serializable.json.model.TBillCsvTmp;

public class GsonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	@Before
	public void before() throws IOException{
		begin = new Date();
		File jsonFile = new File(GsonDemo.class.getResource("/com/daojia/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
	}
	
	@Test
	public void testGson(){
		Gson gson = new GsonBuilder().create();
		List<TBillCsvTmp> result = gson.fromJson(data, new TypeToken<List<TBillCsvTmp>>(){}.getType());
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
