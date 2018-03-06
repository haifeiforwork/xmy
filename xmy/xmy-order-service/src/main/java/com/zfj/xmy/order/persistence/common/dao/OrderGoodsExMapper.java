package com.zfj.xmy.order.persistence.common.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.common.pojo.dto.LableDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderGoodsDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.ShoppingDto;
/**
 * 
 * @author ljie
 *6.16
 */
public interface OrderGoodsExMapper {
	/**
	 * @param criteriaParameter
	 * @param rowBounds
	 * @return List<OrderGoodsDto>
	 * @author lij
	 * @date 2017年7月19日 下午2:52:59
	 * 查询订单商品的扩展信息（分组查询，查询采购清单）
	 */
	public List<OrderGoodsDto> shoppingList(CriteriaParameter criteriaParameter,RowBounds bounds);
	
	/**
	 * @param criteriaParameter
	 * @param rowBounds
	 * @return List<OrderGoodsDto>
	 * @author lij
	 * @date 2017年7月19日 下午2:52:59
	 * 查询订单商品的扩展信息（分组查询，查询采购清单）
	 */
	public List<OrderGoodsDto> shoppingList(CriteriaParameter criteriaParameter);
	
	/**
	 * 查询赠品需采购的清单
	 * @param criteriaParameter
	 * @return    
	 * @return List<OrderGoodsDto>    
	 * Date:2017年10月25日 下午3:57:31 
	 * @author hexw
	 */
	public List<OrderGoodsDto> shoppingPresentList(CriteriaParameter criteriaParameter);
	
	int shoppingListCount(CriteriaParameter criteriaParameter);
	
	/**
	 * 查询采购商品对应的订单编号及数量
	 * @param criteriaParameter
	 * @return List<ShoppingDto>
	 * @author lij
	 * @date 2017年10月16日 下午5:25:01
	 */
	public List<ShoppingDto> shoppingDtoList(CriteriaParameter criteriaParameter);
	/**
	 * 查询采购商品的对应的订单信息
	 * @param criteriaParameter
	 * @return List<OrderGoodsDto>
	 * @author lij
	 * @date 2017年10月18日 下午2:12:46
	 */
	public List<OrderGoodsDto> findOrderGoodsDtos(CriteriaParameter criteriaParameter);
	/**
	 * 查询采购赠品的对应的订单信息
	 * @param criteriaParameter
	 * @return List<OrderGoodsDto>
	 * @author lij
	 * @date 2017年10月18日 下午2:12:46
	 */
	public List<OrderGoodsDto> findOrderGoodsParent(CriteriaParameter criteriaParameter);
	
	/**
	 * 查询导出标签的实体类
	 * @param criteriaParameter
	 * @return List<LableDto>
	 * @author lij
	 * @date 2017年10月18日 下午3:24:02
	 */
	public List<LableDto> findLableDto(CriteriaParameter criteriaParameter);
	
	/**
	 * 查询导出赠品标签的实体类
	 * @param criteriaParameter
	 * @return List<LableDto>
	 * @author lij
	 * @date 2017年10月18日 下午3:24:02
	 */
	public List<LableDto> findLableParentDto(CriteriaParameter criteriaParameter);
	
}