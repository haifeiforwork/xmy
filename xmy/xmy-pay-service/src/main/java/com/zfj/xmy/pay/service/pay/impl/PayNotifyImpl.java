package com.zfj.xmy.pay.service.pay.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xml.sax.InputSource;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.persistence.dao.TradeProductionMapper;
import com.zfj.xmy.common.persistence.pojo.TradeProduction;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.PayConstant;
import com.zfj.xmy.pay.service.pay.PayNotify;
import com.zfj.xmy.pay.service.pay.vo.AlipayConfigVo;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;
import com.zfj.xmy.pay.service.unionpay.sdk.AcpService;
import com.zfj.xmy.pay.service.unionpay.sdk.LogUtil;
import com.zfj.xmy.pay.service.unionpay.sdk.SDKConstants;
import com.zfj.xmy.pay.service.wechat.RequestHandler;
import com.zfj.xmy.pay.service.wechat.WxPayResult;
import com.zfj.xmy.util.MapUtil;


public abstract class PayNotifyImpl implements PayNotify {

    private static Logger logger = LoggerFactory.getLogger(PayNotify.class);
	
	
	
	/**
	 * 支付通知
	 * @param request
	 * @param response
	 * @param paytype
	 * @return
	 * @Description 
	 * @date 2017年8月10日  下午4:01:54
	 * @author wy
	 * 2017
	 * @return String
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void payNotify (HttpServletRequest request,HttpServletResponse response,int paytype,TradeProductionMapper tradeProductionMapper,PayBase payBase){
		switch (paytype) {
		case PayBaseImpl.TRADETYPE_WX_JSAPI:
			String retStr = payNotifyWechat(request, payBase, tradeProductionMapper);
			returnStr(response, retStr);
			break;
		case PayBaseImpl.TRADETYPE_WX_APP:
			String retString = payNotifyWechat(request, payBase, tradeProductionMapper);
			returnStr(response, retString);
			break;
		case PayBaseImpl.TRADETYPE_ALIPAY_APP:
			String retAli = payNotifyAlipay(request, payBase, tradeProductionMapper);
			returnStr(response, retAli);
			break;
		case PayBaseImpl.TRADETYPE_UNIONPAY_APP:
			String retUnionApp = payNotifyUnionApp(request, tradeProductionMapper);
			returnStr(response, retUnionApp);
			break;
		case PayBaseImpl.TRADETYPE_WX_MWEB:
			String retmweb = payNotifyWechat(request, payBase, tradeProductionMapper);
			returnStr(response, retmweb);
			break;
		case PayBaseImpl.TRADETYPE_WX_SCANCODE:
			String retScancode = payNotifyWechat(request, payBase, tradeProductionMapper);
			returnStr(response, retScancode);
			break;
		default:
			String errormsg = "支付回调失败-传入支付类型不正确!";
			logger.error(errormsg, new BusinessException(errormsg));
			returnStr(response, PayConstant.PAY_CONSTANT.RESULT_FAILE);
		}
	}
	
	/**
	 * 银联 - 手机 控件支付 回调
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年8月17日  下午5:32:53
	 * @author wy
	 * 2017
	 * @return String
	 */
	private String payNotifyUnionApp(HttpServletRequest request,TradeProductionMapper tradeProductionMapper) {
		String encoding = request.getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = getAllRequestParam(request);
		LogUtil.printRequestLog(reqParam);

		//重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(reqParam, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
			//验签失败，需解决验签问题
			
		} else {
			LogUtil.writeLog("验证签名结果[成功].");
			//【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
			
			String orderId =reqParam.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
			String respCode = reqParam.get("respCode");
			String txnAmt = reqParam.get("txnAmt"); //交易金额 - 单位分
			String reqReserved = reqParam.get("reqReserved"); //	商户自定义保留域，交易应答时会原样返回
			
			//判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
			if ("00".equals(respCode)) {
				double amtdb = new Double(txnAmt)/100 ; //交易金额- 元 - double 类型
				String amount = Double.toString(amtdb) ; //交易金额- 元
				
				//1.更新交易记录表
				TradeProduction production = updateTradeProduction(amtdb,reqReserved, tradeProductionMapper);
				//2.处理业务
				HandleBusiness(orderId,amount, reqReserved, production);
			}
			
		}
		LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		//返回给银联服务器http 200  状态码
		return PayConstant.PAY_CONSTANT.RESULT_OK;
	}


	

	/**
	 * 微信支付通知 ( 微信APP 和 微信 JSAPI 共用 )
	 * @Description:  
	 * @param request
	 * @param payNotify 回调对象
	 * @return  responseXml WEB页面输出字符
	 * @author wy
	 * @date Dec 28, 2016  10:42:43 AM
	 */
	private String payNotifyWechat(HttpServletRequest request,
			PayBase payBase,TradeProductionMapper tradeProductionMapper) {
		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SortedMap<String, String> m = parseXmlToSortMap(notityXml);
		WxPayResult wpr = new WxPayResult();
		wpr.setAppid(m.get("appid").toString());
		wpr.setBankType(m.get("bank_type").toString());
		wpr.setCashFee(m.get("cash_fee").toString());
		wpr.setFeeType(m.get("fee_type").toString());
		wpr.setIsSubscribe(m.get("is_subscribe").toString());
		wpr.setMchId(m.get("mch_id").toString());
		wpr.setNonceStr(m.get("nonce_str").toString());
		wpr.setOpenid(m.get("openid").toString());
		wpr.setOutTradeNo(m.get("out_trade_no").toString());
		wpr.setResultCode(m.get("result_code").toString());
		wpr.setReturnCode(m.get("return_code").toString());
		wpr.setSign(m.get("sign").toString());
		wpr.setAttach(m.get("attach"));
		wpr.setTimeEnd(m.get("time_end").toString());
		wpr.setTotalFee(m.get("total_fee").toString());
		wpr.setTradeType(m.get("trade_type").toString());
		wpr.setTransactionId(m.get("transaction_id").toString());
		String sign = m.get("sign").toString();
		
		// 对于支付结果通知的内容一定要做签名验证
		String trade_type = wpr.getTradeType();
		String mch_id = "";
		String appid = "";
		String appsecret = "";
		String partnerkey = "";

		// 商户号 -
		if ("JSAPI".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(
					PayBaseImpl.TRADETYPE_WX_JSAPI, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
		} else if ("APP".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(
					PayBaseImpl.TRADETYPE_WX_APP, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
		} else if ("MWEB".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(
					PayBaseImpl.TRADETYPE_WX_APP, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
		}else if ("NATIVE".equals(trade_type)) {
			WxPayConfigVo wxPayConfigVo = payBase.getPayConfigObject(
					PayBaseImpl.TRADETYPE_WX_SCANCODE, WxPayConfigVo.class);
			appid = wxPayConfigVo.getAppid();
			mch_id = wxPayConfigVo.getMch_id();
			appsecret = wxPayConfigVo.getAppsecret();
			partnerkey = wxPayConfigVo.getPartnerkey();
		}
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String resign = reqHandler.createSign(m);
		if ("SUCCESS".equals(wpr.getResultCode()) && sign.equals(resign)) {
			String amount = Double.toString(new Double(wpr.getTotalFee())/100) ; //交易金额- 元
			//1.更新交易记录表
			TradeProduction production = updateTradeProduction(new Double(wpr.getTotalFee())/100,wpr.getAttach(), tradeProductionMapper);
			//2.处理业务
			HandleBusiness(wpr.getOutTradeNo(),amount, wpr.getAttach(), production);
			
			
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
		} else {
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		return resXml;
	}
	
	/**
	 * 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static SortedMap parseXmlToSortMap(String xml) {
		SortedMap<String, String> retMap = new TreeMap<String, String>();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	
	
	/**
	 * 支付宝 支付通知
	 * @Description:  
	 * @param map
	 * @param payNotify
	 * @return  
	 * @author wy
	 * @date Jan 3, 2017  5:36:11 PM
	 */
	private String payNotifyAlipay(HttpServletRequest request,PayBase payBase,TradeProductionMapper tradeProductionMapper) {

		//1.获取返回数据
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();
		while (enumeration.hasMoreElements()) {
			String key =  enumeration.nextElement();
			if ("sign".equals(key)) {
				String val = request.getParameter(key);
				map.put(key,val);
			}else if ("sign_type".equals(key)) {
				map.put(key,request.getParameter(key));
			}else {
				String val = null;
				try {
					val = URLDecoder.decode(request.getParameter(key), "utf-8");
				} catch (UnsupportedEncodingException e) {
					String errormsg = "支付回调失败-支付宝返回键值不正常!";
					logger.error(errormsg, new BusinessException(errormsg));
					e.printStackTrace();
				}
				map.put(key,val);
			}
			System.out.println(key + "----:----"+request.getParameter(key));
		}
		//2.获取配置
		AlipayConfigVo alipayConfig = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_ALIPAY_APP, AlipayConfigVo.class);

		String appid = alipayConfig.getAppid();
		String partner_id = alipayConfig.getPartner_id();
		String private_key = alipayConfig.getPrivate_key();
		String public_key = alipayConfig.getPublic_key();
		String download_public_key = alipayConfig.getDownload_public_key();
		String server_url = alipayConfig.getServer_url();
		
		String failed = PayConstant.PAY_CONSTANT.RESULT_FAILE;
		//3.处理回调请求
		try {
			String sign = map.get("sign");
			String urlparam = MapUtil.getUrlParamsByMap(map, true);			
			System.out.println("----------原始参数内容：----------"+urlparam); 
			System.out.println("----------获取到的签名值：----------"+sign);
			Boolean signRes = AlipaySignature.rsaCheckV1(map, download_public_key, AlipayConstants.CHARSET_UTF8);
			//测试 
			//signRes = true;
			//TODO:改回来
			System.out.println("----------签名结果：----------"+signRes);
			if (signRes) {
				/**
				 * 签名验证通过后，必须严格按照如下的描述校验通知参数的合法性：
				 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
				 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
				 * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
				 * 4、验证app_id是否为该商户本身。
				 * 上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
				 */
				JSONObject vanillajson = new JSONObject(map);
				String orderNum = vanillajson.getStr("out_trade_no");
				Double total_amount = vanillajson.getDouble("total_amount");
				String seller_id = vanillajson.getStr("seller_id");
				String app_id = vanillajson.getStr("app_id");
				
				System.out.println("返回的商户订单号:"+orderNum);
				System.out.println("返回的金额:"+total_amount);
				System.out.println("返回的卖家ID:"+seller_id);
				System.out.println("返回的appid:"+app_id);

				if (!total_amount.equals(getAmountByOrderNum(orderNum))) {
					return failed;
				}
				if (!partner_id.equals(seller_id)) {
					return failed;
				}
				if (!appid.equals(app_id)) {
					return failed;
				}
				//map.get("trade_status").equals("TRADE_SUCCESS");
				String trade_status = map.get("trade_status");
				System.out.println("交易状态:"+trade_status);
				if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
					String attach = map.get("passback_params");
					
					//1.更新交易记录表
					TradeProduction production = updateTradeProduction(total_amount, attach,tradeProductionMapper);
					//2.处理业务
					HandleBusiness(orderNum, Double.toString(total_amount), attach,production);
				}
				
			}else {
				return failed;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return failed;
		}
		return failed;
		
	}
	
	
	
	
	
	
	/**
	 * 支付成功 后 处理业务方法
	 *  (要防止重复做业务,回调成功通知可能会发送数次)
	 * 
	 * @Description 
	 * @date 2017年8月10日  下午3:54:49
	 * @author wy
	 * 2017
	 * @param orderNum 商户订单号
	 * @param amount 交易金额 (元)
	 * @param attach 附加数据
	 * @param production 交易记录
	 * @return void
	 */
	@Override
	public abstract void HandleBusiness(String orderNum,String amount, String attach,TradeProduction production );
	
	/**
	 * 根据商户订单号  查询金额(用于验证金额)
	 * @param orderNum
	 * @return
	 * @Description 
	 * @date 2017年8月10日  下午2:29:40
	 * @author wy
	 * 2017
	 * @return Double
	 */
	@Override
	public abstract  double getAmountByOrderNum(String orderNum);
	/**
	 * WEB页面返回字符
	 * @param response
	 * @param returnStr
	 * @Description 
	 * @date 2017年8月10日  下午4:22:27
	 * @author wy
	 * 2017
	 * @return void
	 */
	private void returnStr(HttpServletResponse response,String returnStr){
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			out.write(returnStr.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新交易记录表
	 * @param amount 交易金额
	 * @param attach 附加信息 (业务流水号 flowno)
	 * @Description 
	 * @date 2017年8月11日  下午5:56:23
	 * @author wy
	 * 2017
	 * @return TradeProduction 更新后的实体
	 */
	private TradeProduction updateTradeProduction(double amount,String attach,TradeProductionMapper tradeProductionMapper){
		
		CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.equalTo("flowno", attach); 
		parameter.setOrderByClause("created desc");
		List<TradeProduction> productions = tradeProductionMapper.selectByExample(parameter);
		if (!CollectionUtils.isEmpty(productions)) {
			TradeProduction production = productions.get(0);
			production.setNoticestate(PayConstant.TRADE_PRODUCTION.NOTICESTATE_OK);
			production.setTranstate(PayConstant.TRADE_PRODUCTION.TRANSTATE_OK);
			production.setTrantime(DateUtil.date());
			production.setTranmoney(amount);
			tradeProductionMapper.updateByPrimaryKeySelective(production);
			return production;
		}
		return null;
		
		
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
