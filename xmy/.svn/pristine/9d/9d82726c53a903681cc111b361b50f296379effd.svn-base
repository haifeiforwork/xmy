package com.zfj.xmy.user.service.common.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.persistence.dao.SmsLogMapper;
import com.zfj.xmy.common.persistence.pojo.SmsLog;
import com.zfj.xmy.user.service.common.SmsLogService;

@Service
public class SmsLogServiceImpl implements SmsLogService {

	@Autowired
	private SmsLogMapper smsLogMapper;
	
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
}
