package com.zfj.xmy.activity.service.cms;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.PointTactics;

public interface PointTacticsService {
	/**
	 * 根据ID查询PointTactics
	 * @param reqData
	 * @return
	 * @Date 2017年7月3日上午11:16:24
	 */
	PointTactics findPointTactics(long id);
	/**
	 * 新增策略
	 * @param pointTactics    
	 * @return void    
	 * Date:2017年7月4日 下午4:39:29
	 */
	void insertPointTactics(PointTactics pointTactics);

	/**
	 * 查询策略 分页
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年7月4日 下午4:59:01
	 */
	void selectPointTacticsAndPage(ReqData reqData, PageBean pageBean);
	
	/**
	 * 删除策略
	 * @param id
	 * @return    
	 * @return int    
	 * Date:2017年7月4日 下午4:39:29
	 */
	int deletePointTactics(long id);
	
	/**
	 * 更新策略
	 * @param id
	 * @return    
	 * @return void    
	 * Date:2017年7月5日 下午2:39:29
	 */
	void update(PointTactics pointTactics);
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return    
	 * @return int    
	 * Date:2017年7月5日 下午8:14:09
	 */
	int updateStatus(long id, int status);
}
