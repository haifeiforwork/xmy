package com.zfj.xmy.activity.persistence.app.dao;

import java.util.Date;
import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.app.pojo.dto.AppCouponOutDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.coupon.CouponCenterOutDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.coupon.GetAvailbleCouponInDto;
import com.zfj.xmy.activity.persistence.app.pojo.dto.coupon.GetAvailbleCouponOutDto;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsOut;

/**
 * 
 * @author cj
 * @createDate 2017年11月3日
 *
 */
public interface CouponExMapper {
	
	/**
	 * 查找优惠券
	 * @return
	 */
	List<AppCouponOutDto> findCoupon(CriteriaParameter criteriaParameter);
	
	/**
	 * 随机选取商品数据
	 * @param count 条数
	 * @return
	 * @Description 
	 * @date 2017年11月22日  下午2:17:25
	 * @author wy
	 * 2017
	 * @return List<AppGoodsOut>
	 */
	List<AppGoodsOut> findRandomGoods(int count) ;
	
	/**
	 * 查询用户优惠券详情
	 * @param parameter
	 * @return
	 * @Description 
	 * @date 2017年11月15日  下午3:55:06
	 * @author wy
	 * 2017
	 * @return List<EntityCoupon>
	 */
	List<GetAvailbleCouponOutDto> findUserCoupon(CriteriaParameter parameter);

	/**
	 * 获取领劵中心 优惠劵信息
	 * @param userId
	 * @Description 
	 * @date 2017年11月22日  上午10:21:25
	 * @author wy
	 * 2017
	 * @return void
	 */
	List<CouponCenterOutDto> findCouponCenters(Long userId);
}
