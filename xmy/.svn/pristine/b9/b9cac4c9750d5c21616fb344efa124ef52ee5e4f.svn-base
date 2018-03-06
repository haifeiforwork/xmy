package com.zfj.xmy.order.persistence.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.app.OrderStatusCountDto;

public interface OrderExMapper {
	/**
	 * @param parameter
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年7月19日 下午2:54:37
	 * 根据条件查询订单扩展信息（订单相关的商品）
	 */
	public List<OrderDto> findOrderDto(CriteriaParameter parameter);
	/**
	 * @param parameter
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年7月19日 下午2:54:37
	 * 根据条件分页查询订单扩展信息（订单相关的商品）
	 */
	public List<OrderDto> findOrderDto(CriteriaParameter parameter,RowBounds rowBounds);
	
	/**
	 * 根据搜索条件查询我的订单
	 * @param parameter
	 * @param rowBounds
	 * @return    
	 * @return List<OrderDto>    
	 * Date:2017年12月7日 下午3:00:08 
	 * @author hexw
	 */
	public List<OrderDto> findOrderDtoByCondition(Map<String,Object> param,RowBounds rowBounds);
	
	/**
	 * 根据状态分组获取订单数量
	 * @param parameter
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午2:26:50
	 * @author wy
	 * 2017
	 * @return List<OrderStatusCountDto>
	 */
	public List<OrderStatusCountDto> findOrderStatusCount(CriteriaParameter parameter);

	/**
	 * @param parameter
	 * @param rowBounds
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年8月2日 下午8:47:28
	 * 根据条件分页查询（PC个人中心订单筛选使用）
	 */
	public List<OrderDto> findScreenOrderDto(CriteriaParameter parameter,RowBounds rowBounds);
	/**
	 * @return String
	 * @author lij
	 * @date 2017年9月8日 上午10:21:05
	 * 查询最大订单号
	 */
	String findMaxNum();
	/**
	 * 筛选订单属于重庆九大主城区
	 * @param criteriaParameter
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年10月18日 下午5:09:32
	 */
	public List<OrderDto> findOrderIsCQ(CriteriaParameter criteriaParameter);
	/**
	 * 筛选订单不属于重庆九大主城区
	 * @param criteriaParameter
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年10月18日 下午5:20:57
	 */
	public List<OrderDto> findOrderNoCQ(CriteriaParameter criteriaParameter);
}
