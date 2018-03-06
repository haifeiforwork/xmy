package com.zfj.xmy.order.service.cms;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.order.persistence.common.pojo.dto.LableDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderGoodsDto;
/**
 * 
 * @author ljie
 *
 */
public interface OrderGoodsService {
	
	/**
	 * 
	 * @param reData
	 * @return List<OrderGoods>
	 * @author ljie
	 * @date 2017年6月16日 下午4:12:06
	 * 根据条件查询全部订单商品
	 */
	List<OrderGoods> findOrderGoodsByOrderId(ReqData reData,Boolean parent);
	
	/**
	 * 
	 * @param reqData
	 * @return List<OrderGoodsDto>
	 * @author ***
	 * @date 2017年6月16日 下午4:12:37
	 * 根据订单ID分组查询订单商品，即采购清单
	 */
	List<OrderGoodsDto> findShoppingList(ReqData reqData,PageBean pageBean,List<Object> ordersid);
	//修改单个订单商品
	int updateOrderGoods(long id,int goodnum);
	/**
	 * 删除订单商品
	 * @param id
	 * @return int
	 * @author lij
	 * @date 2017年10月28日 下午4:53:02
	 */
	int deleteOrderGoods(Long id);
	
	/**
	 * 查询采购清单
	 * @param reqData
	 * @param type
	 * @param orderIds
	 * @return List<OrderGoodsDto>
	 * @author ***
	 * @date 2017年10月17日 上午10:19:54
	 */
	List<OrderGoodsDto> exleGoodsList(ReqData reqData,Integer type,String orderIds);
	/**
	 * 查询要导出的订单数据
	 * @param reqData
	 * @param orderIds
	 * @param type
	 * @return List<OrderDto>
	 * @author lij
	 * @date 2017年10月18日 上午10:08:48
	 */
	List<OrderDto> exleOrderDto(ReqData reqData,String orderIds,Integer type);
	/**
	 * @param reqData
	 * @param orderIds
	 * @param type
	 * @return List<OrderGoodsDto>
	 * @author 查询要导出的标签
	 * @date 2017年10月18日 下午3:12:14
	 */
	List<LableDto> excleLable(ReqData reqData,String orderIds,Integer type);
}