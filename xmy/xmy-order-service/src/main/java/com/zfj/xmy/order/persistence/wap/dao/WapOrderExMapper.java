package com.zfj.xmy.order.persistence.wap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.wap.pojo.dto.WapOrderQueryDto;

public interface WapOrderExMapper {
	
	List<OrderDto> findOrders(WapOrderQueryDto dto);

	List<OrderDto> getOrderByGoodsId(@Param("userId")Long userId, @Param("goodsId")String goodsId);
	
}
