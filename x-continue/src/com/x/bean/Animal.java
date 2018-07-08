package com.x.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Animal {

	private String name;
	private Integer height;
	private static String idCard;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public static String getIdCard() {
		return idCard;
	}
	public static void setIdCard(String idCard) {
		Animal.idCard = idCard;
	}
}
