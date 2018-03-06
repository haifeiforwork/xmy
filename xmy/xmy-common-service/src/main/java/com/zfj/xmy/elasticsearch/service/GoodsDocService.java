package com.zfj.xmy.elasticsearch.service;

import java.util.List;

import com.zfj.xmy.elasticsearch.document.GoodsDoc;

public interface GoodsDocService extends BaseSearchService<GoodsDoc,Long> {
	//修改单个
	int updateGoods(Long id);
	//添加单个上架商品
	int addGoods(Long id);
	//删除单个下架商品
	int deleteGoods(Long id);
	/**
	 * 批量添加到es的已上架的商品 
	 * @return int
	 * @author lij
	 * @date 2017年10月25日 下午3:23:35
	 */
	int putwayGoods();
	
	public List<GoodsDoc> queryAllGoods();
	/**
	 * 活动商品添加搜索
	 * @return int
	 * @author lij
	 * @date 2017年12月8日 上午10:20:39
	 */
	int putwayActivtyGoods();
	/**
	 * 买即赠活动上架
	 *  void
	 * @author lij
	 * @date 2017年12月8日 下午3:48:23
	 */
	void buyAndPresentPutway();
	/**
	 * 专题活动上下架
	 *  void
	 * @author lij
	 * @date 2017年12月8日 下午3:57:46
	 */
	void promotionActivityPutway();
	/**
	 * 限时活动上下架
	 *  void
	 * @author lij
	 * @date 2017年12月8日 下午4:01:49
	 */
	void limitActivityPutway();
}	
