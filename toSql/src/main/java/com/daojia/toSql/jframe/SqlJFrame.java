package com.daojia.toSql.jframe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.daojia.toSql.generator.SqlGenerator;
import com.daojia.toSql.jframe.adapter.JFrameWindowAdapter;
import com.daojia.toSql.util.Constant;
import com.daojia.toSql.util.Constant.SQLTableInfo;
import com.daojia.toSql.util.FileWriterUtils;
import com.daojia.toSql.util.FiledEnum;
import com.daojia.toSql.util.JCalenderTextField;
import com.daojia.toSql.util.XLSReader;
import com.qt.datapicker.DatePicker;


/**
 * @Title: SqlJFrame.java
 * @Description:Frame
 * @Author：daojia
 * @CreateTime：2018年6月10日上午10:37:29
 * @version v1.0
 */
@SuppressWarnings("serial")
public class SqlJFrame extends JFrame {
	private static Logger log = Logger.getLogger(SqlJFrame.class.getName());
	//数据库连接JPanel
	private JPanel settingPanel;
	//生成Sql数据JPanel
	private JPanel genaratorPanel ;
	//模板导入数据 JPanel
	private JPanel templatePanel ;
	
	private JTextField datePicker;
	
	// 桌面路径
	private File desktop = FileSystemView.getFileSystemView().getHomeDirectory();
	// 当前选择sheet表
	private int currSheetIndex = 0;
	// 表格对象
	private JTable table;
	private DefaultTableModel tableModel;
	// 需要隐藏的行
	private int[] selectedRows;
	

	/**
	 * Create the frame.
	 */
	public SqlJFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("数据库生成工具V0.1");
		setBounds(200, 20, 986, 700);
		
