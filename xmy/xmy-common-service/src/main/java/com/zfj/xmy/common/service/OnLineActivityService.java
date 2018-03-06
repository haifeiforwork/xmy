package com.zfj.xmy.common.service;  

import java.math.BigDecimal;
import java.util.Map;

import com.zfj.xmy.common.persistence.pojo.ActivityQuestion;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.OnlineActivity;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.app.DiscountActivityDto;

/** 
 * @Title: OnLineActivityService.java 
 * @Package com.zfj.xmy.common.service 
 * @Description: 
 * @author hexw
 * @date 2017年11月12日 下午11:11:47 
 */
public interface OnLineActivityService {

	/**
	 * 判断用户是否满足优惠条件
	 * @param userId
	 * @return Integer
	 * @author lij
	 * @date 2017年11月10日 下午3:48:53
	 */
	Integer checkUserOrders(Long userId);
	
	/**
	 * 判断是全部活动时间
	 * @return Integer
	 * @author ***
	 * @date 2017年11月10日 下午3:58:24
	 */
	Integer chekActiviDate();
	/**
	 * 判断是否属于重庆活动时间
	 * @return Integer
	 * @author ***
	 * @date 2017年11月10日 下午3:58:48
	 */
	Integer chekCqActiviDate();
	

	Integer checkUserOrder(Long userId);
	
	/**
	 * 
	 * @param userId    
	 * @return void    
	 * Date:2017年11月13日 上午7:55:16 
	 * @author hexw
	 */
	Integer checkDate();
	
	
	/**
	 * 判断APP首单赠送时间  Integer 0：不在活动范围时间内 1.在活动范围时间内
	 * @param userId    
	 * @return void    
	 * Date:2017年11月13日 上午7:55:16 
	 * @author lij
	 */
	Integer checkAppDate(Long userId);
	
	/**
	 * 
	 * @return    
	 * @return Integer    
	 * Date:2017年11月13日 上午7:58:39 
	 * @author hexw
	 */
	Integer checkCQDate();
	
	DiscountActivityDto getDiscountPrice(BigDecimal goodsSumPrice, Long userId,
			String province);
	
	/**
	 * app用户登录 赠送8.8折优惠券
	 * @param userId    
	 * @return void    
	 * Date:2017年11月22日 下午5:02:45 
	 * @author hexw
	 */
	Coupon appPresentCoupon(Long userId);
	
	/**
	 * 判断赠送优惠券活动是否在活动范围内 0 不在 1在
	 * @param onlineActvivty
	 * @return    
	 * @return Integer    
	 * Date:2018年1月5日 上午11:11:45 
	 * @author hexw
	 */
	Map<String,Object> checkPresenCoupontDate();
	
	/**
	 * 判断是否是拼团抽奖商品
	 * @param goodsId
	 * @return
	 */
	boolean judgeLuckWap(Long goodsId);
	/**
	 * 根据用户名称查询该用户是否可以参加猜猜看活动
	 * @param userId
	 * @return ActivityQuestion
	 * @author lij
	 * @date 2018年1月29日 下午2:17:33
	 */
	ActivityQuestion findOnlineActvity();
	/**
	 * 用户提交答案后判断是否正确正确了就赠送相关优惠卷
	 * @param userAnswer
	 * @param userId
	 * @param questionId void
	 * @author lij
	 * @date 2018年1月29日 下午2:54:16
	 */
	int submitAnswer(String userAnswer,Long userId,Long questionId);
}
  