package com.zfj.xmy.activity.service.cms;  

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.LimitGoodsDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;

/** 
 * @Title: LimitActivityService.java 
 * @Package com.zfj.xmy.activity.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年7月5日 下午8:26:21 
 */
public interface CmsLimitActivityService {
	
	/**
	 * 查询商品
	 * @param idStr
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月5日 下午8:49:52
	 */
	List<Goods> findGoods(String idStr);
	
	/**
	 * 添加一个限时活动
	 * @param limitActivity
	 * @return    
	 * @return Long    
	 * Date:2017年7月6日 下午2:26:38
	 */
	void insertActivity(LimitActivity limitActivity, String[] price,
			String[] goodsId, String[] limitNum, String[] allNum,
			String[] completeNum,String[] activityTime);

	/**
	 * 查询活动列表
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年7月6日 下午7:13:52
	 */
	void selectActivityList(ReqData reqData, PageBean pageBean);
	
	/**
	 * 删除活动
	 * @param id
	 * @return    
	 * @return int    
	 * Date:2017年7月6日 下午7:54:36
	 */
	int delActivity(String id);
	
	/**
	 * 根据id查询限时活动
	 * @param id
	 * @return    
	 * @return LimitActivity    
	 * Date:2017年7月6日 下午8:31:34
	 */
	LimitActivity getLimitActivity(String id);
	
	/**
	 * 查询限时活动商品
	 * @param id
	 * @return    
	 * @return List<LimitGoodsDto>    
	 * Date:2017年7月7日 上午10:09:33
	 */
	List<LimitGoodsDto> selectLimitGoodsByActId(String id);
	
	/**
	 * 修改限时活动
	 * @param limitActivity
	 * @return    
	 * @return long    
	 * Date:2017年7月7日 上午11:01:37
	 */
	void updateActivity(LimitActivity limitActivity,String[] goodsId,
			String[] price, String[] limitNum, String[] allNum, String[] completeNum,String[] activityTime);
	
	/**
	 * 移除活动商品
	 * @param goodsId
	 * @param actId    
	 * @return void    
	 * Date:2017年7月7日 下午2:31:02
	 */
	void delActivityGoods(String goodsId, String actId);
	
	
}
  