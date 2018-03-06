package com.zfj.xmy.common.persistence.pojo.common;

public class couponReceiveByPhoneDto {

	/**
	 * 手机号
	 */
	private String mobilePhone;
	
	/**
	 * coupon表id
	 */
	private Long couponCategoryId;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getCouponCategoryId() {
		return couponCategoryId;
	}

	public void setCouponCategoryId(Long couponCategoryId) {
		this.couponCategoryId = couponCategoryId;
	}
}
