package com.zfj.xmy.activity.service.common;  

import java.util.List;

import com.zfj.xmy.activity.persistence.app.pojo.dto.AppActivityInDto;
import com.zfj.xmy.activity.persistence.common.pojo.dto.AppBuyAndPresentOutDto;
import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityDir;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Goods;

/** 
 * @Title: AppLimitActivityService.java 
 * @Package com.zfj.xmy.activity.service.app 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 上午11:20:45 
 */
public interface LimitActivityService {
	
	/**
	 *  开抢了活动
	 * @return    
	 * @return List<AppLimitActivityDir>    
	 * Date:2017年7月31日 下午1:57:19 
	 * @author hexw
	 */
	List<LimitActivityDir> findLimitActivityList(AppActivityInDto activityInDto);

	/**
	 * 根据type查询活动  活动类型 （1冰点价，2天天特价，3每周特价，4整件惠）
	 * @Description 
	 * @param type
	 * @return
	 * @Author liuw
	 * @Date 2017年8月17日下午4:34:21
	 */
	//List<LimitActivityDir> findLimitActivityList(Integer type);
	
	/**
	 * 查询买即赠活动
	 * @return    
	 * @return List<AppBuyAndPresentOutDto>    
	 * Date:2017年8月21日 下午2:20:00 
	 * @author hexw
	 */
	List<AppBuyAndPresentOutDto> findBuyAndPersentActivity();
	
	/**
	 * 活动开抢了top图
	 * @return     type 0 top图  1商品广告图
	 * @return List<AdImage>    
	 * Date:2017年8月23日 上午11:34:06 
	 * @author hexw
	 */
	List<AdImage> findActivityImage(Integer type);
	
	/**
	 * 判断用户购买活动商品是否符合活动规则
	 * @param userId
	 * @param activityId
	 * @param goods_id
	 * @param num
	 * @return    
	 * @return int 0符合 1 用户超过了活动限定次数 2 购买的商品超过了活动商品的限购数量    
	 * Date:2017年10月14日 上午10:42:07 
	 * @author hexw
	 */
	int checkLimitGoods(Long userId, Long activityId, Long goodsId,
			Integer num);
	
	/**
	 * 修改活动商品成交量
	 * @param activityId
	 * @param goodsId
	 * @param num    
	 * @return void    
	 * Date:2017年10月14日 上午11:46:23 
	 * @author hexw
	 */
	void updateActivityGoodsComplNum(Long activityId, Long goodsId, Integer num);
	
	/**
	 * 定时修改冰点价活动商品  上下架状态  
	 *     
	 * @return void    
	 * Date:2017年10月26日 下午2:52:11 
	 * @author hexw
	 */
	void updateFreezingActivityGoods();
	
	/**
	 * 定时修改天天特价商品  上下架状态
	 *     
	 * @return void    
	 * Date:2017年10月26日 下午2:53:00 
	 * @author hexw
	 */
	void updateDayDayActivityGoods();
	
	/**
	 * 定时修改每周特价商品  上下架状态
	 *     
	 * @return void    
	 * Date:2017年10月26日 下午2:55:01 
	 * @author hexw
	 */
	void updateWeekWeekActivityGoods();
	
	/**
	 * 定时修改专题活动 上下架状态
	 *     
	 * @return void    
	 * Date:2017年12月17日 下午6:05:53 
	 * @author hexw
	 */
	void updatePromotionActivityGoodsPutway();
	/**
	 * 根据商品id判断该冰点价商品是否已经购买完了
	 * @param goodsId
	 * @return Integer 0：可以购买  1：商品不存在 2：商品已经卖完了
	 * @author lij
	 * @date 2018年1月9日 下午4:06:09
	 */
	Integer isSelaGoodsByGoodsId(Long goodsId);
}
  