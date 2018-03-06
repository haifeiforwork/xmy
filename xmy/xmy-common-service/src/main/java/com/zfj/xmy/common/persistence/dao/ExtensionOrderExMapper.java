package com.zfj.xmy.common.persistence.dao;  
/** 
 * @Title: ExtensionOrderExMapper.java 
 * @Package com.zfj.xmy.order.persistence.common.dao 
 * @Description: 扩展订单Mapper
 * @author hexw
 * @date 2018年1月18日 下午4:30:42 
 */
public interface ExtensionOrderExMapper {
	
	
	/**
	 * 订单扩展存储过程
	 *     
	 * @return void    
	 * Date:2018年1月18日 下午4:38:24 
	 * @author hexw
	 */
	void extendOrderTime() ;
	
	
	/**
	 * 增加订单备注存储过程
	 *     
	 * @return void    
	 * Date:2018年1月18日 下午4:38:48 
	 * @author hexw
	 */
	void addOrderRemark();
}
  