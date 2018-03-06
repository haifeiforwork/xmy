package com.zfj.xmy.order.persistence.app.pojo.dto;

import java.math.BigDecimal;
import java.util.List;

public class AppPayInDto {
	
	private Boolean withOrderNo;//是否带订单号：true：带 flase:不带
	
	private String orderNo;//订单号
	
	private BigDecimal payAmount;//需支付的金额 withOrderNo为flase 必须要
	
	private Integer payType;//支付方式：1余额支付 2扫卡支付
	
	private List<CardInDto> cards;//购物卡集合
	
	private String username;//用户名 支付方式为余额支付时 不能为空
	
	private String password;//密码  支付方式为余额支付时 不能为空

	public Boolean getWithOrderNo() {
		return withOrderNo;
	}

	public void setWithOrderNo(Boolean withOrderNo) {
		this.withOrderNo = withOrderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public List<CardInDto> getCards() {
		return cards;
	}

	public void setCards(List<CardInDto> cards) {
		this.cards = cards;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
