package com.zfj.xmy.goods.service.wap;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;

public interface WapShoppingCardService {
	/**
	 * 购物车激活明细
	 * @param userId
	 * @return
	 */
	List<ShoppingCard> activateDetail(Long userId);
	
}
