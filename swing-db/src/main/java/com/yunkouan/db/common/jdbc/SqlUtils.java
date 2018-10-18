package com.yunkouan.db.common.jdbc;
 
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.yunkouan.db.common.utils.Constant.EdiHeaderInfo;
 
public class SqlUtils {
    public static final int INSERTSQL = 1;
    public static final int UPDATESQL = 2;
    private static Logger log = Logger.getLogger(SqlUtils.class.getName());
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
    /**
     * 获取InsertSQL
     * 
     * @param model
     * @return
     */
    public static String getInsertSQL(String tableName, Object model) {
        if (model == null) {
            log.info("传入参数为null！！");
            throw new NullPointerException();
        }
        return getInsertOrUpdateSQL(tableName, INSERTSQL, model);
    }
 
    /**
     * 获取UpdateSQL
     * 
     * @param model
     * @return
     */
    public static String getUpdateSQL(String tableName, Object model) {
        if (model == null) {
            log.info("传入参数为null！！");
            throw new NullPointerException();
        }
        return getInsertOrUpdateSQL(tableName, UPDATESQL, model);
    }
 
    /**
     * 选择Insert或者Update语句,如果是UPDATE，则是可传参的根据ID更新
     * 
     * @param type
     * @param model
     * @return
     */
    private static String getInsertOrUpdateSQL(String tableName, int type, Object model) {
        String sql = null;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Class<?> cl = model.getClass();
        Field[] fields = cl.getDeclaredFields();
        String table = tableName;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (type == 1) {
                    sb1.append(",");
                    sb1.append(field.getName());
                    if (field.getType().equals(String.class)) {
                        String value = (String) field.get(model);
                        if(value == null){
                        	value = new String("");
                        }
                        sb2.append(",");
                        sb2.append("'");
                        sb2.append(value);
                        sb2.append("'");
                    } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    	Integer value = 0;
                    	if (field.get(model) != null) {
                    		value = Integer.valueOf(field.get(model).toString());
                    	}
                        sb2.append(",");
                        sb2.append(value.intValue());
                    } else if (field.getType().equals(Date.class)){
                    	Date value = (Date) field.get(model);
                        sb2.append(",");
                        sb2.append("'");
                        if(value == null){
                        	value = new Date();
                        }
                        sb2.append(sdf.format(value));
                        sb2.append("'");
                    } else if (field.getType().equals(Double.class)){
                    	Double value = Double.valueOf(field.get(model).toString());
                        sb2.append(",");
                        sb2.append(value);
                    }
                } else if (type == 2) {
                    if (field.getType().equals(String.class)) {
                        String value = (String) field.get(model);
                        sb1.append(",");
                        sb1.append(field.getName());
                        sb1.append("='");
                        sb1.append(value);
                        sb1.append("'");
                    } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                        int value = field.getInt(model);
                        sb1.append(",");
                        sb1.append(field.getName());
                        sb1.append("=");
                        sb1.append(value);
                    } else if (field.getType().equals(Date.class)){
                    	Date value = (Date) field.get(model);
                        if(value == null){
                        	value = new Date();
                        }
                        sb1.append(",");
                        sb1.append(field.getName());
                        sb1.append("='");
                        sb1.append(sdf.format(value));
                        sb1.append("'");
                    } else if (field.getType().equals(Double.class)){
                    	Double value = Double.valueOf(field.get(model).toString());
                        sb1.append(",");
                        sb1.append(field.getName());
                        sb1.append("=");
                        sb1.append(value);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (type == 1) {
            sql = String.format("insert into %s (%s) values(%s)", table,
                    sb1.substring(1), sb2.substring(1));
        } else if (type == 2) {
            sql = String.format("update %s set %s where %s=?", table,
                    sb1.substring(1), EdiHeaderInfo.EDI_TABEL_FIELD_ID);
        }
        log.info("执行语句：" + sql);
        return sql;
    }
 
    /**
     * 获取DeleteSQL
     * 
     * @param table
     * @param primkey
     * @return
     */
    public static String getDeleteSQL(String table, String primkey) {
        String sql = String.format("delete from %s where %s=?", table, primkey);
        log.info("执行语句：" + sql);
        return sql;
    }
 
    /**
     * 根据ID查询
     * 
     * @param table
     * @param primkey
     * @param id
     * @return
     */
    public static String getQueryByFieleSQL(String table, String filed) {
        String sql = null;
        sql = String.format("select * from %s where %s like ?", table, filed);
        log.info("执行语句：" + sql);
        return sql;
    }
 
    /**
     * 获取查询所有记录的SQL
     * 
     * @param model
     * @param OrderBy
     * @return
     */
    public static String getQueryAllSQL(String table, String orderBy) {
        if (table == null) {
            log.info("传入参数为null！！");
            throw new NullPointerException();
        }
        String sql = null;
        sql = String.format("select * from %s order by %s", table, orderBy);
        log.info("执行语句：" + sql);
        return sql;
    }
 
}