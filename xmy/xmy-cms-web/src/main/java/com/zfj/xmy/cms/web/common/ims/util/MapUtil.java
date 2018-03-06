package com.zfj.xmy.cms.web.common.ims.util;

import java.util.Map;


public class MapUtil {

	/**
	 * 请求参数转字符串
	 * @param paramMap
	 * @return
	 * @Description 
	 * @date 2018年1月25日  下午5:58:26
	 * @author wy
	 * 2018
	 * @return String
	 */
	public static String  MapToParams(Map<String, String[]> paramMap) {
		if (paramMap == null){
			return "";
		}
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
			params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
		}
		return params.toString();
	}
}
