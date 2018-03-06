package com.zfj.xmy.order.service.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.PayWay;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppGoodsVo;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderAllDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderDir;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderVo;
import com.zfj.xmy.order.persistence.app.pojo.dto.CancelOrderInDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.OrderOutDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.RefundInDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.app.OrderStatusCountDto;

public interface AppOrderService {

	/**
	 * 根据用户id获取app订单信息
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2017年7月20日  上午9:55:57
	 * @author wy
	 * 2017
	 * @param appOrderAll 
	 * @return List<Order>
	 */
	public List<OrderOutDto> findByUserId(Long userid, AppOrderAllDto appOrderAll);

	
	/**
	 * 申请退款
	 * @param refundIn
	 * @Description 
	 * @date 2017年8月1日  上午11:05:26
	 * @author wy
	 * 2017
	 * @return void
	 */
	public void refund(RefundInDto refundIn);
	
	/**
	 * 取消订单
	 * @param cancelOrderIn
	 * @Description 
	 * @date 2017年8月1日  下午4:30:00
	 * @author wy
	 * 2017
	 * @return void
	 */
	public void cancelOrder(CancelOrderInDto cancelOrderIn);


	/**
	 * 确认收货
	 * @param cancelOrderIn
	 * @Description 
	 * @date 2017年8月2日  上午9:20:42
	 * @author wy
	 * 2017
	 * @return void
	 */
	public void confirmReceipt(CancelOrderInDto cancelOrderIn);

	/**
	 * 根据状态分组获取订单数量
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午2:35:59
	 * @author wy
	 * 2017
	 * @return List<OrderStatusCountDto>
	 */
	List<OrderStatusCountDto> getOrderCount(Long userid);

	/**
	 * 根据活动类型查询商品价格
	 * @param activityId
	 * @param goodsId
	 * @param activityType
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年8月22日 下午3:04:39 
	 * @author hexw
	 */
	BigDecimal getActivityGoodsPrice(long activityId, long goodsId,
			Integer activityType);

	/**
	 * 查询订单
	 * @param list
	 * @param userId
	 * @return    
	 * @return AppOrderDir    
	 * Date:2017年10月11日 下午12:23:15 
	 * @author hexw
	 */
	AppOrderDir findOrder(List<AppGoodsVo> list, Long userId);

	/**
	 * 生成订单/查询下单前数据
	 * @param vo
	 * @param list   
	 * @param 是否生成订单 ： true 生成订单,false 只是查询 
	 * @return void    
	 * Date:2017年10月11日 下午12:37:28 
	 * @author hexw
	 */
	 Map<String,Object> insertOrder(AppOrderVo vo, List<AppGoodsVo> list,Boolean isgenarateOrder);


	 /***
	  * 订单详情
	  * @param cancelOrderIn
	  * @Description 
	  * @date 2017年10月23日  上午9:20:49
	  * @author wy
	  * 2017
	  * @return void
	  */
	public JSONObject OrderDetail(CancelOrderInDto cancelOrderIn);

	
	/**
	 * 根据购物车信息生成AppGoodsVo (查询订单使用到)
	 * @param shoppingCart
	 * @return    
	 * @return AppGoodsVo    
	 * Date:2017年10月30日 上午11:40:30 
	 * @author hexw
	 */
	AppGoodsVo createAppGoodsVo(ShoppingCart shoppingCart);

	
	/**
	 * 就算运费和开业活动打折后的金额
	 * @param list
	 * @param provinces
	 * @param area
	 * @param userId
	 * @return    
	 * @return Map<String,Object>    
	 * Date:2017年11月13日 上午1:39:21 
	 * @author hexw
	 */
	Map<String, Object> getFreight(List<AppGoodsVo> list, String provinces,
			String area, Long userId);


	void spendPoints(Long userId, Integer spendPoints);
	/**
	 * 根据订单编号查询订单信息
	 * @param orderNo
	 * @return Order
	 * @author lij
	 * @date 2018年1月24日 下午2:05:30
	 */
	List<Order> getOrderByOrderNo(String orderNo);
}
