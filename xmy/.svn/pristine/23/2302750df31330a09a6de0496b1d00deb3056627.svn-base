package com.zfj.xmy.goods.service.app;  

import java.util.List;
import java.util.Map;

import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingCartDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingInCartDto;

/** 
 * @Title: AppShoppingCartService.java 
 * @Package com.zfj.xmy.goods.service.app.impl 
 * @Description: 购物车
 * @author hexw
 * @date 2017年7月27日 下午4:17:00 
 */
public interface AppShoppingCartService {
	
	/**
	 * 添加商品到购物车
	 * @param shoppingCart    
	 * @return void    
	 * Date:2017年7月27日 下午4:50:39 
	 * @author hexw
	 */
	void addGoodsToShoppingCart(List<ShoppingCart> shoppingCarts,Long userId);
	
	/**
	 * 查询购物车 接口
	 * @param userId
	 * @return    
	 * @return List<AppShoppingCartDir>    
	 * Date:2017年7月27日 下午4:33:06 
	 * @author hexw
	 */
	List<AppShoppingCartDir> findShoppingCarts(long userId);

	
	/**
	 * 购物车删除商品
	 * @param userId
	 * @param gooId
	 * @return    
	 * @return int    
	 * Date:2017年7月27日 下午4:20:59 
	 * @author hexw
	 */
	void deleteShoppingCartGoods(long userId,  List<AppShoppingInCartDto> list);

	/**
	 * 添加商品到购物车 未登录
	 * @param shoppingCart
	 * @Description 
	 * @date 2017年8月4日  上午10:18:21
	 * @author wy
	 * 2017
	 * @return void
	 */
	//void addGoodsToShoppingCartNoLogin(ShoppingCart shoppingCart);
	
	/**
	 * 修改购物车商品数量
	 * @param goodsId
	 * @param userId
	 * @param num    
	 * @return void    
	 * Date:2017年8月15日 下午4:36:35 
	 * @author hexw
	 */
	void updateGoodsNum(long goodsId, long userId, Integer num);




	
	
}
  