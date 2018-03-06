package com.zfj.xmy.order.service.app;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;

public interface CollectionGoodsService {

	/**
	 * 
	 * @Description 查询所有符合条件的记录
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月24日下午2:06:52
	 */
	List<CollectionGoods> findsAllCollectionGoods(ReqData reqData);

	/**
	 * 
	 * @Description 查找到收藏夹的所有商品
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月24日下午3:36:38
	 */
	List<GoodsWithBLOBs> findCollectionGoods(ReqData reqData);

	/**
	 * 
	 * @Description 根据单个收藏夹id，删除单条收藏夹记录
	 * @param id
	 * @Author liuw
	 * @Date 2017年7月25日上午9:39:17
	 */
	void delCollectionGoodsById(Long id);

	/**
	 * 
	 * @Description 插入单条收藏夹记录
	 * @param collectionGoods
	 * @Author liuw
	 * @Date 2017年7月25日上午9:43:16
	 */
	void addCollectionGoods(CollectionGoods collectionGoods);

	/**
	 * 
	 * @Description 检测并删除单条收藏夹记录（检测这个记录是不是本人的）
	 * @param userId 用户id
	 * @param collectionId 收藏夹单个记录的id
	 * @Author liuw
	 * @Date 2017年7月31日上午11:37:55
	 */
	void delAndCheckCollectionGoods(Long userId, Long collectionId);
	
}
