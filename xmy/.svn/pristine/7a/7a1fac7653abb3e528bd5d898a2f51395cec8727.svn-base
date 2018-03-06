package com.zfj.xmy.order.persistence.app.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppOrderGoodsDir;

/** 
 * @Title: AppOrderExMapper.java 
 * @Package com.zfj.xmy.order.persistence.app.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 下午8:24:50 
 */
public interface AppOrderExMapper {

	
	/**
	 * 查询订单商品
	 * @param parameter
	 * @return    
	 * @return List<AppOrderGoodsDir>    
	 * Date:2017年7月31日 下午8:31:04 
	 * @author hexw
	 */
	AppOrderGoodsDir findOrderGoods(CriteriaParameter parameter);
	
	/**
	 * 根据商品id查询商品一级分类名称
	 * @param parameter
	 * @return    
	 * @return List<String>    
	 * Date:2017年8月1日 上午11:23:51 
	 * @author hexw
	 */
	List<String> findCategoryName(CriteriaParameter parameter);
	
	/**
	 * 查询发票内容
	 * @param parameter
	 * @return    
	 * @return String    
	 * Date:2017年8月3日 上午11:10:39 
	 * @author hexw
	 */
	String findInvoiceContent(CriteriaParameter parameter);
	
	/**
	 * 查询订单号最大值
	 * @param parameter
	 * @return    
	 * @return String    
	 * Date:2017年8月3日 下午4:35:06 
	 * @author hexw
	 */
	String findMaxNum(CriteriaParameter parameter);
	
}
  