package com.zfj.xmy.wap.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.wap.web.common.SessionInfo;

public class AuthenticationInterceptor implements HandlerInterceptor{

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
	
	public String getIpAddress(HttpServletRequest req, HttpServletResponse res) {
		
		if(null == req.getHeader("x-forwarded-for")) {
			return req.getRemoteAddr();
		}
		return req.getHeader("x-forwarded-for");
		
	}
	

	@Override
	/**
	 * 方法调用前
	 * 个人中心uri /gotoPage/mine/center
	 * 
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) arg0.getSession().getAttribute("wap_session");
		String fromApp = arg0.getParameter("from");
		if(StringUtil.isEmpty(fromApp) && ("ios".equals(fromApp) || "android".equals(fromApp))){
			arg0.getRequestDispatcher("/home/download").forward(arg0, arg1);
			return false;
		}
		if(null != sessionInfo && null != sessionInfo.getUser()) {
			return true;
		} else {
			if(judage(arg0,arg1)){
				throw new BusinessException(BusinessException.EX_CODE_NONE_AUTHORIZE, "未登录或登录过期");
			}else{
				String uri = arg0.getServerName() + "://"+ arg0.getServerPort() + arg0.getRequestURI();
				uri = arg0.getQueryString() == null ? uri : uri + "?" + arg0.getQueryString();
				/*for(int i = 2; i >= 1; --i) {
					uri = uri.substring(uri.indexOf("/") + 1);
				}*/
				System.out.println("请求uri:---->/" + uri);
//				arg0.setAttribute("requestUri", arg0.getRequestURL().toString());
				arg0.getRequestDispatcher("/index/login?requestUri=" + arg0.getRequestURL().toString()).forward(arg0, arg1);
			}
			
			return false;
		}
		
	}
	
	/**
	 * 判断是否ajax请求
	 * @author cj
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean judage(HttpServletRequest request, HttpServletResponse response){
		
		String acceptHeader = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(acceptHeader) ? true:false;
		
	}
	
}
