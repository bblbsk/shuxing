package com.daojia.toSql.model;

import org.apache.poi.ss.usermodel.Row;

import com.daojia.toSql.interfaces.FiledDefineInterface;

@FiledDefineInterface(
		IsNull = 0,
		fieldCommend = 0,
		fieldDefaulValue = 0,
		fieldLength = 0,
		fieldType = 0,
		filedName = 0)
public class FiledModel {

	private Row row;

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}
	
	
	public FiledModel() {
		super();
	}
	
	public FiledModel(Row row) {
		super();
		this.row = row;
	}

}
