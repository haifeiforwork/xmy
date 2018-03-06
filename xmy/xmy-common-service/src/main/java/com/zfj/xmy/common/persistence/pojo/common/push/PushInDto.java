package com.zfj.xmy.common.persistence.pojo.common.push;

import java.util.Date;
import java.util.List;

public class PushInDto {

	/**
	 * 推送目标 - 0 全局推送 
	 */
	public static Integer TARGET_PUSH_ALL = 0;
	/**
	 * 推送目标 - 1,按标签推送 
	 */
	public static Integer TARGET_PUSH_BY_LABEL = 1;
	
	/**
	 * 推送方式  - 0 立即推送
	 */
	public static Integer TYPE_PUSH_NOW = 0;
	/**
	 * 推送方式  -  1 定时推送
	 */
	public static Integer TYPE_PUSH_TASK = 1;
	
	/**
	 * 推送正文
	 */
	private String content;
	
	/**
	 * 推送的标签id组
	 */
	private List<Long> labelids; 
	
	/**
	 * 推送目标 (0 全局推送,1,按标签推送)
	 */
	private Integer target; 
	/**
	 * 推送时间
	 */
	private Date time;
	/**
	 * 推送方式 (0立即推送,1定时推送)
	 */
	private Integer type;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Long> getLabelids() {
		return labelids;
	}
	public void setLabelids(List<Long> labelids) {
		this.labelids = labelids;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	} 
	
	
}
