package com.zfj.xmy.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;

/***
 * 百度地图类
 * @author wy
 *
 */
public class BaiduUtil {

	/**
	 * 基本地址
	 */
	private static String baidubaseurl = "http://api.map.baidu.com/geocoder/v2/"; 
	
	/**
	 * 百度 ak (密钥)
	 */
	//private static String baiduak = BaseProp.BASE.getValue("BAIDU_AK");
	private static String baiduak = "dIZWEo324hWNcncdZwci4eMusQU4enlx";
	
	/**
	 * 百度 sk 
	 */
	//private static String baidusk = BaseProp.BASE.getValue("BAIDU_SK");
	private static String baidusk = "LsEnNeVPvMOaaMLEguziDnvWzORHgfla";
	
	/***
	 * 逆地理编码服务 
	 * 根据经纬度获取地址
	 * @param paramsMap
	 * @return
	 * @Description 
	 * @date 2017年10月12日  下午4:29:10
	 * @author wy
	 * 2017
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public static String getAdressByGeo(String lat,String lng) throws UnsupportedEncodingException{
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("location", lat+","+lng);
		paramMap.put("output", "json");
		paramMap.put("ak", baiduak);
		paramMap.put("sn", getSN(paramMap));
		String resultStr = HttpUtil.get(baidubaseurl, paramMap);
		return resultStr;
	}
	
	
	
	
	
	//java版计算signature签名
	
	public static void main(String[] args) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {

		// 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
//		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
//		paramsMap.put("address", "百度大厦");
//		paramsMap.put("output", "json");
//		paramsMap.put("ak", baiduak);
//		System.out.println(getSN(paramsMap));
		String res = getAdressByGeo("29.6389200238317", "106.553510739202");
		System.out.println(res);
	}

	/***
	 * 生成sn
	 * @return
	 * @Description 
	 * @date 2017年10月12日  下午4:10:25
	 * @author wy
	 * 2017
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public static String getSN(Map<?, ?> paramsMap) throws UnsupportedEncodingException {
		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = BaiduUtil.toQueryString(paramsMap);
		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/geocoder/v2/?" + paramsStr + baidusk);
		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
		// 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
		return BaiduUtil.MD5(tempStr);
	}
	
	// 对Map内所有value作utf8编码，拼接返回结果
	public static String toQueryString(Map<?, ?> data)
			throws UnsupportedEncodingException {
		StringBuffer queryString = new StringBuffer();
		for (Entry<?, ?> pair : data.entrySet()) {
			queryString.append(pair.getKey() + "=");
			queryString.append(URLEncoder.encode((String) pair.getValue(),
					"UTF-8") + "&");
		}
		if (queryString.length() > 0) {
			queryString.deleteCharAt(queryString.length() - 1);
		}
		return queryString.toString();
	}

	// 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 微信- 获取微信扫码地址
	 * @param code
	 * @return
	 * @Description 
	 * @date 2017年9月30日  上午10:40:20
	 * @author wy
	 * 2017
	 * @return String
	 *//*
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
	
	*//**
	 * 微信- 根据 code 获取openid
	 * @param code
	 * @Description 
	 * @date 2017年9月29日  下午2:26:50
	 * @author wy
	 * 2017
	 * @return void
	 *//*
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
	}*/
}
