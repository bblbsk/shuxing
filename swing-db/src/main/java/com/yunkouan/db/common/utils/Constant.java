package com.yunkouan.db.common.utils;


/**
  * @ClassName: Constant
  * @Description: 常量类
  * @author-csx
  * @date 2017年5月3日 下午10:09:52
  *
  */
public class Constant {

	//---------------- 公共常量--------------------------------------------------
	
	//空字符串
	public static String EMPTY_STRING = "";
	//Integer 默认值
	public static int INTEGER_DOUBLE = 0;
	//Double 默认值
	public static double ZERO_DOUBLE = 0.0;
	//sql like 符号
	public static String SQL_LIKE_SYMBOL = "%";
	//excel文件名称
	public static String EXCEL_FILE_NAME = "edi.xls";
	
	/**
	 * 数据库名称
	 */
	public static String DATABASE_CUS_NAME = "yka_customs_standard";
	public static String DATABASE__CIQ_NAME = "yka_ciq_standard";
	
	/**
	 * edi_header表项目信息
	 */
	
	public static class EdiHeaderInfo{
		//表头
		public static String[] TABLE_COLUMN_NAMES = new String[]{
						"条码号", "清单编号", "订单号","主要货物名称","进出口", "是否布控", "重量 ",
						"物流企业名称","发件人","收件人", "收件人地址","件数",	"主键", "保存"};
		//展示字段
		public static String EDI_HEARDER_TABEL_NAME = "edi_header";
		public static String EDI_RECEIPT_TABEL_NAME = "edi_receipt";
		public static String EDI_TABEL_FIELD_BARCODE = "barcode_no";
		public static String EDI_TABEL_FIELD_ENTRYID = "entry_id";
		public static String EDI_TABEL_FIELD_ORDERNO = "order_no";
		public static String EDI_TABEL_FIELD_MAINGNAME = "main_g_name";
		public static String EDI_TABEL_FIELD_IEFLAG = "i_e_flag";
		public static String EDI_TABEL_FIELD_DECTYPE = "dec_type";
		public static String EDI_TABEL_FIELD_GROSSWT = "gross_wt";
		public static String EDI_TABEL_FIELD_LOGISTICSNAME = "logistics_name";
		public static String EDI_TABEL_FIELD_SENDERNAME = "sender_name";
		public static String EDI_TABEL_FIELD_OWNERNAME = "owner_name";
		public static String EDI_TABEL_FIELD_OWNERADDRESS = "owner_address";
		public static String EDI_TABEL_FIELD_PACKNO = "pack_no";
		public static String EDI_TABEL_FIELD_ID = "id";
		//展示字段序号
		public static int EDI_TABEL_FIELD_BARCODE_NO = 0;
		public static int EDI_TABEL_FIELD_ENTRYID_NO = 1;
		public static int EDI_TABEL_FIELD_ORDERNO_NO = 2;
		public static int EDI_TABEL_FIELD_MAINGNAME_NO= 3;
		public static int EDI_TABEL_FIELD_IEFLAG_NO= 4;
		public static int EDI_TABEL_FIELD_DECTYPE_NO= 5;
		public static int EDI_TABEL_FIELD_GROSSWT_NO= 6;
		public static int EDI_TABEL_FIELD_LOGISTICSNAME_NO = 7;
		public static int EDI_TABEL_FIELD_SENDERNAME_NO = 8;
		public static int EDI_TABEL_FIELD_OWNERNAME_NO = 9;
		public static int EDI_TABEL_FIELD_OWNERADDRESS_NO = 10;
		public static int EDI_TABEL_FIELD_PACKNO_NO = 11;
		public static int EDI_TABEL_FIELD_ID_NO= 12;
		public static int EDI_TABEL_FIELD_SAVEBUTTON_NO= 13;
	}
	
	
	/**
	  * @ClassName: MysqlParams
	  * @Description: Mysql数据库常量
	  * @author-csx
	  * @date 2017年4月25日 上午9:13:21
	  *
	  */
	public static class MysqlParams{
		public static String MYSQL_DB_DRIVER = "com.mysql.jdbc.Driver";
		public static String MYSQL_DB_URL_PRIFIX = "jdbc:mysql://";
		public static String MYSQL_DB_URL_SUFFIX = ":3306/";
		public static String MYSQL_DB_URL_IP_SPECIFIC = "localhost";
		public static String MYSQL_DB_USER = "root";
		public static String MYSQL_DB_PASSWORD = "Yka@yunkouan.49";
		public static String MYSQL_DB_PASSWORD_FILE = "D:\\software\\Tomcat\\readme";
		public static String MYSQL_DB_DATABASENAME = "";
	}
	
	/**
	  * @ClassName: EdiParams
	  * @Description: 有关edi信息的常量
	  * @author-csx
	  * @date 2017年4月25日 上午9:30:49
	  *
	  */
	public static class EdiParams{
		public static String IPORT_CHINESE = "进口";
		public static String EPORT_CHINESE = "出口";
		public static String DEC_CHINESE = "布控";
		public static String NDEC_CHINESE = "非布控";
		public static String DEC = "1";
		public static String NDEC = "0";
		public static String IPORT = "I";
		public static String EPORT = "E";
		
		
	}
}
