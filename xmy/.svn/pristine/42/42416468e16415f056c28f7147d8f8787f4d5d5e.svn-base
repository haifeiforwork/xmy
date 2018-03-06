package com.zfj.xmy.order.service.pc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	/**
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge void
	 * @author lij
	 * @date 2017年8月25日 下午2:17:51
	 * 新建一个cookie
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		Cookie cookie;
		try {
			cookie = new Cookie(URLEncoder.encode(name, "utf-8"), value);
			cookie.setPath("/");
			if(maxAge>0) cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    try {
			if(cookieMap.containsKey(URLDecoder.decode(name,"utf-8"))){
			    Cookie cookie = (Cookie)cookieMap.get(URLDecoder.decode(name,"utf-8"));
			    return cookie;
			}else{
			    return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}   
	    return null;
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
}
