package com.shuxing.serializable.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class FastPropertyDemo {
	
	static class Model{
		@JSONField(name="A")
		private String a = "value";

		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
	}

	public static void main(String[] args) throws Exception{
		System.out.println(JSON.toJSONString(new Model()));
	}
}
