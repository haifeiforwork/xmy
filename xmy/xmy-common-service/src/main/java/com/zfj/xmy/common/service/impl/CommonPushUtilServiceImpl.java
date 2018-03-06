package com.zfj.xmy.common.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.standard.MediaSize.JIS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.JiguangPushRecordExMapper;
import com.zfj.xmy.common.persistence.dao.JiguangPushRecordMapper;
import com.zfj.xmy.common.persistence.dao.JiguangPushUserLabelMapper;
import com.zfj.xmy.common.persistence.dao.UserCommExMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.JiguangPushRecord;
import com.zfj.xmy.common.persistence.pojo.JiguangPushUserLabel;
import com.zfj.xmy.common.persistence.pojo.MsgPushInfo;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.common.push.PushInDto;
import com.zfj.xmy.common.persistence.pojo.common.push.findJiguangPushRecordListResultDto;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.quartz.QuartzManager;
import com.zfj.xmy.quartz.dto.PushReturnDto;
import com.zfj.xmy.quartz.job.JiguangPushQuartz;
import com.zfj.xmy.util.PushUtil;

@Service
public class CommonPushUtilServiceImpl extends BaseService implements CommonPushUtilService {

	@Autowired
	private JiguangPushRecordMapper jiguangPushRecordMapper;
	
	@Autowired
	private JiguangPushUserLabelMapper jiguangPushUserLabelMapper;
	
	@Autowired
	private UserCommExMapper userCommExMapper;
	
	@Autowired
	private QuartzManager quartzManager;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JiguangPushRecordExMapper jiguangPushRecordExMapper;
	/**
	 * 推送任务组标记
	 */
	public static final String PUSH_JOB_GROUP = "PUSH_JOB_GROUP"; 
	/**
	 * 推送触发器组标记
	 */
	public static final String PUSH_TRIGGER_GROUP = "PUSH_TRIGGER_GROUP"; 
	
	
	