		//创建窗口关闭监听
		addWindowListener(new JFrameWindowAdapter());

		
		/**
		 * JTabbedPane Tab容器 
		 */
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		URL imageUrl = SqlJFrame.class.getClassLoader().getResource("logo.jpg");
		Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);
		setIconImage(image);
		contentPane.setLayout(null);
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 5, 1313, 662);
		contentPane.add(tabbedPane);
		
		/**
		 *加载模板
		 */
		templatePanel = new JPanel();
		templatePanel.setLayout(null);
		tabbedPane.addTab("加载模板", null, templatePanel, null);
		
		final JComboBox<String> excelList = new JComboBox<String>();
		excelList.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		excelList.setBounds(342, 222, 227, 37);
		templatePanel.add(excelList);
		
		JButton chooseExcel = new JButton("上传模板");
		chooseExcel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		chooseExcel.setBounds(472, 141, 144, 37);
		chooseExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser(desktop);
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
				jfc.showDialog(new JLabel(), "选择");  
				File file=jfc.getSelectedFile();
				try {
					if (!file.getName().endsWith(Constant.EXCEL_FILE_SUFFIX)) {
						JOptionPane.showMessageDialog(null, "请选择Excel文件!");
						return;
					}
					// 先清空旧的数据
					excelList.removeAllItems();
					XLSReader.loadXLSFile(file);
					List<String> sheetNames = XLSReader.getSheetNames();
					for (String string : sheetNames) {
						excelList.addItem(string);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		templatePanel.add(chooseExcel);
		
		JButton showTemplate = new JButton("数据修改");
		showTemplate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		showTemplate.setBounds(449, 327, 137, 55);
		// 数据修改
		showTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (XLSReader.getWb() == null) {
					JOptionPane.showMessageDialog(null, "请先上传模板!");
					return;
				}
				String currSheet = excelList.getSelectedItem().toString();
				currSheetIndex = Integer.valueOf(currSheet.split("----")[0].trim());
				// 展示数据
				Sheet sheet = XLSReader.getSheets().get(currSheetIndex);
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					Vector<String> vector = new Vector<String>();
					Row row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						// 非表头行，序号列或字段长度需要转成整形
						if (i > 0 && (j == 0 || j == 4)) {
							vector.add(Double.valueOf(row.getCell(j).toString()).intValue() + "");
						} else {
							vector.add(row.getCell(j).toString());
						}
					}
					if (i == 0) {// 表头
						SQLTableInfo.TABLE_COLUMN_NAMES = vector.toArray(new String[]{});
						tableModel.setColumnIdentifiers(SQLTableInfo.TABLE_COLUMN_NAMES);
					} else {
						// 表体
						tableModel.addRow(vector);
					}
				}
				
				//设置【字段类型】列为JComboBox
				TableColumn filedType = table.getColumnModel().getColumn(SQLTableInfo.FILED_TYPE);
				filedType.setCellEditor(new DefaultCellEditor(new JComboBox<String>(SQLTableInfo.TYPE_COMBOBOXDATA)));
				//设置【是否为空】列为JComboBox
				TableColumn isNull = table.getColumnModel().getColumn(SQLTableInfo.IS_NULL);
				isNull.setCellEditor(new DefaultCellEditor(new JComboBox<String>(SQLTableInfo.ISNOT_COMBOBOXDATA)));
				
				//切换panel
				tabbedPane.setSelectedIndex(1);
			}
		});
		templatePanel.add(showTemplate);
		
		JButton getTemplate = new JButton("下载模板");
		getTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//获取文件导出路径，默认为桌面
					JFileChooser chooser_desti = new JFileChooser(desktop);
					chooser_desti.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser_desti.setDialogTitle("选择文件导出路径");
					int ret = chooser_desti.showOpenDialog(null);
					//选择路径
					if(ret == JFileChooser.APPROVE_OPTION){
						File desFile = chooser_desti.getSelectedFile().getAbsoluteFile();
						// 获取jar包中excel文件
						InputStream is = SqlJFrame.class.getClassLoader().getResourceAsStream(Constant.EXCEL_FILE_NAME);
						// 写到临时文件
						File srcFile = FileWriterUtils.writeToFile(is);
						// 复制临时文件到导出文件
						FileUtils.copyFileToDirectory(srcFile, desFile, true);
					}else if(ret == JFileChooser.CANCEL_OPTION){
						return;
					}
				} catch (Exception e1) {
					log.info("复制文件失败:" + e1.getMessage());
				}
			}
		});
		getTemplate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		getTemplate.setBounds(118, 141, 144, 37);
		templatePanel.add(getTemplate);
		
		JLabel lblNewLabel_2 = new JLabel("说明：获取Excel模板，对模板进行修改后，上传模板，进行sql转换");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(118, 78, 498, 24);
		templatePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Excel文件中可生成标签：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(118, 222, 169, 37);
		templatePanel.add(lblNewLabel_3);
		
		JButton button = new JButton("直接生成");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (XLSReader.getWb() == null) {
					JOptionPane.showMessageDialog(null, "请先上传模板!");
					return;
				}
				String currSheet = excelList.getSelectedItem().toString();
				currSheetIndex = Integer.valueOf(currSheet.split("----")[0].trim());
				// 展示数据
				Object[] message = new Object[2];
				message[0] = new JLabel("语句预览");

				JTextArea cb = new JTextArea();
				SqlGenerator sg = new SqlGenerator();
				
				// 生成sql
				cb.setText(sg.generatorByIndex(currSheetIndex));
				message[1] = cb;

				// Options
				String[] options = {"关闭"};
				int result = JOptionPane.showOptionDialog(SqlJFrame.this,
						message, // the dialog message array
						"脚本展示", // the title of the dialog window
						JOptionPane.DEFAULT_OPTION, // option type
						JOptionPane.INFORMATION_MESSAGE, // message type
						null, // optional icon, use null to use the default icon
						options, // options string array, will be made into
									// buttons
						options[0] // option that should be made into a default
									// button
						);
				switch (result) {
					case 0:
						//TODO if need todo 
						break;
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(240, 327, 137, 55);
		templatePanel.add(button);
		
		
		//TODO 
		/**
		 * sql生成模板
		 */
		genaratorPanel = new JPanel();
		genaratorPanel.setBackground(Color.WHITE);
		genaratorPanel.setLayout(null);
		tabbedPane.addTab("生成语句", null, genaratorPanel, null);
		
		//初始显示数据
		Object[][] data = new Object[0][SQLTableInfo.TABLE_COLUMN_NAMES.length];;

		tableModel = new DefaultTableModel(data, SQLTableInfo.TABLE_COLUMN_NAMES);
		table = new JTable(tableModel);
		table.setFont(new Font("微软雅黑",Font.PLAIN,14));
		//设置表头样式
		JTableHeader header=table.getTableHeader();
		header.setFont(new Font("微软雅黑",Font.PLAIN,16));
		//宽度自适应
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//设置高度
		table.setRowHeight(35);
		//列不可拖动
		table.getTableHeader().setReorderingAllowed(false);
		
		//增加滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 120, 928, 456);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		genaratorPanel.add(scrollPane);
		
		final JButton removeRow = new JButton("隐藏选中行");
		//添加移除一行事件绑定
		removeRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 进行隐藏操作
				if ("隐藏选中行".equals(removeRow.getText())) {
					selectedRows = table.getSelectedRows();
					if(selectedRows == null || selectedRows.length < 1){
						JOptionPane.showMessageDialog(null, "请选择要隐藏的行!");
						return;
					}
					for (int i = 0; i < selectedRows.length; i++) {
						int rowId = selectedRows[i];
						table.setRowHeight(rowId, 1);
					}
					removeRow.setText("显示隐藏行");
				} else {
					// 进行恢复操作
					for (int i = 0; i < selectedRows.length; i++) {
						int rowId = selectedRows[i];
						table.setRowHeight(rowId, 35);
					}
					removeRow.setText("隐藏选中行");
				}
			}
		});
		removeRow.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		removeRow.setBounds(317, 30, 115, 36);
		genaratorPanel.add(removeRow);
		
		final JButton generatorButton = new JButton("生成语句");
		generatorButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		generatorButton.setBounds(645, 30, 115, 36);
		generatorButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (XLSReader.getWb() == null) {
					JOptionPane.showMessageDialog(null, "请先上传模板!");
					return;
				}
				String currSheet = excelList.getSelectedItem().toString();
				currSheetIndex = Integer.valueOf(currSheet.split("----")[0].trim());
				// 展示数据
				Object[] message = new Object[2];
				message[0] = new JLabel("语句预览");

				JTextArea cb = new JTextArea();
				SqlGenerator sg = new SqlGenerator();
				
				// 生成sql
				cb.setText(sg.generatorByTableData(currSheetIndex, table));
				message[1] = cb;

				// Options
				String[] options = {"关闭"};
				int result = JOptionPane.showOptionDialog(SqlJFrame.this,
						message, // the dialog message array
						"脚本展示", // the title of the dialog window
						JOptionPane.DEFAULT_OPTION, // option type
						JOptionPane.INFORMATION_MESSAGE, // message type
						null, // optional icon, use null to use the default icon
						options, // options string array, will be made into
									// buttons
						options[0] // option that should be made into a default
									// button
						);
				switch (result) {
					case 0:
						//TODO if need todo 
						break;
				}
			}
		});
		genaratorPanel.add(generatorButton);
		
		JLabel lblNewLabel_1 = new JLabel("在末尾新增空白行");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(37, 76, 115, 21);
		genaratorPanel.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("隐藏/恢复选中行(可多选)");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_2.setBounds(317, 76, 146, 21);
		genaratorPanel.add(label_2);
		
		JButton quitConnect = new JButton("返回");
		quitConnect.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		quitConnect.setBounds(806, 30, 108, 36);
		quitConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清除已展示数据
				for (int i = table.getRowCount(); i > 0 ; i--) {
					tableModel.removeRow(i - 1);
				}
				tableModel.setRowCount(table.getRowCount());
				// 切换tab
				tabbedPane.setSelectedIndex(0);
			}
		});
		genaratorPanel.add(quitConnect);
		
		JButton copySelect = new JButton("复制选中行");
		copySelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					return;
				}
				Vector<String> clone = new Vector<String>();
				for (int i = 0; i < table.getColumnCount(); i++) {
					clone.add(table.getValueAt(selectedRow, i).toString());
				}
				tableModel.insertRow(++selectedRow, clone);
				// 更新序号
				updateRowNum();
			}
		});
		copySelect.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		copySelect.setBounds(175, 29, 115, 36);
		genaratorPanel.add(copySelect);
		
		JLabel label_6 = new JLabel("复制选中行增加到末尾");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		label_6.setBounds(175, 76, 115, 21);
		genaratorPanel.add(label_6);
		
		JButton btnNewButton_1 = new JButton("删除选中行");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] deleteRows = table.getSelectedRows();
				//删除选中行
				if(deleteRows == null || deleteRows.length < 1){
					JOptionPane.showMessageDialog(null, "请选择要删除的行!");
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "确认删除，无法撤销?", "确认删除", JOptionPane.OK_OPTION);
				if (result != JOptionPane.OK_OPTION) {
					return;
				}
				
				for (int i = deleteRows.length ; i > 0; i--) {
					int rowId = deleteRows[i-1];
					tableModel.removeRow(rowId);
				}
				tableModel.setRowCount(table.getRowCount());
				// 更新序号
				updateRowNum();
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.setBounds(469, 30, 115, 36);
		genaratorPanel.add(btnNewButton_1);
		
		JButton addRow = new JButton("添加一条");
		addRow.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		addRow.setBounds(37, 30, 115, 36);
		genaratorPanel.add(addRow);
		// 添加一条
		addRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 在选中行处添加一行，若没有选中行则在第一行添加
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					tableModel.insertRow(0, new Vector<Object>());
				} else {
					tableModel.insertRow(++selectedRow, new Vector<Object>());
				}
				
			}
		});
		
		//TODO 
		/***
		 * 配置pannel
		 */
		settingPanel = new JPanel();
		tabbedPane.addTab("语句配置", null, settingPanel, null);
		settingPanel.setLayout(null);
		
		JComboBox<String> dbConnect_db_type = new JComboBox<String>();
		dbConnect_db_type.setBounds(289, 34, 174, 45);
		dbConnect_db_type.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dbConnect_db_type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mysql", "Oracle", "SqlServer"}));
		settingPanel.add(dbConnect_db_type);
		
		JLabel lblNewLabel = new JLabel("预读取配置文件中的配置");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(140, 89, 141, 26);
		settingPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("若修改了Excel文件中的列顺序，则需要这里进行设置，并保存");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(140, 125, 375, 26);
		settingPanel.add(lblNewLabel_4);
		
		JLabel label = new JLabel("数据库类型：");
		label.setBounds(140, 34, 141, 45);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(label);
		
		JLabel lblip = new JLabel("字段描述：");
		lblip.setBounds(140, 161, 100, 45);
		lblip.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(lblip);
		
		JLabel label_1 = new JLabel("字段类型：");
		label_1.setBounds(140, 340, 100, 45);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(label_1);
		
		JLabel lblmysql = new JLabel("注：暂支持Mysql，该项可忽略");
		lblmysql.setBounds(531, 40, 155, 37);
		lblmysql.setForeground(Color.RED);
		lblmysql.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		settingPanel.add(lblmysql);
		
		JLabel label_7 = new JLabel("字段名称：");
		label_7.setBounds(140, 247, 100, 45);
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(label_7);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_1.setBounds(280, 161, 94, 45);
		comboBox_1.setName(FiledEnum.FIELD_COMMEND.getName());
		comboBox_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_2.setBounds(280, 247, 94, 45);
		comboBox_2.setName(FiledEnum.FILED_NAME.getName());
		comboBox_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(comboBox_2);
		
		JLabel label_3 = new JLabel("字段长度：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_3.setBounds(140, 420, 100, 45);
		settingPanel.add(label_3);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_3.setName(FiledEnum.FIELD_TYPE.getName());
		comboBox_3.setBounds(280, 327, 94, 45);
		settingPanel.add(comboBox_3);
		
		JLabel label_4 = new JLabel("默认值：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_4.setBounds(452, 163, 100, 45);
		settingPanel.add(label_4);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_4.setBounds(280, 420, 94, 45);
		comboBox_4.setName(FiledEnum.FIELD_LENGTH.getName());
		settingPanel.add(comboBox_4);
		
		JLabel label_8 = new JLabel("是否为空：");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_8.setBounds(452, 247, 100, 45);
		settingPanel.add(label_8);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_5.setBounds(587, 161, 94, 45);
		comboBox_5.setName(FiledEnum.FIELD_DEFAULVALUE.getName());
		settingPanel.add(comboBox_5);
		
		JComboBox<String> comboBox_6 = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2","3", "4", "5","6", "7"}));
		comboBox_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox_6.setBounds(587, 247, 94, 45);
		comboBox_6.setName(FiledEnum.IS_NULL.getName());
		settingPanel.add(comboBox_6);
		
		final JButton connectCheck = new JButton("保存配置");
		connectCheck.setBounds(351, 500, 155, 45);
		connectCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 将当前的配置，写入枚举对象中，但不持久化
				updateSettingDateToEnum();
			}
		});
		connectCheck.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		settingPanel.add(connectCheck);
		
		//时间插件
		datePicker = new JCalenderTextField();
		datePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatePicker dp = new DatePicker((Observer) datePicker, Locale.SIMPLIFIED_CHINESE);
				Date selectedDate = dp.parseDate(datePicker.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(datePicker);
			}
		});
