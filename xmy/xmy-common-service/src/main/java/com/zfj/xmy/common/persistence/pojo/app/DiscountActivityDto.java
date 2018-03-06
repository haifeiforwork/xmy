package com.zfj.xmy.common.persistence.pojo.app;  

import java.math.BigDecimal;

/** 
 * @Title: DiscountActivityDto.java 
 * @Package com.zfj.xmy.common.persistence.pojo.app 
 * @Description: 
 * @author hexw
 * @date 2017年12月29日 上午11:33:43 
 */
public class DiscountActivityDto {
	
	private String activityRemark;
	
	private BigDecimal discountPrice = new BigDecimal("0.0");

	public String getActivityRemark() {
		return activityRemark;
	}

	public void setActivityRemark(String activityRemark) {
		this.activityRemark = activityRemark;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	
	
	
}
  