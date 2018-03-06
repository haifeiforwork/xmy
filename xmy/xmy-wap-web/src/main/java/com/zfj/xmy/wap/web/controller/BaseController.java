package com.zfj.xmy.wap.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zfj.base.log.Log4jUtils;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.wap.web.common.SessionInfo;
/**
 * 
 * @author dengq
 * @createDate 2017年8月1日
 * @desription 公共的控制层
 */

public abstract class BaseController {

	/**
	 *@author cj
	 *设置日期型数据绑定
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	protected final Logger logger = Log4jUtils.getLogger(this.getClass()) ;
	
	/**
	 * 设置session信息
	 * @param user
	 */
	protected void setSessionInfo(User user){
		HttpSession session = WebUtil.getSession() ;
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SystemConstant.PC_SESSION_INFO) ;
		if(null == sessionInfo){
			sessionInfo = new SessionInfo() ;
		}
		sessionInfo.setUserId(user.getId()) ;
		sessionInfo.setUserName(user.getName()) ;
		session.setAttribute(SystemConstant.PC_SESSION_INFO,sessionInfo) ;
	}
	
	protected void setWapSessionInfo(User user){
		HttpSession session = WebUtil.getSession() ;
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SystemConstant.XMYWAP.WAP_SESSION) ;
		if(null == sessionInfo){
			sessionInfo = new SessionInfo() ;
		}
		sessionInfo.setUser(user);
		sessionInfo.setUserName(user.getName());
		sessionInfo.setUserId(user.getId());
		session.setAttribute(SystemConstant.XMYWAP.WAP_SESSION,sessionInfo) ;
	}
	
	protected void clearSession(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
	protected void setWapSessionInfo(UserInfo userInfo) {
		HttpSession session = WebUtil.getSession() ;
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SystemConstant.XMYWAP.WAP_SESSION) ;
		if(null == sessionInfo){
			sessionInfo = new SessionInfo() ;
		}
		sessionInfo.setUserInfo(userInfo);
		session.setAttribute(SystemConstant.XMYWAP.WAP_SESSION, sessionInfo) ;
	}
	
	protected void setWapSessionInfo(List<UserAddrees> address) {
		HttpSession session = WebUtil.getSession() ;
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SystemConstant.XMYWAP.WAP_SESSION) ;
		if(null == sessionInfo){
			sessionInfo = new SessionInfo() ;
		}
		sessionInfo.setUserAddress(address);
		session.setAttribute(SystemConstant.XMYWAP.WAP_SESSION, sessionInfo) ;
	}
}
