package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.User;

public interface PcCouponService {
	/**
	 * @param UserId
	 * @return List<Coupon>
	 * @author lij
	 * @date 2017年8月3日 下午3:58:51
	 * 根据用户查询他的所有有效的优惠卷
	 */
	List<Coupon> findCouponByUserId(long userId);
	
	/**
	 * @param UserId
	 * @return List<Coupon>
	 * @author zhangh
	 * @date 2017年8月14日 上午11:18:51
	 * 分页查询用户的可用、已用、过期优惠券
	 */
	void findAllCoupon(long userId,PageBean pageBean,int status);
	
	/**
	 * @param UserId
	 * @return List<Coupon>
	 * @author zhangh
	 * @date 2017年8月14日 上午11:18:51
	 * 分页查询用户的新到账、将过期、最优惠优惠券
	 */
	void findSortCoupon(long userId,PageBean pageBean,int sort);
	
	/**
	 * @param UserId
	 * @return List<Integer>
	 * @author zhangh
	 * @date 2017年8月15日 上午10:18:51
	 * 统计用户的可用、已用、过期优惠券数量
	 */
	List<Integer> countCoupon(long userId);
	
	/**
	 * 根据用户id查询优惠券
	 * @param userId
	 * @param pageBean    
	 * @return void    
	 * Date:2017年11月1日 下午4:11:52 
	 * @author hexw
	 */
	void findCouponByUserId(Long userId, PageBean pageBean);
	/**
	 * 绑定优惠卷
	 * @param userId
	 * @param paperCode
	 * @return Integer
	 * @author lij
	 * @date 2017年11月20日 下午3:25:40
	 */
	Integer userBindPaperCoupon(Long userId,String paperCode);
}