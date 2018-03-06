package com.zfj.xmy.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import com.zfj.xmy.quartz.dto.PushReturnDto;

@Component
public class QuartzManager {
	
	private Scheduler scheduler;

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	} 
	
	/** 
     * @Description:启动所有定时任务 
     */  
    public void startJobs() {  
        try {  
            scheduler.start(); 
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    } 
    
    /**
     * 关闭所有任务
     * 
     * @Description 
     * @date 2017年12月18日  下午2:13:30
     * @author wy
     * 2017
     * @return void
     */
    public void shutdownJobs() {
    	try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param cron   时间设置，参考quartz说明文档  
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, String cron) {  
        try {  
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);  

            // 启动  
            if (!scheduler.isShutdown()) {  
                scheduler.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /** 
     * @Description: 移除一个任务 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     */  
    public void removeJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName) {  
        try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器  
            scheduler.unscheduleJob(triggerKey);// 移除触发器  
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**
     * 添加一个定时任务
     * (只执行一次)
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param triggerdate 任务触发时间点
     * @Description 
     * @date 2017年12月18日  下午4:00:52
     * @author wy
     * 2017
     * @return void
     */
	@SuppressWarnings({ "rawtypes" })
	public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, Date triggerdate) {
		addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, triggerdate, null);
	}  
	
	/**
     * 添加一个定时任务
     * (只执行一次)
     * @param jobName 任务名 
     * @param jobGroupName  任务组名 
     * @param triggerName 触发器名 
     * @param triggerGroupName 触发器组名 
     * @param jobClass  任务 
     * @param triggerdate 任务触发时间点
     * @param extData 任务附加数据
     * @Description 
     * @date 2017年12月18日  下午4:00:52
     * @author wy
     * 2017
     * @return void
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, Date triggerdate,Map extData) {
		try {

			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobName, jobGroupName).build();
			if (extData instanceof Map<?, ?>) {
				jobDetail.getJobDataMap().putAll(extData); //任务附加数据
			}
			
			// 触发器
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
					.newTrigger();

			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, triggerGroupName);
			triggerBuilder.startAt(triggerdate); //设置定时时间
			//triggerBuilder.startAt(DateBuilder.nextGivenMinuteDate(new Date(), 2)); //设置定时时间
			
			// 创建Trigger对象
			SimpleTrigger trigger = (SimpleTrigger) triggerBuilder.build();
			
			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询所有定时推送
	 * @return
	 * @Description 
	 * @date 2017年12月19日  上午11:40:10
	 * @author wy
	 * 2017
	 * @return List<PushReturnDto>
	 */
	public List<PushReturnDto> queryAllPush(){
		try {
			List<PushReturnDto> pushReturnDtos = new ArrayList<PushReturnDto>();
			List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
			for (String triggerGroupName : triggerGroupNames) {
				for (TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName))) {
					Trigger trigger = scheduler.getTrigger(triggerKey);
					JobKey jobKey = trigger.getJobKey();
					String jobName = jobKey.getName();
					String triggerName = triggerKey.getName();
					String jobGroup = jobKey.getGroup();
					Map<String, Object> extData =scheduler.getJobDetail(jobKey).getJobDataMap();
					Date nextFireTime = trigger.getNextFireTime();
					
					PushReturnDto pushReturn = new PushReturnDto();
					pushReturn.setJobName(jobName);//jobname
					pushReturn.setTriggerName(triggerName);
					pushReturn.setTime(nextFireTime); //下一次运行时间
					pushReturn.setExtData(extData); //附带数据
					pushReturnDtos.add(pushReturn);
				}
			}			
			return pushReturnDtos;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null; 
	}
}
