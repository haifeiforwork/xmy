package com.zfj.xmy.user.service.pc;

public interface PcSmsLogService {

	/**
	 * 发送短信验证码
	 * @author dengq
	 * @param telephone
	 */
	public void sendMessage(String mobilePhone) ;
	
	/**
	 * 验证短信验证码的正确性
	 * @author dengq
	 * @param code
	 * @param telephone
	 */
	public void valid(String code,String mobilePhone) ;
	/**
	 * 验证短信验证码的正确性
	 * @param code
	 * @param phone
	 * @return int 0:验证通过 1.验证码不对 2.验证码过期
	 * @author lij
	 * @date 2017年10月10日 下午5:52:12
	 */
	public int validCode(String code,String phone);
	
}
