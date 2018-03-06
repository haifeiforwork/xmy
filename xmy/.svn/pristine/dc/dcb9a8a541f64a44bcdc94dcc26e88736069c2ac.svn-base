package com.zfj.xmy.activity.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.service.cms.BuyAndPresentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;

@Service
public class BuyAndPresentServiceIpml extends BaseService implements BuyAndPresentService {
	@Autowired
	private BuyAndPresentMapper buyAndPresentMapper;

	@Override
	public int insert(BuyAndPresent buyAndPresent) {
		int insert = buyAndPresentMapper.insert(buyAndPresent);
		return insert;
	}

	@Override
	public void findBuyAndPresentS(ReqData reqData, PageBean pageBean) {
		reqData.setSortname("start_time");
		reqData.setSortorder("desc");
		List<BuyAndPresent> selectByExampleAndPage = buyAndPresentMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		int countByExample = buyAndPresentMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setData(selectByExampleAndPage);
		pageBean.setTotalCount(countByExample);
	}

	@Override
	public BuyAndPresent findBuyAndPresent(Object id) {
		BuyAndPresent buyAndPresent = buyAndPresentMapper.selectByPrimaryKey(id);
		return buyAndPresent;
	}

	@Override
	public List<BuyAndPresent> findBuyAndPresentS(ReqData reqData) {
		List<BuyAndPresent> selectByExample = buyAndPresentMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}

	@Override
	public int updateBuyAndPresent(BuyAndPresent buyAndPresent) {
		int updateByPrimaryKey = buyAndPresentMapper.updateByPrimaryKey(buyAndPresent);
		return updateByPrimaryKey;
	}

	@Override
	public int deleteByPrimaryKey(Object string) {
		int deleteByPrimaryKey = buyAndPresentMapper.deleteByPrimaryKey(string);
		return deleteByPrimaryKey;
	}
}
