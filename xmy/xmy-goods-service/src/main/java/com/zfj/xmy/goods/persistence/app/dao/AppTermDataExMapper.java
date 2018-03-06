package com.zfj.xmy.goods.persistence.app.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppAdImageDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppEnterpriseDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsDto;

/** 
 * @Title: AppTermDataExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.app.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月21日 上午10:32:24 
 */
public interface AppTermDataExMapper {
	
	/**
	 * 查询所有的分类
	 * @param parameter
	 * @return    
	 * @return List<AppTermDataDir>    
	 * Date:2017年7月21日 上午10:40:34 
	 * @author hexw
	 */
	List<AppTermDataDir> findCategorys(CriteriaParameter parameter);
		
	
	List<AppEnterpriseDir> findCategory(CriteriaParameter parameter);
	
	/**
	 * @param parameter
	 * @return List<TermData>
	 * @author lij
	 * @date 2017年7月27日 下午2:17:56
	 * 根据商品多个商品id查询商品的二级分类名称
	 */
	List<TermData> findGoodsTypeName(CriteriaParameter parameter);
	
	/**
	 * 查询一级分类top广告图
	 * @param parameter
	 * @return    
	 * @return List<AppAdimageDir>    
	 * Date:2017年8月15日 下午8:44:01 
	 * @author hexw
	 */
	List<AppAdImageDir> findAdImage(CriteriaParameter parameter);
	/**
	 * @param parameter
	 * @return List<String>
	 * @author lij
	 * @date 2017年8月16日 下午2:37:14
	 * 筛选条件查询父级名称
	 */
	List<String> findTowName(CriteriaParameter parameter);
}
  