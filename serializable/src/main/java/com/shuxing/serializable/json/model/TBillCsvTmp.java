package com.shuxing.serializable.json.model;

import java.io.Serializable;

public class TBillCsvTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String chargeDay;

	private String chargeMode;

	private String chargeMoney;

	private String chargeMonth;

	private String discountAmount;

	private String goodsName;

	private String originalPrice;
	
	private String billDay;

	private String resourceId;

	public TBillCsvTmp() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChargeDay() {
		return this.chargeDay;
	}

	public void setChargeDay(String chargeDay) {
		this.chargeDay = chargeDay;
	}

	public String getChargeMode() {
		return this.chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public String getChargeMoney() {
		return this.chargeMoney;
	}

	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getChargeMonth() {
		return this.chargeMonth;
	}

	public void setChargeMonth(String chargeMonth) {
		this.chargeMonth = chargeMonth;
	}

	public String getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getBillDay() {
		return billDay;
	}

	public void setBillDay(String billDay) {
		this.billDay = billDay;
	}

	@Override
	public String toString() {
		return "TBillCsvTmp [id=" + id + ", chargeDay=" + chargeDay
				+ ", chargeMode=" + chargeMode + ", chargeMoney=" + chargeMoney
				+ ", chargeMonth=" + chargeMonth + ", discountAmount="
				+ discountAmount + ", goodsName=" + goodsName
				+ ", originalPrice=" + originalPrice + ", billDay=" + billDay
				+ ", resourceId=" + resourceId + "]";
	}

}