package com.zfj.xmy.order.service.wap;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

public interface WapUserSpendPointsService {
	/**
	 * 
	 * @param reqData
	 * @return List<UserSpendPoints>
	 * @author lijie
	 * @date 2017年7月3日 下午2:27:59
	 * 根据 条件查询订单
	 */
	List<UserSpendPoints> findUserSpendPoints(ReqData reqData);
	
	//根据条件查询单个记录
	UserSpendPoints getUserSpendPoints(ReqData reqData);
	
	//修改单个记录
	int updateUserSpendPoints(UserSpendPoints userSpendPoints,ReqData reqData);
	/**
	 * 
	 * @param reqData
	 * @param pageBean
	 * @return List<UserSpendPoints>
	 * @author lijie
	 * @date 2017年7月3日 下午4:39:18
	 * 根据条件分页查询记录
	 */
	List<UserSpendPoints> findSpendPoints(ReqData reqData,PageBean pageBean);
	/**
	 * 
	 * @param points void
	 * @author lijie
	 * @date 2017年7月3日 下午4:58:18
	 * 添加新记录到用户消费记录
	 */
	void saveSpendPoints(UserSpendPoints points); 
	
	/**
	 * 
	 * @Description 返回查找到的总消费数（总积分数）
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月4日下午5:22:11
	 */
	int findTotalCost(ReqData reqData);
}
