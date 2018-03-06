package com.zfj.xmy.pay.persistence.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 购买商品 
 * @author wy
 *
 */
public class LaunchPayGoodsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品订单号
	 */
	private String orderid;
	
	/**
	 * 支付方式类型
	 */
	private Integer paytype;

	 /**
     * (余额支付/购物卡) 填写金额
     */
    private BigDecimal payAmount;
    
	/**
	 * 客户端id (安全验证)
	 */
	private String clientIp;
	
	/**
	 * wxOpenid
	 */
	private String wxOpenid;
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	
}