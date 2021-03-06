package com.zfj.xmy.common.service;  

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.app.AppCouponInDto;
import com.zfj.xmy.common.persistence.pojo.common.couponReceiveByPhoneDto;


/** 
 * @Title: CommonCouponService.java 
 * @Package com.zfj.xmy.common.service 
 * @Description: 
 * @author hexw
 * @date 2017年11月22日 下午4:23:12 
 */
public interface CommonCouponService {
	/***
	 * 领取优惠券
	 * @param coupon
	 * @param canGetForNotShowInCouponCenter 设置为前台不显示的时候 是否 还可以领取 (某些活动需要)  true 还可以领取 , false 不可以领取 默认设置为 false
	 * @Description 
	 * @date 2017年11月23日  下午3:46:00
	 * @author wy
	 * 2017
	 * @return void
	 */
	void toReceiveCoupon(AppCouponInDto coupon,
			boolean canGetForNotShowInCouponCenter);
	/**
	 * 查询用户已有优惠券
	 * @param userId
	 * @return
	 */
	List<CouponUser> findCouponUserByUserId(Long userId);
	/**
	 * 根据优惠卷类型id查询已经绑定的总条数
	 * @param couponTypeId
	 * @return Integer
	 * @author ***
	 * @date 2017年12月21日 下午3:23:01
	 */
	Integer countCoup(Long couponTypeId);
	/**
	 * 使用任意手机号领取优惠券
	 * (一个手机号只能领取一次)
	 * @param phoneDto
	 * @Description 
	 * @date 2018年1月2日  下午3:36:22
	 * @author wy
	 * 2018
	 * @return void
	 */
	void couponReceiveByPhone(couponReceiveByPhoneDto couponReceiveByPhoneDto);
	/***
     * 领取优惠券(登录)
     * @param coupon
     * @param canGetForNotShowInCouponCenter 设置为前台不显示的时候 是否 还可以领取 (某些活动需要)  true 还可以领取 , false 不可以领取 默认设置为 false
     * @Description 
     * @date 2017年11月23日  下午3:46:00
     * @author wy
     * 2017
     * @return void
     */
	boolean toReceiveCouponLogin(AppCouponInDto coupon,boolean canGetForNotShowInCouponCenter);
	
	/**
	 * 绑定用户购买优惠券商品对应绑定的优惠券
	 * @return void    
	 * Date:2018年3月5日 下午3:53:10 
	 * @author hexw
	 */
	void bindBuyCouponGoods(Long orderId);
}
  