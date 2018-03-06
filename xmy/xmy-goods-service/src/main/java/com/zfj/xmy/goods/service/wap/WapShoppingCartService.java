package com.zfj.xmy.goods.service.wap;

import com.zfj.xmy.goods.persistence.wap.pojo.dto.ShoppingGoodsUpdateDto;

public interface WapShoppingCartService {

	Integer updateNum(ShoppingGoodsUpdateDto dto);

}
