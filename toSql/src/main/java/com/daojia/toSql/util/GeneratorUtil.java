package com.daojia.toSql.util;

import com.daojia.toSql.util.Constant.SqlConstant;


/**
 * @Title: GeneratorUtil.java
 * @Description:
 * @Author：daojia
 * @CreateTime：2018年6月6日下午10:20:10
 * @version v1.0
 */
public class GeneratorUtil {
	
	
	public static String buildCreateTable(String tableName){
		StringBuilder sb = new StringBuilder("").append(SqlConstant.CREATE_TABLE);
		
		sb.append(" ")
		.append(SqlConstant.FILED_WRAP).append(tableName).append(SqlConstant.FILED_WRAP)
		.append(" ")
		.append(SqlConstant.BRACKET_LEFT)
		.append(SqlConstant.NEXT_LINE);
		return sb.toString();
	}
	
	public static String buildTailTable(){
		StringBuilder sb = new StringBuilder();
		sb.append(" PRIMARY KEY (`id`)")
		.append(SqlConstant.NEXT_LINE)
		.append(SqlConstant.BRACKET_RIGHT).append(" ")
		.append("ENGINE=InnoDB DEFAULT CHARSET=utf8");
		return sb.toString();
	}

	/**
	 * @Description:`filed`
	 * @Method: wrapField
	 * @ReturnType String
	 * @Author daojia
	 * @CreateTime 2018年6月6日下午10:19:57
	 * @throws
	 */
	public static String wrapField(String filed){
		return new StringBuilder(" ").append(SqlConstant.FILED_WRAP).append(filed).append(SqlConstant.FILED_WRAP).toString();
	}
	
	
	/**
	 * @Description:'commnet'
	 * @Method: wrapComment
	 * @ReturnType String
	 * @Author daojia
	 * @CreateTime 2018年6月6日下午10:22:17
	 * @throws
	 */
	public static String wrapComment(String comment){
		return new StringBuilder(" ").append(SqlConstant.COMMENT).append(" ").append(SqlConstant.COMMENT_WRAP).append(comment).append(SqlConstant.COMMENT_WRAP).append(",").toString();
	}
	
	
	/**
	 * @Description: varchar(100)
	 * @Method: wrapFiledType
	 * @ReturnType String
	 * @Author daojia
	 * @CreateTime 2018年6月6日下午10:24:22
	 * @throws
	 */
	public static String wrapFiledType(String type, int length){
		return new StringBuilder(" ").append(type).append(SqlConstant.BRACKET_LEFT).append(length).append(SqlConstant.BRACKET_RIGHT).toString();
	}
	
	/**
	 * @Description: varchar(100)
	 * @Method: wrapDefaulValue
	 * @ReturnType String
	 * @Author daojia
	 * @CreateTime 2018年6月6日下午10:24:22
	 * @throws
	 */
	public static String wrapDefaulValue(String dafaulValue){
		return new StringBuilder(" ").append(SqlConstant.DEFAULT).append(" ").append(dafaulValue).toString();
	}
	
	/**
	 * @Description: varchar(100)
	 * @Method: wrapNotNull
	 * @ReturnType String
	 * @Author daojia
	 * @CreateTime 2018年6月6日下午10:24:22
	 * @throws
	 */
	public static String wrapNotNull(){
		return new StringBuilder(" ").append(SqlConstant.NOT_NULL).toString();
	}
}
