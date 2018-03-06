package com.zfj.xmy.order.service.common;

import java.util.List;

import com.xiaoleilu.hutool.json.JSONObject;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;

public interface CommonOrderService {

	/**
	 * 获取订单物流信息
	 * @param orderId
	 * @return
	 * @Description 
	 * @date 2017年10月10日  下午4:33:26
	 * @author wy
	 * 2017
	 * @return JSONObject
	 */
	JSONObject getOrderLogisticsInfo(Long orderId);
	
	/**
	 * 检查订单商品里面是否含有跨境商品
	 * @param list
	 * @return    
	 * @return Boolean    
	 * Date:2017年11月16日 上午10:02:25 
	 * @author hexw
	 */
	Boolean checkOrderGoodsIsAcrossBorders(List<OrderGoods> list);

}
