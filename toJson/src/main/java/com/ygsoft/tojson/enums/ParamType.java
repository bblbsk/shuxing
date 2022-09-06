package com.ygsoft.tojson.enums;

import lombok.Data;

/**
* 参数类型
* @className ParamType.java
* @author liyongmei
* @date 2022/6/28 10:56
* @version V1.0
*/
public enum ParamType {

    IN(0, "输入参数"),
    OUT(1, "输出参数");

    private Integer type;
    private String desc;

    ParamType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
