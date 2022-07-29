 package com.ygsoft.tojson.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ygsoft.tojson.util.Constant.SqlConstant;
import com.ygsoft.tojson.util.FiledEnum;
import com.ygsoft.tojson.util.GeneratorUtil;
import com.ygsoft.tojson.util.XLSReader;


/**
 * @Title: SqlGenerator.java
 * @Description:语句生成器
 * @Author：shuxing
 * @CreateTime：2018年6月10日上午10:34:34
 * @version v1.0
 */
public class MysqlSqlGenerator {

	public static void main(String[] args) throws IOException {

		Workbook wb = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\shuxing\\Desktop\\数据库设计.xls")));  
		
		Sheet sheetAt = wb.getSheetAt(0);
		
		StringBuilder sb = new StringBuilder(GeneratorUtil.buildCreateTable("table_name"));
		
		for (int i = 1; i < sheetAt.getLastRowNum(); i++) {
			String generator = generator(sheetAt.getRow(i));
			sb.append(generator).append(SqlConstant.NEXT_LINE);
		}
		
		sb.append(GeneratorUtil.buildTailTable());
		
		System.out.println(sb.toString());
	}
	
	
	/**
	 * @Description:通过Excel文件，生成指定的sheetIndex表 
	 * @Method: generatorByIndex
	 * @ReturnType String
	 * @Author shuxing
	 * @CreateTime 2018年6月9日下午9:49:55
	 * @throws
	 */
	public String generatorByIndex(int index){
		StringBuilder sb = new StringBuilder();
		Sheet sheetAt = XLSReader.getWb().getSheetAt(index);
		sb = new StringBuilder(GeneratorUtil.buildCreateTable(sheetAt.getSheetName()));
		for (int i = 1; i < sheetAt.getLastRowNum(); i++) {
			String generator = generator(sheetAt.getRow(i));
			sb.append(generator).append(SqlConstant.NEXT_LINE);
		}
		
		sb.append(GeneratorUtil.buildTailTable());  
		return sb.toString();
	}
	
	
	/**
	 * @Description:通过JTable内容生成sql
	 * @Method: generatorByTableData
	 * @ReturnType String
	 * @Author shuxing
	 * @CreateTime 2018年6月9日下午9:53:03
	 * @throws
	 */
	public String generatorByTableData(int index, JTable jTable){
		StringBuilder sb = new StringBuilder();
		String sheetName = XLSReader.getSheets().get(index).getSheetName();
		sb = new StringBuilder(GeneratorUtil.buildCreateTable(sheetName));
		
		for (int i = 0; i < jTable.getRowCount(); i++) {
			Row row = trans2Row(i, jTable);
			if (row == null) {
				continue;
			}
			String generator = generator(row);
			sb.append(generator).append(SqlConstant.NEXT_LINE);
		}
		
		sb.append(GeneratorUtil.buildTailTable());  
		return sb.toString();
		
	}
	
	/**
	 * @Description:将model转成sql
	 * @Method: generator
	 * @ReturnType String
	 * @Author shuxing
	 * @CreateTime 2018年6月7日下午7:11:51
	 * @throws
	 */
	public static String generator(Row row){
		StringBuilder sql = new StringBuilder("");
		
		TreeSet<FiledEnum> fileds = new TreeSet<FiledEnum>(new Comparator<FiledEnum>() {
			@Override
			public int compare(FiledEnum o1, FiledEnum o2) {
				return o1.getJoiningIndex() - o2.getJoiningIndex();
			}
		});
		
		for (FiledEnum rate : FiledEnum.values()) {
			fileds.add(rate);
		}
		
		for (FiledEnum rate : fileds) {
			switch (rate){
				case FILED_NAME:
					sql.append(GeneratorUtil.wrapField(getValueByIndex(row, rate.getValueIndex())));
					break;
				case FIELD_COMMEND:
					sql.append(GeneratorUtil.wrapComment(getValueByIndex(row, rate.getValueIndex())));
					break;
				case FIELD_TYPE:
					int lengthIndex = FiledEnum.FIELD_LENGTH.getValueIndex();
					int typeIndex = FiledEnum.FIELD_TYPE.getValueIndex();
					sql.append(GeneratorUtil.wrapFiledType(getValueByIndex(row, typeIndex), getIntegerValueByIndex(row, lengthIndex)));
					break;
				case FIELD_DEFAULVALUE:
					sql.append(GeneratorUtil.wrapDefaulValue(getValueByIndex(row, rate.getValueIndex())));
					break;
				case IS_NULL:
					sql.append(GeneratorUtil.wrapNotNull(getValueByIndex(row, rate.getValueIndex())));
					break;
			}
		}
		
		return sql.toString();
	}
	
	
	/**
	 * @Description:获取指定位置的值
	 * @Method: getValueByIndex
	 * @ReturnType String
	 * @Author shuxing
	 * @CreateTime 2018年6月7日下午7:40:26
	 * @throws
	 */
	public static String getValueByIndex(Row row, int index){
		return row.getCell(index).toString();
	}

	
	/**
	 * @Description:获取指定位置的整数值
	 * @Method: getIntegerValueByIndex
	 * @ReturnType int
	 * @Author shuxing
	 * @CreateTime 2018年6月7日下午10:15:06
	 * @throws
	 */
	public static int getIntegerValueByIndex(Row row, int index){
		return Double.valueOf(row.getCell(index).toString()).intValue();
	}
	
	
	/**
	 * @Description:将jTable中的一行数据转成poi的Row对象
	 * @Method: trans2Row
	 * @ReturnType Row
	 * @Author shuxing
	 * @CreateTime 2018年6月9日下午10:10:04
	 * @throws
	 */
	public Row trans2Row(int rowIndex, JTable jTable){
		// 创建临时行
		Row row = XLSReader.getWb().createSheet().createRow(0);
		for (int i = 0; i < jTable.getColumnCount(); i++) {
			Cell cell = row.createCell(i, CellType.STRING);
			Object value = jTable.getValueAt(rowIndex, i);
			// 忽略空白行
			if (value == null && i == 0) {
				return null;
			}
			cell.setCellValue(jTable.getValueAt(rowIndex, i).toString());
		}
		return row;
	}
}
