package com.ygsoft.tojson.converter;

/**
* 转换器
* @className ColumnValueConverter.java
* @author caishixian
* @date 2022/6/28 19:24
* @version V1.0
*/
public interface ColumnValueConverter {

    /**
     * 转换列的值
     * @param value
     * @return
     */
    public Object converter(String value);


}
