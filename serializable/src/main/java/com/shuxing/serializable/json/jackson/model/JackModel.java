package com.shuxing.serializable.json.jackson.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class JackModel {

	@JsonProperty("A")
	private String a;
	
	@JsonProperty("B")
	private String b;
	

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}
