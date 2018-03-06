package com.zfj.xmy.activity.persistence.cms.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.ShoppingCardDto;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;

public interface ShoppingCardExMapper {
	/**
	 * 查询全部购物卡
	 * @param parameter
	 * @param rowBounds
	 * @return List<ShoppingCardDto>
	 * @author lij
	 * @date 2017年10月31日 下午5:24:54
	 */
	List<ShoppingCardDto> findShoppingCard(CriteriaParameter parameter,RowBounds rowBounds);
	/**
	 * 查询总条数
	 * @param parameter
	 * @return Integer
	 * @author lij
	 * @date 2017年10月31日 下午7:49:23
	 */
	Integer countTotalShoppingCard(CriteriaParameter parameter);
	
	/**
	 * 批量添加购物卡
	 * @param shoppingCards
	 * @return
	 * @Description 
	 * @date 2017年12月14日  下午5:28:28
	 * @author wy
	 * 2017
	 * @return int
	 */
	int insertBatch(List<ShoppingCard> shoppingCards);
}
