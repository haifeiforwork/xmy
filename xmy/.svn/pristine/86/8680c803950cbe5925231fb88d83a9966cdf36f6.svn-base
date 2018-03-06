package com.zfj.xmy.user.persistence.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.zfj.xmy.common.persistence.dao.SmsLogMapper;
import com.zfj.xmy.user.persistence.pojo.dto.SmsLogDto;
/**
 * 
 * @Description 
 * @Author cj
 * @Date 2017年6月29日下午4:00:48
 */
public interface SmsLogExMapper extends SmsLogMapper{
	
	/**
	 * 验证手机验证码是否存在
	 * @param mobilePhone
	 * @param code
	 * @param time
	 * @return
	 */
	SmsLogDto checkCode(@Param("mobilePhone")String mobilePhone, @Param("time") Date time);
}
