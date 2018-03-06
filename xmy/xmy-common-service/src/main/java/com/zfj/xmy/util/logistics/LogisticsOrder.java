package com.zfj.xmy.util.logistics;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LogisticsOrder {

	/***
	 * 客户单号(订单编号)
	 */
	private  String orderNo;
	/***
	 * 订单订购日期
	 */
	private Date orderTime;
	/***
	 * 收货人名称
	 */
	private String consigneeName;
	/**
	 * 收货人联系方式
	 */
	private String consigneePhone;
	/***
	 * 收货人地址
	 */
	private String consigneeAddress;
	
	private BigDecimal DSHK;//应代收货款
	private BigDecimal HKZE; //货款总额
	
	/***
	 * 订单货品明细(一个或多个订单货品明细信息)
	 */
	private List<GoodsVo> orderdetail ;

	public List<GoodsVo> getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(List<GoodsVo> orderdetail) {
		this.orderdetail = orderdetail;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public BigDecimal getHKZE() {
		return HKZE;
	}

	public void setHKZE(BigDecimal hKZE) {
		HKZE = hKZE;
	}

	public BigDecimal getDSHK() {
		return DSHK;
	}

	public void setDSHK(BigDecimal dSHK) {
		DSHK = dSHK;
	}
}
