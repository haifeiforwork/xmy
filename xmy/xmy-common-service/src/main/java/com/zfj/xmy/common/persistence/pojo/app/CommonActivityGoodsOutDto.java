package com.zfj.xmy.common.persistence.pojo.app;  

import java.math.BigDecimal;

/** 
 * @Title: CommonActivityGoodsOutDto.java 
 * @Package com.zfj.xmy.common.persistence.pojo.app 
 * @Description: 
 * @author hexw
 * @date 2017年12月8日 下午2:02:43 
 */
public class CommonActivityGoodsOutDto {

	
	private String activityName; //活动名称
	
	private BigDecimal activityPrice ; //商品活动价格
	
	private Integer goodsLimitNum ; //商品限购数量
	
	private Integer userLimitNum; // 用户限购数量
	
	private Integer  goodsResidueNum; //商品剩余数量

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getGoodsLimitNum() {
		return goodsLimitNum;
	}

	public void setGoodsLimitNum(Integer goodsLimitNum) {
		this.goodsLimitNum = goodsLimitNum;
	}

	public Integer getUserLimitNum() {
		return userLimitNum;
	}

	public void setUserLimitNum(Integer userLimitNum) {
		this.userLimitNum = userLimitNum;
	}

	public Integer getGoodsResidueNum() {
		return goodsResidueNum;
	}

	public void setGoodsResidueNum(Integer goodsResidueNum) {
		this.goodsResidueNum = goodsResidueNum;
	}
	
	
	
	
	
	
	
}
  