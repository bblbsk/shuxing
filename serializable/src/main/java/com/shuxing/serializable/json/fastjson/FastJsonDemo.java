package com.shuxing.serializable.json.fastjson;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shuxing.serializable.json.model.TBillCsvTmp;

public class FastJsonDemo {

	private Date begin;
	private Date end;
	private String data;
	
	@Before
	public void before() throws IOException{
		begin = new Date();
		File jsonFile = new File(FastJsonDemo.class.getResource("/com/daojia/serializable/json/jsonData.json").getPath()); 
		data = FileUtils.readFileToString(jsonFile);
	}
	
	@Test
	public void testFastJson(){
		JSONArray jsonArray = JSON.parseArray(data);
		List<TBillCsvTmp> result = jsonArray.toJavaList(TBillCsvTmp.class);
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
