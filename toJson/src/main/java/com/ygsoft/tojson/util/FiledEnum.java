package com.ygsoft.tojson.util;

public enum FiledEnum {
	
	FILED_NAME(Constant.FiledOrderConstant.FILED_NAME, Constant.FiledValueIndexConstant.FILED_NAME, "FILED_NAME"),
	FIELD_TYPE(Constant.FiledOrderConstant.FIELD_TYPE, Constant.FiledValueIndexConstant.FIELD_TYPE, "FIELD_TYPE"),
	FIELD_LENGTH(Constant.FiledOrderConstant.FIELD_LENGTH, Constant.FiledValueIndexConstant.FIELD_LENGTH, "FIELD_LENGTH"),
	FIELD_DEFAULVALUE(Constant.FiledOrderConstant.FIELD_DEFAULVALUE, Constant.FiledValueIndexConstant.FIELD_DEFAULVALUE, "FIELD_DEFAULVALUE"),
	FIELD_COMMEND(Constant.FiledOrderConstant.FIELD_COMMEND, Constant.FiledValueIndexConstant.FIELD_COMMEND, "FIELD_COMMEND"),
	IS_NULL(Constant.FiledOrderConstant.IS_NULL, Constant.FiledValueIndexConstant.IS_NULL, "IS_NULL");

	
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
