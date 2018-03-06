package com.zfj.xmy.order.persistence.pc.pojo.dto;

import com.zfj.xmy.common.persistence.pojo.OrderGoods;

public class PcOrderGoodsDto extends OrderGoods{
	
	private String imgPath;//商品图片
	
	private String activityName;//活动名称
	
	private String presentName;//赠送商品名称
	
	private Long activityId;//活动id
	
	private Long pointsId;//积分id
	
	private String goodsDetailUrl ; //商品详情跳转链接
	
	public Long getPointsId() {
		return pointsId;
	}

	public void setPointsId(Long pointsId) {
		this.pointsId = pointsId;
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

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getGoodsDetailUrl() {
		return goodsDetailUrl;
	}

	public void setGoodsDetailUrl(String goodsDetailUrl) {
		this.goodsDetailUrl = goodsDetailUrl;
	}
	
	
}