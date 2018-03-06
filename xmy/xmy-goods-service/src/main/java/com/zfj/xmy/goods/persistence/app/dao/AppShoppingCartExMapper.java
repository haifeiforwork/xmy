package com.zfj.xmy.goods.persistence.app.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingCartDir;

/** 
 * @Title: AppShoppingCartExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.app.dao 
 * @Description:  购物车
 * @author hexw
 * @date 2017年7月27日 下午3:33:17 
 */
public interface AppShoppingCartExMapper {
	
	/**
	 * 查询购物车
	 * @param paramter
	 * @return    
	 * @return AppShoppingCartDir    
	 * Date:2017年7月27日 下午4:15:32 
	 * @author hexw
	 */
	List<AppShoppingCartDir> selectShoppingCart(CriteriaParameter paramter);
	
	/**
	 * 
	 * @param paramter
	 * @return    
	 * @return Integer    
	 * Date:2017年10月14日 下午2:35:35 
	 * @author hexw
	 */
	Integer countLimitAcivity(CriteriaParameter paramter);
	
}
  