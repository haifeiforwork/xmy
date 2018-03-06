package com.zfj.xmy.goods.persistence.pc.dao;  

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcCategoryGoodsDir;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;

/** 
 * @Title: PcCategoryGoodsExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.pc.dao 
 * @Description: 
 * @author hexw
 * @date 2017年8月10日 下午6:29:47 
 */
public interface PcCategoryGoodsExMapper {
	
	/**
	 * 根据父级id查询二级分类
	 * @param parameter
	 * @return    
	 * @return List<PcCategoryDir>    
	 * Date:2017年8月11日 下午2:21:35 
	 * @author hexw
	 */
	List<PcCategoryGoodsDir> findCategorys(CriteriaParameter parameter);
	
	/**
	 * 查询商品
	 * @param parameter
	 * @return    
	 * @return List<PcGoodsDir>    
	 * Date:2017年8月11日 下午2:37:43 
	 * @author hexw
	 */
	List<PcGoodsDir> findGoods(CriteriaParameter parameter,RowBounds rowBounds);
	
	/**
	 * 根据商品多个商品id查询商品
	 * @param parameter
	 * @param rowBounds
	 * @return    
	 * @return List<PcGoodsDir>    
	 * Date:2017年9月4日 下午2:01:11 
	 * @author hexw
	 */
	List<PcGoodsDir> findGoodsByIds(CriteriaParameter parameter,RowBounds rowBounds);
	
}
  