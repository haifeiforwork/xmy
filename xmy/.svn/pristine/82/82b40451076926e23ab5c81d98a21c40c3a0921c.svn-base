package com.zfj.xmy.goods.service.pc;  

import java.util.List;
import java.util.Map;

import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcCategoryDir;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcCategoryGoodsDir;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;

/** 
 * 
 * 商品分类
 * @Title: PcCategoryServcie.java 
 * @Package com.zfj.xmy.goods.service.pc 
 * @Description: 
 * @author hexw
 * @date 2017年8月10日 下午4:40:54 
 */
public interface PcCategoryService {
	
	/**
	 * 查询二级分类商品
	 * @param parentId
	 * @return    
	 * @return List<CategoryGoodsDir>    
	 * Date:2017年8月10日 下午8:46:35 
	 * @author hexw
	 */
	List<PcCategoryGoodsDir> findCategoryGoods(long parentId);
	
	/**
	 * 一级分类下面的二级菜单
	 * @param parentId
	 * @return    
	 * @return PcCategoryDir    
	 * Date:2017年8月14日 下午5:21:14 
	 * @author hexw
	 */
	PcCategoryDir findCategoryMenu(long parentId);
	
	/**
	 * 鲜一下 （一级分类销量前20的商品）
	 * @param categoryId
	 * @return    
	 * @return List<PcGoodsDir>    
	 * Date:2017年8月25日 上午10:29:44 
	 * @author hexw
	 */
	List<PcGoodsDir> findFreshGoods(long categoryId);
	
	/**
	 * 查询一级对应分类的货柜及top图
	 * @param categoryId
	 * @return    
	 * @return Map<Object,String>    
	 * Date:2017年9月4日 下午2:13:14 
	 * @author hexw
	 */
	Map<String, Object> findSecondCategoryContainer(long categoryId);
	
	/**
	 * 查询一级分类top图
	 * @param id
	 * @return
	 */
	List<AdImage> findAdimageById(long id);
	
	/**
	 * 查询积分商城广告
	 * @param name
	 * @return
	 */
	List<AdImage> findPointsAd(String name);
	/**
	 * 根据一级分类查询下面的商品名称
	 * @param id
	 * @param pageIndex
	 * @return List<Goods>
	 * @author	lij
	 * @date 2017年10月24日 下午9:20:23
	 */
	List<Goods> findChangeGoods(Long id,Integer pageIndex);

}
  