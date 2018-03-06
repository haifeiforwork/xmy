package com.zfj.xmy.order.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.PayLogMapper;
import com.zfj.xmy.common.persistence.pojo.PayLog;
import com.zfj.xmy.order.persistence.common.dao.PayLogExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.PayLogDto;
import com.zfj.xmy.order.service.cms.PayLogService;
@Service
public class PayLogServiceImpl extends BaseService implements PayLogService {

	@Autowired
	private PayLogExMapper payLogExMapper ;
	@Autowired
	private PayLogMapper payLogMapper;
	
	@Override
	public void findTest(PageBean pageBean, ReqData reqData) {
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.like("user_name","%"+reqData.getValue("user_name")+"%") ;
		List<PayLogDto> data = payLogExMapper.findTest(parameter,pageBean.getRowBounds()) ;
		pageBean.setData(data) ;
		pageBean.setTotalCount(1) ;
	}

	@Override
	public void findTest2(PageBean pageBean, ReqData reqData) {
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		List<PayLogDto> data = payLogExMapper.findTest(parameter,pageBean.getRowBounds()) ;
		pageBean.setData(data) ;
	}

	@Override
	public void updatePaylog(PayLog paylog) {
		int i = payLogMapper.updateByPrimaryKey(paylog);
		
		System.out.println(i);
	}


}
