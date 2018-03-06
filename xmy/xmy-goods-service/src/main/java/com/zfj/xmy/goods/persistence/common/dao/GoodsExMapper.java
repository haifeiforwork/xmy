package com.zfj.xmy.goods.persistence.common.dao;  

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsDto;

/** 
 * @Title: GoodsExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.dao 
 * @Description: 
 * @author hexw
 * @date 2017年6月15日 下午1:40:44 
 */
public interface GoodsExMapper {
	
	/**
	 * 
	 * @param parameter
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月25日 下午8:41:03 
	 * @author hexw
	 */
	public List<Goods> findGoods(CriteriaParameter parameter);
	
	
	
}
  