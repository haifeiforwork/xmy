package com.zfj.xmy.util;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;

/***
 * QQ 登录类
 * @author wy
 *
 */
public class QQLogin {

	
	
	/**
	 * 1.获取Authorization Code 的地址
	 */
	private static String getcodeurl = "https://graph.qq.com/oauth2.0/authorize"; 
	/**
	 * Step2：通过Authorization Code获取Access Token
	 */
	private static String gettokenurl = "https://graph.qq.com/oauth2.0/token"; 
	/**
	 * 获取用户OpenID_ 获取地址
	 */
	private static String getopenidurl = "https://graph.qq.com/oauth2.0/me";
	
	/**
	 * 获取用户基本信息 地址
	 */
	private static String get_user_info_url = "https://graph.qq.com/user/get_user_info";
	
	/**
	 * 获取unionid 地址
	 */
	private static String get_unionid_url = "https://graph.qq.com/oauth2.0/get_unionid";
	
	/**
	 * 登录回调地址
	 */
	private static String callbackurl = BaseProp.BASE.getValue("QQ_LOGIN_CALLBACKURL");
	private static String appid = BaseProp.BASE.getValue("QQ_LOGIN_APPID");
	private static String appkey = BaseProp.BASE.getValue("QQ_LOGIN_APPKEY");
	
	/**
	 * QQ- 获取QQ授权登录地址
	 * @param code
	 * @return
	 * @Description 
	 * @date 2017年9月30日  上午10:40:20
	 * @author wy
	 * 2017
	 * @return String
	 */
	public static String qqGetAuthorizationCodeFullUrl() {
		String url = getcodeurl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("client_id", appid);
		paramMap.put("redirect_uri", callbackurl);
		paramMap.put("response_type", "code");
		paramMap.put("state", new Date().getTime());
		return url +"?"+ HttpUtil.toParams(paramMap);
	}
	
	/**
	 * QQ- 根据 code 获取 token
	 * @param code
	 * @Description 
	 * @date 2017年9月29日  下午2:26:50
	 * @author wy
	 * 2017
	 * @return String token值
	 */
	public static String qqAuthorizationcodeToGetToken(String code) {
		String url = gettokenurl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("client_id", appid);
		paramMap.put("client_secret", appkey);
		paramMap.put("grant_type", "authorization_code");
		paramMap.put("code", code);
		paramMap.put("redirect_uri", callbackurl);
		
		String retStr = HttpUtil.get(url, paramMap);
		System.out.println("qqtoken:获取结果: "+retStr);
		
		Map<String, List<String>> map =  HttpUtil.decodeParams(retStr, "utf-8");
		String token = "";
		List<String> tokens = map.get("access_token");
		if (CollectionUtil.isNotEmpty(tokens)) {
			token = tokens.get(0);
		}
		if (StringUtil.isEmpty(token)) {
			throw new BusinessException("获取qq token出错");
		}
		return token;
	}
	
	/**
	 * QQ- 根据 openid 获取 unionid
	 * @param openid
	 * @return
	 * @Description 
	 * @date 2018年1月4日  上午10:06:05
	 * @author wy
	 * 2018
	 * @return String
	 */
	public static String qqGetUnionid(String openid) {
		//?openid=YOUR_OPENID&client_id=YOUR_APPID
		String url = get_unionid_url;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("openid", openid);
		paramMap.put("client_id",appid );
		String retStr = HttpUtil.get(url, paramMap);
		System.out.println("qq unionid :获取结果: "+retStr);
		
		String jsonStr = retStr.replace("callback(", "").replace(")", "");
		JSONObject rejo = new JSONObject(jsonStr);
		String unionid = rejo.getStr("unionid");
		
		if (StringUtil.isEmpty(unionid)) {
			throw new BusinessException("获取qq unionid 出错");
		}
		return unionid;
	}
	/**
	 * QQ- 根据 token 获取 unionid
	 * @param token
	 * @return
	 * @Description 
	 * @author wangs
	 * @return String
	 */
	public static String qqTokenToGetUnionid(String token) {
		String url = getopenidurl;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("access_token", token);
		paramMap.put("unionid", 1);
		String retStr = HttpUtil.get(url, paramMap);
		System.out.println("qq openid :获取结果: "+retStr);
		
		String jsonStr = retStr.replace("callback(", "").replace(")", "");
		JSONObject rejo = new JSONObject(jsonStr);
		String unionid = rejo.getStr("unionid");
		//String unionid = rejo.getStr("unionid");
		
		if (StringUtil.isEmpty(unionid)) {
			throw new BusinessException("获取qq unionid 出错");
		}
		
		return unionid;
		
	}
	
	/**
     * QQ- 根据 token 获取 unionid , openid
     * @param token
     * @return
     * @Description 
     * @date 2017年9月30日  下午5:12:21
     * @author wy
     * 2017
     * @return String
     */
    public static String qqTokenToGetOpenid(String token) {
        String url = getopenidurl;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("access_token", token);
        //paramMap.put("unionid", 1);
        String retStr = HttpUtil.get(url, paramMap);
        System.out.println("qq openid :获取结果: "+retStr);
        
        String jsonStr = retStr.replace("callback(", "").replace(")", "");
        JSONObject rejo = new JSONObject(jsonStr);
        String openid = rejo.getStr("openid");
        //String unionid = rejo.getStr("unionid");
        
        if (StringUtil.isEmpty(openid)) {
            throw new BusinessException("获取qq openid 出错");
        }
        
        return openid;
        
    }
	
	/***
	 * 获取用户基本信息
	 * @param token
	 * @param openid
	 * @param appid
	 * @return
	 * @Description 
	 * @date 2017年10月9日  上午11:12:38
	 * @author wy
	 * 2017
	 * @param unionid 
	 * @return String
	 */
	public static JSONObject getUserInfo(String token,String openid){
		String url = get_user_info_url;
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("access_token", token);
		paramMap.put("oauth_consumer_key", appid);
		paramMap.put("openid", openid);
		String retStr = HttpUtil.get(url, paramMap);
		System.out.println("qq get_user_info :获取结果: "+retStr);
		
		JSONObject rejo = new JSONObject(retStr);
		return rejo;
	}
}
