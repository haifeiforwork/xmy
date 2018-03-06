package com.zfj.xmy.activity.service.app;

import java.util.List;

import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

/**
 * @Title: PointGoodsService.java
 * @package com.zfj.xmy.activity.service.app
 * @author zhangh
 * @Date 2017年7月24日 下午4:44:41 
 *
 */
public interface PointsService {
	/**
	 * 积分/余额明细
	 * @param uid
	 * @return List<UserSpendPoints>
	 * Date:2017年7月24日 下午2:50:27 
	 */
	List<UserSpendPoints> findUserSpendPoints(Long uid,int type);
	
	/**
	 * 积分商城活动
	 * @return List<PointsActivity> 
	 * Date:2017年7月26日 下午2:08:27 
	 * @author zhangh
	 */
	List<PointsActivity> findPointsActivity();
	
	/**
	 * 积分商城活动商品查询
	 * @param type
	 * @return List<PointsGoodsDto>
	 * Date:2017年7月24日 下午4:50:27 
	 * @author zhangh
	 */
	List<PointsGoodsDto> findPointGoods(Long aid);

	/**
	 * 判断是否签到了
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年9月13日下午5:36:46
	 */
	boolean isSign(Long userId);

	/**
	 * 返回连续签到天数
	 * @Description 
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年9月13日下午5:58:24
	 */
	Integer signDays(Long userId);

	/**
	 * 签到
	 * @Description 
	 * @param userId
	 * @Author liuw
	 * @Date 2017年9月13日下午6:01:37
	 */
	Integer sign(Long userId);

	/**
	 *  weight取值(1 赚积分 2会员日 3神秘好礼 4 超值抢购 5专题狂欢)
	 *  会员中心（赚积分、会员日、神秘好礼、超值抢购、专题狂欢）详细页面
	 * @Description 
	 * @param termData
	 * @return
	 * @Author liuw
	 * @Date 2017年9月18日上午11:28:43
	 */
	TermData memberRight(TermData termData);
}
