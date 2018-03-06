package com.zfj.xmy.wap.web.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;

/**
 * wap端处理异常
 * @author cj
 * @createDate 2017年10月29日
 *
 */
@Component
public class WapException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		String acceptHeader = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(acceptHeader)){
			response.setCharacterEncoding("UTF-8") ; 
			boolean isBusiness = ex instanceof BusinessException ;
			int code = isBusiness ? ((BusinessException)ex).getCode() : HttpStatus.INTERNAL_SERVER_ERROR.value() ;
			String msg = "BUSINESS_EXCEPTION" ;
			if(code == 100) 
				msg = "SESSION_LOST" ;
			else if(code == 500)
				msg = "BUSINESS_EXCEPTION" ;
			else if(code == 999)
				msg = "SESSION_LOST_ACTIVITY" ;
			else
				msg = ex.getMessage() ;
			try {
				response.getWriter().print(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ex.printStackTrace();
		
		return new ModelAndView();
	}

}