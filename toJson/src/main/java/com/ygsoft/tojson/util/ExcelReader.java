package com.ygsoft.tojson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ygsoft.tojson.enums.ExcelFieldColumnEnum;
import com.ygsoft.tojson.enums.ParamType;
import com.ygsoft.tojson.model.ExcelFieldColumnModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 读取Excel
* @className ExcelReader.java
* @author caishixian
* @date 2022/6/28 11:14
* @version V1.0
*/
public class ExcelReader {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\admin\\Desktop\\template.xlsx");
        String interfaceJsonParam = readInterfaceJsonParam(file);
        System.out.println("interfaceJsonParam = " + interfaceJsonParam);
    }

    public static String readInterfaceJsonParam(File file) throws Exception {
        // 加载模板
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        XSSFSheet inputSheet = xssfWorkbook.getSheetAt(0);
        int lastRowNum = inputSheet.getLastRowNum();
        // 表格数据
        List<ExcelFieldColumnModel> modelList = new ArrayList<>();
        for (int i = 1; i < lastRowNum; i++) {
            // 表格列配置
            ExcelFieldColumnEnum[] columnEnums = ExcelFieldColumnEnum.values();
            // 表格行数据
            ExcelFieldColumnModel columnModel = new ExcelFieldColumnModel();
            XSSFRow sheetRow = inputSheet.getRow(i);
            // 循环每一列
            for (ExcelFieldColumnEnum columnEnum : columnEnums) {
                XSSFCell rowCell = sheetRow.getCell(columnEnum.getColumnIndex());
                // 单元格值
                String cellValue = rowCell.getStringCellValue();
                if (StringUtils.isEmpty(cellValue)) {
                    continue;
                }
                // 单元格对应字段
                Field declaredField = columnModel.getClass().getDeclaredField(columnEnum.getFieldName());
                declaredField.setAccessible(true);
                // 单元格字段转换
                if (columnEnum.getConverter() != null) {
                    declaredField.set(columnModel, columnEnum.getConverter().converter(cellValue));
                } else {
                    declaredField.set(columnModel, cellValue);
                }
            }
            if (StringUtils.isNotBlank(columnModel.getCnName())) {
                modelList.add(columnModel);
            }
        }

        System.out.println("modelList = " + JSON.toJSONString(modelList, true));
        // 属性类型映射
        Map<String, String> fieldTypeMap = modelList.stream()
                .filter(model -> "java.lang.Object".equals(model.getCellType()))
                .collect(Collectors.toMap(ExcelFieldColumnModel::getEnName, ExcelFieldColumnModel::getCellType));
        // 缓存为json对象
        JSONObject jsonObject = new JSONObject(true);
        for (ExcelFieldColumnModel model : modelList) {
            String paramType = model.getParamType();
            String innerObjectKey = null;
            if (!ParamType.IN.getDesc().equals(paramType)) {
                // 层级对象key
                innerObjectKey = paramType.substring(ParamType.IN.getDesc().length());
                String fieldType = fieldTypeMap.get(innerObjectKey);
                if ("java.util.List".equals(fieldType)) {
                    putValue(jsonObject.getJSONArray(innerObjectKey).getJSONObject(0), model);
                    continue;
                }
                putValue(jsonObject.getJSONObject(innerObjectKey), model);
                continue;
            }
            putValue(jsonObject, model);
        }

        return JSON.toJSONString(jsonObject, true);
    }

    /**
    * 维护数据
    * @param jsonObject
    * @return void
    * @author caishixian
    * @date 2022/6/28 23:22
    */
    private static void putValue(JSONObject jsonObject, ExcelFieldColumnModel model) {
        if (jsonObject == null) {
            return;
        }

        switch (model.getCellType()) {
            case "java.lang.String":
                jsonObject.put(model.getEnName(), StringUtils.EMPTY);
                return;
            case "java.lang.Integer":
                jsonObject.put(model.getEnName(), 0);
                return;
            case "java.lang.Long":
                jsonObject.put(model.getEnName(), 0L);
                return;
            case "java.lang.Object":
                jsonObject.put(model.getEnName(), new JSONObject(true));
                return;
            case "java.util.List":
                jsonObject.put(model.getEnName(), new JSONArray().add(new JSONObject(true)));
                return;
        }
        jsonObject.put(model.getEnName(), StringUtils.EMPTY);
    }


}
