package com.zfj.xmy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.index.RedisIndexDefinition.OrCondition;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.OrderCommitMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.OrderCommit;
import com.zfj.xmy.common.service.OrderCommitService;
@Service
public class OrderCommitServiceImpl implements OrderCommitService{
	
	@Autowired
	private OrderCommitMapper commitMapper;
	
	@Override
	public int isCommitOrder() {
		int i = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("id", 1, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderCommit> commit = commitMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(commit)){
			OrderCommit orderCommit = commit.get(0);
			if(!ObjectUtils.isEmpty(orderCommit)){
				if(orderCommit.getStatus()>0){//不能提交订单
					i=1;
				}
			}
		}
		return i;
	}

}
