package com.ygsoft.tojson.model;

import com.ygsoft.tojson.interfaces.FiledDefineInterface;
import org.apache.poi.ss.usermodel.Row;

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
