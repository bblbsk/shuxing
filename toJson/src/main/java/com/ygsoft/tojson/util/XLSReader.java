package com.ygsoft.tojson.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSReader {

	public static Workbook wb;
	
	private static List<Sheet> sheets = new ArrayList<Sheet>();
	
	private static List<String> sheetNames = new ArrayList<String>();
	
	public static void loadXLSFile(File file) throws FileNotFoundException, IOException {
		if (!file.exists()) {
			return;
		}
		wb = new XSSFWorkbook(new FileInputStream(file));
		sheetNames.clear();
		sheets.clear();
		
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheetNames.add(i + " ---- " + wb.getSheetName(i));
			sheets.add(wb.getSheetAt(i));
		}
		
	}

	public static Workbook getWb() {
		return wb;
	}

	public static void setWb(Workbook wb) {
		XLSReader.wb = wb;
	}

	public static List<Sheet> getSheets() {
		return sheets;
	}

	public static void setSheets(List<Sheet> sheets) {
		XLSReader.sheets = sheets;
	}

	public static List<String> getSheetNames() {
		return sheetNames;
	}

	public static void setSheetNames(List<String> sheetNames) {
		XLSReader.sheetNames = sheetNames;
	}
	
}
