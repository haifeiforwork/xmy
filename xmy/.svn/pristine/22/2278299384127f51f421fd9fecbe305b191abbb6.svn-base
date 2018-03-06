package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

/**
 * @Title: PointsService.java
 * @package com.zfj.xmy.activity.service.pc
 * @author zhangh
 * @Date 2017年7月31日 下午4:44:41 
 *
 */
public interface PcPointsService {
	/**
	 * 积分明细
	 * @param uid
	 * @return List<UserSpendPoints>
	 * Date:2017年7月31日 下午4:50:27 
	 */
	List<UserSpendPoints> findUserSpendPoints(Long uid);
	
	/**
	 * 积分明细分页
	 * @param uid
	 * Date:2017年7月31日 下午4:50:27 
	 */
	void findUserSpendPointsByPage(Long uid,PageBean pageBean);
	
	/**
	 * 用户信息
	 * @param uid
	 * @return UserInfo
	 * Date:2017年8月3日 上午9:50:27 
	 */
	UserInfo findUserinfo(Long uid);
}
