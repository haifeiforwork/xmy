package com.zfj.xmy.order.persistence.app.pojo.dto;

import java.util.List;

/**
 * 取消订单  输入数据
 * @author wy
 *
 */
public class CancelOrderInDto {

	/***
	 * 订单ID
	 */
	private Long orderId;
	
	/**
	 * 申请退款人用户id
	 */
	private Long userid;
	
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	
}
