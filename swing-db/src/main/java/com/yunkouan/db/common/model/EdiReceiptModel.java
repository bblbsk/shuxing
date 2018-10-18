package com.yunkouan.db.common.model;

import java.util.Date;


/**
 * The persistent class for the edi_receipt database table.
 * 
 */
public class EdiReceiptModel {

	private String id;
	private String entry_id;	
	private String header_id;	
	private String barcode_no;	
	private String voyage_no;
	private Integer aog_receipt;
	private String aog_status_code;
	private String aog_status_name;
	private Date aog_ready_time;
	private Date aog_confirm_time;
	private Integer rsk_receipt;
	private String rsk_status_code;
	private String rsk_status_name;
	private Date rsk_ready_time;
	private Date rsk_confirm_time;
	private Integer sca_receipt;
	private String sca_status_code;
	private String sca_status_name;
	private Date sca_ready_time;
	private Date sca_confirm_time;
	private Date create_time;
	private Date update_time;
	
	public EdiReceiptModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}

	public String getHeader_id() {
		return header_id;
	}

	public void setHeader_id(String header_id) {
		this.header_id = header_id;
	}

	public String getBarcode_no() {
		return barcode_no;
	}

	public void setBarcode_no(String barcode_no) {
		this.barcode_no = barcode_no;
	}

	public String getVoyage_no() {
		return voyage_no;
	}

	public void setVoyage_no(String voyage_no) {
		this.voyage_no = voyage_no;
	}

	public Integer getAog_receipt() {
		return aog_receipt;
	}

	public void setAog_receipt(Integer aog_receipt) {
		this.aog_receipt = aog_receipt;
	}

	public String getAog_status_code() {
		return aog_status_code;
	}

	public void setAog_status_code(String aog_status_code) {
		this.aog_status_code = aog_status_code;
	}

	public String getAog_status_name() {
		return aog_status_name;
	}

	public void setAog_status_name(String aog_status_name) {
		this.aog_status_name = aog_status_name;
	}

	public Date getAog_ready_time() {
		return aog_ready_time;
	}

	public void setAog_ready_time(Date aog_ready_time) {
		this.aog_ready_time = aog_ready_time;
	}

	public Date getAog_confirm_time() {
		return aog_confirm_time;
	}

	public void setAog_confirm_time(Date aog_confirm_time) {
		this.aog_confirm_time = aog_confirm_time;
	}

	public Integer getRsk_receipt() {
		return rsk_receipt;
	}

	public void setRsk_receipt(Integer rsk_receipt) {
		this.rsk_receipt = rsk_receipt;
	}

	public String getRsk_status_code() {
		return rsk_status_code;
	}

	public void setRsk_status_code(String rsk_status_code) {
		this.rsk_status_code = rsk_status_code;
	}

	public String getRsk_status_name() {
		return rsk_status_name;
	}

	public void setRsk_status_name(String rsk_status_name) {
		this.rsk_status_name = rsk_status_name;
	}

	public Date getRsk_ready_time() {
		return rsk_ready_time;
	}

	public void setRsk_ready_time(Date rsk_ready_time) {
		this.rsk_ready_time = rsk_ready_time;
	}

	public Date getRsk_confirm_time() {
		return rsk_confirm_time;
	}

	public void setRsk_confirm_time(Date rsk_confirm_time) {
		this.rsk_confirm_time = rsk_confirm_time;
	}

	public Integer getSca_receipt() {
		return sca_receipt;
	}

	public void setSca_receipt(Integer sca_receipt) {
		this.sca_receipt = sca_receipt;
	}

	public String getSca_status_code() {
		return sca_status_code;
	}

	public void setSca_status_code(String sca_status_code) {
		this.sca_status_code = sca_status_code;
	}

	public String getSca_status_name() {
		return sca_status_name;
	}

	public void setSca_status_name(String sca_status_name) {
		this.sca_status_name = sca_status_name;
	}

	public Date getSca_ready_time() {
		return sca_ready_time;
	}

	public void setSca_ready_time(Date sca_ready_time) {
		this.sca_ready_time = sca_ready_time;
	}

	public Date getSca_confirm_time() {
		return sca_confirm_time;
	}

	public void setSca_confirm_time(Date sca_confirm_time) {
		this.sca_confirm_time = sca_confirm_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}