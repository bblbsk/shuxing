package com.ygsoft.tojson.util;


/**
 * @Title: GeneratorUtil.java
 * @Description:
 * @Author：daojia
 * @CreateTime：2018年6月6日下午10:20:10
 * @version v1.0
 */
public class GeneratorUtil {
	
	
	public static String buildCreateTable(String tableName){
		StringBuilder sb = new StringBuilder("").append(Constant.SqlConstant.CREATE_TABLE);
		
		sb.append(" ")
		.append(Constant.SqlConstant.FILED_WRAP).append(tableName).append(Constant.SqlConstant.FILED_WRAP)
		.append(" ")
		.append(Constant.SqlConstant.BRACKET_LEFT)
		.append(Constant.SqlConstant.NEXT_LINE);
		return sb.toString();
	}
	
	public static String buildTailTable(){
		StringBuilder sb = new StringBuilder();
		sb.append(" PRIMARY KEY (`id`)")
		.append(Constant.SqlConstant.NEXT_LINE)
		.append(Constant.SqlConstant.BRACKET_RIGHT).append(" ")
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
		return new StringBuilder(" ").append(Constant.SqlConstant.FILED_WRAP).append(filed).append(Constant.SqlConstant.FILED_WRAP).toString();
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
		return new StringBuilder(" ").append(Constant.SqlConstant.COMMENT).append(" ").append(Constant.SqlConstant.COMMENT_WRAP).append(comment).append(Constant.SqlConstant.COMMENT_WRAP).append(",").toString();
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
		return new StringBuilder(" ").append(type).append(Constant.SqlConstant.BRACKET_LEFT).append(length).append(Constant.SqlConstant.BRACKET_RIGHT).toString();
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
		return new StringBuilder(" ").append(Constant.SqlConstant.DEFAULT).append(" ").append(dafaulValue).toString();
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
		return new StringBuilder(" ").append(Constant.SqlConstant.NOT_NULL).toString();
	}
}
