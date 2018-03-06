package com.zfj.xmy.goods.persistence.app.pojo.dto;

public class AppOrderMethod {
	private String orderByName;//排序名称
	private Integer orderMethod;//1.新品上架 2.销量排序 3.评论数量排序 4.价格排序
	public String getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}
	public Integer getOrderMethod() {
		return orderMethod;
	}
	public void setOrderMethod(Integer orderMethod) {
		this.orderMethod = orderMethod;
	}
	
}
