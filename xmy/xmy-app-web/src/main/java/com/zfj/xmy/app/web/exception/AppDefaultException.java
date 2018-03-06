package com.zfj.xmy.app.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.bean.RespError;
import com.zfj.base.commons.mini.exception.DefaultException;
import com.zfj.base.util.common.StringUtil;
/**
 * 异常处理
 * @author dengq
 * @createDate 2017年7月26日
 * @desription
 */
@Component
public class AppDefaultException extends DefaultException implements HandlerExceptionResolver {

	//异常处理之前执行的方法，按实际需求来编写
	@Override
	public void prevHandler(HttpServletRequest request,HandlerMethod handlerMethod, Exception ex, RespError respError) {
		if(StringUtil.isEmpty(respError.getErrorMsg())){
			respError.setErrorMsg("业务系统异常") ;
		}
		System.out.println("//-----------------执行前的异常信息---------start--------") ;
		ex.printStackTrace() ;
		System.out.println("//-----------------执行前的异常信息---------end----------") ;
	}
	//异常处理之后执行的方法，按实际需求来编写
	@Override
	public void afterHander(HttpServletRequest request, HandlerMethod handlerMethod, Exception ex) {
		System.out.println("//-----------------执行后的异常信息---------start--------") ;
		ex.printStackTrace() ;
		System.out.println("//-----------------执行后的异常信息---------end----------") ;
	}
	//异常处理时返回的公共视图方法，按实际需求来编写
	@Override
	public ModelAndView viewHandler(HttpServletRequest request, Exception ex) {
		return null;
	}


}
