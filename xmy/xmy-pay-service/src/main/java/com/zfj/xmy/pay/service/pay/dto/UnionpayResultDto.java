package com.zfj.xmy.pay.service.pay.dto;

/**
 * 银联-手机控件支付 - 接口返回参数
 * @author wy
 *
 */
public class UnionpayResultDto {

	/**
	 * 返回码
	 */
	private String respCode;
	/**
	 * 返回信息
	 */
	private String respMsg;
	/**
	 * tn 号  
	 */
	private String tn;
	/**
	 * 构造方法
	 * @param respCode
	 * @param respMsg
	 * @param tn
	 */
	public UnionpayResultDto(String respCode, String respMsg, String tn) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.tn = tn;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	
	
}
