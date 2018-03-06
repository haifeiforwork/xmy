package com.zfj.xmy.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;

/***
 * 微信 登录类
 * @author wy
 *
 */
public class WechatLogin {

	/**
	 * 获取code地址
	 */
	private static String getcodeurl = "https://open.weixin.qq.com/connect/qrconnect"; 
	/**
	 * 获取openid地址
	 */
	private static String getopenidurl = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	/**
	 * 获取用户信息地址
	 */
	private static String getuserinfourl = "https://api.weixin.qq.com/sns/userinfo";
	/**
	 * 登录回调地址
	 */
	private static String callbackurl = BaseProp.BASE.getValue("WECHAT_LOGIN_CALLBACKURL");
	
	private static String appid = BaseProp.BASE.getValue("WECHAT_LOGIN_APPID");
	private static String secret = BaseProp.BASE.getValue("WECHAT_LOGIN_SECRET");
	
	/**
	 * 微信- 获取微信扫码地址
	 * @param code
	 * @return
	 * @Description 
	 * @date 2017年9月30日  上午10:40:20
	 * @author wy
	 * 2017
	 * @return String
	 */
	public static String wechatGetCodeFullUrl() {
		String url = getcodeurl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("appid", appid);
		paramMap.put("redirect_uri", callbackurl);
		paramMap.put("response_type", "code");
		paramMap.put("scope", "snsapi_login");
		paramMap.put("state", new Date().getTime());
		return url +"?"+ HttpUtil.toParams(paramMap);
	}
	
	
	public static JSONObject wechatcodeToGetInfo(String code) {
		String url = getopenidurl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("appid", appid);
		paramMap.put("secret", secret);
		paramMap.put("code", code);
		paramMap.put("grant_type", "authorization_code");
		String retStr = HttpUtil.get(url, paramMap);
		JSONObject rejo = new JSONObject(retStr);
		return rejo;
	}
	
	/**
	 * 微信- 根据 code 获取openid
	 * @param code
	 * @Description 
	 * @date 2017年9月29日  下午2:26:50
	 * @author wy
	 * 2017
	 * @return openid
	 */
	public static String wechatcodeToGetOpenid(String code) {
		JSONObject rejo = wechatcodeToGetInfo(code);
		System.out.println("威信登录返回"+ rejo);
		String openid = rejo.getStr("openid");
		if (StringUtil.isEmpty(openid)) {
			throw new BusinessException("获取微信openId出错");
		}
		return openid;
	}


	public static JSONObject getUserInfo(String token, String openid) {
		String url = getuserinfourl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("access_token", token);
		paramMap.put("openid", openid);
		String retStr = HttpUtil.get(url, paramMap);
		JSONObject rejo = new JSONObject(retStr);
		return rejo;
	}
}
