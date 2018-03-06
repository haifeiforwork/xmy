package com.zfj.xmy.config.service;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Notification;
import com.zfj.xmy.common.persistence.pojo.TermData;

public interface NotificationService {

	void findNotification(ReqData reqData, PageBean pageBean);
	int fingNotificationCount(ReqData reqData);
	int insert(Notification notification);
	int deleteByPrimaryKey(Object string);
	Notification selectByPrimaryKey(Object id);
	/**
	 * 根据条件查询记录列表
	 * @Description 
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年9月28日下午8:16:26
	 */
	List<Notification> findNotifications(ReqData reqData);
}
