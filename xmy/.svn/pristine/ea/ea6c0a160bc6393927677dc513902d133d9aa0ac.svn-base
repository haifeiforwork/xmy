package com.zfj.xmy.pc.web.common;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.SystemConstant;

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
	
	/**
	 * 登录用户的密码
	 */
	private String password ;
	
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
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取sessionInfo信息
	 * @return
	 */
	public static SessionInfo get(){
		HttpSession session = WebUtil.getSession() ;
		return null == session ? null : (SessionInfo)session.getAttribute(SystemConstant.PC_SESSION_INFO) ;
	}
}
