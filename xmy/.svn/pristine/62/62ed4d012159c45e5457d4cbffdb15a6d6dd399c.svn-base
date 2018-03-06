package com.zfj.xmy.activity.service.cms;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.LimitGoodsDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;

/** 
 * @Title: PointsActivityService.java 
 * @Package com.zfj.xmy.activity.service
 * @Description: 
 * @author zhangh
 * @date 2017年7月11日 下午4:26:21 
 */
public interface PointsActivityService {
	/**
	 * 查询商品
	 * @param idStr
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月11日 下午4:49:52
	 */
	List<Goods> findGoods(String idStr);
	/**
	 * 添加一个积分活动
	 * @param pointsActivity
	 * @return    
	 * @return Long    
	 * Date:2017年7月11日 下午4:26:38
	 */
	Long insertActivity(PointsActivity pointsActivity);
	/**
	 * 添加活动商品
	 * @param activityId
	 * @param pointsGoods    
	 * @return void    
	 * Date:2017年7月11日 下午5:31:51
	 */
	void addActivityGoods(String activityId,String pointsGoods);
	/**
	 * 查询活动列表
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年7月11日 下午7:13:52
	 */
	void selectActivityList(ReqData reqData, PageBean pageBean);
	/**
	 * 删除活动
	 * @param id
	 * @return    
	 * @return int    
	 * Date:2017年7月11日 下午7:54:36
	 */
	int delActivity(String id);
	/**
	 * 根据id查询积分活动
	 * @param id
	 * @return    
	 * @return PointsActivity    
	 * Date:2017年7月11日 下午8:31:34
	 */
	PointsActivity getPointsActivity(String id);
	/**
	 * 查询限时活动商品
	 * @param id
	 * @return    
	 * @return List<PointsGoodsDto>    
	 * Date:2017年7月12日 上午10:09:33
	 */
	List<PointsGoodsDto> selectPointsGoodsByActId(String id);
	/**
	 * 修改限时活动
	 * @param pointsActivity
	 * @return    
	 * @return long    
	 * Date:2017年7月12日 上午11:01:37
	 */
	long updateActivity(PointsActivity pointsActivity);
	
	/**
	 * 移除活动商品
	 * @param goodsId
	 * @param actId    
	 * @return void    
	 * Date:2017年7月12日 下午2:31:02
	 */
	void delActivityGoods(String goodsId, String actId);
	
}
