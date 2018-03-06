package com.zfj.xmy.app.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.interceptor.AppMobileInterceptor;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.redis.TokenManager;

/**
 * 移动端app的权限拦截
 * @author dengq
 * @createDate 2017年7月24日
 * @desription
 */
public class AppAuthorInterceptor extends AppMobileInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenManager tokenManager ;
	
	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		this.process(request, response, object) ;
		return true ;
	}

	///验证请求的数据类型
	@Override
	public String validContentType(HttpServletRequest request) {
		String contentType = request.getHeader("content-type");
		if (contentType.indexOf(";")>0){
			contentType = contentType.substring(0, contentType.lastIndexOf(";"));
		}
		if (StringUtil.isEmpty(contentType)
				|| ((!SystemConstant.REQ_CONTENT_TYPE_JSON_CHARSET
						.equals(contentType.toLowerCase())) && !SystemConstant.REQ_CONTENT_TYPE_JSON
						.equals(contentType.toLowerCase())) && !SystemConstant.REQ_CONTENT_TYPE_MULTIPART
						.equals(contentType.toLowerCase())) {
			throw new BusinessException("请求类型不正确");
			
		}
		return contentType ;
	}
	//验证请求的数据权限
	@Override
	public void validSecurity(HttpServletRequest request) {
		String token = request.getHeader("token") ;
		//这里作数据权限的控制，在redis中读取
		String userId = null  ;
		if (!StringUtil.isEmpty(token)) {
			userId = tokenManager.get(token) ;
		}
		if(StringUtil.isEmpty(token) || StringUtil.isEmpty(userId)){
			throw new BusinessException(BusinessException.EX_CODE_NONE_AUTHORIZE,"请先登录") ;
		}
		request.setAttribute(AppLocalInfo.LOCAL_USER_ID,Long.parseLong(userId)) ;
	}
	
}
