package com.zfj.xmy.activity.persistence.app.pojo.dto.coupon;

import java.util.Date;
import java.util.List;

import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsOut;

public class CouponCenterOutDto {

	// 电子优惠券id
	private Long id;
	// 名称
	private String name;
	// 优惠券价值
	private Double couponValue;
	// 优惠券图片
	private String couponImg;
	// 优惠券使用开始时间
	private Date effectiveStartTime;
	// 优惠券使用截止时间
	private Date effectiveEndTime;
	// 满多少减
	private Integer quota;
	// (使用范围（1：全场通用；2：分类使用；3：限定商品；4：排队商品）)
	private Integer useRange;
	// 类型：0 用户未领取 1 用户已经领取
	private Integer isUserGet;
	// 该劵是否还有剩余 0 没有 1有
	private Integer allAvable;

	private Double usePercent;// 优惠券领取百分比

	private List<AppGoodsOut> goodsList;// 商品列表
	
	private Integer userUseStatus; // 用户对该劵的使用状态 (0 未领取或者未使用 1.已使用)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponImg() {
		return couponImg;
	}

	public void setCouponImg(String couponImg) {
		this.couponImg = couponImg;
	}

	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}

	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}

	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}

	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getUseRange() {
		return useRange;
	}

	public void setUseRange(Integer useRange) {
		this.useRange = useRange;
	}

	public Integer getIsUserGet() {
		return isUserGet;
	}

	public void setIsUserGet(Integer isUserGet) {
		this.isUserGet = isUserGet;
	}

	public Integer getAllAvable() {
		return allAvable;
	}

	public void setAllAvable(Integer allAvable) {
		this.allAvable = allAvable;
	}

	public Double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(Double usePercent) {
		this.usePercent = usePercent;
	}

	public List<AppGoodsOut> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<AppGoodsOut> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getUserUseStatus() {
		return userUseStatus;
	}

	public void setUserUseStatus(Integer userUseStatus) {
		this.userUseStatus = userUseStatus;
	}

}
