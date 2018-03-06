package com.zfj.xmy.goods.service.wap.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.goods.persistence.wap.dao.WapShoppingCartExMapper;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.ShoppingGoodsUpdateDto;
import com.zfj.xmy.goods.service.wap.WapShoppingCartService;

@Service
public class WapShoppingCartServiceImpl implements WapShoppingCartService{
	
	@Autowired
	private ShoppingCartMapper cartMapper;
	
	@Autowired
	private WapShoppingCartExMapper cartExMapper;
	
	@Override
	public Integer updateNum(ShoppingGoodsUpdateDto dto) {
		return cartExMapper.updateGoodsNum(dto);
	}
	
}
