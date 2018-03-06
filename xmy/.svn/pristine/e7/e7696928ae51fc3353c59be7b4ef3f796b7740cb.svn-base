package com.zfj.xmy.common.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;

/**
 * @author lij
 */
public interface CommonGoodsService {
	/**
	 * @param orderId void
	 * @author lij
	 * @date 2017年7月26日 上午11:26:33
	 * 根据订单id修改商品的商品成交量
	 */
	void updateGoodsSumDealByOrderId(long orderId);
	/**
	 * @param goodsId void
	 * @author lij
	 * @date 2017年7月26日 上午11:28:13
	 * 根据商品id修改商品的平论数量
	 */
	void updateGoodsSumCommentByGoodsId(long goodsId);
	
	/**
	 * 获取商品的第一张图片
	 * @param goodsId
	 * @return    
	 * @return String    
	 * Date:2017年8月14日 下午3:11:12 
	 * @author hexw
	 */
	String getFirstGoodsImg(long goodsId);
	
	/**
	 * 根据多个id查询商品
	 * @param ids
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年8月21日 下午2:18:07 
	 * @author hexw
	 */
	List<Goods> findsGoodsWithIds(List<Object> ids);
	
	/**
	 * 查询企业定制商品第一张图片
	 * @param goodsId
	 * @return    
	 * @return String    
	 * Date:2017年9月29日 下午3:47:28 
	 * @author hexw
	 */
	String getCustomizationImage(long goodsId);
	
	/**
	 * 获取打折商品的总价格 （不包含跨境专区和企业定制的商品）
	 * @param goodsList
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月12日 下午7:41:11 
	 * @author hexw
	 */
	BigDecimal getDiscountSumPrice(List<Object> ids,Long userId,Map<Long, Integer> map);
	
	/**
	 * 获取打折商品的总价格 （不包含跨境专区和企业定制的商品）
	 * @param goodsList
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月12日 下午7:41:11 
	 * @author hexw
	 */
	BigDecimal getDiscountSumPrice(List<Object> ids,Long userId);
	
	/**
	 * 查询企业定制商品第二张图片（wap）
	 * @param goodsId
	 * @return
	 */
	String getCustomizationImageWap(long goodsId);
	
	/**
	 * 商品第二张图片（wap）
	 * @param goodsId
	 * @return
	 */
	String getSecondGoodsImg(long goodsId);
	
	/**
	 * 计算购物车商品总金额
	 * @param ids
	 * @param userId
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月13日 上午2:09:08 
	 * @author hexw
	 */
	BigDecimal getShoppingCartAllGoodsPrice(List<Object> ids, Long userId);
	
	/**
	 * 获取订单商品总金额
	 * @param orderId
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月13日 上午2:53:02 
	 * @author hexw
	 */
	BigDecimal getOrderGoodsSumPrice(Long orderId);
	
	/**
	 * 上线活动  获取订单商品总金额 排除跨境专区和企业定制商品
	 * @param orderId
	 * @param ids
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月13日 上午3:50:03 
	 * @author hexw
	 */
	BigDecimal getOnlineOrderGoodsSumPrice(Long orderId, List<Object> ids);
	
	boolean findOnlineActivityGoods(Long id);
	/**
	 * 根据商品ID查询商品编码
	 * @param id
	 * @return String
	 * @author lij
	 * @date 2017年11月13日 下午4:14:40
	 */
	String findGoodsNo(Long id);
	
}	
