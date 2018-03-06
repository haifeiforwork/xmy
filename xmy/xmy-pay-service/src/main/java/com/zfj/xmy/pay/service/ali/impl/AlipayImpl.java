package com.zfj.xmy.pay.service.ali.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.pay.service.ali.AlipayClientSyncDto;
import com.zfj.xmy.pay.service.ali.Alipay;
import com.zfj.xmy.pay.service.pay.PayConstant;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;


public abstract  class AlipayImpl  implements Alipay {

//	@Autowired
//	private  PayConfigService payConfigService;
	
	/**
	 * 支付宝 客户端同步返回验签
	 * @param alipayClientSync 原始字符串/签名字符
	 * @Description 
	 * @date 2017年8月10日  下午1:57:26
	 * @author wy
	 * 2017
	 * @return void
	 */
	public  void alipayClientSyncSign(AlipayClientSyncDto alipayClientSync,PayConfigService payConfigService) {
		 
		//1.获取输入数据
		 String alipay_trade_app_pay_response = alipayClientSync.getAlipay_trade_app_pay_response();
		 String sign = alipayClientSync.getSign();
		 String signtype = StringUtil.isEmpty(alipayClientSync.getSign_type())?AlipayClientSyncDto.SIGN_TYPE:alipayClientSync.getSign_type();
		 
		 //2.获取配置数据
		 String WECHAT_JSAPI = payConfigService.getPayConfig(PayBaseImpl.TRADETYPE_ALIPAY_APP);
		JSONObject jo = new JSONObject(WECHAT_JSAPI);
			
		String appid = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_APPID);
		String download_public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_DOWNLOAD_PUBLIC_KEY);
		String partner_id = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PARTNER_ID);
		String private_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PRIVATE_KEY);
		String public_key = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_PUBLIC_KEY);
		String server_url = jo.getStr(PayConstant.PAY_CONSTANT.ALIPAY_SERVER_URL);
		 
		 //3.验签
		 try {
//			ObjectMapper mapper = new ObjectMapper();
//		    Map<String, String> map = new HashMap<String, String>();
//			map = mapper.readValue(alipay_trade_app_pay_response, new TypeReference<Map<String, String>>(){});
//			map.put("sign", sign);
//			
			Boolean isSignOk = AlipaySignature.rsaCheck(alipay_trade_app_pay_response, sign, download_public_key, AlipayConstants.CHARSET_UTF8, signtype);
			if (isSignOk) {
				/**
				 * 签名验证通过后，必须严格按照如下的描述校验通知参数的合法性：
				 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
				 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
				 * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
				 * 4、验证app_id是否为该商户本身。
				 * 上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
				 */
				JSONObject vanillajson = new JSONObject(alipay_trade_app_pay_response);
				String orderNum = vanillajson.getStr("out_trade_no");
				Double total_amount = vanillajson.getDouble("total_amount");
				String seller_id = vanillajson.getStr("seller_id");
				String app_id = vanillajson.getStr("app_id");
				
				Double amount = getAmountByOrderNum(orderNum);
				
				if (!total_amount.equals(new Double(amount))) {
					throw new BusinessException("金额验签失败");
				}
				if (!partner_id.equals(seller_id)) {
					throw new BusinessException("商户验签失败");
				}
				if (appid.equals(app_id)) {
					throw new BusinessException("商户验签失败");
				}
				
				//验签成功业务
			}else {
				throw new BusinessException("验签失败");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new BusinessException("验签失败");
		}
	}
	
	/**
	 * 根据商户订单号查询金额(用于验证金额)
	 * @param orderNum
	 * @return
	 * @Description 
	 * @date 2017年8月10日  下午2:29:40
	 * @author wy
	 * 2017
	 * @return Double
	 */
	public abstract  double getAmountByOrderNum(String orderNum);
}
