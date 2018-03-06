package com.zfj.xmy.order.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.OrderPathMapper;
import com.zfj.xmy.common.persistence.pojo.OrderPath;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.order.service.cms.OrderPathService;
@Service
public class OrderPathServiceImpl extends BaseService implements OrderPathService {

	@Autowired
	private OrderPathMapper orderPathMapper;
	@Override
	public List<OrderPath> findOrderPathByOrderId(ReqData reqData) {
		
		return orderPathMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	@Override
	public void addOrderPath(OrderPath orderPath) {
		
		orderPathMapper.insert(orderPath);
		
	}
	/**
	 * 添加到订单轨迹
	 */
	@Override
	public void addOrderPath(long orderId, String content, SysUser sysUser) {
		OrderPath orderPath = new OrderPath();
		orderPath.setOrderId(orderId);
		orderPath.setChangeContent(content);
		orderPath.setChangeTime(new Date());
		orderPath.setUserName(sysUser.getName());
		orderPathMapper.insert(orderPath);
	}

}
