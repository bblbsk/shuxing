package com.yunkouan.db.common.model;

import java.util.Date;

/**
 * <P>Title: yka-customs</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2014</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date:2016年4月1日-上午10:04:52</P>
 * @author wanghuan
 * @version 1.1.0
*/
public class EdiHeaderModel {
	private String id;				//id
	private String barcode_no;		//包裹单号
	private String entry_id;		//清单号
	private String order_no;		//订单号
	private String main_g_name;		//主要货物名称
	private String i_e_flag;		//进出口标志I、E
	private String dec_Type;		//审单验放指令审单验放指令（1放行，2分拣,3查验）
	private Double gross_wt;		//毛重
	private String logistics_name;	//物流企业名称
	private String sender_name;		//发件人
	private String owner_name;		//收件人
	private String owner_address;	//收件人地址
	private Double pack_no;		//件数
	private Date create_Time;		//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBarcode_no() {
		return barcode_no;
	}
	public void setBarcode_no(String barcode_no) {
		this.barcode_no = barcode_no;
	}
	public String getMain_g_name() {
		return main_g_name;
	}
	public void setMain_g_name(String main_g_name) {
		this.main_g_name = main_g_name;
	}

	public String getI_e_flag() {
		return i_e_flag;
	}
	public void setI_e_flag(String i_e_flag) {
		this.i_e_flag = i_e_flag;
	}
	public String getDec_Type() {
		return dec_Type;
	}
	public void setDec_Type(String dec_Type) {
		this.dec_Type = dec_Type;
	}
	public Double getGross_wt() {
		return gross_wt;
	}
	public void setGross_wt(Double gross_wt) {
		this.gross_wt = gross_wt;
	}
	public Date getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(Date create_Time) {
		this.create_Time = create_Time;
	}
	public String getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOwner_address() {
		return owner_address;
	}
	public void setOwner_address(String owner_address) {
		this.owner_address = owner_address;
	}
	public Double getPack_no() {
		return pack_no;
	}
	public void setPack_no(Double pack_no) {
		this.pack_no = pack_no;
	}
	@Override
	public String toString() {
		return "EdiHeaderModel [id=" + id + ", barcode_no=" + barcode_no
				+ ", entry_id=" + entry_id + ", order_no=" + order_no
				+ ", main_g_name=" + main_g_name + ", i_e_flag=" + i_e_flag
				+ ", dec_Type=" + dec_Type + ", gross_wt=" + gross_wt
				+ ", logistics_name=" + logistics_name + ", sender_name="
				+ sender_name + ", owner_name=" + owner_name
				+ ", owner_address=" + owner_address + ", pack_no=" + pack_no
				+ ", create_Time=" + create_Time + "]";
	}

}
