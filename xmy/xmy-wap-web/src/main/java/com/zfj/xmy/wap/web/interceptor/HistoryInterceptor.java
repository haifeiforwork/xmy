package com.zfj.xmy.wap.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.wap.web.common.SessionInfo;

public class HistoryInterceptor implements HandlerInterceptor{

	@Override
	/**
	 * 试图渲染完成后
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	/**
	 * 方法调用执行完成后
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}
	

	@Override
	/**
	 * 方法调用前
	 * 
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String fromApp = arg0.getParameter("from");
		String fromWap = arg0.getParameter("fromWap");
		if(!StringUtil.isEmpty(fromApp) && StringUtil.isEmpty(fromWap) && ("ios".equals(fromApp) || "android".equals(fromApp))){
			arg0.getRequestDispatcher("/home/download?fromWap=fromWap").forward(arg0, arg1);;
			return false;
		}
		return true;
	}
	
}
