package com.zfj.xmy.user.persistence.pojo.dto.app;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户登录传出数据
 * @author Administrator
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class UserLoginOutDto {

	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户手机号
	 */
	private String mobilePhone;
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	/**
	 * 最近一次登录时间
	 */
	private Date lastLoginTime;
	
	
	private String token;
	private Date tokenExpire;
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getTokenExpire() {
		return tokenExpire;
	}
	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
	}
    
    
}
