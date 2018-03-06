package com.zfj.xmy.goods.persistence.app.pojo.dto;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * @Title: AppActivityGoodsDir.java 
 * @Package com.zfj.xmy.goods.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月19日 下午2:02:54 
 */
public class AppActivityInfoDir {
	
	private Date  endTime; //活动时间
	
	private Date currentTime; // 当前时间
	
	private long presentGoodsId; //赠品id
	
	private String presentName; //赠品名称
	
	private String presentFullName; //赠品全名称
	
	private String activityName;//活动名称
	
	private Integer limitTotalNum;//搞活动的总量
	
	private Integer activityType;//活动类型
	private Long activityId;//活动id
	
	private BigDecimal activityPrice; //活动价格

	
	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getLimitTotalNum() {
		return limitTotalNum;
	}

	public void setLimitTotalNum(Integer limitTotalNum) {
		this.limitTotalNum = limitTotalNum;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public long getPresentGoodsId() {
		return presentGoodsId;
	}

	public void setPresentGoodsId(long presentGoodsId) {
		this.presentGoodsId = presentGoodsId;
	}

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public String getPresentFullName() {
		return presentFullName;
	}

	public void setPresentFullName(String presentFullName) {
		this.presentFullName = presentFullName;
	}
	
	
}
  