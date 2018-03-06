package com.zfj.xmy.user.service.pc.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.commons.mini.constant.BaseConstant;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.log.LogExp;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.CommonUtil;
import com.zfj.xmy.common.persistence.dao.SmsLogMapper;
import com.zfj.xmy.common.persistence.pojo.SmsLog;
import com.zfj.xmy.user.service.pc.PcSmsLogService;
import com.zfj.xmy.util.SendSMSUtil;
@Service
public class PcSmsLogServiceImpl extends BaseService implements PcSmsLogService {

	@Autowired
	private SmsLogMapper smsLogMapper ;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void sendMessage(final String mobilePhone) {
		CommonUtil.validEmpty(mobilePhone,"手机号码不能为空");
		if(!CommonUtil.checkMobilePhone(mobilePhone)){
			LogExp.error(logger,"手机号码格式不正确") ;
		}
		String code = generateRandom(6) ;
		try {
			SendSMSUtil.sendSMS(mobilePhone,code) ;
		} catch (UnsupportedEncodingException e) {
			LogExp.error(logger,"发送内容出错，发送失败") ;
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
		
//		AliSMS.send(new SMSend() {
//			@Override
//			public void invoke(SMSData smsData,AlibabaAliqinFcSmsNumSendResponse response) {
//				//保存发送信息
//				SmsLog log = new SmsLog() ;
//				log.setId(StringUtil.generateKey()) ;
//				log.setCode(smsData.getCode()) ;
//				log.setStatus(response.isSuccess() ? BaseConstant.STATUS_ENABLE : BaseConstant.STATUS_DISABLE) ;
//				log.setMobilePhone(smsData.getMobilePhone()) ;
//				log.setCreateTimestamp(new Date()) ;
//				log.setMessage("【"+BaseProp.BASE.getValue(AliSMS.SIGN_NAME)+"】 验证码"+smsData.getCode()+"，感谢您的支持！") ;
//				smsLogMapper.insertSelective(log) ;
//			}
//			
//			@Override
//			public void getTemplate(List<SMSData> smsDataList) {
//				//1、校验手机号码的发送时间间隔，必须超过1分钟
//				CriteriaParameter parameter = new CriteriaParameter() ;
//				Criteria criteria = parameter.createCriteria() ;
//				criteria.equalTo("mobile_phone",mobilePhone) ;
//				parameter.setOrderByClause("create_timestamp desc") ;
//				List<SmsLog> smsList = smsLogMapper.selectByExample(parameter) ;
//				if(!CollectionExtUtils.isEmpty(smsList) && (new Date().getTime() - smsList.get(0).getCreateTimestamp().getTime() < 1 * 60 * 1000)){
//					LogExp.error(logger,"短信发送时间间隔必须大于1分钟") ;
//				}
//				
//				SMSData smsData = new SMSData() ;
//				smsData.setMobilePhone(mobilePhone) ;
//				smsData.setSmsParam("{\"code\":\":code\",\"product\":\"纸飞机科技\"}") ;
//				smsData.setProductName("纸飞机科技") ;
//				smsDataList.add(smsData) ;
//			}
//		}) ;
	}
	
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
	public void valid(String code, String mobilePhone) {
		//根据条件查询验证码信息
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.equalTo("mobile_phone",mobilePhone) ;
		criteria.equalTo("code",code) ;
		List<SmsLog> smsList = smsLogMapper.selectByExample(parameter) ;
		CommonUtil.validEmpty(smsList,"验证码不正确！");
		SmsLog smsLog = smsList.get(0) ;
		//校验短信时间在x分钟内有效
		if(new Date().getTime() - smsLog.getCreateTimestamp().getTime() > Integer.parseInt(BaseProp.BASE.getValue("code_valid")) * 60 * 1000){
			LogExp.error(logger,"验证码已经过期，请重新获取") ;
		}
	}

	@Override
	public int validCode(String code, String phone) {
		int i=0;
		//根据条件查询验证码信息
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.equalTo("mobile_phone",phone) ;
		criteria.equalTo("code",code) ;
		List<SmsLog> smsList = smsLogMapper.selectByExample(parameter) ;
		if(ObjectUtils.isEmpty(smsList)){
			i=1;
		}else{
			SmsLog smsLog = smsList.get(0) ;
			//校验短信时间在x分钟内有效
			if(new Date().getTime() - smsLog.getCreateTimestamp().getTime() > Integer.parseInt(BaseProp.BASE.getValue("code_valid")) * 60 * 1000){
				//LogExp.error(logger,"验证码已经过期，请重新获取") ;
				i=2;
			}
		}
		return i;
	}
	
	

}
