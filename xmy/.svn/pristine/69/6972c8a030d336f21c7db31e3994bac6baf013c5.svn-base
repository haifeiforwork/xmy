package com.zfj.xmy.activity.persistence.common.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.common.pojo.dto.CouponUserStatistisDto;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;

public interface CouponUserExMapper {

	/**
	 * 批量添加优惠劵
	 * @param couponUsers
	 * @return
	 * @Description 
	 * @date 2017年11月20日  上午11:35:07
	 * @author wy
	 * 2017
	 * @return int
	 */
	int insertBatch(List<CouponUser> couponUsers);

	/***
	 * 获取用户使用状态统计 (未使用和已经使用且没有过期的)
	 * @param userid
	 * @Description 
	 * @date 2017年11月21日  上午10:59:17
	 * @author wy
	 * 2017
	 * @return void
	 */
	List<CouponUserStatistisDto> getCouponUserStatistis(Long userid);
	/***
	 * 获取用户使用状态统计 (没有使用过并且过期了的)
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2017年11月21日  上午11:22:35
	 * @author wy
	 * 2017
	 * @return List<CouponUserStatistisDto>
	 */
	CouponUserStatistisDto getCouponUserStatistisDep(Long userid);
	/**
	 * 查找优惠券列表 - 分页 
	 * @param parameter
	 * @param rowBounds
	 * @return
	 * @Description 
	 * @date 2018年2月2日  下午2:10:27
	 * @author wy
	 * 2018
	 * @return List<Coupon>
	 */
	List<Coupon> findCouponsAndPage(CriteriaParameter parameter,RowBounds rowBounds);
	
}
