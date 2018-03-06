package com.zfj.xmy.order.service.wap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.order.service.wap.WapUserSpendPointsService;
@Service
public class WapUserSpendPointsServiceImpl extends BaseService implements WapUserSpendPointsService{
	
	@Autowired
	private UserSpendPointsMapper mapper;
	@Override
	public List<UserSpendPoints> findUserSpendPoints(ReqData reqData) {
		
		return mapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	@Override
	public UserSpendPoints getUserSpendPoints(ReqData reqData) {
		
		return mapper.selectByPrimaryKey((long)reqData.getValue("id"));
	}
	@Override
	public int updateUserSpendPoints(UserSpendPoints userSpendPoints, ReqData reqData) {
		int i = mapper.updateByExample(userSpendPoints, ReqUtil.reqParameterToCriteriaParameter(reqData));
		return i;
	}
	@Override
	public List<UserSpendPoints> findSpendPoints(ReqData reqData, PageBean pageBean) {
		List<UserSpendPoints> selectByExampleAndPage = mapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		return selectByExampleAndPage;
	}
	@Override
	public void saveSpendPoints(UserSpendPoints points) {
		mapper.insert(points);
	}
	@Override
	public int findTotalCost(ReqData reqData) {
		int spend_type = (int) reqData.getValue("spend_type");
		int totalCostPoint=SystemConstant.CONTENT.ZERO;
		PageBean pageBean=new PageBean();
		List<UserSpendPoints> findSpendPoints = this.findSpendPoints(reqData, pageBean);
		if(spend_type==SystemConstant.userSpendPoints.SPEND_TYPE_SPEND)//消费扣款/积分消耗
		{
			for (UserSpendPoints userSpendPoints : findSpendPoints) {
				//totalCostPoint+=userSpendPoints.getMoneyPoint();
			}
		}
		return totalCostPoint;
	}

}
