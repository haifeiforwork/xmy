package com.zfj.xmy.common.service.impl;  

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.service.CommonPointsService;

/** 
 * @Title: CommonPointsServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年11月26日 上午10:49:23 
 */
@Service
public class CommonPointsServiceImpl extends BaseService implements CommonPointsService {

	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void defaultPresentPoints(Long userId){
		UserSpendPoints userSpendPoints = new UserSpendPoints();
		userSpendPoints.setUserId(userId);
		userSpendPoints.setType(SystemConstant.userSpendPoints.TYPE_POINT);
		userSpendPoints.setMoneyPoint(new BigDecimal(SystemConstant.USER.PRESENT_POINTS));
		userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SAVE);
		userSpendPoints.setRemarks("注册赠送50积分");
		userSpendPoints.setCreatTime(new Date());
		userSpendPoints.setSign(SystemConstant.userSpendPoints.ORTHER);
		userSpendPointsMapper.insert(userSpendPoints);
		
	}
	
}
  