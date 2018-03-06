package com.zfj.xmy.pc.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Target({ElementType.TYPE,ElementType.METHOD}) //作用于类或者方法
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Lijie {
	
}