//		datePicker.setBounds(668, 142, 144, 37);
//		templatePanel.add(datePicker);
//		datePicker.setColumns(10);
		
		// JTabbedPane标签切换的监听
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
				int selectedIndex = tabbedPane.getSelectedIndex();
				switch (selectedIndex) {
					case 2:// 配置标签页
						JPanel component = (JPanel) tabbedPane.getComponents()[2];
						updateSettingData(component.getComponents());
						break;
				}
			}
		});
	}
	
	
	/**
	 * @Description:更新序号
	 * @Method: updateRowNum
	 * @Author daojia
	 * @CreateTime 2018年6月10日上午10:58:11
	 * @throws
	 */
	private void updateRowNum(){
		for (int i = 0; i < table.getRowCount(); i++) {
			int j = i+1;	//序号
			table.setValueAt(j, i, 0);
		}
	}
	
	
	/**
	 * @Description:更新配置标签页数据
	 * @Method: updateSettingData
	 * @Author daojia
	 * @CreateTime 2018年6月10日上午11:30:02
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void updateSettingData(Component[] components){
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof JComboBox) {
				JComboBox<String> jComboBox = (JComboBox<String>) components[i];
				if (jComboBox.getName() == null) {
					continue;
				}
				switch(jComboBox.getName()){
					case "FILED_NAME":
						jComboBox.setSelectedIndex(FiledEnum.FILED_NAME.getValueIndex());
						break;
					case "FIELD_TYPE":
						jComboBox.setSelectedIndex(FiledEnum.FIELD_TYPE.getValueIndex());
						break;
					case "FIELD_LENGTH":
						jComboBox.setSelectedIndex(FiledEnum.FIELD_LENGTH.getValueIndex());
						break;
					case "FIELD_DEFAULVALUE":
						jComboBox.setSelectedIndex(FiledEnum.FIELD_DEFAULVALUE.getValueIndex());
						break;
					case "FIELD_COMMEND":
						jComboBox.setSelectedIndex(FiledEnum.FIELD_COMMEND.getValueIndex());
						break;
					case "IS_NULL":
						jComboBox.setSelectedIndex(FiledEnum.IS_NULL.getValueIndex());
						break;
				}
			}
		}
	}
	
	
	/**
	 * @Description:将配置Panel中的值，更新到枚举内存中
	 * @Method: updateSettingDateToEnum
	 * @Author daojia
	 * @CreateTime 2018年6月10日下午12:24:25
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private void updateSettingDateToEnum(){
		// 用于判断是否有相同的编号
		List<Integer> indexs = new ArrayList<Integer>();
		Component[] components = settingPanel.getComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof JComboBox) {
				JComboBox<String> jComboBox = (JComboBox<String>) components[i];
				if (jComboBox.getName() == null) {
					continue;
				}
				// 校验当前index是否重复使用
				int result = checkIndexRepeat(indexs, Integer.valueOf(jComboBox.getSelectedItem().toString()));
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "当前位置：【"+ result +"】重复设置，请修改!");
					return;
				}
				
				switch(jComboBox.getName()){
					case "FILED_NAME":
						FiledEnum.FILED_NAME.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
					case "FIELD_TYPE":
						FiledEnum.FIELD_TYPE.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
					case "FIELD_LENGTH":
						FiledEnum.FIELD_LENGTH.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
					case "FIELD_DEFAULVALUE":
						FiledEnum.FIELD_DEFAULVALUE.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
					case "FIELD_COMMEND":
						FiledEnum.FIELD_COMMEND.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
					case "IS_NULL":
						FiledEnum.IS_NULL.setValueIndex(Integer.valueOf(jComboBox.getSelectedItem().toString()));
						break;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "已保存!");
	}
	
	
	/**
	 * @Description:判断当前index是否已经被使用，通一个index只能用一次
	 * @Method: checkIndexRepeat
	 * @ReturnType int
	 * @Author daojia
	 * @CreateTime 2018年6月10日下午12:36:24
	 * @throws
	 */
	public int checkIndexRepeat(List<Integer> indexs, int currIndex){
		if (indexs.contains(currIndex)) {
			return currIndex;
		}
		indexs.add(currIndex);
		return 0;
	}
}
