package com.zfj.xmy.cms.web.common.ims.annotation;
import java.lang.annotation.*; 
/**
 * 自定义注解 拦截Service
 * @author linrx
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemServiceLog {
	  String description()  default "";
}
