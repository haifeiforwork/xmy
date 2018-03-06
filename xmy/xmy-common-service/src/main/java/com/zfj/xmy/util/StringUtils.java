package com.zfj.xmy.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;

/**
 * String处理工具类
 * @Description 
 * @Author liuw
 * @Date 2017年8月15日下午3:02:04
 */
public class StringUtils {

	/**
	 * 默认以逗号分割字符串
	 * @Description 
	 * @param original 需要分割的字符串<br>
	 * eg.		original="12,20,"
	 * @return {@code List<Object>} 返回一个分割后的list数组
	 * @Author liuw
	 * @Date 2017年8月15日下午3:03:59
	 */
	public static List<Object> exchangeSplit(String original){
		if(original==null||original.equals("")){
			return null;
		}
		return exchangeSplit(original, ",");
	}
	/**
	 * 
	 * @Description 
	 * @param original 需要分割的字符串
	 * eg.		original="12,20,"
	 * @param regex 以什么进行分割，可以是正则表达式
	 * @return
	 * @Author liuw
	 * @Date 2017年8月15日下午3:09:47
	 */
	public static List<Object> exchangeSplit(String original,String regex){
		Object[] split = original.split(regex);
		List<Object> asList = Arrays.asList(split);
		return asList;
	}
	
	/**
	 * 判断字符串是否包含中文
	 * @param str
	 * @return boolean
	 * @author lij
	 * @date 2017年11月23日 上午11:12:14
	 */
	public static boolean isContainChinese(String str){
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			return true;
		}
		return false;
	}
}
