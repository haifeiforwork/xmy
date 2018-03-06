package com.zfj.xmy.pay.service.pay.vo;

/**
 * 银联 配置
 * @author wy
 *
 */
public class UnionPayConfigVo {

	/**
	 * 商户号码
	 */
	private String mer_id;
	/**
	 * 回调地址
	 */
	private String notify_url;
	
	
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getMer_id() {
		return mer_id;
	}
	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}
	
	
}
