package com.yunkouan.db.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.commons.io.FileUtils;

//import com.qt.datapicker.DatePicker;
import com.yunkouan.db.common.datapicker.JCalenderTextField;
import com.yunkouan.db.common.jdbc.DBCheck;
import com.yunkouan.db.common.jdbc.DBUtils;
import com.yunkouan.db.common.jdbc.SqlUtils;
import com.yunkouan.db.common.listener.JFrameWindowAdapter;
import com.yunkouan.db.common.model.EdiHeaderModel;
import com.yunkouan.db.common.utils.Constant;
import com.yunkouan.db.common.utils.Constant.EdiHeaderInfo;
import com.yunkouan.db.common.utils.Constant.EdiParams;
import com.yunkouan.db.common.utils.Constant.MysqlParams;
import com.yunkouan.db.common.utils.ExcelTransUtils;
import com.yunkouan.db.common.utils.FileWriterUtils;
import com.yunkouan.db.common.utils.PassWordService;

@SuppressWarnings("serial")
public class DBJFrame extends JFrame {
	private static Logger log = Logger.getLogger(DBJFrame.class.getName());
	
	//记录excel文件路径
	private JTextField excelFile;
	//数据库连接密码
	private JTextField dbConnect_db_pwd;
	//数据库连接JPanel
	private JPanel dbConnect;
	//手工导入数据JPanel
	private JPanel importByHand ;
	//Excel导入数据 JPanel
	private JPanel importByExcel ;
	private JTextField datePicker;

	/**
	 * Create the frame.
	 */
	public DBJFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("\u6570\u636E\u5E93\u5BFC\u5165\u5DE5\u5177V1.0");
		setBounds(50, 50, 1319, 695);
		
		//创建窗口关闭监听
		addWindowListener(new JFrameWindowAdapter());

		
		/**
		 * JTabbedPane Tab容器 
		 */
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		URL imageUrl = DBJFrame.class.getClassLoader().getResource("icon.jpg");
		Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);
		setIconImage(image);
		contentPane.setLayout(null);
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 5, 1313, 662);
		contentPane.add(tabbedPane);
		
		/**
		 *数据库连接测试 
		 */
		dbConnect = new JPanel();
		tabbedPane.addTab("\u6570\u636E\u5E93\u8FDE\u63A5\u6D4B\u8BD5", null, dbConnect, null);
		dbConnect.setLayout(null);
		
		JComboBox<String> dbConnect_db_type = new JComboBox<String>();
		dbConnect_db_type.setBounds(568, 69, 174, 45);
		dbConnect_db_type.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect_db_type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mysql", "Oracle", "SqlServer"}));
		dbConnect.add(dbConnect_db_type);
		
		JLabel label = new JLabel("\u6570\u636E\u5E93\u7C7B\u578B\uFF1A");
		label.setBounds(399, 69, 141, 45);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(label);
		
		JLabel lblip = new JLabel("\u6570\u636E\u5E93\u670D\u52A1\u5668IP:");
		lblip.setBounds(399, 157, 141, 45);
		lblip.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(lblip);
		
		final JTextField dbConnect_db_ip = new JTextField();
		dbConnect_db_ip.setBounds(568, 157, 174, 45);
		dbConnect_db_ip.setText(MysqlParams.MYSQL_DB_URL_IP_SPECIFIC);
		dbConnect_db_ip.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(dbConnect_db_ip);
		dbConnect_db_ip.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8FDE\u63A5\u6570\u636E\u5E93\u540D\u79F0");
		label_1.setBounds(399, 334, 141, 45);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(label_1);
		
		final JComboBox<String> dbConnect_db_name = new JComboBox<String>();
		dbConnect_db_name.setBounds(568, 334, 174, 45);
		dbConnect_db_name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect_db_name.setModel(new DefaultComboBoxModel<String>(new String[] {"\u6D77\u5173\u6570\u636E\u5E93", "\u56FD\u68C0\u6570\u636E\u5E93"}));
		dbConnect.add(dbConnect_db_name);
		
		final JButton connectCheck = new JButton("\u8FDE\u63A5\u6D4B\u8BD5");
		connectCheck.setBounds(551, 421, 206, 45);
		connectCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ip
				MysqlParams.MYSQL_DB_URL_IP_SPECIFIC = dbConnect_db_ip.getText();
				//db_name
				if(dbConnect_db_name.getSelectedItem().toString().contains("海关")){
					MysqlParams.MYSQL_DB_DATABASENAME = Constant.DATABASE_CUS_NAME;
				}else{
					MysqlParams.MYSQL_DB_DATABASENAME = Constant.DATABASE__CIQ_NAME;
				}
				//pwd
				MysqlParams.MYSQL_DB_PASSWORD = dbConnect_db_pwd.getText();
				//test
				boolean result = DBCheck.dbConnect(MysqlParams.MYSQL_DB_URL_IP_SPECIFIC, MysqlParams.MYSQL_DB_PASSWORD, MysqlParams.MYSQL_DB_DATABASENAME);
				//连接结果
				//成功时，展示数据导入Panel
				if(result){
					tabbedPane.addTab("\u624B\u5DE5\u5F55\u5165\u6570\u636E\u5E93\u6570\u636E", null, importByHand, null);
					tabbedPane.addTab("Excel\u5BFC\u5165\u6570\u636E\u5E93\u6570\u636E", null, importByExcel, null);
					tabbedPane.remove(dbConnect);
				}else{
					//失败时，提示
					JOptionPane.showMessageDialog(null, result == true ? "连接成功!" : "连接失败!");
				}
			}
		});
		connectCheck.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(connectCheck);
		
		JLabel lblmysql = new JLabel("\u6CE8\uFF1A\u6682\u652F\u6301Mysql\uFF0C\u8BE5\u9879\u53EF\u5FFD\u7565");
		lblmysql.setBounds(806, 75, 155, 37);
		lblmysql.setForeground(Color.RED);
		lblmysql.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		dbConnect.add(lblmysql);
		
		JLabel label_7 = new JLabel("\u6570\u636E\u5E93\u670D\u52A1\u5668\u5BC6\u7801:");
		label_7.setBounds(399, 241, 141, 45);
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect.add(label_7);
		
		dbConnect_db_pwd = new JTextField();
		dbConnect_db_pwd.setBounds(568, 241, 174, 45);
		String pwd = PassWordService.getReadMeInfos().getDbPassword();
		if (null == pwd) {
			pwd = "Yka@yunkouan.";
		}
		dbConnect_db_pwd.setText(pwd);
		dbConnect_db_pwd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect_db_pwd.setColumns(10);
		dbConnect.add(dbConnect_db_pwd);
		// 登录绑定回车键
		dbConnect_db_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					connectCheck.doClick();
				}
			}
		});
		
		/**
		 * 手工导入数据库数据
		 */
		importByHand = new JPanel();
		//TODO
