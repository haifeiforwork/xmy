package com.zfj.xmy.common;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;

import com.zfj.base.log.Log4jUtils;
import com.zfj.base.log.LogExp;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.base.util.common.StringUtil;

/**
 * 一些公共使用的方法
 * @author dengq
 * @createDate 2017年8月3日
 * @desription
 */
public class CommonUtil {

	private static Logger logger = Log4jUtils.getLogger(CommonUtil.class) ;
	
	/**
	 * 空验证
	 * @author dengq
	 * @param value
	 * @param msg
	 */
	public static void validEmpty(Object value,String msg){
		if(null == value){
			LogExp.error(logger,msg);
		}
		if(value instanceof String){
			if(StringUtil.isEmpty(value)){
				LogExp.error(logger,msg) ;
			}
		}else if(value instanceof Collection){
			if(CollectionExtUtils.isEmpty((Collection<?>)value)){
				LogExp.error(logger,msg) ;
			}
		}else if(value instanceof Map){
			if(CollectionExtUtils.isEmpty((Map<?,?>)value)){
				LogExp.error(logger,msg) ;
			}
		}
	}
	/**
	 * 是否email
	 * @author dengq
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str){
		return StringUtil.isMatch("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",str) ;
	}
	/**
	 * 是否手机号码
	 * @author dengq
	 * @param str
	 * @return
	 */
	public static boolean checkMobilePhone(String str){
		return StringUtil.isMatch("^1[3|4|5|7|8]\\d{9}$",str) ;
	}
	
}
