package com.zfj.xmy.util;

import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.mail.MailUtil;
import com.zfj.base.util.mail.model.JMail;

public class EmailUtil {

	private static String host = "smtp.163.com";
	
	private static String sendEmail = "xbncp365@163.com";
	
	private static String sendEmailPassword = "365xbncp";
	
	private static String sendName = "香满圆";
	
	/**
	 * 发送邮件
	 * @param toEmailAddress
	 * @param content
	 * @Description 
	 * @date 2017年9月7日  下午4:01:48
	 * @author wy
	 * 2017
	 * @return void
	 */
	public static void sendEmail(String toEmailAddress,String title , String content){
		JMail jMail = new JMail();
		jMail.setCharSet("utf-8");
		
		jMail.setHost(host);
		jMail.setSSL(true);
		jMail.setRecipientEmail(toEmailAddress);
		jMail.setSendEmail(sendEmail);
		jMail.setSendEmailPassword(sendEmailPassword);
		jMail.setSendName(sendName);
		jMail.setSmtpPort(465);
		
		jMail.setSendTitle(title);
		jMail.setContent(content);
		
		
		try {
			MailUtil.send(jMail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("发送失败");
		}
	}
}
