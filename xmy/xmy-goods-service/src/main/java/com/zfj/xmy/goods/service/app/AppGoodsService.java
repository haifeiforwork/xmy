package com.zfj.xmy.goods.service.app;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppCommentDto;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppActivityInfoDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppCollectVo;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsOut;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppIndexAdvertisementGoodsDto;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppOrderMethod;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppScreenInDto;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppScreenOutDto;


public interface AppGoodsService {
	
	/**
	 * 搜索商品评价
	 * @param goodsId
	 * @return    
	 * @return List<AppCommentDto>    
	 * Date:2017年7月26日 上午10:50:14 
	 * @author hexw
	 */
	List<AppCommentDto> findComment(long goodsId);

	
	/**
	 * @param name
	 * @param orderMethod 排序方式 1.新品上架 2.销量排序 3.评论数量排序 4.价格排序 
	 * @return List<Goods>
	 * @author liji
	 * @date 2017年7月26日 下午3:13:40
	 * 根据商品名称查询你商品信息
	 */
	List<AppGoodsOut> findGoodsByGoodsName(String name,Integer orderMethod,PageBean pageBean);
	
	/**
	 * @param name
	 * @param orderMethod 排序方式 1.新品上架 2.销量排序 3.评论数量排序 4.价格降序 5.价格升序
	 * @return List<Goods>
	 * @author liji
	 * @date 2017年7月26日 下午3:13:40
	 * 根据二级分类id商品信息
	 */
	void findGoodsByTypeId(Long id,Integer orderMethod,PageBean pageBean,Boolean priceOrder);
	/**
	 * 根据一级分类id商品信息
	 * @param id
	 * @param orderMethod
	 * @param pageIndex
	 * @return List<AppGoodsOut>
	 * @author lij
	 * @date 2017年11月20日 下午8:26:31
	 */
	void findGoodsByOneId(Long id,Integer orderMethod,PageBean pageBean,Boolean priceOrder);
	/**
	 * @param name
	 * @param orderMethod 排序方式 1.新品上架 2.销量排序 3.评论数量排序 4.价格降序 5.价格升序
	 * @return List<Goods>
	 * @author liji
	 * @date 2017年7月26日 下午3:13:40
	 * 根据分词id商品信息
	 */
	void findGoodsByParticipleId(Long id,Integer orderMethod,PageBean pageBean,Boolean priceOrder);
	

	/**
	 * 查询商品图片
	 * @param goodsId
	 * @return    
	 * @return List<String>    
	 * Date:2017年7月27日 下午4:30:45 
	 * @author hexw
	 */
	List<String> selectGoodsImage(long goodsId);
	

	/**
	 * @return List<AppScreenOutDto>
	 * @author lij
	 * @date 2017年7月27日 上午9:40:25
	 * 根据商品名称查询筛选条件
	 */
	List<AppScreenOutDto> findScreenListByName(String name);
	/**
	 * @param id
	 * @return List<AppScreenOutDto>
	 * @author lij
	 * @date 2017年8月17日 下午3:38:28
	 * 根据二级分类查询筛选条件
	 */
	List<AppScreenOutDto> findScreenListByTwo(Long id);
	/**
	 * 根据一级分类查询筛选条件
	 * @param id
	 * @return List<AppScreenOutDto>
	 * @author lij
	 * @date 2017年11月20日 下午8:48:47
	 */
	List<AppScreenOutDto> findScreenListByOne(Long id);
	/**
	 * @param id
	 * @return List<AppScreenOutDto>
	 * @author lij
	 * @date 2017年8月17日 下午3:39:42
	 * 根据分词Id查询筛选条件
	 */
	List<AppScreenOutDto> findScreenListByParticipleId(Long id);
	/**
	 * @return List<AppOrderMethod>
	 * @author lij
	 * @date 2017年8月17日 下午7:46:01
	 * 查询排序方式
	 */
	List<AppOrderMethod> findOrderMethod();
	
	
	/**
	 * @param appScreenInDto
	 * @return List<AppGoodsOut>
	 * @author lij
	 * @date 2017年7月27日 下午4:15:13
	 * 根据筛选条件查询商品
	 */
	void findGoodsByScreen(AppScreenInDto appScreenInDto,PageBean pageBean);

	/**
	 * 获取首页一级分类以及对应的广告，以及商品
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月9日下午5:41:23
	 */
	List<AppIndexAdvertisementGoodsDto> getAppIndexAdvertisementGoodsDto();

	/**
	 * 获取商品详细信息
	 * @param goodsid
	 * @param activityId
	 * @param activityType
	 * @return    
	 * @return AppGoodsDir    
	 * Date:2017年8月19日 下午2:56:30 
	 * @author hexw
	 */
	AppGoodsDir getGoodsDetails(long goodsid,long activityId,Integer activityType,Long userId);

	/**
	 * 收藏商品
	 * @param vo    
	 * @return void    
	 * Date:2017年8月24日 下午2:49:46 
	 * @author hexw
	 */
	void updateCollect(AppCollectVo vo, Long userId);


	/**
	 * 取得活动详细信息的
	 * @Description 
	 * @param activityId 活动id
	 * @param goodsId 商品id
	 * @param activityType 活动类别
	 * @return
	 * @Author liuw
	 * @Date 2017年9月1日下午2:54:26
	 */
	AppActivityInfoDir getActivityInfo(long activityId, long goodsId,
			Integer activityType);

}
