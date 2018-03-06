package com.zfj.xmy.order.service.pc;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCartDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCouponDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;

public interface ShoppingCartService {
	
	List<Goods> addShoppingCartCookie(Goods goods,List<Goods> list,HttpServletResponse response);
	
	void addShoppingCart(PcCartDto cartDto,HttpServletResponse response);
	
	void deleteShoppingCart(PcCartDto pcCartDto,HttpServletResponse response);
	/**
	 * @return List<PcGoodsDto>
	 * @author lij
	 * @date 2017年8月25日 下午4:10:36
	 * 查询未登录的购物车信息
	 */
	List<PcGoodsDto> findUnLoadShoppingCart(String goodsId);
	/**
	 *  void
	 * @author lij
	 * @date 2017年8月28日 下午3:02:06
	 * 清空全部购物车信息
	 */
	void deleteAllShoppingCart(HttpServletResponse response,String goodsId);
	
	/**
	 * 查询优惠券和订单商品 可以使用优惠券的商品
	 * @param goodsList
	 * @return    
	 * @return List<PcCouponDto>    
	 * Date:2017年10月10日 下午3:17:42 
	 * @author hexw
	 */
	List<PcCouponDto> findCoupon(List<PcGoodsDto> goodsList);
	
	/**
	 * 批量删除购物车商品
	 * @param goodsIds
	 * @param userId
	 * @param response    
	 * @return void    
	 * Date:2017年10月20日 下午4:22:59 
	 * @author hexw
	 */
	void deletGoods(String goodsIds, Long userId, HttpServletResponse response);
	
	/**
	 * 查找用户所选的商品
	 * @param goodsId
	 * @return
	 */
	List<PcGoodsDto> findChooseGoods(String goodsId);
	
}