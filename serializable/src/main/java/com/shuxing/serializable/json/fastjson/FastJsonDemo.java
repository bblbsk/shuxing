package com.shuxing.serializable.json.fastjson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shuxing.serializable.common.model.PerformanceModel;

public class FastJsonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	private List<PerformanceModel> models = new ArrayList<PerformanceModel>();
	
	@Before
	public void before() throws IOException{
		File jsonFile = new File(FastJsonDemo.class.getResource("/com/shuxing/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
		
		// 创建对象
		for (int i = 0; i < 101234; i++) {
			PerformanceModel model = new PerformanceModel("shuxing", i, i * 1.1);
			models.add(model);
		}
		
		begin = new Date();
	}
	
	@Test
	public void testFastJson(){
		// 序列化
		String jsonString = JSON.toJSONString(models);
		
		// 反序列化
		JSONArray jsonArray = JSON.parseArray(jsonString);
		List<PerformanceModel> result = jsonArray.toJavaList(PerformanceModel.class);
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
