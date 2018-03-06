package com.zfj.xmy.goods.persistence.common.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDir;

/** 
 * @Title: CategoryWordSegExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.common.dao 
 * @Description: 
 * @author hexw
 * @date 2017年8月9日 下午2:17:01 
 */
public interface CategoryWordSegExMapper {
	
	/**
	 * 查询规格
	 * @param parameter
	 * @return    
	 * @return List<CategoryWordSegDir>    
	 * Date:2017年8月9日 下午2:20:31 
	 * @author hexw
	 */
	List<CategoryWordSegDir> findCategoryWordSeg(CriteriaParameter parameter);
	
	/**
	 * 查询商品搜索词汇
	 * @param parameter
	 * @return    
	 * @return List<String>    
	 * Date:2017年8月10日 上午10:24:35 
	 * @author hexw
	 */
	List<String> findGoodsSpec(CriteriaParameter parameter);
}
  