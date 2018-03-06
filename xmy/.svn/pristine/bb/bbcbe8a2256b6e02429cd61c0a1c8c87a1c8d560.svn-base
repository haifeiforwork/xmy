package com.zfj.xmy.quartz.job;

import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.xiaoleilu.hutool.util.CollectionUtil;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.util.PushUtil;

/**
 * 
 * 极光推送定时任务
 * @author wy
 *
 */

public class JiguangPushQuartz extends QuartzJobBean {

	@Autowired
	private CommonPushUtilService commonPushUtilService;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); //注入
		
		Map<String, Object> map = context.getJobDetail().getJobDataMap();
		
		
		//直接广播  && 带标签的分组广播
		
		String msg = (String) map.get("msg");
		@SuppressWarnings("unchecked")
		List<Long> labelids = (List<Long>) map.get("labelids");
		if (!CollectionUtil.isEmpty(labelids)) {
			commonPushUtilService.sendBroadcastsWithLabel(msg, labelids);
		}
		if (!StringUtil.isEmpty(msg)) {
			commonPushUtilService.sendBroadcasts(msg);
		}
		
	}

}
