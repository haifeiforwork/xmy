package com.zfj.xmy.config.persistence.dao;  

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.config.persistence.pojo.LimitActivityGoodsDir;

/** 
 * @Title: CommonLimitActivityExMapper.java 
 * @Package com.zfj.xmy.config.persistence.dao 
 * @Description: 
 * @author hexw
 * @date 2017年10月14日 下午3:02:59 
 */
public interface CommonLimitActivityExMapper {
	
	
	/**
	 * 查询当日可抢购的商品
	 * @param parameter
	 * @return    
	 * @return List<AppLimitActivityGoodsDir>    
	 * Date:2017年7月31日 下午1:51:23 
	 * @author hexw
	 */
	List<LimitActivityGoodsDir> findActivityGoods(CriteriaParameter parameter);
	
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
	List<Integer> countLimitAcivityGoodsNum(CriteriaParameter parameter);
	
	/**
	 * 购买改天的活动商品个数
	 * @param parameter
	 * @return    
	 * @return Integer    
	 * Date:2017年10月14日 下午4:34:26 
	 * @author hexw
	 */
	Integer countLimitAcivityGoods(CriteriaParameter parameter);
	
	
	
	/**
	 * 查询上线活动商品 （不包含企业定制,和跨境专区的商品）
	 * @param parameter
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年11月12日 下午7:15:06 
	 * @author hexw
	 */
	 List<Goods> findOnlineActivityGoods(CriteriaParameter parameter);
	 
	 /**
	  * 通过ID查询商品是否在活动范围内
	  * @Description 
	  * @param id
	  * @return
	  * @Author liuw
	  * @Date 2017年11月10日上午5:34:00
	  */
	 Goods findOnlineActivityGoodsByGoodsId(@Param("id") Long id);
	 
	 /**
	  * 检查用户是否购买过该商品
	  * @param parameter
	  * @return    
	  * @return Integer    
	  * Date:2018年1月3日 下午3:01:50 
	  * @author hexw
	  */
	 Integer countOrderGoods(CriteriaParameter parameter);
}
  