package com.zfj.xmy.activity.persistence.common.dao;  

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityDir;
import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityGoodsDir;

/** 
 * @Title: AppLimitActivityExMapper.java 
 * @Package com.zfj.xmy.activity.persistence.app.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 上午11:03:36 
 */
public interface LimitActivityExMapper {

	/**
	 * 查询限时限量活动
	 * @param parameter
	 * @return    
	 * @return List<AppLimitActivityDir>    
	 * Date:2017年7月31日 上午11:24:21 
	 * @author hexw
	 */
	List<LimitActivityDir> findList(CriteriaParameter parameter);
	
	/**
	 * 查询活动商品
	 * @param parameter
	 * @return    
	 * @return List<AppLimitActivityGoodsDir>    
	 * Date:2017年7月31日 下午1:51:23 
	 * @author hexw
	 */
	List<LimitActivityGoodsDir> findActivityGoods(CriteriaParameter parameter,RowBounds rowBounds);

	/**
	 * 统计用户参加同一个活动的次数
	 * @param parameter
	 * @return    
	 * @return Integer    
	 * Date:2017年10月13日 下午5:57:56 
	 * @author hexw
	 */
	Integer countLimitAcivity(CriteriaParameter parameter);
	
	
	/**
	 * 统计用户购买同一个活动商品数量
	 * @param parameter
	 * @return    
	 * @return Integer    
	 * Date:2017年10月14日 上午10:26:21 
	 * @author hexw
	 */
	List<Integer> countLimitAcivityGoods(CriteriaParameter parameter);
}
  