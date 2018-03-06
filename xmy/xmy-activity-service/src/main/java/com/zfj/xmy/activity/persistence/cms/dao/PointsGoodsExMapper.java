package com.zfj.xmy.activity.persistence.cms.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.LimitGoodsDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;

/** 
 * @Title: PointsGoodsExMapper.java 
 * @Package com.zfj.xmy.activity.persistence.dao 
 * @Description: 
 * @author zhangh
 * @date 2017年7月12日 上午9:27:39 
 */
public interface PointsGoodsExMapper {
	
	/**
	 * 
	 * @return    
	 * @return List<PointsGoodsDto>    
	 * Date:2017年7月12日 上午9:43:10
	 */
	List<PointsGoodsDto> selectPointsGoods(CriteriaParameter parameter);
	
}
  