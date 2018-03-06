package com.zfj.xmy.user.service.wap;

import java.math.BigDecimal;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.user.persistence.wap.dto.ShoppingCartOutDto;

public interface WapShoppingCardService {
	/**
	 * 查用绑定的所有购物卡
	 * @param userId
	 * @return List<ShoppingCard>
	 * @author lij
	 * @date 2017年10月27日 下午5:08:35
	 */
	List<ShoppingCard> findShoppingCardByUserId(Long userId);
	
	void updateShoppingCard(Long id,Integer type,BigDecimal money);
	
	Integer findCountByUserId(Long userId);
	
}
