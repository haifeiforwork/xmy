package com.zfj.xmy.pay.service.unionpay.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.PayConstant;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.pay.vo.UnionPayConfigVo;
import com.zfj.xmy.pay.service.unionpay.UnionPayWap;
import com.zfj.xmy.pay.service.unionpay.sdk.AcpService;
import com.zfj.xmy.pay.service.unionpay.sdk.CertUtil;
import com.zfj.xmy.pay.service.unionpay.sdk.SDKConfig;
import com.zfj.xmy.pay.service.util.MoneyUtil;

@Service
public class UnionPayWapImpl implements UnionPayWap{

	private static Logger logger = LoggerFactory
			.getLogger(UnionPayWapImpl.class);
	@Autowired
	private PayBase payBase;
	
	@Override
	public String WapConsume(String orderNum, Double amount, String subject,
			String attach, String notify_url,String frontUrl) {
		// 传入参数
		String txnAmt = MoneyUtil.getMoney(amount + "");// 交易金额 单位为分
		String orderId = orderNum;// 商户订单号
		String backUrl = notify_url;// 通知回调地址

		// 配置的可变参数 - 测试
		UnionPayConfigVo unionPayConfig = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_UNIONPAY_WAP, UnionPayConfigVo.class);
		String merId =  unionPayConfig.getMer_id();//"";//商户号码
		String certId = CertUtil.getSignCertId(); //证书ID
		String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();  //app 消费类交易请求url
		
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); //"https://gateway.test.95516.com/gateway/api/frontTransReq.do";  //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
		

		// 其它参数
		String tnxTime = DateUtil.format(DateUtil.date(), "YYYYMMddHHmmss");
		//
		Map<String, String> contentData = new HashMap<String, String>();

		/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
		contentData.put("version", PayConstant.PAY_CONSTANT.UNIONPAY_VERSION); // 版本号
																				// 全渠道默认值
		contentData.put("encoding", PayConstant.PAY_CONSTANT.UNIONPAY_ENCODING); // 字符集编码 // 可以使用UTF-8,GBK两种方式
		contentData.put("frontUrl", frontUrl);		//前台通知地址，要改成成功页面											
		contentData.put("certId", certId);
		contentData.put("signMethod",
				PayConstant.PAY_CONSTANT.UNIONPAY_SIGNMETHOD); // 签名方法
		contentData.put("txnType", "01"); // 交易类型 01:消费
		contentData.put("txnSubType", "01"); // 交易子类 01：消费
		contentData.put("bizType", "000201"); // 填写000201
		contentData.put("channelType", "08"); // 渠道类型 08手机
		
		/***商户接入参数***/
		contentData.put("merId", merId);   		 				//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
		contentData.put("accessType", "0");            		 	//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
		contentData.put("backUrl", backUrl);
		
		contentData.put("orderId", orderId);        	 	    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
		contentData.put("txnTime", tnxTime);		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		contentData.put("reqReserved", attach);					 	//商户自定义保留域，交易应答时会原样返回 
		contentData.put("txnAmt", txnAmt);						//交易金额 单位为分，不能带小数点
		contentData.put("currencyCode", "156");                 //境内商户固定 156 人民币
		contentData.put("orderDesc", subject);                  //描述订单信息，显示在银联支付控件或客户端支付界面中
		
		/**对请求参数进行签名并发送http post请求，接收同步应答报文**/  
		Map<String, String> reqData = AcpService.sign(contentData, PayConstant.PAY_CONSTANT.UNIONPAY_ENCODING);//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
				 							 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
		
		
		
		////
		
		String html = AcpService.createAutoFormHtml(requestFrontUrl, reqData,PayConstant.PAY_CONSTANT.UNIONPAY_ENCODING);   //生成自动跳转的Html表单
		
		logger.debug("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);
		//将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
		JSONObject jo = new JSONObject();
		jo.put("html", html);
		String retStr = jo.toString();
		return retStr;
	}
	
}
