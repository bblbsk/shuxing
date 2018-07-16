package com.shuxing.serializable.json.gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shuxing.serializable.common.model.PerformanceModel;

public class GsonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	private List<PerformanceModel> models = new ArrayList<PerformanceModel>();
	
	@Before
	public void before() throws IOException{
		File jsonFile = new File(GsonDemo.class.getResource("/com/shuxing/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
		
		// 创建对象
		for (int i = 0; i < 101234; i++) {
			PerformanceModel model = new PerformanceModel("shuxing", i, i * 1.1);
			models.add(model);
		}
		
		begin = new Date();
	}
	
	@Test
	public void testGson(){
		Gson gson = new GsonBuilder().create();
		
		// 序列化
		String json = gson.toJson(models);
		
		// 反序列化
		List<PerformanceModel> result = gson.fromJson(json, new TypeToken<List<PerformanceModel>>(){}.getType());
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
