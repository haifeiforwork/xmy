package com.zfj.xmy.activity.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.activity.service.cms.PointTacticsService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.PointTacticsMapper;
import com.zfj.xmy.common.persistence.pojo.PointTactics;

@Service
public class PointTacticsServiceImpl extends BaseService implements PointTacticsService{
	
	@Autowired
	private PointTacticsMapper pointTacticsMapper;
	
	@Override
	public void insertPointTactics(PointTactics pointTactics) {
		pointTacticsMapper.insert(pointTactics);
	}

	@Override
	public void selectPointTacticsAndPage(ReqData reqData, PageBean pageBean) {
		List<PointTactics> tacticsList=pointTacticsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(tacticsList);
	}
	
	@Override
	public int deletePointTactics(long id) {
		int count=pointTacticsMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public PointTactics findPointTactics(long id) {
		PointTactics pt=pointTacticsMapper.selectByPrimaryKey(id);
		return pt;
	}

	@Override
	public void update(PointTactics pointTactics) {
		PointTactics old=pointTacticsMapper.selectByPrimaryKey(pointTactics.getId());
		if(null==old){
			throw new BusinessException("积分策略不存在") ;
		}
		System.out.println(pointTactics.toString());
		pointTacticsMapper.updateByPrimaryKey(pointTactics);
	}

	@Override
	public int updateStatus(long id, int status) {
		PointTactics pt=pointTacticsMapper.selectByPrimaryKey(id);
		pt.setStatus(status);
		return pointTacticsMapper.updateByPrimaryKey(pt);
	}

}
