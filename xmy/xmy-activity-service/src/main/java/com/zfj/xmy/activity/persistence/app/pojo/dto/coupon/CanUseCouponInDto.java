package com.zfj.xmy.activity.persistence.app.pojo.dto.coupon;

/**
 * 用户可使用的优惠劵条件
 * @author wy
 *
 */
public class CanUseCouponInDto {

	/**
	 * 订单价格 
	 */
	private Double orderAmount;

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
}
