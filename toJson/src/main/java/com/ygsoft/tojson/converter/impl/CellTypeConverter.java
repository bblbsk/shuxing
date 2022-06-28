package com.ygsoft.tojson.converter.impl;

import com.ygsoft.tojson.converter.ColumnValueConverter;

import java.util.Date;
import java.util.List;

public class CellTypeConverter implements ColumnValueConverter {

    @Override
    public String converter(String value) {
        switch (value) {
            case "数值":
            case "整形":
                return Integer.class.getName();
            case "长整形":
                return Long.class.getName();
            case "浮点数":
                return Double.class.getName();
            case "日期":
                return Date.class.getName();
            case "字符":
                return String.class.getName();
            case "数组":
                return List.class.getName();
            case "Object":
            case "对象":
                return Object.class.getName();
        }
        return String.class.getName();
    }
}
