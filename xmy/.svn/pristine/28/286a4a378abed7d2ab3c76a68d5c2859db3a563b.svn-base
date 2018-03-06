package com.zfj.xmy.order.service.app;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.BrowsedGoods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.order.persistence.app.pojo.dto.BrowsedGoodsOutDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.GuessLikeGoodsOutVO;

public interface BrowsedGoodsService {

	/**
	 * 
	 * @Description 根据条件，查询所有的浏览记录
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月25日上午11:10:25
	 */
	List<BrowsedGoods> findBrowsedGoods(ReqData reqData);

	/**
	 * 
	 * @Description 插入单条足迹记录
	 * @param browsedGoods
	 * @Author liuw
	 * @Date 2017年7月25日上午11:17:17
	 */
	void insertBrowsedGoods(BrowsedGoods browsedGoods);

	/**
	 * 
	 * @Description 
	 * @param findBrowsedGoods 根据足迹列表。在goos表中，查找对应的商品
	 * @return
	 * @Author liuw
	 * @Date 2017年7月25日上午11:28:20
	 */
	List<GoodsWithBLOBs> findBrowsedGoodsWithGoods(	List<BrowsedGoods> findBrowsedGoods);
  /**
   * 
   * @Description 根据传入的id集合，删除对应的足迹记录
   * @param id
   * @Author liuw
   * @Date 2017年7月25日上午11:38:26
   */
	void delBrowsedGoods(Object ...id);

	/***
	 * 
	 * @Description 根据足迹列表，查询并转换成足迹dto扩展类列表
	 * @param findBrowsedGoods
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日下午4:54:19
	 */
	List<BrowsedGoodsOutDto> browsedGoodsToBrowsedGoodsDto(	List<BrowsedGoods> findBrowsedGoods);

	/**
	 * 查找猜你喜欢
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月2日下午3:47:38
	 */
	List<GuessLikeGoodsOutVO> findGuessLikeGoods(Long userId);

	/**
	 * 商品推荐，可以查询本商品的同类商品
	 * @Description 
	 * @param id 需查询的同类商品的id
	 * @return
	 * @Author liuw
	 * @Date 2017年8月24日上午11:01:16
	 */
	List<GuessLikeGoodsOutVO> goodsRecommend(Long id);

	
	/**
	 * 会员推荐
	 * @return    
	 * @return List<AdImage>    
	 * Date:2017年10月21日 下午7:04:05 
	 * @author hexw
	 */
	List<AdImage> findVipRecommend();

}
