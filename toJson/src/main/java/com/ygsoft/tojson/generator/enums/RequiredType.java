package com.ygsoft.tojson.generator.enums;

/**
* 是否必填
* @className ParamType.java
* @author caishixian
* @date 2022/6/28 10:56
* @version V1.0
*/
public enum RequiredType {

    NOT_REQUIRED(0, "非必填"),
    REQUIRED(1, "必填");

    private Integer type;
    private String desc;

    RequiredType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
