package com.zfj.xmy.common.service;  

import com.appdev.db.common.pojo.PageBean;

/** 
 * @Title: CommonCollectionGoodsService.java 
 * @Package com.zfj.xmy.common.service 
 * @Description: 
 * @date 2017年12月1日 上午11:40:38 
 */
public interface CommonCollectionGoodsService {
	
	
	void findGoodsCollectPage(Long id, PageBean pageBean, String category,
			String goodsName);

}
  