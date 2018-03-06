package com.zfj.xmy.common.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.util.MailLogger;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.util.mail.MailUtil;
import com.zfj.base.util.mail.model.JMail;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.persistence.dao.EmailLogMapper;
import com.zfj.xmy.common.persistence.pojo.EmailLog;
import com.zfj.xmy.common.service.EmailLogService;

@Service
public class EmailLogServiceImpl implements EmailLogService {

	@Autowired 
	private EmailLogMapper emailLogMapper;
	@Override
	public void addMailLog(EmailLog emailLog){
		
		String generateToken = UUIDUtil.generateToken();
		emailLog.setId(generateToken);
		emailLogMapper.insertSelective(emailLog);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void sendEmail(EmailLog emailLog){
		JMail jMail = new JMail();
		String code=RandomUtil.randomNumbers(6);
		String message="您绑定的香满圆验证码为:"+code;
		jMail.setCharSet("utf-8");
		jMail.setContent(message);
		jMail.setHost("smtp.mxhichina.com");
		jMail.setSSL(true);
		jMail.setRecipientEmail(emailLog.getEmail());
		jMail.setSendEmail("noreply@time-cost.com");
		jMail.setSendEmailPassword("Coolong9898");
		jMail.setSendName("xmy_admin");
		jMail.setSendTitle("香满圆邮箱绑定通知");
		jMail.setSmtpPort(465);
		MailUtil.send(jMail);
		EmailLog log=new EmailLog();
		log.setCode(code);
		log.setEmail(emailLog.getEmail());
		log.setCreateTimestamp(new Date());
		log.setStatus(0);
		log.setMessage(message);
		this.addMailLog(log);
	}
}
