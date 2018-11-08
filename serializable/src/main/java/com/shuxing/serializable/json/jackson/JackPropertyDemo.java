package com.shuxing.serializable.json.jackson;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class JackPropertyDemo {
	
	static class Model{
		@JsonProperty("A")
		private String a = "value";

		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
	}

	public static void main(String[] args) throws Exception{
		System.out.println(new ObjectMapper().writeValueAsString(new Model()));
	}
}
