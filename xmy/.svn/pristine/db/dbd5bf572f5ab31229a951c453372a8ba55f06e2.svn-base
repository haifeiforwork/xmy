package com.zfj.xmy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;


/**
 * 邮寄发送类
 * 
 * @author wangsong
 * @date Aug 30, 2011
 */
public class Jmail {
	private String host;// 主机地址
	private String transPort;// 邮寄协议
	private String charSet;// 编码格式
	private String sendeMail;// 发件人地址
	private String sendTitle;// 发送主题
	private String sendName;// 发件人
	private String content;// 发送内容

	
	/**
	 * 发送邮件
	 * 
	 * @param putName
	 *            收件人地址
	 */
	public void sendMail(String putName) {

		try {

			File f = new File(Jmail.class.getResource("emailset.properties")
					.toURI());

			InputStream in = new FileInputStream(f);
			Properties pro = new Properties();
			pro.load(in);
			String pwd="Coolong9898";
			this.host = pro.getProperty("mail.host");
			this.transPort = pro.getProperty("mail.transport.protocol");
			this.charSet = pro.getProperty("mail.charset");
			this.sendeMail = pro.getProperty("mail.sendemail");

			
			if(this.sendTitle.equals("工时日志意见反馈回复")){
				this.sendeMail="service@time-cost.com";
				pwd="time-cost0609";
			}
			
			// 中文编码
			// this.sendName=new
			// String(pro.getProperty("mail.sendname").getBytes("ISO8859-1"),"utf-8");
			// this.sendTitle=new
			// String(pro.getProperty("mail.sendtitle").getBytes("ISO8859-1"),"utf-8");

			// 正则验证邮件格式
			
			//if (Pattern.matches(GlobalConstant.REG_EMAIL, putName)) {

				
				Properties p = new Properties();
				p.put("mail.smtp.host", this.host);
				p.put("mail.smtp.port", "25");
				p.put("mail.smtp.auth", "true");
				//Coolong9898
				MyAuthenticator myauth = new MyAuthenticator(this.sendeMail,
						pwd);
				// 根据邮件会话属性和密码验证器构造一个发送邮件的session
				Session sendMailSession = Session.getInstance(p, myauth);
			
				try {
					// 根据session创建一个邮件消息
					Message mailMessage = new MimeMessage(sendMailSession);
					// 创建邮件发送者地址
					String nick = javax.mail.internet.MimeUtility.encodeText("工时日志"); 
					Address from = new InternetAddress(nick+"<"+this.sendeMail+">");
					// 设置邮件消息的发送者
					mailMessage.setFrom(from);
					// 创建邮件的接收者地址，并设置到邮件消息中
					Address to = new InternetAddress(putName);
					// Message.RecipientType.TO属性表示接收者的类型为TO
					mailMessage.setRecipient(Message.RecipientType.TO, to);
					// 设置邮件消息的主题
					mailMessage.setSubject(this.getSendTitle());
					// 设置邮件消息发送的时间
					mailMessage.setSentDate(new Date());
					// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
					Multipart mainPart = new MimeMultipart();
					// 创建一个包含HTML内容的MimeBodyPart
					BodyPart html = new MimeBodyPart();
					// 设置HTML内容
					html.setContent(this.getContent(), "text/html; charset=utf-8");
					mainPart.addBodyPart(html);
					// 将MiniMultipart对象设置为邮件内容
					mailMessage.setContent(mainPart);
					// 发送邮件
					Transport.send(mailMessage);
					
					

				} catch (MessagingException ex) {
					ex.printStackTrace();
				}

				
				
			//}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMailByFile(String putName) {

		try {

			File f = new File(UtilTool.class.getResource("emailset.properties")
					.toURI());

			InputStream in = new FileInputStream(f);
			Properties pro = new Properties();
			pro.load(in);

			this.host = pro.getProperty("mail.host");
			this.transPort = pro.getProperty("mail.transport.protocol");
			this.charSet = pro.getProperty("mail.charset");
			this.sendeMail = pro.getProperty("mail.sendemail");

			// 中文编码

			// 正则验证邮件格式
			if (Pattern.matches("^\\S+@\\S+\\.\\S+$", putName)) {
				EmailAttachment attachment = new EmailAttachment();

				attachment.setPath("c://青年律师创业大赛全国巡回赛广东赛区方案.doc");
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setName(MimeUtility
						.encodeText("青年律师创业大赛全国巡回赛广东赛区方案.doc"));

				// 创建hmtl格式邮件
				HtmlEmail email = new HtmlEmail();
				// 这里是SMTP发送服务器的名字
				email.setHostName(this.host);
				// 字符编码集的设置
				email.setCharset(this.charSet);
				// 收件人的邮箱
				email.addTo(putName);
				// 发送人的邮箱
				email.setFrom(this.getSendeMail(), this.getSendName());
				// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
				email.setAuthentication(this.sendeMail, "Wbonline1");
				email.setSubject(this.getSendTitle());
				// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
				email.setMsg(this.getContent());

				// 添加附件
				email.attach(attachment);

				// 发送
				email.send();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// 邮箱地址不为空时，发送邮件
		// Jmail mail=new Jmail();
		// mail.setSendName("纸飞机测试邮件");
		// //设置发送内容
		// String
		// strTxt="<TABLE cellSpacing=0 cellPadding=1 width=500><TBODY><TR>" +
		// "<TD style='BORDER-RIGHT: #0000cc 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #0000cc 1px solid; PADDING-LEFT: 4px; FONT-WEIGHT: bold; FONT-SIZE: 24px; PADDING-BOTTOM: 4px; BORDER-LEFT: #0000cc 1px solid; COLOR: #fff; LINE-HEIGHT: 150%; "
		// +
		// "PADDING-TOP: 4px; BORDER-BOTTOM: #0000cc 0px solid; FONT-FAMILY: 'lucida grande',tahoma,'bitstream vera sans',helvetica,sans-serif' align=left bgColor=#0033CC>&nbsp; 誉博律师呼叫中心</TD>"
		// +
		// "</TR><TR><TD bgColor=#0000cc><TABLE cellSpacing=0 cellPadding=20 width='100%' bgColor=#ffffff><TBODY><TR><TD style='FONT-SIZE: 14px; COLOR: #000; LINE-HEIGHT: 150%; FONT-FAMILY: 'lucida grande',tahoma,'bitstream vera sans',helvetica,sans-serif'>"
		// +
		// "<strong>亲爱的朋友： </strong><BLOCKQUOTE><BR><p>您已经一个月未登录誉博律师呼叫中心官网。您的问题解决了吗？是否还需要我们的帮助。若您对我们的服务满意，敬请转告您的朋友。誉博律师呼叫中心随时为您提供专业的法律咨询服务，您可以拨打95089热线，或登录 <a href='http://www.95089.com'>www.95089.com</a> "
		// +
		// "免费向我们提出咨询。</p>谢谢您的支持。<BR><BR><a href='http://weibo.com/lawercallcenter' target='_blank'>誉博新浪官方微薄</a> <a href='http://t.qq.com/lawyercallcenter' target='_blank'>誉博腾讯官方微薄</a></BLOCKQUOTE><BR><BR>誉博律师呼叫中心<BR><A href='http://www.95089.com/' target='_blank'>http://www.95089.com/</A> <BR>"+"<BR><BR>此邮件为系统自动发出的邮件，请勿直接回复。"
		// +
		// " </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
		// mail.setContent(strTxt);
		// mail.setSendTitle("您已经一个月未登录誉博律师呼叫中心官网了！");
		//
		// //邮箱地址不能为空
		// mail.sendMail("284161972@qq.com");

//		Jmail m = new Jmail();
//		m.sendHtml();

	}

	public void sendHtml() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.mxhichina.com");
		p.put("mail.smtp.port", "25");
		p.put("mail.smtp.auth", "true");

		MyAuthenticator myauth = new MyAuthenticator("noreply@time-cost.com",
				"Coolong9898");
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(p, myauth);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress("noreply@time-cost.com");
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress("noreply@time-cost.com");
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject("测试发送企业邮箱");
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			String strTxt = "<TABLE cellSpacing=0 cellPadding=1 width=500><TBODY><TR>"
					+ "<TD style='BORDER-RIGHT: #0000cc 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #0000cc 1px solid; PADDING-LEFT: 4px; FONT-WEIGHT: bold; FONT-SIZE: 24px; PADDING-BOTTOM: 4px; BORDER-LEFT: #0000cc 1px solid; COLOR: #fff; LINE-HEIGHT: 150%; "
					+ "PADDING-TOP: 4px; BORDER-BOTTOM: #0000cc 0px solid; FONT-FAMILY: 'lucida grande',tahoma,'bitstream vera sans',helvetica,sans-serif' align=left bgColor=#0033CC>&nbsp; 誉博律师呼叫中心</TD>"
					+ "</TR><TR><TD bgColor=#0000cc><TABLE cellSpacing=0 cellPadding=20 width='100%' bgColor=#ffffff><TBODY><TR><TD style='FONT-SIZE: 14px; COLOR: #000; LINE-HEIGHT: 150%; FONT-FAMILY: 'lucida grande',tahoma,'bitstream vera sans',helvetica,sans-serif'>"
					+ "<strong>亲爱的朋友： </strong><BLOCKQUOTE><BR><p>您已经一个月未登录誉博律师呼叫中心官网。您的问题解决了吗？是否还需要我们的帮助。若您对我们的服务满意，敬请转告您的朋友。誉博律师呼叫中心随时为您提供专业的法律咨询服务，您可以拨打95089热线，或登录 <a href='http://www.95089.com'>www.95089.com</a> "
					+ "免费向我们提出咨询。</p>谢谢您的支持。<BR><BR><a href='http://weibo.com/lawercallcenter' target='_blank'>誉博新浪官方微薄</a> <a href='http://t.qq.com/lawyercallcenter' target='_blank'>誉博腾讯官方微薄</a></BLOCKQUOTE><BR><BR>誉博律师呼叫中心<BR><A href='http://www.95089.com/' target='_blank'>http://www.95089.com/</A> <BR>"
					+ "<BR><BR>此邮件为系统自动发出的邮件，请勿直接回复。"
					+ " </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
			html.setContent(strTxt, "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTransPort() {
		return transPort;
	}

	public void setTransPort(String transPort) {
		this.transPort = transPort;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getSendeMail() {
		return sendeMail;
	}

	public void setSendeMail(String sendeMail) {
		this.sendeMail = sendeMail;
	}

	public String getSendTitle() {
		return sendTitle;
	}

	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
