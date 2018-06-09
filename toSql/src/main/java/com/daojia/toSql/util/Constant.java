package com.daojia.toSql.util;

import javax.swing.DefaultComboBoxModel;

public class Constant {

	
	/**
	 * @Title: Constant.java
	 * @Description:组织Sql所需的常量
	 * @Author：daojia
	 * @CreateTime：2018年6月7日下午7:37:27
	 * @version v1.0
	 */
	public static class SqlConstant{
		
		public static String CREATE_TABLE = "CREATE TABLE";
		
		public static String NOT_NULL = "NOT NULL";
		
		public static String COMMENT = "COMMENT";
		
		public static String DEFAULT = "DEFAULT";
		
		public static String PRIMARY_KEY = "PRIMARY KEY";
		
		public static String ENGINE = "ENGINE=InnoDB DEFAULT CHARSET=utf8";
		
		public static String FILED_WRAP = "`";
		
		public static String COMMENT_WRAP = "'";
		
		public static String BRACKET_LEFT = "(";
		
		public static String BRACKET_RIGHT = ")";
		
		public static String NEXT_LINE = "\r\n";		
	}
	

	
	
	/**
	 * @Title: FiledOrderConstant.java
	 * @Description:字段拼接顺序
	 * @Author：daojia
	 * @CreateTime：2018年6月7日下午8:03:16
	 * @version v1.0
	 */
	public static class FiledOrderConstant{
		public static final int FILED_NAME = 1;
		public static final int FIELD_TYPE = 2;
		public static final int FIELD_LENGTH = 3;
		public static final int IS_NULL = 4;
		public static final int FIELD_DEFAULVALUE = 5;
		public static final int FIELD_COMMEND = 6;
	}
	
	
	/**
	 * @Title: Constant.java
	 * @Description:字段属性位置
	 * @Author：daojia
	 * @CreateTime：2018年6月7日下午7:06:08
	 * @version v1.0
	 */
	public static class FiledValueIndexConstant{
		public static final int FILED_NAME = PropertiesUtils.getIntegerValue("FILED_NAME");
		public static final int FIELD_TYPE = PropertiesUtils.getIntegerValue("FIELD_TYPE");
		public static final int FIELD_LENGTH = PropertiesUtils.getIntegerValue("FIELD_LENGTH");
		public static final int FIELD_DEFAULVALUE = PropertiesUtils.getIntegerValue("FIELD_DEFAULVALUE");
		public static final int FIELD_COMMEND = PropertiesUtils.getIntegerValue("FIELD_COMMEND");
		public static final int IS_NULL = PropertiesUtils.getIntegerValue("IS_NULL");
	}
	
	/**
	 * 模板名称
	 */
	public static final String EXCEL_FILE_NAME = "sql.xls";
	/**
	 * excel文件拓展名
	 */
	public static final String EXCEL_FILE_SUFFIX = ".xls";
	
	
	
	/**
	 * edi_header表项目信息
	 */
	public static class SQLTableInfo{
		//表头
		public static String[] TABLE_COLUMN_NAMES = new String[8];
		//展示字段序号
		public static int FILED_TYPE = 3;
		public static int IS_NULL = 6;
		public static int IS_PRIMARY_KEY = 7;
		
		// 字段位置可选项
		public static DefaultComboBoxModel<String> INDEX_COMBOBOXDATA = new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6"});
		// 字段类型可选择
		public static DefaultComboBoxModel<String> TYPE_COMBOBOXDATA = new DefaultComboBoxModel<String>(new String[] {"int", "bigint", "varchar", "timestamp","datetime"});
		// 是否为空，是否主键
		public static DefaultComboBoxModel<String> ISNOT_COMBOBOXDATA = new DefaultComboBoxModel<String>(new String[] {"N", "Y"});
	}
}
