package com.yunkouan.db.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.yunkouan.db.common.utils.Constant;
import com.yunkouan.db.common.utils.Constant.MysqlParams;


/**
  * @ClassName: DbParamsCheck
  * @Description: TODO
  * @author-csx
  * @date 2017年4月25日 上午9:11:36
  *
  */
public class DBCheck {
	 private static Logger log = Logger.getLogger(DBCheck.class.getName());
	
	// 声明Connection对象
	private static Connection conn;
	
	/**
	 * @Description: 测试连接数据库
	 * @param ip：数据库host
	 * @param passwd：数据库密码
	 * @param databaseName：连接的数据库名
	 * @return true/false
	 */
	public static boolean dbConnect(String ip, String passwd, String databaseName) {
		// 驱动程序名
		String driver = MysqlParams.MYSQL_DB_DRIVER;
		// URL指向要访问的数据库名mydata
		String url = MysqlParams.MYSQL_DB_URL_PRIFIX + ip + MysqlParams.MYSQL_DB_URL_SUFFIX + databaseName;
		// MySQL配置时的用户名
		String user = MysqlParams.MYSQL_DB_USER;
		// MySQL配置时的密码
		String password = passwd;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				DBUtils.setConnect(true);
			}
		} catch (ClassNotFoundException e) {
			log.info("连接数据库失败:" + e.getMessage());
			DBUtils.setConnect(false);
		} catch (SQLException e) {
			log.info("连接数据库失败:" + e.getMessage());
			DBUtils.setConnect(false);
		} catch (Exception e) {
			log.info("连接数据库失败:" + e.getMessage());
			DBUtils.setConnect(false);
		}
		return DBUtils.isConnect();
	}
	
	public static Connection getConnection(){
		if(conn == null){
			boolean isConnect = dbConnect(MysqlParams.MYSQL_DB_URL_IP_SPECIFIC, MysqlParams.MYSQL_DB_PASSWORD, Constant.DATABASE_CUS_NAME);
			if(!isConnect){
				JOptionPane.showMessageDialog(null, "初始化数据库连接失败，请先进行数据库连接测试!");
				return null;
			}
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(DBCheck.dbConnect("localhost", "Yka@yunkouan.49", "yka_customs_standard"));
	}
}
