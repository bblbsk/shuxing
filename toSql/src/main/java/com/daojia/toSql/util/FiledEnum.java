package com.daojia.toSql.util;

import com.daojia.toSql.util.Constant.FiledOrderConstant;
import com.daojia.toSql.util.Constant.FiledValueIndexConstant;

public enum FiledEnum {
	
	FILED_NAME(FiledOrderConstant.FILED_NAME, FiledValueIndexConstant.FILED_NAME, "FILED_NAME"),
	FIELD_TYPE(FiledOrderConstant.FIELD_TYPE, FiledValueIndexConstant.FIELD_TYPE, "FIELD_TYPE"),
	FIELD_LENGTH(FiledOrderConstant.FIELD_LENGTH, FiledValueIndexConstant.FIELD_LENGTH, "FIELD_LENGTH"),
	FIELD_DEFAULVALUE(FiledOrderConstant.FIELD_DEFAULVALUE, FiledValueIndexConstant.FIELD_DEFAULVALUE, "FIELD_DEFAULVALUE"),
	FIELD_COMMEND(FiledOrderConstant.FIELD_COMMEND, FiledValueIndexConstant.FIELD_COMMEND, "FIELD_COMMEND"),
	IS_NULL(FiledOrderConstant.IS_NULL, FiledValueIndexConstant.IS_NULL, "IS_NULL");

	
	// 拼接顺序
	private int joiningIndex;
	
	// excel中所在列
	private int valueIndex;
	
	// name
	private String name;
	

	public int getJoiningIndex() {
		return joiningIndex;
	}

	public int getValueIndex() {
		return valueIndex;
	}
	public void setValueIndex(int valueIndex) {
		this.valueIndex = valueIndex;
	}

	public String getName() {
		return name;
	}

	private FiledEnum() {
	}

	private FiledEnum(int joiningIndex, int valueIndex, String name) {
		this.joiningIndex = joiningIndex;
		this.valueIndex = valueIndex;
		this.name = name;
	}



	public static void main(String[] args) {
		for (FiledEnum rate : FiledEnum.values()) {
			System.out.println(rate.getJoiningIndex());
		}
	}
}