//		tabbedPane.addTab("\u624B\u5DE5\u5F55\u5165\u6570\u636E\u5E93\u6570\u636E", null, importByHand, null);
		importByHand.setLayout(null);
		
		JButton addRow = new JButton("\u6DFB\u52A0\u4E00\u6761");
		addRow.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		addRow.setBounds(37, 30, 115, 36);
		importByHand.add(addRow);
		
		//初始显示数据
		String querySql = SqlUtils.getQueryAllSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE);
		List<Vector<String>> queryAllInfo = DBUtils.queryAllInfo(querySql);
		Object[][] data;
		//初始化失败时，数据为null
		if(queryAllInfo == null){
			data = new Object[0][EdiHeaderInfo.TABLE_COLUMN_NAMES.length];
		}else{
			data = new Object[queryAllInfo.size()][EdiHeaderInfo.TABLE_COLUMN_NAMES.length];
			if(queryAllInfo.size() > 0){
				for (int i = 0; i < queryAllInfo.size(); i++) {
					data[i] = queryAllInfo.get(i).toArray();
				}
			}
		}

		final DefaultTableModel tableModel = new DefaultTableModel(data, EdiHeaderInfo.TABLE_COLUMN_NAMES);
		final JTable table = new JTable(tableModel);
		table.setFont(new Font("微软雅黑",Font.PLAIN,14));
		//取消宽度自适应
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//设置每一列的宽度
		for (int j = 0; j < EdiHeaderInfo.TABLE_COLUMN_NAMES.length; j++) {
			TableColumn col = table.getColumnModel().getColumn(j);
			//主键列不可见
			if(j == EdiHeaderInfo.EDI_TABEL_FIELD_ID_NO){
				col.setMinWidth(0);
				col.setMaxWidth(0);
			//进出口、是否布控、重量
			}else if (j >= EdiHeaderInfo.EDI_TABEL_FIELD_IEFLAG_NO && j <= EdiHeaderInfo.EDI_TABEL_FIELD_GROSSWT_NO){
				col.setPreferredWidth(90);
			//件数
			}else if (j == EdiHeaderInfo.EDI_TABEL_FIELD_PACKNO_NO || j == EdiHeaderInfo.EDI_TABEL_FIELD_SAVEBUTTON_NO){
				col.setPreferredWidth(80);
			}else{
				col.setPreferredWidth(120);
			}
		}
		//设置高度
		table.setRowHeight(35);
		//列不可拖动
		table.getTableHeader().setReorderingAllowed(false);
		//设置进出口项为JComboBox 
		TableColumn ioColumn = table.getColumnModel().getColumn(EdiHeaderInfo.EDI_TABEL_FIELD_IEFLAG_NO);
		JComboBox<String> ioComboBox = new JComboBox<String>();
		ioComboBox.addItem(EdiParams.IPORT_CHINESE);
		ioComboBox.addItem(EdiParams.EPORT_CHINESE);
		ioColumn.setCellEditor(new DefaultCellEditor(ioComboBox));
		//设置是否布控项为JComboBox
		TableColumn decColumn = table.getColumnModel().getColumn(EdiHeaderInfo.EDI_TABEL_FIELD_DECTYPE_NO);
		JComboBox<String> decComboBox = new JComboBox<String>();
		decComboBox.addItem(EdiParams.DEC_CHINESE);
		decComboBox.addItem(EdiParams.NDEC_CHINESE);
		decColumn.setCellEditor(new DefaultCellEditor(decComboBox));
		//添加保存按钮
		TableColumn column = table.getColumnModel().getColumn(EdiHeaderInfo.EDI_TABEL_FIELD_SAVEBUTTON_NO);
		JTableButton jtb = new JTableButton(table);
		column.setCellRenderer(jtb);
		column.setCellEditor(jtb);
		//设置表头样式
		JTableHeader header=table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,16));
		//增加滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 120, 1262, 442);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		importByHand.add(scrollPane);
		
		JButton removeRow = new JButton("\u9690\u85CF\u9009\u4E2D\u884C");
		//添加移除一行事件绑定
		removeRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				//暂时只删除选中行好了
				if(selectedRows == null || selectedRows.length < 1){
					JOptionPane.showMessageDialog(null, "请选择要删除的行!");
					return;
				}
				for (int i = 0; i < selectedRows.length; i++) {
					int rowId = selectedRows[i];
					tableModel.removeRow(rowId - i);
				}
				tableModel.setRowCount(table.getRowCount());
			}
		});
		removeRow.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		removeRow.setBounds(317, 30, 115, 36);
		importByHand.add(removeRow);
		
		final JTextField queryBarcode = new JTextField();
		queryBarcode.setBounds(649, 30, 121, 36);
		importByHand.add(queryBarcode);
		queryBarcode.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u6761\u7801\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(554, 29, 85, 36);
		importByHand.add(lblNewLabel);
		
		final JButton queryByBarcode = new JButton("\u67E5\u8BE2");
		//根据条码号查询数据
		queryByBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//需要坚持是否连接了数据库
				if(!DBUtils.isConnect()){
					JOptionPane.showMessageDialog(null, "请先进行数据库连接测试!");
					return;
				}
				//barcode
				String barcode = queryBarcode.getText();
				//结果集
				List<Vector<String>> queryAllInfo;
				//barcode为空，则查询所有
				if(Constant.EMPTY_STRING.endsWith(barcode) || barcode == null){
					String querySql = SqlUtils.getQueryAllSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE);
					queryAllInfo = DBUtils.queryAllInfo(querySql);
				}else{
					String sql = SqlUtils.getQueryByFieleSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE);
					queryAllInfo = DBUtils.queryInfoByBarcode(sql, barcode);
				}
				//渲染数据
				if(queryAllInfo.size() > 0){
					//查询到结果后，移除已展示数据
					if(table.getRowCount() > 0){
						for (int i = table.getRowCount() - 1 ; i >= 0; i--) {
							tableModel.removeRow(i);
						}
					}
					//展示查询数据
					for (int i = 0; i < queryAllInfo.size(); i++) {
						tableModel.addRow(queryAllInfo.get(i));
					}
				}
			}
		});
		queryByBarcode.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		queryByBarcode.setBounds(799, 30, 115, 36);
		importByHand.add(queryByBarcode);
		
		//条码框绑定键盘按键
		queryBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					queryByBarcode.doClick();
				}
			}
		});
		
		//JTable行添加行点击事件，绑定键盘回车键
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					JButton jbutton = (JButton) new JTableButton(table).getCellEditorValue();
					jbutton.doClick();
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("在第一行处新增空白行");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(37, 76, 115, 21);
		importByHand.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("\u5220\u9664(\u53EF\u591A\u9009)\u672A\u4FDD\u5B58\u884C\uFF0C\u5BF9\u5DF2\u4FDD\u5B58\u8BB0\u5F55\u505A\u9690\u85CF\uFF0C\u53EF\u901A\u8FC7\u67E5\u8BE2\u518D\u6B21\u83B7\u53D6");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_2.setBounds(317, 76, 309, 21);
		importByHand.add(label_2);
		
		JLabel label_3 = new JLabel("\u67E5\u8BE2\u7ED1\u5B9A\uFF1A\u56DE\u8F66\u952E");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_3.setBounds(932, 30, 85, 21);
		importByHand.add(label_3);
		
		JLabel label_4 = new JLabel("\u4FDD\u5B58\u7ED1\u5B9A\uFF1A\u7A7A\u683C\u952E");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_4.setBounds(932, 61, 85, 21);
		importByHand.add(label_4);
		
		JButton quitConnect = new JButton("断开连接");
		quitConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出是否退出的确认框,是：0，否：1，取消：2
				int result = JOptionPane.showConfirmDialog(null, "确认断开连接?", "断开连接", JOptionPane.OK_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					tabbedPane.remove(importByHand);
					tabbedPane.remove(importByExcel);
					tabbedPane.addTab("\u6570\u636E\u5E93\u8FDE\u63A5\u6D4B\u8BD5", null, dbConnect, null);
					// 设置数据库未连接
					DBUtils.setConnect(false);
				}
			}
		});
		quitConnect.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		quitConnect.setBounds(1119, 30, 108, 36);
		importByHand.add(quitConnect);
		
		JButton copySelect = new JButton("复制选中行");
		copySelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EdiHeaderModel ediModel = JTableButton.generalModel(table);
				// 空白行不可用于复制
				if (ediModel == null) {
					return;
				}
				tableModel.insertRow(0, new Vector<Object>());
				int selectedRow = table.getSelectedRow();
				if (selectedRow == 0) {
					selectedRow ++;
				}
				table.setRowSelectionInterval(selectedRow, selectedRow);
				// 添加数据
