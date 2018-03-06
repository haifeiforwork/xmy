package com.zfj.xmy.activity.persistence.app.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsOut;

/**
 * 优惠券输出封装类
 * @Description 
 * @Author liuw
 * @Date 2017年8月7日上午10:01:48
 */
public class AppCouponOutDto {
	//电子优惠券id
	 private Long id;
	 //名称
	 private String name;
	 //优惠券价值
	 private BigDecimal couponValue;
	 //优惠券图片
	 private String couponImg;
	 //优惠券使用开始时间
	 private Date effectiveStartTime;
	 //优惠券使用截止时间
	 private Date effectiveEndTime;
	 //满多少减
	 private Integer quota;
	 //(使用范围（1：全场通用；2：分类使用；3：限定商品；4：排队商品）)
	 private Integer useRange;
	 //类型：1 未领取 2 已经领取 3 领取完
	 private Integer type;
	 
	 private Integer usePercent;//优惠券领取百分比
	 
	 private List<AppGoodsOut> goodsList;//商品列表
	 
	public Integer getUsePercent() {
		return usePercent;
	}
	public void setUsePercent(Integer usePercent) {
		this.usePercent = usePercent;
	}
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
	public BigDecimal getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(BigDecimal couponValue) {
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<AppGoodsOut> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<AppGoodsOut> goodsList) {
		this.goodsList = goodsList;
	}
	
	 
}
