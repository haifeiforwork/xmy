package com.zfj.xmy.pay.service.pay.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 微支付配置字段
 * 
 * @author wy
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class WxPayConfigVo {

	private String appid ;
	private String mch_id ;
	private String appsecret ;
	private String partnerkey ;
	private String unified_pay_api ;
	
	/**
	 * 回调地址
	 */
	private String notify_url;
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getPartnerkey() {
		return partnerkey;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getUnified_pay_api() {
		return unified_pay_api;
	}
	public void setUnified_pay_api(String unified_pay_api) {
		this.unified_pay_api = unified_pay_api;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	
}
