package com.zfj.xmy.quartz;

import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zfj.xmy.common.service.CommonPushUtilService;

public class QuartzTest extends QuartzJobBean {

	@Autowired
	private CommonPushUtilService commonPushUtilService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("任务运行：了 HHHHHHHHHHHHHHHHHHHHHHH");
		Map<String, Object> map = context.getJobDetail().getJobDataMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println("附带数据:"+ key +":"+" " + map.get(key)  );
		}
		
	}
}
