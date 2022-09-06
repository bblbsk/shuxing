 package com.ygsoft.tojson.util;

 import com.ygsoft.tojson.util.Constant.SqlConstant;
 import org.apache.commons.lang3.StringUtils;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.*;

 import javax.swing.*;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.List;
 import java.util.TreeSet;


/**
* OracleSqlGenerator
* @className OracleSqlGenerator.java
* @author liyongmei
* @date 2022/7/29 16:17
* @version V1.0
*/
 public class OracleSqlGenerator {

     /**
      * @Description:通过Excel文件，生成指定的sheetIndex表
      * @Method: generatorByIndex
      * @ReturnType String
      * @Author liyongmei
      * @CreateTime 2022年6月9日下午9:49:55
      * @throws
      */
     public String generatorByIndex(int index){
         StringBuilder sb = new StringBuilder();
         Sheet sheetAt = XLSReader.getWb().getSheetAt(index);
         sb = new StringBuilder(GeneratorUtil.buildCreateOracleTable(sheetAt.getSheetName()));
         List<String> comments = new ArrayList<>();
         // 生成表字段
         for (int i = 1; i < sheetAt.getLastRowNum(); i++) {
             String generator = generator(sheetAt.getSheetName(), sheetAt.getRow(i), comments);
             sb.append(generator).append(SqlConstant.NEXT_LINE);
         }
         // 主键字段
         String primaryFieldName = getValueByIndex(sheetAt.getRow(1), FiledEnum.FILED_NAME.getValueIndex());
         // 拼接主键
         sb.append(GeneratorUtil.buildOracleTailTable(sheetAt.getSheetName(), primaryFieldName));
         sb.append(SqlConstant.NEXT_LINE);
         // 拼接字段注释
         for (String comment : comments) {
             sb.append(comment).append(SqlConstant.NEXT_LINE);
         }
         return sb.toString().toUpperCase();
     }

     /**
      * @Description:将model转成sql
      * @Method: generator
      * @ReturnType String
      * @Author liyongmei
      * @CreateTime 2022年6月7日下午7:11:51
      * @throws
      */
     public static String generator(String tableName, Row row, List<String> comments){
         StringBuilder sql = new StringBuilder("");

         TreeSet<FiledEnum> fileds = new TreeSet<FiledEnum>(new Comparator<FiledEnum>() {
             @Override
             public int compare(FiledEnum o1, FiledEnum o2) {
                 return o1.getJoiningIndex() - o2.getJoiningIndex();
             }
         });

         for (FiledEnum rate : FiledEnum.values()) {
             fileds.add(rate);
         }
         String fieldName = new String();
         for (FiledEnum rate : fileds) {
             switch (rate){
                 case FILED_NAME:
                     fieldName = getValueByIndex(row, rate.getValueIndex());
                     sql.append(GeneratorUtil.wrapOracleField(fieldName));
                     break;
                 case FIELD_COMMEND:
                     comments.add(GeneratorUtil.wrapOracleComment(tableName, fieldName, getValueByIndex(row, rate.getValueIndex())));
                     break;
                 case FIELD_TYPE:
                     int lengthIndex = FiledEnum.FIELD_LENGTH.getValueIndex();
                     int typeIndex = FiledEnum.FIELD_TYPE.getValueIndex();
                     sql.append(GeneratorUtil.wrapFiledType(getValueByIndex(row, typeIndex), getIntegerValueByIndex(row, lengthIndex)));
                     break;
                 case FIELD_DEFAULVALUE:
                     sql.append(GeneratorUtil.wrapDefaulValue(getValueByIndex(row, rate.getValueIndex())));
                     break;
                 case IS_NULL:
                     sql.append(GeneratorUtil.wrapNotNull(getValueByIndex(row, rate.getValueIndex())));
                     break;
             }
         }

         return sql.toString();
     }


     /**
      * @Description:获取指定位置的值
      * @Method: getValueByIndex
      * @ReturnType String
      * @Author liyongmei
      * @CreateTime 2022年6月7日下午7:40:26
      * @throws
      */
     public static String getValueByIndex(Row row, int index){
         return row.getCell(index).toString();
     }


     /**
      * @Description:获取指定位置的整数值
      * @Method: getIntegerValueByIndex
      * @ReturnType int
      * @Author liyongmei
      * @CreateTime 2022年6月7日下午10:15:06
      * @throws
      */
     public static int getIntegerValueByIndex(Row row, int index){
         String cellValue = row.getCell(index).toString();
         if (StringUtils.isEmpty(cellValue)) {
             return 0;
         }
         return Double.valueOf(cellValue).intValue();
     }

     /**
      * @Description:将jTable中的一行数据转成poi的Row对象
      * @Method: trans2Row
      * @ReturnType Row
      * @Author liyongmei
      * @CreateTime 2022年6月9日下午10:10:04
      * @throws
      */
     public Row trans2Row(int rowIndex, JTable jTable){
         // 创建临时行
         Row row = XLSReader.getWb().createSheet().createRow(0);
         for (int i = 0; i < jTable.getColumnCount(); i++) {
             Cell cell = row.createCell(i, CellType.STRING);
             Object value = jTable.getValueAt(rowIndex, i);
             // 忽略空白行
             if (value == null && i == 0) {
                 return null;
             }
             cell.setCellValue(jTable.getValueAt(rowIndex, i).toString());
         }
         return row;
     }
 }
