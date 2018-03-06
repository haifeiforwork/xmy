package com.zfj.xmy.pay.service.ali;

/**
 * 支付宝 客户端同步返回验签 DTO
 *
 * @Description:  
 * @author wy
 * @time Dec 30, 2016 2:07:41 PM
 */
public class AlipayClientSyncDto{

	public static String SIGN_TYPE = "RSA";
	
	/**
	 * 原始字符串
	 */
	public String alipay_trade_app_pay_response;
	
	/**
	 * 签名类型
	 */
	public  String sign_type;
	
	/**
	 * 签名字符
	 */
	public String sign;

	
	public String getAlipay_trade_app_pay_response() {
		return alipay_trade_app_pay_response;
	}

	public void setAlipay_trade_app_pay_response(
			String alipay_trade_app_pay_response) {
		this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
}
