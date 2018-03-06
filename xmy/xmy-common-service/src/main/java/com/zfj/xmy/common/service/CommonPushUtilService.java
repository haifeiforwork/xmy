package com.zfj.xmy.common.service;

import java.util.Date;
import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.JiguangPushRecord;
import com.zfj.xmy.common.persistence.pojo.common.push.PushInDto;
import com.zfj.xmy.common.persistence.pojo.common.push.findJiguangPushRecordListResultDto;
import com.zfj.xmy.quartz.dto.PushReturnDto;

public interface CommonPushUtilService {

	void testme();

//	/**
//	 * 后台推送，促销 活动等(广播)
//	 * 
//	 * @param msg 推送内容信息
//	 * @param labelid (不指定推送标签时 为广播)
//	 * @Description 
//	 * @date 2017年12月19日  上午10:09:18
//	 * @author wy
//	 * 2017
//	 * @param labelid 
//	 * @return void
//	 */
//	void Push(String msg, Long labelid);

	/**
     * 后台定时推送，促销 活动等(广播)
     * 
     * @Description 
     * @date 2017年12月19日  上午10:10:36
     * @author wy
     * 2017
     * @return void
     */
	void addPush(String msg, Date date);

	/**
	 * 查询所有定时推送
	 * 
	 * @Description 
	 * @date 2017年12月19日  上午11:09:24
	 * @author wy
	 * 2017
	 * @return void
	 */
	List<PushReturnDto> queryAllPush();

	/**
	 * 删除推送
	 * @param jobName
	 * @param triggerName
	 * @Description 
	 * @date 2017年12月20日  下午5:50:43
	 * @author wy
	 * 2017
	 * @return void
	 */
	void delPush(String jobName, String triggerName);

	/**
	 * 推送给单一用户
	 * @param userId
	 * @param msg
	 * @Description 
	 * @date 2017年12月25日  下午3:36:59
	 * @author wy
	 * 2017
	 * @return void
	 */
	void Push(Long userId, String msg);

	/**
	 * 查询推送记录 
	 * @param parameter
	 * @param pageBean
	 * @return
	 * @Description 
	 * @date 2018年1月29日  上午9:40:12
	 * @author wy
	 * 2018
	 * @return List<JiguangPushRecord>
	 */
	List<findJiguangPushRecordListResultDto> findJiguangPushRecordList(
			CriteriaParameter parameter, PageBean pageBean);

	/**
	 * 推送主方法
	 * @param pushInDto
	 * @Description 
	 * @date 2018年1月31日  上午10:00:18
	 * @author wy
	 * 2018
	 * @return void
	 */
	void Push(PushInDto pushInDto);

	/**
	 * 广播推送
	 * @param msg
	 * @Description 
	 * @date 2018年1月31日  上午11:00:04
	 * @author wy
	 * 2018
	 * @return void
	 */
	void sendBroadcasts(String msg);

	/**
	 * 带标签的分组 广播推送
	 * @param msg
	 * @param labelids
	 * @Description 
	 * @date 2018年1月31日  上午11:00:18
	 * @author wy
	 * 2018
	 * @return void
	 */
	void sendBroadcastsWithLabel(String msg, List<Long> labelids);

	/**
	 * 带标签的分组 广播推送
	 * @param msg
	 * @param labelid
	 * @Description 
	 * @date 2018年1月31日  下午4:32:05
	 * @author wy
	 * 2018
	 * @return void
	 */
	void sendBroadcastsWithLabel(String msg, Long labelid);

	/**
	 * commonPushUtilService
	 * @param userid
	 * @param pageBean
	 * @Description 
	 * @date 2018年2月5日  下午2:15:18
	 * @author wy
	 * 2018
	 * @return void
	 */
	void findRecordsByUser(Long userid, PageBean pageBean);

	


}
