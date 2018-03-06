package com.zfj.xmy.pay.service.unionpay;

public interface UnionPayWap {

	/**
	 * 手机网页支付- 消费交易 - 获取 打开银联页面地址 (浏览器跳转)
	 * 
	 * @param orderId 商户订单号
	 * @param amount 交易金额 (元)
	 * @param subject 订单标题
	 * @param attach 
	 * @param notify_url 通知回调地址
	 * @return
	 * @Description 
	 * @date 2017年8月18日  下午5:37:06
	 * @author wy
	 * 2017
	 * @param frontUrl 
	 * @return String
	 */
	String WapConsume(String orderNum, Double amount, String subject,
			String attach, String notify_url, String frontUrl);

}
