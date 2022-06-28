package com.ygsoft.tojson.converter.impl;

import com.ygsoft.tojson.converter.ColumnValueConverter;
import com.ygsoft.tojson.enums.RequiredType;

public class RequiredTypeConverter implements ColumnValueConverter {

    @Override
    public Object converter(String value) {
        if (RequiredType.REQUIRED.getDesc().equals(value)) {
            return RequiredType.REQUIRED.getType();
        }
        return RequiredType.NOT_REQUIRED.getType();
    }
}
