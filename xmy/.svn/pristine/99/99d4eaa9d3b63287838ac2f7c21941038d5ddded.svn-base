package com.zfj.xmy.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.NotificationMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.Notification;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.config.service.NotificationService;
@Service
public class NotificationServiceImpl  extends BaseService implements NotificationService {
	
	@Autowired
	private NotificationMapper notificationMapper;
	@Override
	public void findNotification(ReqData reqData, PageBean pageBean)
	{
		List<Notification> data = notificationMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(data);
	}
	@Override
	public int fingNotificationCount(ReqData reqData) {
		int countByExample = notificationMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return countByExample;
	}
	@Override
	public int insert(Notification notification) {
		int insert = notificationMapper.insert(notification);
		return insert;
	}
	@Override
	public int deleteByPrimaryKey(Object string) {
		int deleteByPrimaryKey = notificationMapper.deleteByPrimaryKey(string);
		return deleteByPrimaryKey;
	}
	@Override
	public Notification selectByPrimaryKey(Object id) {
		Notification selectByPrimaryKey = notificationMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
	@Override
	public List<Notification> findNotifications(ReqData reqData){
		List<Notification> selectByExample = notificationMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
}