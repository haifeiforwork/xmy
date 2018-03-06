package com.zfj.xmy.pay.service.wechat.impl;

import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.StrUtil;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.dto.WxPayDto;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;
import com.zfj.xmy.pay.service.util.MoneyUtil;
import com.zfj.xmy.pay.service.wechat.GetWxOrderno;
import com.zfj.xmy.pay.service.wechat.RequestHandler;
import com.zfj.xmy.pay.service.wechat.Sha1Util;
import com.zfj.xmy.pay.service.wechat.TenpayUtil;
import com.zfj.xmy.pay.service.wechat.WeChatApi;
import com.zfj.xmy.pay.service.wechat.WechatH5Result;

@Service
public class WeChatApiImpl implements WeChatApi{

	@Autowired
	private  PayConfigService payConfigService;
	
	@Autowired
	private PayBase payBase;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*//微信支付jsApi
		WxPayDto tpWxPay = new WxPayDto();
		tpWxPay.setOpenId(Configure.getOpenId());
		tpWxPay.setBody("商品信息");
		tpWxPay.setOrderId(getNonceStr());
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee("0.01");
		
	    getPackage(tpWxPay);
	    
	    //扫码支付
	    WxPayDto tpWxPay1 = new WxPayDto();
	    tpWxPay1.setBody("商品信息");
	    tpWxPay1.setOrderId(getNonceStr());
	    tpWxPay1.setSpbillCreateIp("127.0.0.1");
	    tpWxPay1.setTotalFee("0.01");
		getCodeurl(tpWxPay1);*/

	}
	
	/**
	 * 获取微信扫码支付二维码连接
	 */
	/*public static String getCodeurl(WxPayDto tpWxPayDto){
		
		// 1 参数
		// 订单号
		String orderId = tpWxPayDto.getOrderId();
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = tpWxPayDto.getNotifyUrl();
		String trade_type = "NATIVE";

		// 商户号
		String mch_id = Configure.getMchid();
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", Configure.getAppid());
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(Configure.getAppid(), Configure.getAppsecret(), Configure.getPartnerkey());

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + Configure.getAppid() + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "</xml>";
		String code_url = "";
		//String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		code_url = new GetWxOrderno().getCodeUrl(Configure.UNIFIED_PAY_API, xml);
		System.out.println("code_url----------------"+code_url);
		
		return code_url;
	}*/
	
	
	@Override
	@SuppressWarnings("static-access")
	public String getPackage(WxPayDto tpWxPayDto) {
		
		
		
		// 1 参数
		// 订单号
		String orderId = tpWxPayDto.getOrderId();
		// 附加数据 原样返回
		String attach = StrUtil.nullToEmpty(tpWxPayDto.getAttach());
		// 总金额以分为单位，不带小数点
		String totalFee = MoneyUtil.getMoney(tpWxPayDto.getTotalFee());
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = tpWxPayDto.getNotifyUrl();
		String trade_type = tpWxPayDto.getTradetype();
		String openid = tpWxPayDto.getOpenId();
		
		String wap_url = tpWxPayDto.getWapUrl();
		String wap_name = tpWxPayDto.getWapName();
		String scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": "+wap_url+",\"wap_name\": "+wap_name+"}}";
		
		String mch_id = "";
		String appid = "";
		String appsecret = "";
		String partnerkey = "";
		
		String unified_pay_api = "";
		
		// 商户号 - 
		if ("JSAPI".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_WX_JSAPI, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
			unified_pay_api = wxPayConfigVo.getUnified_pay_api();
		}else if ("APP".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_WX_APP, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
			unified_pay_api = wxPayConfigVo.getUnified_pay_api();
		}else if ("MWEB".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_WX_MWEB, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
			unified_pay_api = wxPayConfigVo.getUnified_pay_api();
		}else if ("NATIVE".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_WX_SCANCODE, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
			unified_pay_api = wxPayConfigVo.getUnified_pay_api();
		}
		// ---必须参数
		
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		
		if ("JSAPI".equals(trade_type)) {
			packageParams.put("openid", openid);
		}
		if ("MWEB".equals(trade_type)) {
			packageParams.put("scene_info",scene_info );
		}
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);		
		reqHandler.init(appid, appsecret, partnerkey);
		
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" 
		        + "<appid>" + appid + "</appid>" 
		        + "<mch_id>" + mch_id + "</mch_id>" 
		        + "<nonce_str>" + nonce_str + "</nonce_str>" 
		        + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>";
		if ("JSAPI".equals(trade_type)) {
			xml += "<openid>" + openid + "</openid>";
		}
		if ("MWEB".equals(trade_type)) {
			xml += "<scene_info>" + scene_info + "</scene_info>";
		}
				xml += "</xml>";
		String prepay_id = "";
		//String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		
		if ("JSAPI".equals(trade_type)) {
			prepay_id = new GetWxOrderno().getPayNo(unified_pay_api, xml);
			System.out.println("获取到的预支付ID：" + prepay_id);
			//微信网页付款
			//获取prepay_id后，拼接最后请求支付所需要的package
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			String packages = "prepay_id="+prepay_id;
			finalpackage.put("appId", appid);  
			finalpackage.put("timeStamp", timestamp);  
			finalpackage.put("nonceStr", nonce_str);  
			finalpackage.put("package", packages);  
			finalpackage.put("signType", "MD5");
			//要签名
			String finalsign = reqHandler.createSign(finalpackage);
			finalpackage.put("packageinfo", packages);  
			finalpackage.put("paySign", finalsign);
			
			JSONObject jo = new JSONObject(finalpackage);
			String finaPackage = jo.toString();
			System.out.println("V3 jsApi package:"+finaPackage);
			return finaPackage;
		}else if ("APP".equals(trade_type)) {
			prepay_id = new GetWxOrderno().getPayNo(unified_pay_api, xml);
			System.out.println("获取到的预支付ID：" + prepay_id);
			//微信APP付款
			//获取prepay_id后，拼接最后请求支付所需要的package
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			String packages = "Sign=WXPay";
			finalpackage.put("appid", appid);  
			finalpackage.put("partnerid", mch_id);
			finalpackage.put("prepayid", prepay_id);
			finalpackage.put("package", packages);
			finalpackage.put("noncestr", nonce_str);
			finalpackage.put("timestamp", timestamp); 
			String finalsign = reqHandler.createSign(finalpackage);
			
			finalpackage.put("packageinfo", packages);
			finalpackage.put("sign", finalsign);
			
			JSONObject jo = new JSONObject(finalpackage);
			String finaPackage = jo.toString();
			System.out.println("V3 jsApi package:"+finaPackage);
			
			//APp的返回结果再、包一层
			JSONObject retJo = new JSONObject();
			retJo.put("wechat", jo);
			return retJo.toString();
		}else if ("MWEB".equals(trade_type)) {
			WechatH5Result h5Result = new GetWxOrderno().getPayNoAndMweburl(unified_pay_api, xml);
			System.out.println("获取到的预支付ID：" + h5Result.getPrepay_id());
			//微信H5付款
			//获取prepay_id后，拼接最后请求支付所需要的package
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			finalpackage.put("mweburl", h5Result.getMweb_url());
			String finalsign = reqHandler.createSign(finalpackage);
			finalpackage.put("sign", finalsign);
			
			JSONObject jo = new JSONObject(finalpackage);
			String finaPackage = jo.toString();
			System.out.println("V3 mweb package:"+finaPackage);
			return finaPackage;
		}else if ("NATIVE".equals(trade_type)) {
			String code_url = new GetWxOrderno().getCodeUrl(unified_pay_api, xml);
			//微信H5付款
			//获取prepay_id后，拼接最后请求支付所需要的package
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			finalpackage.put("code_url", code_url);
			String finalsign = reqHandler.createSign(finalpackage);
			finalpackage.put("sign", finalsign);
			
			JSONObject jo = new JSONObject(finalpackage);
			String finaPackage = jo.toString();
			System.out.println("V3 NATIVE package:"+finaPackage);
			return finaPackage;
		}
		return "";
		
		
	}

	
	
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	
}
