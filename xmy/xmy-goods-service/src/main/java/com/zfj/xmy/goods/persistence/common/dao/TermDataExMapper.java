package com.zfj.xmy.goods.persistence.common.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;

/** 
 * @Title: TermDataExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.dao 
 * @Description: 
 * @author hexw
 * @date 2017年6月15日 上午10:59:22 
 */
public interface TermDataExMapper {
	
	List<TermDataDir> findCategory(CriteriaParameter parameter);
	
	
	//List<TermDataDto> findCategoryList(CriteriaParameter parameter,RowBounds rowBounds);
	
	
	/**
	 * 查询类别类别
	 * @return    
	 * @return List<TermDataDto>    
	 * Date:2017年6月20日 下午2:07:36
	 */
	List<TermDataDir> findCategoryList(CriteriaParameter parameter);
	
	/**
	 * 
	 * @Description 根据goods 的分类id集合，获取到一级分类
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月24日下午5:13:05
	 */
	List<TermData> getCategoryByGoodsCategoryIds(Long id);
	
}
  