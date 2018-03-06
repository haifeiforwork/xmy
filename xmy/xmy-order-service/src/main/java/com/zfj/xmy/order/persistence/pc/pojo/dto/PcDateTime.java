package com.zfj.xmy.order.persistence.pc.pojo.dto;

import com.xiaoleilu.hutool.date.DateTime;

/**
 * @author 控制前台显示时间的类
 */
public class PcDateTime {
	private String month;//月份
	private String day;//日期
	private Integer week;//周几
	private DateTime dateTime;//今天的日期
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
