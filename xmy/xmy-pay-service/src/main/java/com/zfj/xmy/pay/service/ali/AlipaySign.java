package com.zfj.xmy.pay.service.ali;

public interface AlipaySign {

	/**
	 * 支付宝-APP 订单签名
	 * @Description:  
	 * @param amount 订单金额
	 * @param subject 订单商品名
	 * @param attach 
	 * @param notify_url 支付通知地址
	 * @return  
	 * @author wy
	 * @date Dec 30, 2016  10:47:46 AM
	 */
	String AlipaySignOrder(String orderId, Double amount, String subject,
			String attach, String notify_url);

	

	/***
	 * 支付宝-PC web (统一收单下单) 
	 * @param orderId
	 * @param amount
	 * @param subject
	 * @param attach
	 * @param notify_url
	 * @return
	 * @Description 
	 * @date 2017年8月24日  下午3:37:59
	 * @author wy
	 * 2017
	 * @param frontUrl 
	 * @return String
	 */
	String AlipayTradePagePay(String orderId, Double amount, String subject,
			String attach, String notify_url, String frontUrl);



	/**
	 * 支付宝-老接口 - 即时到账交易接口
	 * @param orderId
	 * @param amount
	 * @param subject
	 * @param attach
	 * @param notify_url
	 * @return
	 * @Description 
	 * @date 2017年8月25日  上午9:28:44
	 * @author wy
	 * 2017
	 * @return String
	 */
	String AlipayCreatedirectPay(String orderId, Double amount, String subject,
			String attach, String notify_url);


	/***
	 * 支付宝-wap (统一收单下单) 
	 * @param orderId
	 * @param amount
	 * @param subject
	 * @param attach
	 * @param notify_url
	 * @return
	 * @Description 
	 * @date 2017年11月7日  下午2:28:49
	 * @author wy
	 * 2017
	 * @param frontUrl 
	 * @return String
	 */
	String AlipayTradeWapPay(String orderId, Double amount, String subject,
			String attach, String notify_url, String frontUrl);

}
