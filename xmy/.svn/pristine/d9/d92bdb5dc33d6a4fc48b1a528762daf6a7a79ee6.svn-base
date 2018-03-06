package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcPointsGoodsDto;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

/** 
 * @Title: PointsStoreService.java 
 * @Package com.zfj.xmy.activity.service.pc
 * @Description: 
 * @author zhangh
 * @date 2017年8月1日 下午2:26:21 
 */
public interface PcPointsStoreService {
	
	/**
	 * 查询积分活动
	 * @return    
	 * @return List<PointsActivity>    
	 * @author zhangh
	 * Date:2017年8月1日 下午2:49:52
	 */
	List<PointsActivity> findPointsActivity();
	
	/**
	 * 根据活动id查询积分活动
	 * @return    
	 * @return List<PointsActivity>    
	 * @author zhangh
	 * Date:2017年8月1日 下午2:49:52
	 */
	PointsActivity findPointsAct(long actId);
	
	/**
	 * 根据活动id商品id查询积分活动商品
	 * @return    
	 * @return List<PointsActivity>    
	 * @author zhangh
	 * Date:2017年8月1日 下午2:49:52
	 */
	PointsGoods findPoGoods(long goodsId,long actId);
	
	/**
	 * 查询积分活动商品
	 * @param id
	 * @return    
	 * @return List<PointsGoodsDto>    
	 * @author zhangh
	 * Date:2017年8月1日 下午2:50:38
	 */
	List<PcPointsGoodsDto> findPointsGoodsDto(Long id);
	
	/**
	 * 根据活动商品id查询活动商品
	 * @param id
	 * @return    
	 * @return List<PointsGoodsDto>    
	 * @author zhangh
	 * Date:2017年8月1日 下午2:50:38
	 */
	PointsGoods findPointGoods(Long id);
	
	/**
	 * 判断当天是否签过到
	 * @param userId
	 * @return    
	 * @return Integer 0.没签过 1.签过
	 * @author zhangh
	 * Date:2017年8月1日 下午2:50:38
	 */
	int findPoints(long userId);
	
	/**
	 * 签到得积分
	 * @param userId
	 * @return    
	 * @return Integer
	 * @author zhangh
	 * Date:2017年8月1日 下午2:50:38
	 */
	void addPoints(long userId);
	
	/**
	 * 获取连续签到天数
	 * @param userId
	 * @return    
	 * @return Integer
	 * @author zhangh
	 * Date:2017年8月24日 下午2:50:38
	 */
	int findDays(long userId);
	
	/**
	 * 获取积分商品类id
	 * @param id
	 * @return    
	 * @return PointsGoods    
	 * Date:2017年11月22日 上午10:15:50 
	 * @author hexw
	 */
	PointsGoods getPointsGoods(Long id);
}