//				tableModel.setValueAt(ediModel.getBarcode_no(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE_NO);
				tableModel.setValueAt(ediModel.getEntry_id(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_ENTRYID_NO);
				tableModel.setValueAt(ediModel.getOrder_no(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_ORDERNO_NO);
				tableModel.setValueAt(ediModel.getMain_g_name(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_MAINGNAME_NO);
				tableModel.setValueAt(ediModel.getI_e_flag(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_IEFLAG_NO);
				tableModel.setValueAt(ediModel.getDec_Type(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_DECTYPE_NO);
				tableModel.setValueAt(ediModel.getGross_wt(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_GROSSWT_NO);
				tableModel.setValueAt(ediModel.getLogistics_name(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_LOGISTICSNAME_NO);
				tableModel.setValueAt(ediModel.getSender_name(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_SENDERNAME_NO);
				tableModel.setValueAt(ediModel.getOwner_name(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_OWNERNAME_NO);
				tableModel.setValueAt(ediModel.getOwner_address(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_OWNERADDRESS_NO);
				tableModel.setValueAt(ediModel.getPack_no(), 0, EdiHeaderInfo.EDI_TABEL_FIELD_PACKNO_NO);
			}
		});
		copySelect.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		copySelect.setBounds(175, 29, 115, 36);
		importByHand.add(copySelect);
		
		JLabel label_6 = new JLabel("在首行处复制选中行");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_6.setBounds(175, 76, 115, 21);
		importByHand.add(label_6);
		//增加行事件绑定
		addRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.insertRow(0, new Vector<Object>());
			}
		});
		
		
		/**
		 * Excel导入数据库数据
		 */
		importByExcel = new JPanel();
		//TODO
//		tabbedPane.addTab("Excel\u5BFC\u5165\u6570\u636E\u5E93\u6570\u636E", null, importByExcel, null);
		importByExcel.setLayout(null);
		
		JButton chooseExcel = new JButton("\u4E0A\u4F20\u4FEE\u6539\u540E\u6587\u4EF6\uFF1A");
		chooseExcel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		chooseExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser(excelFile.getText());  
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
				jfc.showDialog(new JLabel(), "选择");  
				File file=jfc.getSelectedFile();
				excelFile.setText(file.getAbsolutePath());
			}
		});
		chooseExcel.setBounds(420, 141, 144, 37);
		importByExcel.add(chooseExcel);
		
		JLabel label_5 = new JLabel("\u4E0A\u4F20\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_5.setBounds(238, 222, 144, 37);
		importByExcel.add(label_5);
		
		excelFile = new JTextField();
		excelFile.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		excelFile.setBounds(420, 222, 446, 37);
		importByExcel.add(excelFile);
		excelFile.setColumns(10);
		
		JButton importExcel = new JButton("\u6570\u636E\u5BFC\u5165");
		//文件导入
		importExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String excelFilePath = excelFile.getText();
				File excelFile = new File(excelFilePath);
				if(excelFile.isDirectory()){
					JOptionPane.showMessageDialog(null, "请选择上传文件!");
					return;
				}
				List<EdiHeaderModel> models = ExcelTransUtils.fileToModel(new File(excelFilePath));
				if(models == null || models.size() == 0){
					JOptionPane.showMessageDialog(null, "导入文件格式不正确或文件内容为空!");
					return;
				}
				int insertCount = 0;
				int updateCount = 0;
				for (EdiHeaderModel model : models) {
					//校验此包裹号是否已存在，若存在，则进行更新
					List<Vector<String>> vecs = DBUtils.queryInfoByBarcode(SqlUtils.getQueryByFieleSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE), model.getBarcode_no());
					if(vecs != null && vecs.size() > 0){
						String id = vecs.get(0).get(EdiHeaderInfo.EDI_TABEL_FIELD_ID_NO);
						model.setId(id);
						DBUtils.update(SqlUtils.getUpdateSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, model), id);
						updateCount++;
						// 更新EDI信息时，判断是否需要新建回执信息：若根据条码号可以获取到回执信息则不处理，若未获取到，则新增回执信息
						List<Vector<String>> receiptVecs = DBUtils.queryReceiptInfoByBarcode(SqlUtils.getQueryByFieleSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE), model.getBarcode_no());
						// 若未获取到，则新增回执信息
						if (receiptVecs.size() == 0) {
							DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, JTableButton.generatorReceiptModel(model)));
						}
					}else{
						model.setId(new Date().getTime() + "");
						DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, model));
						//新增时，创建回执信息
						DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, JTableButton.generatorReceiptModel(model)));
						insertCount++;
					}
				}
				JOptionPane.showMessageDialog(null, "导入完毕! 插入数量：" + insertCount + ", 更新数量：" + updateCount);
			}
		});
		importExcel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		importExcel.setBounds(427, 327, 137, 55);
		importByExcel.add(importExcel);
		
		JButton getTemplate = new JButton("\u83B7\u53D6\u5BFC\u5165\u6A21\u677F");
		getTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//获取桌面路径
					FileSystemView fsv = FileSystemView.getFileSystemView();
					File desktop = fsv.getHomeDirectory();
					String desktopPath = desktop.getAbsolutePath();
					//获取文件导出路径，默认为桌面
					JFileChooser chooser_desti = new JFileChooser(desktopPath);
					chooser_desti.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser_desti.setDialogTitle("选择文件导出路径");
					int ret = chooser_desti.showOpenDialog(null);
					//选择路径
					if(ret == JFileChooser.APPROVE_OPTION){
						File desFile = chooser_desti.getSelectedFile().getAbsoluteFile();
						excelFile.setText(desFile.getAbsolutePath());
						//获取jar包中excel文件
						InputStream is = DBJFrame.class.getClassLoader().getResourceAsStream(Constant.EXCEL_FILE_NAME);
						//写到临时文件
						File srcFile = FileWriterUtils.writeToFile(is);
						//复制临时文件到导出文件
						FileUtils.copyFileToDirectory(srcFile, desFile, true);
					}else if(ret == JFileChooser.CANCEL_OPTION){
						return;
					}
				} catch (IOException e1) {
					log.info("复制文件失败:" + e1.getMessage());
				}
			}
		});
		getTemplate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		getTemplate.setBounds(238, 141, 144, 37);
		importByExcel.add(getTemplate);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF4\u660E\uFF1A\u83B7\u53D6Excel\u6A21\u677F\uFF0C\u5BF9\u6A21\u677F\u8FDB\u884C\u4FEE\u6539\u540E\uFF0C\u4E0A\u4F20\u6587\u4EF6\u5E76\u8FDB\u884C\u6570\u636E\u5BFC\u5165");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(238, 85, 498, 24);
		importByExcel.add(lblNewLabel_2);

		//时间插件
		datePicker = new JCalenderTextField();
		datePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//                DatePicker dp = new DatePicker((Observer) datePicker, Locale.SIMPLIFIED_CHINESE);
//                Date selectedDate = dp.parseDate(datePicker.getText());
//                dp.setSelectedDate(selectedDate);
//                dp.start(datePicker);
			}
		});
		datePicker.setBounds(668, 142, 144, 37);
//		importByExcel.add(datePicker);
		datePicker.setColumns(10);
	}
}
