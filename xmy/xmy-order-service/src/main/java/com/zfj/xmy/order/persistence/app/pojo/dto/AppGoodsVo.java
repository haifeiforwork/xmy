package com.zfj.xmy.order.persistence.app.pojo.dto;  

import java.math.BigDecimal;

/** 
 * @Title: AppGoodsVo.java 
 * @Package com.zfj.xmy.order.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 下午4:53:38 
 */
public class AppGoodsVo {

	
	private long id; //商品id
	
	private long activityId; //活动id
	
	private long presentGoodsId; //赠品id 
	
	private Integer activityType;  //活动类型
	
	private Long pointsActId;//积分活动id
	
	private Integer num ; //商品数量
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public long getPresentGoodsId() {
		return presentGoodsId;
	}

	public void setPresentGoodsId(long presentGoodsId) {
		this.presentGoodsId = presentGoodsId;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Long getPointsActId() {
		return pointsActId;
	}

	public void setPointsActId(Long pointsActId) {
		this.pointsActId = pointsActId;
	}


	
}
  