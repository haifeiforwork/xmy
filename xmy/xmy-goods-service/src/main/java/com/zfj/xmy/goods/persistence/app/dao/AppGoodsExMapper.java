package com.zfj.xmy.goods.persistence.app.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;

/** 
 * @Title: AppGoodsExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.app.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月27日 上午10:59:19 
 */
public interface AppGoodsExMapper {
	
	
	/**
	 * 商品详情查询
	 * @return    
	 * @return AppGoodsDir    
	 * Date:2017年7月27日 上午11:22:16 
	 * @author hexw
	 */
	AppGoodsDir selectGoods(CriteriaParameter paramter);
	
	
	List<GoodsImage> selectGoodsImage(CriteriaParameter paramter);
	
	/**
	 * 查询活动商品最大编码
	 * @return    
	 * @return String    
	 * Date:2017年11月7日 上午11:39:51 
	 * @author hexw
	 */
	String findActicityGoodsMaxCode();
	
	
	/**
	 * 查询商品一级分类的描述图
	 * @param paramter
	 * @return    
	 * @return String    
	 * Date:2017年12月16日 上午10:56:44 
	 * @author hexw
	 */
	String findGoodsCategoryDescImg(CriteriaParameter paramter);
	
	
}
  