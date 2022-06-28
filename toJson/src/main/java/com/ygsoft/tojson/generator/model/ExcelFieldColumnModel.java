package com.ygsoft.tojson.generator.model;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;

/**
* 列
* @className ExcelFieldColumnModel.java
* @author caishixian
* @date 2022/6/28 10:33
* @version V1.0
*/
@Data
public class ExcelFieldColumnModel {

    /**
     * 参数类型 0:入参，1：出参
     */
    private int paramType;

    /**
     * 字段描述
     */
    private String cnName;

    /**
     * 英文名，字段名
     */
    private String enName;

    /**
     * 数据类型
     */
    private CellType cellType;

    /**
     * 是否必填 0:非必填， 1：必填
     */
    private int requiredType;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 参数说明
     */
    private String desc;

}
