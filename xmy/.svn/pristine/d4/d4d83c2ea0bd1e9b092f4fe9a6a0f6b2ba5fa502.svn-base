package com.zfj.xmy.util;

import java.util.Date;

import com.appdev.util.Md5Util;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.util.net.HttpUtil;
import com.zfj.base.util.net.HttpUtil.ReqData;

/**
 * 韵达物流
 * @author wy
 *
 */
public class YundaExpressUtil {

	/***
	 * 请求地址
	 * 
	 */
	private static String requrl = "http://open.yundasys.com/monitor_new/interface.php/order_query_json";
	
	private static String partnerid = "";
	
	private static String appid = "";
	private static String appkey = "";
	
	private static String userid = "";
	
	/**
	 * 订单系统 物流信息查询服务
	 * @param no
	 * @return
	 * @Description 
	 * @date 2017年9月12日  下午2:16:42
	 * @author wy
	 * 2017
	 * @return Object
	 */
	public static Object getExpressStatus(String no ){
		
		long timestamp = new Date().getTime()/1000;
		String token = Md5Util.md5(userid+timestamp+appkey);
		String reqContent = "";
		
		//5.拼接请求报文
		JSONObject reqJson = new JSONObject();
		reqJson.put("userid",userid );
		reqJson.put("timestamp",timestamp );
		reqJson.put("token",token );
		reqJson.put("data","todo" );
		return HttpReq(reqContent);
	}
	
	
	private static Object HttpReq(String reqContent){
		ReqData reqData = HttpUtil.createReq();
		reqData.setMethod("POST");
		reqData.setUrl(requrl);
		reqData.setReqContent(reqContent);
		return HttpUtil.reqConnection(reqData).getData();
	}
}
