package com.zfj.xmy.common.service;  

import java.math.BigDecimal;

import com.zfj.xmy.common.persistence.pojo.Order;

/** 
 * @Title: CommonOrderService.java 
 * @Package com.zfj.xmy.common.service 
 * @Description: 
 * @author hexw
 * @date 2017年11月18日 下午3:56:27 
 */
public interface CommonPayOrderService {
	
	/**
	 * 获取用户所有购物卡余额
	 * @param userId
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月20日 上午11:15:27 
	 * @author hexw
	 */
	BigDecimal getUserAllBalance(Long userId);
	/**
	 * 登录用户才能参与 true:可以参加 false:不能参加
	 * @param order
	 * @param userId
	 * @return boolean
	 * @author lij
	 * @date 2018年2月1日 下午2:23:06
	 */
	boolean checkOrder(Order order,Long userId);
	
	/**
	 * 登录用户才能参与 true:可以参加 false:不能参加
	 * @param order
	 * @param userId
	 * @return boolean
	 * @author lij
	 * @date 2018年2月1日 下午2:23:06
	 */
	boolean checkOrder(String goodsId,Long userId);
	
	/**
	 * @param orderId   0:支付成功 1：支付失败
	 * @return    
	 * @return int    
	 * Date:2017年11月30日 下午3:25:22 
	 */
	int getIsPaySuccess(Long orderId);

	/**
	 * 判断支付成功的订单是否能取消
	 * @param orderId
	 * @return    
	 * @return int    
	 * Date:2017年12月17日 下午6:53:17 
	 * @author hexw
	 */
	int checkPaySucessOrderIsCancle(Long orderId);

	/**
	 * 定时取消订单（未支付的订单）
	 *     
	 * @return void    
	 * Date:2017年12月19日 下午2:49:52 
	 * @author hexw
	 */
	void cancelOrderTimer();

	/**
	 * 定时扩展订单
	 *     
	 * @return void    
	 * Date:2017年12月29日 下午3:59:51 
	 * @author hexw
	 */
	void extensionOrderTimer();
	/**
	 * 	定时修改订单备注
	 *  void
	 * @author lij
	 * @date 2018年1月5日 上午9:50:24
	 */
	void updateOrderRemark();

	/**
	 * 定时发送短信统计昨天已供货的订单
	 *     
	 * @return void    
	 * Date:2018年1月5日 下午6:22:19 
	 * @author hexw
	 */
	void countExtensionOrderTimer();

	
	/**
	 * 定时执行扩张订单存储过程
	 *     
	 * @return void    
	 * Date:2018年1月18日 下午4:45:41 
	 * @author hexw
	 */
	void extensionOrderTimerPRO();

	
	/**
	 * 定时修改订单备注存储过程
	 *     
	 * @return void    
	 * Date:2018年1月18日 下午4:46:16 
	 * @author hexw
	 */
	void addOrderRemarkTimerPro();
	/**
	 * 定时下架满额减商品
	 *  void
	 * @author lij
	 * @date 2018年2月8日 下午6:32:53
	 */
	void updateGoodsPutway();
	
}
  