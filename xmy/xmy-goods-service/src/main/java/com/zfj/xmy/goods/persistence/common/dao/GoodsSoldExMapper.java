package com.zfj.xmy.goods.persistence.common.dao;  

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsSoldDir;

/** 
 * @Title: AppGoodsSoldDtoMapper.java 
 * @Package com.zfj.xmy.goods.persistence.app.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月24日 下午7:29:47 
 */
public interface GoodsSoldExMapper {
	
	/**
	 * 查询 销量表里一级分类下销量前二十的商品
	 * @param id
	 * @return    
	 * @return List<AppGoodsSoldDto>    
	 * Date:2017年7月25日 上午9:31:06 
	 * @author hexw
	 */
	List<GoodsSoldDir> findGoodsSolad(long id);
	
	
	
	/**
	 * 查询商品表前 num 条数据
	 * @param parentId
	 * @param idStr
	 * @param num
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月27日 下午7:59:04 
	 * @author hexw
	 */
	List<Goods> findRemainGoodsSold(CriteriaParameter parameter,RowBounds rowBounds);
 	
}
  