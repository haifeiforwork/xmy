package com.zfj.xmy.activity.persistence.app.pojo.dto.coupon;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 获取用户优惠劵
 * @author wy
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class GetAvailbleCouponOutDto {

	// 电子优惠券id
	private Long id;
	// 名称
	private String name;
	// 优惠券价值
	private BigDecimal couponValue;
	// 优惠券图片
	private String couponImg;
	// 优惠券使用开始时间
	private Date effectiveStartTime;
	// 优惠券使用截止时间
	private Date effectiveEndTime;
	// 满多少减
	private Double quota;
	
	// (使用范围（1：全场通用；2：分类使用；3：限定商品；4：排队商品）)
	private Integer useRange;
	/**
	 * 使用状态    0 未使用，1 使用了
	 */
	private Integer useStatus;

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

	public Integer getUseRange() {
		return useRange;
	}

	public void setUseRange(Integer useRange) {
		this.useRange = useRange;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

}
