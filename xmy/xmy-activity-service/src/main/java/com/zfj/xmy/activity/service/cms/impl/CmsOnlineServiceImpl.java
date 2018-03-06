package com.zfj.xmy.activity.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.service.cms.CmsOnlineService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.OnlineActivityMapper;
import com.zfj.xmy.common.persistence.pojo.OnlineActivity;
@Service
public class CmsOnlineServiceImpl implements CmsOnlineService{

	@Autowired
	private OnlineActivityMapper activityMapper;
	/**
	 * 分页查询活动数据
	 */
	@Override
	public List<OnlineActivity> listActivity(ReqData reqData, PageBean pageBean) {
		List<OnlineActivity> andPage = activityMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(andPage);
		pageBean.setTotalCount(activityMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		return andPage;
	}
	@Override
	public void addOnlineActivity(OnlineActivity onlineActivity) {
		int insertSelective = activityMapper.insertSelective(onlineActivity);
	}
	@Override
	public OnlineActivity getOnlineActivityById(Long id) {
		
		OnlineActivity activity = activityMapper.selectByPrimaryKey(id);
		
		return activity;
	}
	
	@Override
	public void updateOnlineActivity(OnlineActivity onlineActivity) {
		activityMapper.updateByPrimaryKey(onlineActivity);
	}

}
