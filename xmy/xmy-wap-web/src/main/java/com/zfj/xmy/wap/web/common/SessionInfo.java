package com.zfj.xmy.wap.web.common;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;

public class SessionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录用户的id
	 */
	private Long userId ;
	/**
	 * 登录用户的名称
	 */
	private String userName ;
	
	//用户
	private User user;
	
	//用户信息
	private UserInfo userInfo;
	
	//用户地址
	private List<UserAddrees> userAddress;
	
	//邮箱验证码
	private String emailValidationCode;
	
	//手机验证码
	private String imgValidateCode;
	
	//邮箱
	private String email;
	
	public String getImgValidateCode() {
		return imgValidateCode;
	}

	public void setImgValidateCode(String imgValidateCode) {
		this.imgValidateCode = imgValidateCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailValidationCode() {
		return emailValidationCode;
	}

	public void setEmailValidationCode(String emailValidationCode) {
		this.emailValidationCode = emailValidationCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserAddrees> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddrees> userAddress) {
		this.userAddress = userAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 获取sessionInfo信息
	 * @return
	 */
	public static SessionInfo get(){
		HttpSession session = WebUtil.getSession() ;
		return null == session ? null : (SessionInfo)session.getAttribute(SystemConstant.XMYWAP.WAP_SESSION) ;
	}
}
