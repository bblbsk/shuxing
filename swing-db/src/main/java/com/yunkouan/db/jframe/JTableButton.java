package com.yunkouan.db.jframe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.yunkouan.db.common.jdbc.DBUtils;
import com.yunkouan.db.common.jdbc.SqlUtils;
import com.yunkouan.db.common.model.EdiHeaderModel;
import com.yunkouan.db.common.model.EdiReceiptModel;
import com.yunkouan.db.common.utils.Constant;
import com.yunkouan.db.common.utils.Constant.EdiHeaderInfo;
import com.yunkouan.db.common.utils.Constant.EdiParams;

/**
  * @ClassName: JTableButton
  * @Description: JTable中使用的JButton
  * @author-csx
  * @date 2017年5月3日 上午9:48:40
  *
  */
public class JTableButton extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SqlUtils.class.getName());
	
	private JButton jButton = new JButton("保存");
	private static boolean isInsert = true;
	
	public JTableButton(final JTable table) {
		super();
		jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		jButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//数据库已连接
				if(!DBUtils.isConnect()){
					//数据库未连接
					JOptionPane.showMessageDialog(null, "保存前，需进行数据库连接测试!");
					return;
				}
				EdiHeaderModel ediHeaderModel = generalModel(table);
				//进行数据库操作
				if(ediHeaderModel != null){
					if(isInsert){
						//校验此包裹号是否已存在，若存在，则提示更新
						List<Vector<String>> vecs = DBUtils.queryInfoByBarcode(SqlUtils.getQueryByFieleSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE), ediHeaderModel.getBarcode_no());
						if(vecs != null && vecs.size() > 0){
							JOptionPane.showMessageDialog(null, "包裹号："+ ediHeaderModel.getBarcode_no() +" 已存在，可查询后，进行更新操作!");
							return;
						}
						// 插入EdiHeader信息
						DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, ediHeaderModel));
						// 插入EdiReceipt信息
						logger.info("JTableButton #{}# 单行新增数据时，新建回执信息!");
						DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, generatorReceiptModel(ediHeaderModel)));
						JOptionPane.showMessageDialog(null, "保存成功!");
					}else{
						DBUtils.update(SqlUtils.getUpdateSQL(EdiHeaderInfo.EDI_HEARDER_TABEL_NAME, ediHeaderModel), ediHeaderModel.getId());
						// 更新EDI信息时，判断是否需要新建回执信息：若根据条码号可以获取到回执信息则不处理，若未获取到，则新增回执信息
						List<Vector<String>> receiptVecs = DBUtils.queryReceiptInfoByBarcode(SqlUtils.getQueryByFieleSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE), ediHeaderModel.getBarcode_no());
						// 若未获取到，则新增回执信息
						if (receiptVecs.size() == 0) {
							logger.info("JTableButton #{}# 单行更新数据时，由于条码号变更，新建回执信息!");
							DBUtils.insert(SqlUtils.getInsertSQL(EdiHeaderInfo.EDI_RECEIPT_TABEL_NAME, generatorReceiptModel(ediHeaderModel)));
						}
						JOptionPane.showMessageDialog(null, "更新成功!");
					}
				}
			}
		});
	}

	public static EdiHeaderModel generalModel(JTable table){
		EdiHeaderModel ediHeaderModel = new EdiHeaderModel();
		//获取当前行
		int selectedRow = table.getSelectedRow();
		//包裹号
		ediHeaderModel.setBarcode_no(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_BARCODE_NO)));
		//条码号不能为空
		if(Constant.EMPTY_STRING.equals(ediHeaderModel.getBarcode_no())){
			JOptionPane.showMessageDialog(null, "条码号不能空!");
			return null;
		}
		//清单号
		ediHeaderModel.setEntry_id(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_ENTRYID_NO)));
		//订单号
		ediHeaderModel.setOrder_no(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_ORDERNO_NO)));
		//主要货物名称
		ediHeaderModel.setMain_g_name(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_MAINGNAME_NO)));
		//进出口
		String ieFlag = EdiParams.IPORT;
		if(!EdiParams.IPORT_CHINESE.equals(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_IEFLAG_NO)))){
			ieFlag = EdiParams.EPORT;
		}
		ediHeaderModel.setI_e_flag(ieFlag);
		//布控
		String decType = EdiParams.DEC;
		if(!EdiParams.DEC_CHINESE.equals(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_DECTYPE_NO)))){
			decType = EdiParams.NDEC;
		}
		ediHeaderModel.setDec_Type(decType);
		//净重
		ediHeaderModel.setGross_wt(getDoubleValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_GROSSWT_NO)));
		//物流企业名称
		ediHeaderModel.setLogistics_name(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_LOGISTICSNAME_NO)));
		//发件人
		ediHeaderModel.setSender_name(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_SENDERNAME_NO)));
		//收件人
		ediHeaderModel.setOwner_name(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_OWNERNAME_NO)));
		//收件人地址
		ediHeaderModel.setOwner_address(getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_OWNERADDRESS_NO)));
		//件数
		ediHeaderModel.setPack_no(getDoubleValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_PACKNO_NO)));
		//如果主键为空，则进行新增
		String id = getStringValue(table.getValueAt(selectedRow, EdiHeaderInfo.EDI_TABEL_FIELD_ID_NO));
		if(Constant.EMPTY_STRING.equals(id) || id == null){
			ediHeaderModel.setId(new Date().getTime() + "");
			ediHeaderModel.setCreate_Time(new Date());
			logger.info("插入数据:" + ediHeaderModel.toString());
			isInsert = true;
		}else{
			//如果不为空，则根据id进行更新
			ediHeaderModel.setId(id);
			logger.info("更新数据:" + ediHeaderModel.toString());
			isInsert = false;
		}
		
		return ediHeaderModel;
	}
	
	/**
	  *
	  * @Title: generatorReceiptModel
	  * @Description: 生成回执Model
	  * @throws
	  */
	public static EdiReceiptModel generatorReceiptModel(EdiHeaderModel ediHeaderModel){
		EdiReceiptModel receiptModel = new EdiReceiptModel();
		receiptModel.setId(new Date().getTime() + "");
		receiptModel.setBarcode_no(ediHeaderModel.getBarcode_no());
		receiptModel.setSca_receipt(0);
		receiptModel.setCreate_time(new Date());
		return receiptModel;
	}
	
	
	/**
	  *
	  * @Title: getStringValue
	  * @Description: 返回字段的String类型值，若为空，则返回""
	  * @return Sting    返回类型
	  * @throws
	  */
	public static String getStringValue(Object obj){
		return obj == null ? Constant.EMPTY_STRING : obj.toString();
	}
	
	/**
	  *
	  * @Title: getDoubleValue
	  * @Description: 返回字段的Double类型值，若为空，则返回0.0
	  * @return Double    返回类型
	  * @throws
	  */
	public static Double getDoubleValue(Object obj){
		return obj == null ? Constant.ZERO_DOUBLE : Double.valueOf(obj.toString());
	}
	
	/**
	 *
	 * @Title: getIntegerValue
	 * @Description: 返回字段的Integer类型值，若为空，则返回0.0
	 * @return Integer    返回类型
	 * @throws
	 */
	public Integer getIntegerValue(Object obj){
		return obj == null ? Constant.INTEGER_DOUBLE : Double.valueOf(obj.toString()).intValue();
	}
	
	@Override
	public Object getCellEditorValue() {
		return jButton;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return jButton;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return jButton;
	}

}
