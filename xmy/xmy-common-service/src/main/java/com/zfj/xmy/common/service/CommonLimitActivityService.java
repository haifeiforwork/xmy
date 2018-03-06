package com.zfj.xmy.common.service;  

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;

/** 
 * @Title: CommonLimityActivityService.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年10月14日 下午2:55:14 
 */
public interface CommonLimitActivityService {
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
	 * 判断活动商品是否符合活动规则
	 * @param shoppingCart
	 * @return    
	 * @return int    
	 * Date:2017年10月14日 下午5:21:53 
	 * @author hexw
	 */
	int checkLimitActivity(ShoppingCart shoppingCart);
	
	/**
	 * 查询活动商品
	 * @param activityId
	 * @param goodsId
	 * @return    
	 * @return LimitGoods    
	 * Date:2017年10月16日 上午10:06:19 
	 * @author hexw
	 */
	LimitGoods findLimitGoods(Long activityId, Long goodsId);
	

	
	void updatePointsGoodsComplNum(Integer pointsGoodsId, Integer num);
	
	/**
	 * 判断商品属于线上活动
	 * @Description 
	 * @param id
	 * @return
	 * @Author cj
	 * @Date 2017年11月10日上午5:00:43
	 */
	boolean findOnlineActivityGoods(Long id);
	
	/**
	 * 判断商品是否是属于活动商品
	 * @param goodsId
	 * @return    
	 * @return boolean    
	 * Date:2018年1月4日 上午1:39:30 
	 * @author hexw
	 */
	boolean checkGoodsIsLimitActivityGoods(Long goodsId);
}
  