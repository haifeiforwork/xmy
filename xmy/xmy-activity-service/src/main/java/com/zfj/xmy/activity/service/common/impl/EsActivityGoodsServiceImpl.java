package com.zfj.xmy.activity.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.activity.service.common.EsActivityGoodsService;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;
@Service
public class EsActivityGoodsServiceImpl implements EsActivityGoodsService{
	
	@Autowired
	private GoodsDocService docService;
	
	@Override
	public void updateEsActivityGoods() {
		docService.buyAndPresentPutway();
		docService.limitActivityPutway();
		docService.promotionActivityPutway();
	}
	
}
