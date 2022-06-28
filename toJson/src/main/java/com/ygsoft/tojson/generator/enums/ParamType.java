package com.ygsoft.tojson.generator.enums;

/**
* 参数类型
* @className ParamType.java
* @author caishixian
* @date 2022/6/28 10:56
* @version V1.0
*/
public enum ParamType {

    IN(0, "入参"),
    OUT(1, "出参");

    private Integer type;
    private String desc;

    ParamType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
