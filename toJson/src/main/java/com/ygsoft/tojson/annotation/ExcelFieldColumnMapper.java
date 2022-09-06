package com.ygsoft.tojson.annotation;

import org.apache.poi.ss.usermodel.CellType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 列映射注解
* @className ExcelFiledColumnMapper.java
* @author liyongmei
* @date 2022/6/28 10:27
* @version V1.0
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcelFieldColumnMapper {

    /**
     * 列索引
     * @return
     */
    int columnIndex();

    /**
     * 单元格类型
     * @return
     */
    CellType cellType() default CellType.STRING;
}
