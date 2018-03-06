package com.zfj.xmy.user.service.common;

public interface SmsLogService {

	/**
	 * 验证短信
	 * @param code
	 * @param mobilePhone
	 * @Description 
	 * @date 2017年8月8日  上午9:49:23
	 * @author wy
	 * 2017
	 * @return void
	 */
	void valid(String code, String mobilePhone);

	/**
	 * 限制短信发送频率
	 * @param mobilePhone
	 * @param min 限制分钟数
	 * @Description 
	 * @date 2017年8月8日  上午10:06:11
	 * @author wy
	 * 2017
	 * @return void
	 */
	void limitSmsCount(String mobilePhone, int min);

}
