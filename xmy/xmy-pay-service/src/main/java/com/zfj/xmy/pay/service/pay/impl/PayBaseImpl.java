package com.zfj.xmy.pay.service.pay.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;

@Service
public class PayBaseImpl implements PayBase {

	@Autowired
	private  PayConfigService payConfigService;
	
	/**
	 * 支付方式-微信网页端
	 */
	public static final int TRADETYPE_WX_JSAPI = 0;
	/**
	 * 支付方式-微信 APP
	 */
	public static final int TRADETYPE_WX_APP = 1;
	/**
	 * 支付方式-支付宝 APP
	 */
	public static final int TRADETYPE_ALIPAY_APP = 2;
	
	/**
	 * 支付方式-银联-手机控件支付
	 */
	public static final int TRADETYPE_UNIONPAY_APP = 3;
	/**
	 * 支付方式-银联-手机WAP支付
	 */
	public static final int TRADETYPE_UNIONPAY_WAP = 4;
	/**
	 * 支付方式-银联-网关支付
	 */
	public static final int TRADETYPE_UNIONPAY_GATEWAY = 5;
	
	/**
	 * 支付方式-微信 H5 支付
	 */
	public static final int TRADETYPE_WX_MWEB = 6;
	/**
	 * 支付方式-微信 扫码 支付
	 */
	public static final int TRADETYPE_WX_SCANCODE = 7;
	
	/**
	 * 支付方式-支付宝 - 电脑网站  支付
	 */
	public static final int TRADETYPE_ALIPAY_PC_WEB = 8;

	/**
	 * 支付方式-支付宝 - 老接口-即时到账
	 */
	public static final int TRADETYPE_ALIPAY_OLD_DIRECTPAY = 9;
	
	/**
	 * 支付方式-支付宝 - WAP网站  支付
	 */
	public static final int TRADETYPE_ALIPAY_WAP = 10;
	
	/**
	 * 支付方式  map
	 */
	public static final  Map<Integer,String> getTradeType(){
		Map<Integer,String> result = new HashMap<Integer, String>();
		result.put(TRADETYPE_WX_JSAPI, "微信网页端");
		result.put(TRADETYPE_WX_APP, "微信 APP");
		result.put(TRADETYPE_ALIPAY_APP, "支付宝 APP");
		result.put(TRADETYPE_UNIONPAY_APP, "银联-手机控件支付");
		result.put(TRADETYPE_UNIONPAY_WAP, "银联-手机WAP支付");
		result.put(TRADETYPE_UNIONPAY_GATEWAY, "银联-网关支付");
		result.put(TRADETYPE_WX_MWEB, "微信 H5 支付");
		result.put(TRADETYPE_WX_SCANCODE, "微信 扫码 支付");
		result.put(TRADETYPE_ALIPAY_PC_WEB, "支付宝 - 电脑网站  支付");
		result.put(TRADETYPE_ALIPAY_OLD_DIRECTPAY, "支付宝 - 老接口-即时到账");
		result.put(TRADETYPE_ALIPAY_WAP, "支付宝 - WAP支付");
		return result;
	}
	
	
	@Override
    public  <T> T getPayConfigObject(int payType,Class<T> clazz) {
		String json = payConfigService.getPayConfig(payType);
		return com.alibaba.fastjson.JSONObject.parseObject(json, clazz);
	}
	
	@Override
    public  <T> T getPayConfigByMerchantType(int MerchantType,Class<T> clazz) {
		String json = payConfigService.getPayConfigByMerchantType(MerchantType);
		return com.alibaba.fastjson.JSONObject.parseObject(json, clazz);
	}
	
	
}
