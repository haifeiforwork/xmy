package com.zfj.xmy.goods.service.wap;

import java.util.List;

import com.zfj.xmy.goods.persistence.wap.pojo.dto.CategoryAmountVo;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsVo;

/**
 * @author cj
 *收藏夹商品的service
 */
public interface WapCollectionGoodsService {
	
	/**
	 * 根据用户id查询收藏夹商品
	 * 商品名称可以为空
	 */
	List<GoodsVo> getFavorite(Long id, String categoryName);
	
	/**
	 * 根据id查询收藏夹里所有分类及其数量
	 */
	
	List<CategoryAmountVo> getCategoryAndAmount(Long id);

	void delAndCheckCollectionGoods(Long userId, Long collectionId);
}
