package com.zfj.xmy.common.service.impl;  

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.dao.PointsGoodsMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.config.persistence.dao.CommonLimitActivityExMapper;
import com.zfj.xmy.config.persistence.pojo.LimitActivityGoodsDir;

/** 
 * @Title: CommonLimityActivityService.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年10月14日 下午2:55:14 
 */
@Service
public class CommonLimitActivityServiceImpl extends BaseService implements  CommonLimitActivityService{

	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private CommonLimitActivityExMapper commonLimitActivityExMapper;
	
	@Autowired
	private PointsGoodsMapper pointsGoodsMapper;
	
	@Autowired
	private BuyAndPresentMapper buyAndPresentMapper;
	
	
	@Override
	public int checkLimitActivity(ShoppingCart shoppingCart){
		int result = 0;
		if (ObjectUtil.isNotNull(shoppingCart.getActivityId()) && shoppingCart.getActivityId() !=0 ) {
			LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(shoppingCart.getActivityId());
			if (ObjectUtil.isNull(limitActivity)) {
				throw new BusinessException("未查询到活动");
			}
			//1. 查询出该活动今天可购买的商品
			ReqData  goodsReqData = new ReqData();
			goodsReqData.putValue("a.limit_id", limitActivity.getId(), SystemConstant.REQ_PARAMETER_EQ);
			switch (limitActivity.getType()) {
				case SystemConstant.LIMITACTIVITY.FREEZING : 
					//冰点价
					if (new Date().getTime() > DateUtils.getTwelveOfToday().getTime()){ //当当前时间大于12点 就取今天的活动时间  否则取昨天的
						goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getDayBegin(),"yyyy-MM-dd HH:mm:ss"),  SystemConstant.REQ_PARAMETER_GE);
						goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getDayEnd(),"yyyy-MM-dd HH:mm:ss"),  SystemConstant.REQ_PARAMETER_LE);
					} else {
						goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getBeginDayOfYesterday(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GE);
						goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getEndDayOfYesterDay(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_LE);
					}
					break;
				case SystemConstant.LIMITACTIVITY.DAYDAY: 							
					//天天特价
					goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getDayBegin(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GE);
					goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getDayEnd(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_LE);
					break;
				case SystemConstant.LIMITACTIVITY.WEEKWEEK:
					//每周特价
					goodsReqData.putValue("activity_time", DateUtil.format(DateUtils.getBeginDayOfWeek(), "yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GE);
					goodsReqData.putValue("activity_time",  DateUtil.format(DateUtils.getEndDayOfWeek(),"yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_LE);
					break;
				default :
					break;
			}
			List<LimitActivityGoodsDir> goodsList = commonLimitActivityExMapper.findActivityGoods(ReqUtil.reqParameterToCriteriaParameter(goodsReqData));
			// 2.查询该用户是否已购买过可抢购的商品
			int limitNum = 0;
			for (LimitActivityGoodsDir limitActivityGoodsDir : goodsList) { 
				ReqData reqData = new  ReqData();
				reqData.putValue("a.user_id", shoppingCart.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("b.activity_id", shoppingCart.getActivityId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("b.goods_id", limitActivityGoodsDir.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("a.is_del", SystemConstant.ORDER.IS_DEL_NOT_DELETE,SystemConstant.REQ_PARAMETER_EQ);
				if (commonLimitActivityExMapper.countLimitAcivityGoods(ReqUtil.reqParameterToCriteriaParameter(reqData)) != 0) { //查询订单是否已购买商品
					limitNum = limitNum+1;
				}
				reqData.clearValue();
				reqData.putValue("user_id", shoppingCart.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("activity_id", shoppingCart.getActivityId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", limitActivityGoodsDir.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
				if (shoppingCartMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)) >0 ) { //查询购物车是否已添加该商品
					limitNum = limitNum+1;
				}
			}
			if(!ObjectUtils.isEmpty(limitActivity.getLimitNum())){
				if (limitNum >= limitActivity.getLimitNum() ) {
					result = 1; //用户购买商品 超过了规定的商品
				}
				//3. 判断用户购买单个商品的数量是否超过规定的限购数量
				LimitGoods limitGoods = findLimitGoods(shoppingCart.getActivityId(),shoppingCart.getGoodsId());
				if (ObjectUtil.isNotNull(limitGoods)) {
					if (shoppingCart.getNum() > limitGoods.getLimitNum() || shoppingCart.getNum() >limitGoods.getAllNum()- limitGoods.getCompleteNum()) {
						result = 2;  //用户购买数量大于了活动商品的限定数量
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public LimitGoods findLimitGoods(Long activityId,Long goodsId) {
		ReqData actGoodsReqData =new ReqData();
		actGoodsReqData.putValue("limit_id", activityId,SystemConstant.REQ_PARAMETER_EQ);
		actGoodsReqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(actGoodsReqData));
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public int checkLimitGoods(Long userId,Long activityId,Long goodsId,Integer num){
		int result = 0;
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(activityId);
		if (ObjectUtil.isNotNull(limitActivity)) {
			ReqData reqData = new ReqData();
			reqData.putValue("a.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("b.activity_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
			int orderNum = commonLimitActivityExMapper.countLimitAcivity(ReqUtil.reqParameterToCriteriaParameter(reqData)); //已生成到订单的活动
			reqData.clearValue();
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("activity_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
			int cartNum = shoppingCartMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			 //判断用户参与该活动的次数是否大于活动限定的次数
			if ((orderNum+cartNum) >= limitActivity.getLimitNum()) {
				result = 1; //用户已超过了活动限定次数
			} else { //判断用户购买的活动商品数量是否大于限购商品数量
				reqData.clearValue();
				LimitGoods limitGoods = null;
				//查询活动商品记录
				reqData.putValue("limit_id", activityId,SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
				List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (list.size() > 0 ){
					limitGoods = list.get(0);
				}
				//统计用户购买活动商品的个数
				if (num > limitGoods.getLimitNum()) {
					result = 2;  //用户购买数量大于了活动商品的限定数量
				}
			}
		} else {
			throw new BusinessException("未查询到活动");
		}
		return result;
	}
	
	/**
	 * 修改活动商品完成量
	 * @param activityId
	 * @param goodsId
	 * @param num  商品数量
	 * @return void    
	 * Date:2017年10月14日 上午11:43:10 
	 * @author hexw
	 */
	@Override
	public void updateActivityGoodsComplNum(Long activityId,Long goodsId,Integer num){
		ReqData reqData = new ReqData();
		reqData.putValue("limit_id", activityId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		LimitGoods limitGoods = null;
		if (list.size() > 0 ){
			limitGoods = list.get(0);
			limitGoods.setCompleteNum(limitGoods.getCompleteNum()+num);
			limitGoodsMapper.updateByPrimaryKeySelective(limitGoods);
		}
	}
	
	@Override
	public void updatePointsGoodsComplNum(Integer pointsGoodsId,Integer num){
		PointsGoods pointsGoods = pointsGoodsMapper.selectByPrimaryKey(pointsGoodsId.longValue());
		if (ObjectUtil.isNotNull(pointsGoods)) {
			pointsGoods.setCompleteNum(pointsGoods.getCompleteNum() + num);
			pointsGoodsMapper.updateByPrimaryKey(pointsGoods);
		}
	}
	
	
	/**
	 * 检查活动商品是否过期
	 * @param activityId
	 * @param goodsId
	 * @param activityType
	 * @return    
	 * @return int    
	 * Date:2017年11月3日 下午1:37:36 
	 * @author hexw
	 */
	public int checkActivityGoodsIsLate(Long activityId,Long goodsId,Integer activityType){
		int result = 0;
		switch (activityType) {
			case SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY:
				//买即赠活动
				BuyAndPresent buyAndPresent = buyAndPresentMapper.selectByPrimaryKey(activityId);
				if (ObjectUtil.isNotNull(buyAndPresent)) {
					if (buyAndPresent.getEndTime().getTime() < new Date().getTime() || SystemConstant.BUYANDPRSENT.NOT_USEING == buyAndPresent.getStatus()) {
						result = 1;
					}
				}
				break;
			case SystemConstant.ACTIVITY.LIMIT_ACTIVITY:
				
				break;
			case SystemConstant.ACTIVITY.PROMOTION_ACTIVITY:
				break;
		} 
		return result;
	}
	
	/**
	 * 检查限时限量活动是否失效  过期
	 * @param activityId
	 * @param goodsId
	 * @return    
	 * @return int    
	 * Date:2017年11月3日 上午11:44:41 
	 * @author hexw
	 */
	public int checkLimitActivityGoodsIsLate(Long activityId,Long goodsId){
		int result = 0;
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(activityId);
		if (ObjectUtil.isNotNull(limitActivity)) {
			switch (limitActivity.getType()) {
				case SystemConstant.LIMITACTIVITY.FREEZING:
					//冰点价
					break;
				case SystemConstant.LIMITACTIVITY.DAYDAY:
					//天天特价
					break;
				case SystemConstant.LIMITACTIVITY.WEEKWEEK:
					//每周特价
					break;
				case SystemConstant.LIMITACTIVITY.WOLE:
					//整件惠
					break;
			}
		}
		return result ;
	}

	@Override
	public boolean findOnlineActivityGoods(Long id) {
		
		// 1. 排除跨境专区商品 和企业定制商品
		Goods goods = commonLimitActivityExMapper.findOnlineActivityGoodsByGoodsId(id);
		
		if(goods != null) return true;
		else return false;
	}
	
	@Override
	public boolean checkGoodsIsLimitActivityGoods(Long goodsId){
		boolean flag = false ;
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			flag = true ;
		}
		return flag;
	}
	
}
  