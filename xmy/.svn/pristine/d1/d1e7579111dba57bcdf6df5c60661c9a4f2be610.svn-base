package com.zfj.xmy.wap.web.common;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.zfj.base.util.common.StringUtil;

/**
 * @RequestParamsAndAttribute注解处理器
 * @author cj
 * @createDate 2017年11月3日
 *
 */
@Component
public class RequestAttributeArgumentResolver implements WebArgumentResolver {
	 
    public Object resolveArgument(MethodParameter param,NativeWebRequest request) throws Exception {
 
        Annotation[][] paramAnns = param.getMethod().getParameterAnnotations();
        for (Annotation[] paramAnn : paramAnns) {
        	for(Annotation anno : paramAnn){
        		if (RequestParamsAndAttribute.class.isInstance(anno)) {
        			RequestParamsAndAttribute reqAttr = (RequestParamsAndAttribute) anno;
                    HttpServletRequest httprequest = request.getNativeRequest(HttpServletRequest.class);
                    String value = StringUtil.isEmpty(reqAttr.value()) ? param.getParameterName() : reqAttr.value();
                    if(!StringUtil.isEmpty(httprequest.getParameter(value)))
                    	return httprequest.getParameter(value);
                    else 
                    	return httprequest.getAttribute(value);
                }
        	}
        }
        
		return UNRESOLVED;
    }
}
