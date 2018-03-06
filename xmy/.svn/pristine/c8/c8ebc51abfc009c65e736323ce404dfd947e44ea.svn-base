package com.zfj.xmy.user.service.wap.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateField;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.commons.mini.constant.BaseConstant;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.log.LogExp;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.CommonUtil;
import com.zfj.xmy.common.persistence.dao.SmsLogMapper;
import com.zfj.xmy.common.persistence.pojo.SmsLog;
import com.zfj.xmy.user.persistence.dao.SmsLogExMapper;
import com.zfj.xmy.user.persistence.pojo.dto.SmsLogDto;
import com.zfj.xmy.user.service.wap.WapSmsLogService;
import com.zfj.xmy.util.SendSMSUtil;

@Service
public class WapSmsLogServiceImpl extends BaseService implements WapSmsLogService{
	
	@Autowired
	private SmsLogMapper smsLogMapper ;
	@Autowired
	private SmsLogExMapper smsLogExMapper ;
	
	@Override
	public void valid(String code, String mobilePhone) {
		//根据条件查询验证码信息
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.equalTo("mobile_phone",mobilePhone) ;
		criteria.equalTo("code",code) ;
		List<SmsLog> smsList = smsLogMapper.selectByExample(parameter) ;
		if (CollectionUtils.isEmpty(smsList)) {
			throw  new BusinessException("验证码不正确！");
		}
		SmsLog smsLog = smsList.get(0) ;
		//校验短信时间在10分钟内有效
		if(new Date().getTime() - smsLog.getCreateTimestamp().getTime() > 10 * 60 * 1000){
			throw  new BusinessException("验证码已经过期，请重新获取");
		}
	}
	
	@Override
	public void limitSmsCount(String mobilePhone,int min) {
		// 根据条件查询验证码信息
		CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.equalTo("mobile_phone", mobilePhone);
		parameter.setOrderByClause(" create_timestamp desc ");
		List<SmsLog> smsList = smsLogMapper.selectByExample(parameter);
		if (!CollectionUtils.isEmpty(smsList)) {
			SmsLog smsLog = smsList.get(0);
			// 校验短信时间在N分钟内只能获取一次
			if (new Date().getTime() - smsLog.getCreateTimestamp().getTime() < min * 60 * 1000) {
				throw new BusinessException("验证码每"+min+"分钟只能获取一次");
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void sendMessage(final String mobilePhone) {
		
		CommonUtil.validEmpty(mobilePhone,"手机号码不能为空");
		if(!CommonUtil.checkMobilePhone(mobilePhone)){
			LogExp.error(logger,"手机号码格式不正确") ;
			throw new BusinessException("手机号码格式不正确");
		}
		String code = generateRandom(6) ;
		try {
			SendSMSUtil.sendSMS(mobilePhone,code) ;
		} catch (UnsupportedEncodingException e) {
			LogExp.error(logger,"发送内容出错，发送失败") ;
			throw new BusinessException("发送失败");
		}
		//保存发送信息
		SmsLog log = new SmsLog() ;
		log.setId(StringUtil.generateKey()) ;
		log.setCode(code) ;
		log.setStatus(BaseConstant.STATUS_ENABLE) ;
		log.setMobilePhone(mobilePhone) ;
		log.setCreateTimestamp(new Date()) ;
		log.setMessage(SendSMSUtil.sign+" 验证码"+code+"，感谢您的支持！") ;
		smsLogMapper.insertSelective(log) ;
		
	}
	
	/**
	 * 随机获取6位数字
	 * @param num
	 * @return
	 */
	private String generateRandom(int num) {
		if (0 > num)
			return "";
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < num; i++) {
			buffer.append(random.nextInt(9));
		}
		return buffer.toString();
	}
	
	@Override
	public void checkCode(String phone, String code) {
		
		Date time = DateUtil.offset(new Date(), DateField.MINUTE, -5);
		SmsLogDto smsLog = smsLogExMapper.checkCode(phone, time);
		if(StringUtil.isEmpty(code) || smsLog == null || !code.equals(smsLog.getCode())){
			LogExp.error(logger,"手机验证码输入错误") ;
		}
		
	}
}
