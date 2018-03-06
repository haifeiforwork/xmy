package com.zfj.xmy.app.web.common;

import com.zfj.base.util.web.WebUtil;
/**
 * app本地信息获取对象
 * @author dengq
 * @createDate 2017年8月2日
 * @desription 如果需要扩展本地的其他信息，请在此对象中扩展
 */
public class AppLocalInfo {
	
	public static final String LOCAL_USER_ID = "local_user_id" ;

	private AppLocalInfo(){}
	
	/**
	 * 获取当前的用户的id
	 * @return
	 */
	public static Long getUserId(){
		return (Long) WebUtil.getAttribute(LOCAL_USER_ID) ;
	}
	
}
