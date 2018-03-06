package com.zfj.xmy.common.persistence.pojo.app;

import java.math.BigDecimal;
import java.util.Date;
/**
 * app购物卡激活明细返回
 * @Description 
 * @Author liuw
 * @Date 2017年8月25日上午11:14:40
 */
public class AppShoppingCardOutDto {
	//购物卡id
	private Long id;
	//购物卡名称
	private String name;
	//购物卡卡号
	private String cardNum;
	//激活时间
	private Date activeTime;
	//购物卡总金额
	private BigDecimal totalValue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	
}
