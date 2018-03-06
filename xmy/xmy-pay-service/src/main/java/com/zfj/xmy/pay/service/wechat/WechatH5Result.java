package com.zfj.xmy.pay.service.wechat;

/**
 * 微信H5支付返回数据
 * @author wy
 *
 */
public class WechatH5Result {

	private String prepay_id = "";
	private String mweb_url = "";
	
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getMweb_url() {
		return mweb_url;
	}
	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}
	
}
