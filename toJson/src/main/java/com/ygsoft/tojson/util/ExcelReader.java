package com.ygsoft.tojson.util;

import com.alibaba.fastjson.JSON;
import com.ygsoft.tojson.enums.ExcelFieldColumnEnum;
import com.ygsoft.tojson.model.ExcelFieldColumnModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
* 读取Excel
* @className ExcelReader.java
* @author caishixian
* @date 2022/6/28 11:14
* @version V1.0
*/
public class ExcelReader {

    public static void main(String[] args) throws Exception {
        // 加载模板
        File file = new File("C:\\Users\\admin\\Desktop\\template.xlsx");
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
    }

}