package com.ygsoft.tojson.enums;

import com.ygsoft.tojson.converter.ColumnValueConverter;
import com.ygsoft.tojson.converter.impl.CellTypeConverter;
import com.ygsoft.tojson.converter.impl.RequiredTypeConverter;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
* 数据目录排序字段列名称
* @className DataDirectoryOrderColumnEnum.java
* @author liyongmei
* @date 2022/6/23 10:33
* @version V1.0
*/
@Getter
public enum ExcelFieldColumnEnum {

    PARAM_TYPE(0, "参数类型", "paramType", null),
    CN_NAME(1, "中文名称", "cnName", null),
    EN_NAME(2, "英文名称", "enName", null),
    CELL_TYPE(3, "数据类型", "cellType", new CellTypeConverter()),
    REQUIRED_TYPE(4, "是否必填", "requiredType", new RequiredTypeConverter()),
    DEFAULT_VALUE(5, "默认值", "defaultValue", null),
    DESC(6, "参数说明", "desc", null);

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

    /**
     * 转换器
     */
    private ColumnValueConverter converter;

    ExcelFieldColumnEnum(int columnIndex, String name, String fieldName, ColumnValueConverter converter) {
        this.columnIndex = columnIndex;
        this.name = name;
        this.fieldName = fieldName;
        this.converter = converter;
    }

    /**
    * 通过模型名称获取对应的列名称
    * @param name
    * @return java.lang.String
    * @author liyongmei
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