	@Override
	public void testme() {
		
		//System.out.println("is test me .....");
	}
	
	

    
	/**
	 * 广播
	 * @param msg
	 * @Description 
	 * @date 2018年1月29日  下午2:47:28
	 * @author wy
	 * 2018
	 * @return void
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
    public void sendBroadcasts(String msg) {
		//推送
		String result = PushUtil.sendBroadcasts(msg);
		//记录
		JiguangPushRecord jiguangPushRecord = new JiguangPushRecord();
		jiguangPushRecord.setContent(msg);
		jiguangPushRecord.setCreateTimestamp(DateUtil.date());
		jiguangPushRecord.setType(SystemConstant.JIGUANG_PUSH_RECORD.TYPE_ALL_BROCAST);
		jiguangPushRecord.setResult(result);
		jiguangPushRecordMapper.insertSelective(jiguangPushRecord);
	}
	/**
	 * 按分组(标签)推送
	 * @param msg
	 * @param labelid
	 * @Description 
	 * @date 2018年1月29日  下午2:47:37
	 * @author wy
	 * 2018
	 * @return void
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW) //
	public void sendBroadcastsWithLabel(String msg,Long labelid) {
    	
		//1根据标签查询用户
    	List<String> tokens = userCommExMapper.findUserTokenByPushLabelId(labelid);
    	
    	//2如果没有用户就退出
    	if (CollectionUtil.isEmpty(tokens)) {
    		logger.debug("该标签没有相应的推送用户");
    		return;
		}
    	tokens.removeAll(Collections.singleton(null));//清除空串
    	if (CollectionUtil.isEmpty(tokens)) {
    		logger.debug("该标签没有相应的推送用户");
    		return;
		}
    	System.out.println(tokens);
    	//3如果有用户就根据用户发送推送//TODO
    	String result = PushUtil.sendToUserByTokens(tokens, msg);
    	//记录
    	JiguangPushRecord jiguangPushRecord = new JiguangPushRecord();
    	jiguangPushRecord.setContent(msg);
    	jiguangPushRecord.setCreateTimestamp(DateUtil.date());
    	jiguangPushRecord.setResult(result);
    	jiguangPushRecord.setLabelId(labelid);
    	jiguangPushRecord.setType(SystemConstant.JIGUANG_PUSH_RECORD.TYPE_GROUP_BROCAST);
    	jiguangPushRecordMapper.insertSelective(jiguangPushRecord);
	}
	
    /**
     * 按分组(标签列表)推送
     * @param msg
     * @param labelids
     * @Description 
     * @date 2018年1月31日  上午10:47:52
     * @author wy
     * 2018
     * @return void
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void sendBroadcastsWithLabel(String msg,List<Long> labelids) {
    	if (CollectionUtil.isEmpty(labelids)) {
			throw new BusinessException("标签不能为空");
		}
    	for (Long labelid : labelids) {
			sendBroadcastsWithLabel(msg, labelid);
		}
    }
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addPush(String msg,Date date) {
		
		if (StringUtil.isEmpty(msg)) {
			throw new BusinessException("推送信息不能为空");
		}
		if (ObjectUtil.isNull(date)) {
			throw new BusinessException("推送时间不能为空");
		}
		if (date.before(DateUtil.date())) {
			throw new BusinessException("推送时间必须大于当前时间");
		}
		Map<String, Object> extData = new HashMap<String, Object>();
		extData.put("msg", msg); //推送信息
		
		quartzManager.addJob(RandomUtil.randomUUID(), CommonPushUtilServiceImpl.PUSH_JOB_GROUP, RandomUtil.randomUUID(), CommonPushUtilServiceImpl.PUSH_TRIGGER_GROUP, JiguangPushQuartz.class, date, extData);
	}
	
	@Transactional(rollbackFor=Exception.class)
	private void addPushWithLabels(String msg,Date date,List<Long> labels) {
		if (StringUtil.isEmpty(msg)) {
			throw new BusinessException("推送信息不能为空");
		}
		if (ObjectUtil.isNull(date)) {
			throw new BusinessException("推送时间不能为空");
		}
		if (date.before(DateUtil.date())) {
			throw new BusinessException("推送时间必须大于当前时间");
		}
		if (CollectionUtil.isEmpty(labels)) {
			throw new BusinessException("推送标签不能为空");
		}
		Map<String, Object> extData = new HashMap<String, Object>();
		extData.put("msg", msg); //推送信息
		extData.put("labelids", labels); //推送标签
		
		quartzManager.addJob(RandomUtil.randomUUID(), CommonPushUtilServiceImpl.PUSH_JOB_GROUP, RandomUtil.randomUUID(), CommonPushUtilServiceImpl.PUSH_TRIGGER_GROUP, JiguangPushQuartz.class, date, extData);
	}
	
	@Override
	public List<PushReturnDto> queryAllPush() {
		List<PushReturnDto> pushReturns = quartzManager.queryAllPush();
		for (PushReturnDto pushReturnDto : pushReturns) {
			System.out.println(pushReturnDto.getJobName());
			System.out.println(pushReturnDto.getExtData());
			System.out.println(pushReturnDto.getTime());
		}
		return pushReturns;
	}
	
	@Override
	public void delPush(String jobName,String triggerName) {
		quartzManager.removeJob(jobName, CommonPushUtilServiceImpl.PUSH_JOB_GROUP, triggerName, CommonPushUtilServiceImpl.PUSH_TRIGGER_GROUP);
		//quartzManager.addJob(RandomUtil.randomUUID(), "push", RandomUtil.randomUUID(), "push", JiguangPushQuartz.class, date, extData);
		//System.out.println("is test me .....");
	}
	
	/**
	 * 推送给用户
	 * @param userId
	 * @param msg
	 * @Description 
	 * @date 2017年12月25日  下午3:34:16
	 * @author wy
	 * 2017
	 * @return void
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
    public void Push(Long userId,String msg) {
		//推送
		User user = userMapper.selectByPrimaryKey(userId);
		if (ObjectUtil.isNull(user)) {
			throw new  BusinessException("用户不存在");
		}
//		String result = PushUtil.sendToUserByToken(user.getAppToken(), msg);
//		//记录
//		JiguangPushRecord jiguangPushRecord = new JiguangPushRecord();
//		jiguangPushRecord.setContent(msg);
//		jiguangPushRecord.setUserId(userId+"");
//		jiguangPushRecord.setCreateTimestamp(DateUtil.date());
//		jiguangPushRecord.setType(SystemConstant.JIGUANG_PUSH_RECORD.TYPE_TO_SINGLE);
//		jiguangPushRecord.setResult(result);
//		jiguangPushRecordMapper.insertSelective(jiguangPushRecord);
	}


	@Override
	public List<findJiguangPushRecordListResultDto> findJiguangPushRecordList(
			CriteriaParameter parameter, PageBean pageBean) {
		List<findJiguangPushRecordListResultDto> findJiguangPushRecordListResultDtos = jiguangPushRecordExMapper.findJiguangPushRecordList(parameter, pageBean.getRowBounds());
		
		//List<JiguangPushRecord> jiguangPushRecords = jiguangPushRecordMapper.selectByExampleAndPage(parameter, pageBean.getRowBounds());
		int countByExample = jiguangPushRecordMapper.countByExample(parameter);
		pageBean.setTotalCount(countByExample);
		pageBean.setData(findJiguangPushRecordListResultDtos);
		return findJiguangPushRecordListResultDtos;

	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public void Push(PushInDto pushInDto) {
		//1.验证输入
		Integer target = pushInDto.getTarget(); //推送目标
		Integer type = pushInDto.getType(); //推送类型
		String content = pushInDto.getContent(); //推送正文
		
		if (ObjectUtil.isNull(type)) {
			throw new BusinessException("请选择推送类型");
		}
		if (StringUtil.isEmpty(content)) {
			throw new BusinessException("推送内容不能为空");
		}
		if (ObjectUtil.isNull(target)) {
			throw new BusinessException("请选择推送目标");
		}
		
		//2. 4种推送类型
		if (PushInDto.TARGET_PUSH_ALL.equals(target) && PushInDto.TYPE_PUSH_NOW.equals(type)) {
			sendBroadcasts(content);
		}else if (PushInDto.TARGET_PUSH_ALL.equals(target) && PushInDto.TYPE_PUSH_TASK.equals(type)) {
			Date date = pushInDto.getTime();
			addPush(content, date);
		}else if (PushInDto.TARGET_PUSH_BY_LABEL.equals(target) && PushInDto.TYPE_PUSH_NOW.equals(type)) {
			sendBroadcastsWithLabel(content, pushInDto.getLabelids());
		}else if (PushInDto.TARGET_PUSH_BY_LABEL.equals(target) && PushInDto.TYPE_PUSH_TASK.equals(type)) {
			addPushWithLabels(content, pushInDto.getTime(), pushInDto.getLabelids());
		}else {
			throw new BusinessException("推送目标或推送类型有误");
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findRecordsByUser(Long userid,PageBean pageBean){

		List<JiguangPushRecord> jiguangPushRecords = jiguangPushRecordExMapper.findRecordsByUser(userid, pageBean.getRowBounds());
		
		//去除不要的列
		if (!CollectionUtils.isEmpty(jiguangPushRecords)) {
			for (JiguangPushRecord jiguangPushRecord : jiguangPushRecords) {
				jiguangPushRecord.setId(null);
				jiguangPushRecord.setLabelId(null);
				jiguangPushRecord.setResult(null);
				jiguangPushRecord.setUserId(null);
			}
		}
		pageBean.setData(jiguangPushRecords);
		int count = jiguangPushRecordExMapper.findRecordsByUserCount(userid);
		pageBean.setTotalCount(count);
	}


	
}
