package com.zfj.xmy.util;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 得到 ApplicationContext 工具类
 * Created by DLee on 2016-11-26.
 */
public class SpringContextUtil {

  private SpringContextUtil() {
  }

  private static final List<ApplicationStartedEvent> startedEvents =
      Collections.synchronizedList(new ArrayList<ApplicationStartedEvent>());

  private static boolean initializationed;

  private static ApplicationContext applicationContext;

  public static void addStartedEvents(ApplicationStartedEvent event) {
    startedEvents.add(event);
  }

  public static void setApplicationContext(ApplicationContext context) {
    applicationContext = context;
    if (applicationContext != null && !initializationed) {
      initializationed = true;
      for (ApplicationStartedEvent event : startedEvents) {
        try {
          event.handler(applicationContext);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      startedEvents.clear();
    }
  }

  public static Object getBean(String beanId) {
    return applicationContext.getBean(beanId);
  }

  public static <T> T getBean(String beanId, Class<T> clazz) {
    return applicationContext.getBean(beanId, clazz);
  }

  public static <T> T getBean(Class<T> clazz) {
    return applicationContext.getBean(clazz);
  }

  public static ApplicationContext getContext() {
    return applicationContext;
  }
}
