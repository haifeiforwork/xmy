package com.zfj.xmy.common;  

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/** 
 * @Title: DateUtil.java 
 * @Package com.zfj.xmy.common 
 * @Description: 
 * @author hexw
 * @date 2017年9月11日 上午9:51:15 
 */
public class DateUtils {
	
	//获取当天的开始时间
	public static Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	//获取当天的结束时间
	public static Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	// 获取昨天的开始时间
	public static Date getBeginDayOfYesterday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	// 获取昨天的结束时间
	public static Date getEndDayOfYesterDay() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	// 获取本周的开始时间
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek);
		return getDayStartTime(cal.getTime());
	}

	// 获取本周的结束时间
	public static Date getEndDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return getDayEndTime(weekEndSta);
	}

	// 获取本月的开始时间
	public static Date getBeginDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		return getDayStartTime(calendar.getTime());
	}

	// 获取本月的结束时间
	public static Date getEndDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(), getNowMonth() - 1, day);
		return getDayEndTime(calendar.getTime());
	}

	// 获取本年的开始时间
	public static java.util.Date getBeginDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		// cal.set
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);

		return getDayStartTime(cal.getTime());
	}

	// 获取本年的结束时间
	public static java.util.Date getEndDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getNowYear());
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DATE, 31);
		return getDayEndTime(cal.getTime());
	}

	// 获取某个日期的开始时间
	public static Timestamp getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	// 获取某个日期的结束时间
	public static Timestamp getDayEndTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d)
			calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new Timestamp(calendar.getTimeInMillis());
	}

	// 获取今年是哪一年
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	// 获取本月是哪一月
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}
	
	// 获取明天12点的时间
	public static Date getTwelveOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.HOUR_OF_DAY, 12);
		return cal.getTime();
	}
	
	//获取今天12点的时间
	public static Date getTwelveOfToday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.HOUR_OF_DAY, 12);
		return cal.getTime();
	}
	
	//获取时间  格式月 号
	public static String getMothAndDay(Date date) {
		String str = "";
		if (ObjectUtil.isNotNull(date)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
	        int month=cal.get(Calendar.MONTH)+1;//获取月份
	        int day=cal.get(Calendar.DATE);//获取日
	        str =month+ "月"+day+"日";
		}
        return str;
	}
	
	//获取传入时间 下午12点的时间
	public static Date getTwelveTimePM(Date date){
		Calendar cal = Calendar.getInstance();
		if (ObjectUtil.isNotNull(date)) {
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime() ;
		} else {
			return null;
		}
	}
	
	//获取传入时间三天以后的时间
	public static Date getTreeDateAfterTime(Date date) {
		Calendar cal = Calendar.getInstance();
		if (ObjectUtil.isNotNull(date)) {
			cal.setTime(date);
			cal.add(Calendar.DATE, 3);
			return cal.getTime();
		} else {
			return null;
		}
	}
	
	// 获取昨天的结束时间
	public static Date getExtensionTime(Date date) {
		Calendar cal = Calendar.getInstance();
		if (ObjectUtil.isNotNull(date)) {
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 55);
			cal.set(Calendar.SECOND, 00);
			return cal.getTime();
		} else {
			return null;
		}
	}
	
	// 获取明天的结束时间
	public static Date getTomorrowEndTime(){
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, +1);
		return cal.getTime();
	}
	
	public static void main(String[] args) {
	    
	    System.out.println("-------"+DateUtil.format(DateUtils.getBeginDayOfYesterday(), "yyyy-MM-dd HH:mm:ss")+"========="
	    +DateUtil.format(DateUtils.getEndDayOfYesterDay(), "yyyy-MM-dd HH:mm:ss"));
	    ;
    }

}
  