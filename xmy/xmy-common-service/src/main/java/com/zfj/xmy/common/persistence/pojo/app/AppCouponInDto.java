package com.zfj.xmy.common.persistence.pojo.app;

public class AppCouponInDto {
	private Long id;//优惠券类别ID
	private Long userId;//用户id
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
