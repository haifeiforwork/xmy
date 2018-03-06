package com.zfj.xmy.pay.service.ali.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.CharSet;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi.SHA1withRSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.pay.service.ali.AlipaySign;
import com.zfj.xmy.pay.service.oldAlipay.sign.MD5;
import com.zfj.xmy.pay.service.oldAlipay.util.AlipaySubmit;
import com.zfj.xmy.pay.service.pay.PayConstant;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;
import com.zfj.xmy.pay.service.wechat.Sha1Util;
import com.zfj.xmy.util.MapUtil;

@Service
public class AlipaySignImpl implements AlipaySign {

	@Autowired
	private  PayConfigService payConfigService;
	
	@Override
	public String AlipaySignOrder(String orderId,Double amount,String subject,String attach,String notify_url){
		//支付宝
		
		//1.获取配置
		String WECHAT_JSAPI = payConfigService.getPayConfig(PayBaseImpl.TRADETYPE_ALIPAY_APP);
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject(WECHAT_JSAPI);
		String alipay_appid = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_APPID);
		String private_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PRIVATE_KEY);
		
		
		//2.签名订单
		Map<String, String> params = new HashMap<String, String>();
		String now = DateUtil.now();
		params.put("app_id", alipay_appid);
		params.put("method", "alipay.trade.app.pay");
		params.put("charset", "utf-8");
		params.put("timestamp", now);
		params.put("version", "1.0");
		params.put("notify_url", notify_url);
		params.put("sign_type", "RSA");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("subject", subject);
		jsonObject.put("out_trade_no",orderId );
		DecimalFormat df = new DecimalFormat("#.##");
		jsonObject.put("total_amount", df.format(amount).toString());
		jsonObject.put("product_code", "QUICK_MSECURITY_PAY");
		jsonObject.put("passback_params", attach);
		params.put("biz_content", jsonObject.toString());
		try {
			String sign = AlipaySignature.rsaSign(params, private_key, AlipayConstants.CHARSET_UTF8);
			
			params.put("sign", URLEncoder.encode(sign, "UTF-8"));
			params.put("app_id", URLEncoder.encode(alipay_appid, "UTF-8"));
			params.put("method", URLEncoder.encode("alipay.trade.app.pay", "UTF-8"));
			params.put("charset", URLEncoder.encode("utf-8", "UTF-8"));
			params.put("timestamp", URLEncoder.encode(now, "UTF-8"));
			params.put("version", URLEncoder.encode("1.0", "UTF-8"));
			params.put("notify_url", URLEncoder.encode(notify_url, "UTF-8"));
			params.put("sign_type", URLEncoder.encode("RSA", "UTF-8"));
			params.put("biz_content", URLEncoder.encode(jsonObject.toString(), "UTF-8"));
			
			//-----正常状态
			return MapUtil.getUrlParamsByMap(params, true);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new BusinessException("订单签名出错..");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new BusinessException("编码出错..");
		}
	}
	
	
	@Override
	public String AlipayTradePagePay(String orderId,Double amount,String subject,String attach,String notify_url,String frontUrl){
		//支付宝
		
		//1.获取配置
		String alipay_app = payConfigService.getPayConfig(PayBaseImpl.TRADETYPE_ALIPAY_PC_WEB);
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject(alipay_app);
		
		String alipay_appid = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_APPID);
		String private_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PRIVATE_KEY);
		String server_url = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_SERVER_URL);
		String public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PUBLIC_KEY);//公钥
		String alipay_public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_DOWNLOAD_PUBLIC_KEY);//支付宝公钥
		
		//2.预下单
		
		AlipayClient alipayClient = new DefaultAlipayClient(server_url, alipay_appid, private_key, "json", "utf-8", alipay_public_key, "RSA");
		AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
		alipayTradePagePayRequest.setNotifyUrl(notify_url);
		alipayTradePagePayRequest.setReturnUrl(frontUrl);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("subject", subject);
		jsonObject.put("out_trade_no",orderId );
		DecimalFormat df = new DecimalFormat("#.##");
		jsonObject.put("total_amount", df.format(amount).toString());
		jsonObject.put("product_code", "FAST_INSTANT_TRADE_PAY");
		jsonObject.put("passback_params", attach);
		alipayTradePagePayRequest.setBizContent(jsonObject.toString());
		
		try {
			AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayTradePagePayRequest);
			System.out.print("支付宝pc web 支付返回:"+response.getBody());
			return response.getBody();
		} catch (AlipayApiException e1) {
			e1.printStackTrace();
			throw new BusinessException("支付失败");
		}
	}
	
	@Override
	public String AlipayTradeWapPay(String orderId,Double amount,String subject,String attach,String notify_url,String frontUrl){
		//支付宝
		
		//1.获取配置
		String alipay_app = payConfigService.getPayConfig(PayBaseImpl.TRADETYPE_ALIPAY_WAP);
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject(alipay_app);
		
		String alipay_appid = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_APPID);
		String private_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PRIVATE_KEY);
		String server_url = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_SERVER_URL);
		String public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PUBLIC_KEY);//公钥
		String alipay_public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_DOWNLOAD_PUBLIC_KEY);//支付宝公钥
		
		//2.预下单
		
		AlipayClient alipayClient = new DefaultAlipayClient(server_url, alipay_appid, private_key, "json", "utf-8", alipay_public_key, "RSA");
		AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
		alipayTradeWapPayRequest.setNotifyUrl(notify_url);//后台回调页面
		alipayTradeWapPayRequest.setReturnUrl(frontUrl);//前台通知页面
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("subject", subject);
		jsonObject.put("out_trade_no",orderId );
		DecimalFormat df = new DecimalFormat("#.##");
		jsonObject.put("total_amount", df.format(amount).toString());
		jsonObject.put("product_code", "QUICK_WAP_WAY");
		jsonObject.put("passback_params", attach);
		alipayTradeWapPayRequest.setBizContent(jsonObject.toString());
		
		try {
			AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayTradeWapPayRequest);
			System.out.print("支付宝 wap web 支付返回:"+response.getBody());
			return response.getBody();
		} catch (AlipayApiException e1) {
			e1.printStackTrace();
			throw new BusinessException("支付失败");
		}
	}
	
	
	
	@Override
	public String AlipayCreatedirectPay(String orderId,Double amount,String subject,String attach,String notify_url){
		// 1.获取配置
		String alipay_app = payConfigService.getPayConfig(PayBaseImpl.TRADETYPE_ALIPAY_OLD_DIRECTPAY);
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject(alipay_app);

		String alipay_appid = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_APPID);
		String partner_id = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PARTNER_ID);
		String private_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PRIVATE_KEY);
		String server_url = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_SERVER_URL);
		String public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PUBLIC_KEY);// 公钥
		String alipay_public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_DOWNLOAD_PUBLIC_KEY);// 支付宝公钥
		
		String requrl = "https://mapi.alipay.com/gateway.do" ;//请求支付宝网关地址
		String return_url = "";//前台  页面跳转同步通知页面路径
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("service", "create_direct_pay_by_user");
		paramMap.put("partner", partner_id);
		paramMap.put("_input_charset", "UTF-8");
		paramMap.put("notify_url", notify_url);
//		paramMap.put("return_url", return_url);
		
		//业务参数
		paramMap.put("out_trade_no", orderId);
		paramMap.put("subject", subject);
		paramMap.put("payment_type", "1");
		paramMap.put("total_fee", amount+"");
		paramMap.put("seller_id", alipay_appid);
		paramMap.put("extra_common_param", attach);
		
		//签名
		String sign = AlipaySubmit.buildRequestMysign(paramMap, private_key);
		paramMap.put("sign_type", "RSA");
		paramMap.put("sign", sign);
		
		String sHtmlText = AlipaySubmit.buildRequest(paramMap,"get","确认", private_key);
		return sHtmlText;
	}
}