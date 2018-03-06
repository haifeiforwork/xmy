package com.zfj.xmy.common.persistence.pojo.app;

/**
 * h5 页面输出 Dto
 * @author wy
 *
 */
public class HtmlOut {


	public HtmlOut() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HtmlOut(String url) {
		super();
		this.url = url;
	}
	/**
	 * 页面地址
	 */
	private String url;
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
