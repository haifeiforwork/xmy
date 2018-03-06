package com.zfj.xmy.order.persistence.cms.dao;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.cms.pojo.dto.OrderExcleDto;

public interface OrderExcleMapper {
	/**
	 * 根据时间段查询订单支付信息
	 * @param parameter
	 * @return List<OrderExcleDto>
	 * @author lij
	 * @date 2017年12月20日 下午2:54:06
	 */
	public List<OrderExcleDto> findOrderExcleDto(CriteriaParameter parameter);
	
}
