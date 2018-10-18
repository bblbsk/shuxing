package com.yunkouan.db.common.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import com.yunkouan.db.common.utils.Constant;
import com.yunkouan.db.common.utils.Constant.EdiHeaderInfo;
import com.yunkouan.db.common.utils.Constant.EdiParams;

/**
  * @ClassName: DBUtils
  * @Description: 数据库操作工具
  * @author-csx
  * @date 2017年4月25日 上午9:11:55
  *
  */
public class DBUtils {
	
	 private static Logger logger = Logger.getLogger(DBUtils.class.getName());
	/**
	  * @Fields isConnect : 是否正常连接数据库
	  */
	private static boolean isConnect = false;

	public static boolean isConnect() {
		return isConnect;
	}
	public static void setConnect(boolean isConnect) {
		DBUtils.isConnect = isConnect;
	}

	/**
	  *
	  * @Title: insert
	  * @Description: 根据sql插入数据
	  * @param @param sql
	  * @return int    返回类型
	  * @throws
	  */
	public static void insert(String sql) {
		try {
			Connection conn = DBCheck.getConnection();
			if(conn == null){
				return;
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			logger.info("插入数据失败:" + e.getMessage());
		}
	}
	
	/**
	  *
	  * @Title: insert
	  * @Description: 根据sql更新数据
	  * @param @param sql
	  * @return int    返回类型
	  * @throws
	  */
	public static void update(String sql, String id) {
		try {
			Connection conn = DBCheck.getConnection();
			if(conn == null){
				return;
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			logger.info("更新数据失败:" + e.getMessage());
		}
	}
	
	
	/**
	  *
	  * @Title: queryAllInfo
	  * @Description: 获取所有数据
	  * @param @param sql
	  * @return List<Vector<String>>    返回类型
	  * @throws
	  */
	public static List<Vector<String>> queryAllInfo(String sql){
		List<Vector<String>> result = new ArrayList<Vector<String>>();
		try {
			Connection conn = DBCheck.getConnection();
			if(conn == null){
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			result = generateVectors(rs);
			statement.close();
		} catch (SQLException e) {
			logger.info("获取所有数据:" + e.getMessage());
		}
		return result;
	}
	
	/**
	  *
	  * @Title: queryInfoByBarcode
	  * @Description: 根据条码号查询信息
	  * @param @param sql
	  * @return Object    返回类型
	  * @throws
	  */
	public static List<Vector<String>> queryInfoByBarcode(String sql, String barcode){
		List<Vector<String>> result = new ArrayList<Vector<String>>();
		try {
			Connection conn = DBCheck.getConnection();
			if(conn == null){
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, barcode + Constant.SQL_LIKE_SYMBOL);
			ResultSet rs = statement.executeQuery();
			result = generateVectors(rs);
			statement.close();
		} catch (SQLException e) {
			logger.info("根据条码号查询数据失败:" + e.getMessage());
		}
		return result;
	}
	
	/**
	  *
	  * @Title: queryReceiptInfoByBarcode
	  * @Description: 根据条码号查询回执信息
	  * @param @param sql
	  * @return Object    返回类型
	  * @throws
	  */
	public static List<Vector<String>> queryReceiptInfoByBarcode(String sql, String barcode){
		List<Vector<String>> result = new ArrayList<Vector<String>>();
		try {
			Connection conn = DBCheck.getConnection();
			if(conn == null){
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, barcode + Constant.SQL_LIKE_SYMBOL);
			ResultSet rs = statement.executeQuery();
			result = generateReceiptVectors(rs);
			statement.close();
		} catch (SQLException e) {
			logger.info("根据条码号查询数据失败:" + e.getMessage());
		}
		return result;
	}
	
	/**
	  *
	  * @Title: generateVectors
	  * @Description: 将ResultSet中的数据组织成 List<Vector<String>>
	  * @param ResultSet rs
	  * @return List<Vector<String>>    返回类型
	  * @throws
	  */
	public static List<Vector<String>> generateVectors(ResultSet rs){
		List<Vector<String>> result = new ArrayList<Vector<String>>();
		try {
			while(rs.next()){
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_ENTRYID));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_ORDERNO));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_MAINGNAME));
				vc.add(EdiParams.IPORT.equals(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_IEFLAG)) ? EdiParams.IPORT_CHINESE : EdiParams.EPORT_CHINESE);
				vc.add(EdiParams.DEC.equals(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_DECTYPE)) ? EdiParams.DEC_CHINESE : EdiParams.NDEC_CHINESE);
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_GROSSWT));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_LOGISTICSNAME));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_SENDERNAME));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_OWNERNAME));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_OWNERADDRESS));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_PACKNO));
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_ID));
				result.add(vc);
			}
		} catch (SQLException e) {
			logger.info("将ResultSet中的数据组织成 List<Vector<String>> 异常:" + e.getMessage());
		}
		return result;
	}
	
	/**
	  *
	  * @Title: generateReceiptVectors
	  * @Description: 将ResultSet中的数据组织成 List<Vector<String>>
	  * @param ResultSet rs
	  * @return List<Vector<String>>    返回类型
	  * @throws
	  */
	public static List<Vector<String>> generateReceiptVectors(ResultSet rs){
		List<Vector<String>> result = new ArrayList<Vector<String>>();
		try {
			while(rs.next()){
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString(EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE));
				result.add(vc);
			}
		} catch (SQLException e) {
			logger.info("将ResultSet中的数据组织成 List<Vector<String>> 异常:" + e.getMessage());
		}
		return result;
	}
}
