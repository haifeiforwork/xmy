package com.zfj.xmy.goods.service.app.impl;  

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.dao.PointsGoodsMapper;
import com.zfj.xmy.common.persistence.dao.PromotionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.PromotionGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.common.service.CommonNewActitvityService;
import com.zfj.xmy.goods.persistence.app.dao.AppShoppingCartExMapper;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingCartDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingInCartDto;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.goods.service.app.AppShoppingCartService;

/** 
 * @Title: AppShoppingCartServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.app.impl 
 * @Description: 
 * @author hexw
 * @date 2017年7月27日 下午4:19:11 
 */
@Service
public class AppShoppingCartServiceImpl extends BaseService implements  AppShoppingCartService{

	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private AppShoppingCartExMapper appShoppingCartExMapper;
	
	@Autowired
	private AppGoodsService appGoodsService;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private PointsGoodsMapper pointsGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private PromotionGoodsMapper promotionGoodsMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper ;
	
	@Autowired
	private CommonNewActitvityService commonNewActitvityService;
	
	
	@Override
	public void addGoodsToShoppingCart(List<ShoppingCart> shoppingCarts,Long userId){
		for (ShoppingCart shoppingCart : shoppingCarts) {
			 int resultNum=0;
			 shoppingCart.setUserId(userId);
			// commonNewActitvityService.OrderContainSpeicleGoods(userId, shoppingCart.getGoodsId().toString());
			 commonNewActitvityService.checkAssignActivityGoodsToShoppingCart(shoppingCart); //判断商品是否指定的活动商品
			 if(!ObjectUtils.isEmpty(shoppingCart.getActivityType())&&shoppingCart.getActivityType()!=0){
				 resultNum= commonLimitActivityService.checkLimitActivity(shoppingCart); //如果是活动商品判断是不是符合活动的规则
			 }
			 if (resultNum == 0) {
					//1. 查询用户购物车  是否已经存在商品 
					ReqData reqData = new ReqData();
					reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
					reqData.putValue("goods_id", shoppingCart.getGoodsId() , SystemConstant.REQ_PARAMETER_EQ);
					List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
					//2. 购物车已存在此商品  只是叠加数量
					if (list.size() > 0 ) {   //购物车已存在该商品
						ShoppingCart cart = list.get(0);
						cart.setNum(cart.getNum() + shoppingCart.getNum());
						shoppingCartMapper.updateByPrimaryKeySelective(cart);
					} else {
					//3. 购物车不存在 则新增一条记录
						if(!ObjectUtils.isEmpty(shoppingCart.getActId())){ //积分商品
							reqData.clearValue();
							PointsGoods pointsGoods=pointsGoodsMapper.selectByPrimaryKey(shoppingCart.getActId().longValue());
							UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
							if (ObjectUtil.isNotNull(userInfo)) {
								if (userInfo.getAccPoints() >= pointsGoods.getPoints()) {
									if(!ObjectUtils.isEmpty(pointsGoods)){
										shoppingCart.setActivityPoints(pointsGoods.getPoints());
										shoppingCart.setActId(pointsGoods.getId().intValue());
									}
								} else {
									 throw new BusinessException("个人积分不足");
								}
							}
							
						}
						if (shoppingCart.getActivityId() != null && shoppingCart.getActivityId()!=0  ) {
							BigDecimal activityPrice = getActivityGoodsPrice(shoppingCart.getActivityId(),shoppingCart.getGoodsId(),shoppingCart.getActivityType());
							shoppingCart.setActivityPrice(activityPrice);
						} else {
							shoppingCart.setActivityType(null);
							shoppingCart.setActivityId(null);
						}
						shoppingCart.setUserId(userId);
						shoppingCart.setCreateTime(new Date());
						shoppingCartMapper.insertSelective(shoppingCart);
					} 
			 } else {
				 throw new BusinessException("您购买的商品超过了活动限定的个数");
			 }
		}
	}
	
