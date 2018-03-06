package com.zfj.xmy.common.persistence.pojo.app;  

import java.math.BigDecimal;

/** 
 * @Title: CommonCollertionGoodsDto.java 
 * @Package com.zfj.xmy.common.persistence.pojo.app 
 * @Description: 
 * @date 2017年12月1日 上午11:42:28 
 */
public class CommonCollectionGoodsDto {
	
	
	private Long goodsId;
	
	private String name;
	
	private String goodsImg;
	
	private BigDecimal goodsPrice;
	
	private String activityGoodsTimeStr;
	
	private String waterImg;
	
	private Long activityId;
	
	private Integer activityType;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getActivityGoodsTimeStr() {
		return activityGoodsTimeStr;
	}

	public void setActivityGoodsTimeStr(String activityGoodsTimeStr) {
		this.activityGoodsTimeStr = activityGoodsTimeStr;
	}

	public String getWaterImg() {
		return waterImg;
	}

	public void setWaterImg(String waterImg) {
		this.waterImg = waterImg;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	
	
	

}
  