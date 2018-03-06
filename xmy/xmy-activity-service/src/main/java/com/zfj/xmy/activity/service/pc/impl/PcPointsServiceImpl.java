package com.zfj.xmy.activity.service.pc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.service.pc.PcPointsService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

@Service
public class PcPointsServiceImpl extends BaseService implements PcPointsService{
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper; 
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//1.消费记录 2.积分记录
	private final static int TYPE=2;

	@Override
	public List<UserSpendPoints> findUserSpendPoints(Long uid) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", uid,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", TYPE,SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> list=userSpendPointsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public void findUserSpendPointsByPage(Long uid,PageBean pageBean) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", uid,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", TYPE,SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> list=userSpendPointsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=userSpendPointsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}

	@Override
	public UserInfo findUserinfo(Long uid) {
		UserInfo userInfo=userInfoMapper.selectByPrimaryKey(uid);
		return userInfo;
	}

}
