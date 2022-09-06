package com.ygsoft.tojson.annotation;



/**
 * @Title: FiledDefineInterface.java
 * @Description:字段定义接口
 * @Author：liyongmei
 * @CreateTime：2022年6月7日下午6:44:03
 * @version v1.0
 */
public @interface FiledDefineInterface {

	/**
	 * @Description:字段名称所在列
	 * @Method: filedName
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:44:12
	 * @throws
	 */
	public int filedName() default 0;
	
	/**
	 * @Description:字段描述所在列
	 * @Method: fieldCommend
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:45:37
	 * @throws
	 */
	public int fieldCommend();
	
	
	/**
	 * @Description:字段类型所在列
	 * @Method: fieldType
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:47:14
	 * @throws
	 */
	public int fieldType();
	
	
	/**
	 * @Description:字段长度所在列
	 * @Method: fieldLength
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:47:36
	 * @throws
	 */
	public int fieldLength();
	
	
	/**
	 * @Description:字段默认值所在列
	 * @Method: fieldDefaulValue
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:47:59
	 * @throws
	 */
	public int fieldDefaulValue();
	
	
	/**
	 * @Description:是否为空所在列
	 * @Method: IsNull
	 * @ReturnType int
	 * @Author liyongmei
	 * @CreateTime 2022年6月7日下午6:48:19
	 * @throws
	 */
	public int IsNull();
	
}
