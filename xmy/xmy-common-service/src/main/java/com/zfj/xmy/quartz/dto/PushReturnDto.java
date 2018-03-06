package com.zfj.xmy.quartz.dto;

import java.util.Date;
import java.util.Map;

public class PushReturnDto {

	private String jobName;
	private String triggerName;
	private Date time;
	private Map<String, Object> extData;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Map<String, Object> getExtData() {
		return extData;
	}
	public void setExtData(Map<String, Object> extData) {
		this.extData = extData;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	
	
}
