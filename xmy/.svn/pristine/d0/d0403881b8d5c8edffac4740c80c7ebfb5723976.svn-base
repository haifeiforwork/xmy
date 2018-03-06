package com.zfj.xmy.user.service.wap;

public interface WapSmsLogService {
	
	/**
	 * 发送手机验证码
	 * @param mobilePhone
	 */
	void sendMessage(String mobilePhone);
	
	/**
	 * 验证手机验证码是否正确
	 * @param phone
	 * @param code
	 */
	void checkCode(String phone, String code);

	void valid(String code, String mobilePhone);

	void limitSmsCount(String mobilePhone, int min);

}
