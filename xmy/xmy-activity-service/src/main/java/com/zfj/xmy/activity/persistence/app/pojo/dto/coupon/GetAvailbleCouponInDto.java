package com.zfj.xmy.activity.persistence.app.pojo.dto.coupon;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 获取用户优惠劵进
 * @author wy
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class GetAvailbleCouponInDto {

	/**
	 * 用户id
	 */
	private Long userid;
	/**
	 * 排序
	 */
	private String order;
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
