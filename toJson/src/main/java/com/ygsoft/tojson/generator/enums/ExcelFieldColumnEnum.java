package com.ygsoft.tojson.generator.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
* 数据目录排序字段列名称
* @className DataDirectoryOrderColumnEnum.java
* @author caishixian
* @date 2022/6/23 10:33
* @version V1.0
*/
@Getter
public enum ExcelFieldColumnEnum {

    DATA_NAME(0, "参数类型", "paramType"),
    DATA_CATEGORY(1, "中文名称", "cnName"),
    DATA_LEVEL(2, "英文名称", "enName"),
    DATA_COUNT(3, "数据类型", "cellType"),
    EXIT_TYPE(4, "是否必填", "requiredType"),
    EXIT_TIME( 5, "出境时间", "exit_time"),
    CROSS_SUBJECT_FLOW_TYPE(6, "默认值", "defaultValue"),
    TRANSFER_TIME(7, "参数说明", "desc");

    private static Map<String, String> COLUMN_MAP = new HashMap<>(16);

    /**
     * 列序号
     */
    private int columnIndex;

    /**
     * 列名称
     */
    private String name;

    /**
     * 对应属性名称
     */
    private String fieldName;

    ExcelFieldColumnEnum(int columnIndex, String name, String fieldName) {
        this.columnIndex = columnIndex;
        this.name = name;
        this.fieldName = fieldName;
    }

    /**
    * 通过模型名称获取对应的列名称
    * @param name
    * @return java.lang.String
    * @author caishixian
    * @date 2022/6/23 10:39
    */
    public static String getFieldNameByName(String name) {
        if (COLUMN_MAP.isEmpty()) {
            for (ExcelFieldColumnEnum orderColumnEnum : values()) {
                COLUMN_MAP.put(orderColumnEnum.getName(), orderColumnEnum.getFieldName());
            }
        }
        return COLUMN_MAP.get(name);
    }


}