	public BigDecimal getActivityGoodsPrice(long activityId,long goodsId,Integer activityType){
		BigDecimal prcie = new  BigDecimal(0);
		ReqData reqData = new  ReqData();
		switch (activityType) {
			case SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY:
				// 买即赠
				prcie=goodsMapper.selectByPrimaryKey(goodsId).getPrice();
				break;
			case SystemConstant.ACTIVITY.PROMOTION_ACTIVITY:
				// 专题促销
				reqData.putValue("promotion_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
				List<PromotionGoods> promtionGoods = promotionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (promtionGoods.size() > 0) {
					prcie = promtionGoods.get(0).getPrice();
				} else {
					 throw new BusinessException("未查询到活动商品");
				}
				break;
			case SystemConstant.ACTIVITY.LIMIT_ACTIVITY:
				reqData.clearValue();
				reqData.putValue("limit_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
				List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (list.size() > 0) {
					prcie=list.get(0).getPrice();
				} else {
					 throw new BusinessException("未查询到活动商品");
				}
				break;
			default:
				break;
		}
		return prcie;
	}
	
	/*@Override
	public void addGoodsToShoppingCartNoLogin(ShoppingCart shoppingCart){
		
	}*/
	
	@Override
	public List<AppShoppingCartDir> findShoppingCarts(long userId){
		ReqData reqData = new ReqData();
		reqData.putValue("user_id",userId , SystemConstant.REQ_PARAMETER_EQ);
		List<AppShoppingCartDir> list = appShoppingCartExMapper.selectShoppingCart(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			for (AppShoppingCartDir appShoppingCartDir : list) {
					//获取商品第一张图片
					appShoppingCartDir.setImgPath(commonGoodsService.getFirstGoodsImg(appShoppingCartDir.getGoodsId()));
			}
		}
		return list;
	}
	
	
	@Override
	public void deleteShoppingCartGoods(long userId, List<AppShoppingInCartDto> list){
		for (AppShoppingInCartDto dto : list) {
			ReqData reqData = new  ReqData();
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", dto.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
			 shoppingCartMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}
	}
	
	@Override
	public void updateGoodsNum(long goodsId, long userId, Integer num){
		// 1.0 判断商品是否是拼购抽和三免一活动商品
		boolean flag = commonNewActitvityService.checkPgcSmyGoodsShoppingCart(goodsId);
		if (flag && num > 1) {
			throw new BusinessException("该活动商品只能购买一个哦！");
		}
		ReqData reqData = new  ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			for (ShoppingCart shoppingCart : list) {
				if (ObjectUtil.isNotNull(shoppingCart.getActivityId()) && shoppingCart.getActivityId() != 0){  //活动商品
					LimitGoods limitGoods = commonLimitActivityService.findLimitGoods(shoppingCart.getActivityId(),goodsId);
					if (ObjectUtil.isNotNull(limitGoods)) {
						if (num > limitGoods.getLimitNum()) {
							 throw new BusinessException("超过了活动商品的限购数量");
						} else {
							shoppingCart.setNum(num);
						}
					}
				} if (ObjectUtil.isNotNull(shoppingCart.getActId())) { //积分商品
					UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
					if (ObjectUtil.isNotNull(userInfo)) {
						PointsGoods pointsGoods = pointsGoodsMapper.selectByPrimaryKey(shoppingCart.getActId().longValue());
						if (ObjectUtil.isNotNull(pointsGoods)) {
							if (pointsGoods.getPoints()*num >userInfo.getAccPoints()) {
								throw new BusinessException("个人积分不足");
							} else {
								shoppingCart.setNum(num);
							}
						}
					}
				} else {
					shoppingCart.setNum(num);
				}
				shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
			}
		} else {
			throw new BusinessException("购物车未找到商品");
		}
	}
	
	
	public int checkLimitGoods(Long userId,Long activityId,Long goodsId,Integer num){
		int result = 0;
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(activityId);
		if (ObjectUtil.isNotNull(limitActivity)) {
			ReqData reqData = new ReqData();
			reqData.putValue("a.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("b.activity_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
			int orderNum = appShoppingCartExMapper.countLimitAcivity(ReqUtil.reqParameterToCriteriaParameter(reqData)); //已生成到订单的活动
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
	
}
  