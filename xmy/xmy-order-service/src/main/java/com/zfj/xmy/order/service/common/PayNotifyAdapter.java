package com.zfj.xmy.order.service.common;

import java.math.BigDecimal;

import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.TradeProduction;
import com.zfj.xmy.pay.service.pay.PayNotify;

public interface PayNotifyAdapter extends PayNotify {

	/**
	 * 根据商户订单号 获取订单付款金额
	 * @param orderid
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午3:38:30
	 * @author wy
	 * 2017
	 * @return double
	 */
	double getTotalAmountBySerialnumber(String ordernum);

	/***
	 * 购买商品成功业务处理
	 * @param order
	 * @param autotal 第三方实付金额
	 * @Description 
	 * @date 2017年10月24日  下午7:19:32
	 * @author wy
	 * 2017
	 * 
	 * @return void
	 */
	void buyGoodsBusiness(Order order,String autotal);
	/**
	 * 修改订单支付方式
	 * @param production void
	 * @author lij
	 * @date 2017年12月12日 下午2:30:55
	 */
	void updateOrderPayType(TradeProduction production);

}
