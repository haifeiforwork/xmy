package com.zfj.xmy.common.service;

public interface CommonPresentCouponActivityService {
	
	/**
	 * 赠送优惠券活动
	 * @param userId    
	 * @return void    
	 * Date:2018年1月15日 上午9:27:01 
	 * @author hexw
	 */
	void presentCoupon(Long userId);
	
	/**
	 * 登录时绑定优惠券
	 * @param userId
	 */
	void presentCouponLogin(Long userId);

}
