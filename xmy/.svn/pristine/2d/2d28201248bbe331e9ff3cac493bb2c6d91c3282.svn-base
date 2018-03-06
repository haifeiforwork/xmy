package com.zfj.xmy.wap.web.common;

import java.lang.reflect.Field;

import org.slf4j.Logger;


public class MyUtil {
	
	public static void displayFields(Object o) {
		
		try {
			Class<? extends Object> cls = o.getClass();
			Field[] fields = cls.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field f : fields) {
				String fieldName = f.getName();
				Object value = f.get(o);
				System.out.println("属性名: " + fieldName + " 值: " + value);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void displayFields(Logger logger, Object o) {
		
		try {
			Class<? extends Object> cls = o.getClass();
			Field[] fields = cls.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field f : fields) {
				String fieldName = f.getName();
				Object value = f.get(o);
				logger.info("属性名: " + fieldName + " 值: " + value);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
