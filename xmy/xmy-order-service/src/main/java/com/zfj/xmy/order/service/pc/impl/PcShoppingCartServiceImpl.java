package com.zfj.xmy.order.service.pc.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.dao.CollectionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.dao.PointsGoodsMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.common.service.CommonNewActitvityService;
import com.zfj.xmy.order.persistence.pc.dao.PcShoppCartExMapper;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCollectionGoodsDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
/**
 * @author lij
 * 登录后的购物车管理
 */
@Service
public class PcShoppingCartServiceImpl extends BaseService implements PcShoppingCartService{
	
	@Autowired
	private PcShoppCartExMapper shoppCartExMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private CollectionGoodsMapper collectionGoodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private BuyAndPresentMapper buyAndPresentMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private PointsGoodsMapper pointsGoodsMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private CommonNewActitvityService newActitvityService;
	
	/**
	 * 分页查询用的购物车商品
	 */
	@Override
	public List<PcGoodsDto> findShoppingCartByUserId(Long userId) {
		ReqData reqData = new ReqData();
		reqData.putValue("sc.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		//1. 查询购物车
		List<PcGoodsDto> shoppingCart = shoppCartExMapper.findShoppingCart(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (PcGoodsDto pcGoodsDto : shoppingCart) {
			//2. 活动商品
			if(!ObjectUtils.isEmpty(pcGoodsDto.getActivityId())){
					if(!pcGoodsDto.getActivityId().equals(0)&&!ObjectUtils.isEmpty(pcGoodsDto.getActivityType())){
						// 2.1 买即赠活动商品
						if(pcGoodsDto.getActivityType().equals(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY)){ 
							Goods goods = goodsMapper.selectByPrimaryKey(pcGoodsDto.getPresentId());
							goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
							pcGoodsDto.setPresentGoods(goods);
							pcGoodsDto.setActivityDescription("购买正价商品赠送精选好礼，买的多送得多哟！");
						} 
						// 2.2  限时限量活动
						if (pcGoodsDto.getActivityType().equals(SystemConstant.ACTIVITY.LIMIT_ACTIVITY)) { 
							LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(pcGoodsDto.getActivityId());
							if (ObjectUtil.isNotNull(limitActivity)) {
								pcGoodsDto.setActivityDescription(limitActivity.getDescription()); 
								LimitGoods limitGoods = commonLimitActivityService.findLimitGoods(pcGoodsDto.getActivityId(),pcGoodsDto.getId());
								if  (ObjectUtil.isNotNull(limitGoods)) {
									//查询限时限量活动
									pcGoodsDto.setLimitNum(limitGoods.getLimitNum());
									pcGoodsDto.setResidueNum(limitGoods.getAllNum()-limitGoods.getCompleteNum());
								}
							}
						}
					}
					// 2.3 活动商品价格
					if(!ObjectUtils.isEmpty(pcGoodsDto.getActivityPrice())){
						pcGoodsDto.setSumPrice(pcGoodsDto.getActivityPrice().multiply(BigDecimal.valueOf(Long.parseLong(pcGoodsDto.getCartNum().toString()))));
					}
					pcGoodsDto.setPrice(pcGoodsDto.getActivityPrice());	
			}
			//3. 积分活动	
			if (ObjectUtil.isNotNull(pcGoodsDto.getActId()) && pcGoodsDto.getActId() != 0 ) { 
				PointsGoods pointsGoods = pointsGoodsMapper.selectByPrimaryKey(Long.parseLong(pcGoodsDto.getActId().toString()));
				if (ObjectUtil.isNotNull(pointsGoods)) {
					pcGoodsDto.setLimitNum(pointsGoods.getLimitNum());
					pcGoodsDto.setResidueNum(pointsGoods.getAllNum()-pointsGoods.getCompleteNum());
				}
			}
			//4. 购物车商品图片	
			pcGoodsDto.setImgPath(commonGoodsService.getFirstGoodsImg(pcGoodsDto.getId()));
		}
		return shoppingCart;
	}
	
	
	/**
	 * 修改购物车商品数量
	 */
	@Override
	public void updateShoppingCartNum(Long userId, Integer num, Long goodsId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> selectByExample = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		ShoppingCart shoppingCart = selectByExample.get(0);
		shoppingCart.setNum(num);
		shoppingCartMapper.updateByPrimaryKey(shoppingCart);
	}
	/**
	 * 删除购物车商品信息
	 */
	@Override
	public void deleteShoppingCartGoods(Long userId, Long goodsId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		shoppingCartMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	/**
	 * 添加到收藏夹
	 */
	@Override
	public void addCollectionGoods(Long userId,PcCollectionGoodsDto dto) {
		CollectionGoods collectionGoods = new CollectionGoods();
		collectionGoods.setUserId(userId);
		collectionGoods.setGoodsId(dto.getGoodsId());
		collectionGoods.setGoodsCategoryName(dto.getCategoryName());
		collectionGoods.setCreateTime(new Date());
		collectionGoods.setType(SystemConstant.COLLECTIONGOODS.TYPE_GOODS);
		if (ObjectUtil.isNotNull(dto.getActivityId()) && dto.getActivityId() != 0) { //活动商品
			collectionGoods.setActivityType(dto.getActivityType());
			collectionGoods.setActivityId(dto.getActivityId());
		}
		collectionGoodsMapper.insert(collectionGoods);
	}
	
	
	@Override
	public void addBatchCollectionGoods(Long userId,String goodsIds){
		String[] ids = goodsIds.split("\\,");
		for (String id : ids) {
			//1. 判断用户是否是已收藏过该商品
			ReqData reqData = new ReqData();
			reqData.putValue("goods_id", Long.parseLong(id), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			List<CollectionGoods> list = collectionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (list.size() == 0) {
			//2. 查询购物车	
				reqData.clearValue();reqData.putValue("sc.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("sc.goods_id", Long.parseLong(id), SystemConstant.REQ_PARAMETER_EQ);
				List<PcGoodsDto> shoppingCart = shoppCartExMapper.findShoppingCart(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (shoppingCart.size() > 0) {
					PcGoodsDto dto = shoppingCart.get(0);
					CollectionGoods collectionGoods = new CollectionGoods();
					collectionGoods.setUserId(userId);
					collectionGoods.setGoodsId(dto.getId());
					collectionGoods.setGoodsCategoryName(dto.getCategoryName());
					collectionGoods.setCreateTime(new Date());
					collectionGoods.setType(SystemConstant.COLLECTIONGOODS.TYPE_GOODS);
					if (ObjectUtil.isNotNull(dto.getActivityId()) && dto.getActivityId() != 0) { //活动商品
						collectionGoods.setActivityType(dto.getActivityType());
						collectionGoods.setActivityId(dto.getActivityId());
					}
					collectionGoodsMapper.insert(collectionGoods);
				}
			}
		}
	}
	
	@Override
	public Integer findCollectinoGoodsIsDel(Long userId, Long goodsId) {
		int reslut = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<CollectionGoods> selectByExample = collectionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(selectByExample)){
			reslut = 1;
		}
		return reslut;
	}
	/**
	 * 查询购物车中是否有该商品
	 */
	@Override
	public Integer findCartGoods(Long userId, Long goodsId) {
		int reslut = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> selectByExample = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(selectByExample)){
			reslut = 1;
		}
		return reslut;
	}
	/**
	 * 添加到购物车
	 */
	@Override
	public void addShoppingCart(Long userId, Long goodsId, Integer goodsNum) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		newActitvityService.OrderContainSpeicleGoods(userId, goodsId.toString());
		newActitvityService.addGoodsToCart(shoppingCarts, list, goodsId, userId, goodsNum,null,null);
	}
	/**
	 * 积分商品添加到购物车
	 */
	@Override
	public void addPointsShoppingCart(Long userId, Long goodsId, Integer goodsNum,Integer points,Integer actId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("act_id", actId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		newActitvityService.OrderContainSpeicleGoods(userId, goodsId.toString());
		newActitvityService.addGoodsToCart(shoppingCarts, list, goodsId, userId, goodsNum,actId,points);
	}
	/**
	 * 添加活动商品到购物车
	 */
	@Override
	public void addActivityCart(ShoppingCart shoppingCart) {
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", shoppingCart.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("user_id", shoppingCart.getUserId(),SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			ShoppingCart cart = list.get(0);
			cart.setNum(cart.getNum()+shoppingCart.getNum());
			shoppingCartMapper.updateByPrimaryKey(cart);
		} else {
			//1.是否是买即赠活动
			if(shoppingCart.getActivityType().equals(SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY)){
				BuyAndPresent buyAndPresent = buyAndPresentMapper.selectByPrimaryKey(shoppingCart.getActivityId());
				String[] split = buyAndPresent.getGiftGoodsIds().split(",");
				String giftId = split[0];
				shoppingCart.setPresentGoodsId(Long.parseLong(giftId));
			}
			//2. 限时限量活动
			if (shoppingCart.getActivityType().equals(SystemConstant.ACTIVITY.LIMIT_ACTIVITY)) {
				reqData.clearValue();
				reqData.putValue("limit_id", shoppingCart.getActivityId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("goods_id", shoppingCart.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
				List<LimitGoods> limitGoods = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if (limitGoods.size() > 0) {
					shoppingCart.setActivityPrice(limitGoods.get(0).getPrice());
				} else {
					throw new BusinessException("未查询到活动商品");
				}
			}
			shoppingCart.setActivityPoints(0);
			shoppingCartMapper.insertSelective(shoppingCart);
		}
	}
	
	@Override
	@Transactional (rollbackFor=Exception.class)
	public int getCoupon(long couponId,long userId){
		int result = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("coupon_id", couponId, SystemConstant.REQ_PARAMETER_EQ);
		List<CouponUser> list = couponUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0 ){
			result =  1; //已领取过优惠券 每人每张优惠券限领一张
		} else {
			Coupon coupon = couponMapper.selectByPrimaryKey(couponId) ;
			if (ObjectUtil.isNotNull(coupon)) {
				if (coupon.getCouponCount() - coupon.getUseNum() <= 0 ) {
					result = 2; //优惠券已经抢光了
				} else {
					//将优惠券领取数量加一
					coupon.setUseNum(coupon.getUseNum()+1);
					couponMapper.updateByPrimaryKey(coupon);
					CouponUser couponUser = new CouponUser();
					couponUser.setCouponId(couponId);
					couponUser.setUserId(userId);
					couponUser.setReceiveTime(new Date());
					couponUser.setStatus(0);
					couponUser.setType(1);
					couponUserMapper.insert(couponUser);
				}
			}
		}
		return result;
	}
	
	@Override
	@Transactional (rollbackFor=Exception.class)
	public int checkPointGoods(Long pointsId,Long userId,Long goodsId,Integer num){
		int result = 0;
		//先判断购物车是否已存在积分商品  有的话须叠加商品的个数
		ReqData cartReqData = new ReqData();
		cartReqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		cartReqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		cartReqData.putValue("act_id", pointsId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> cartList = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(cartReqData));
		if (cartList.size() > 0) {
			ShoppingCart shoppingCart = cartList.get(0);
			num = num+shoppingCart.getNum();
		}
		
		PointsGoods pointsGoods = pointsGoodsMapper.selectByPrimaryKey(pointsId);
		if (ObjectUtil.isNotNull(pointsGoods)) {
			if (num > pointsGoods.getLimitNum() ) {
				result = 1;
			}
		}
		
		return result;
	}
	
	@Override
	public PointsGoods getPointsGoodsByActId(Integer actId,Long goodsId) {
		PointsGoods pointsGoods = null;
		ReqData reqData  = new ReqData();
		reqData.putValue("points_id", actId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<PointsGoods> list = pointsGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			pointsGoods = list.get(0);
		}
		return pointsGoods;
	}
	
	@Override
	public ShoppingCart findShoppingCart(Long userId,Long goodsId,Long activityId) {
		ShoppingCart shoppingCart = null;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("activity_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCart> list = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			shoppingCart = list.get(0);
		}
		return shoppingCart;
	}
	
	@Override
	public Integer checkActivityGoodsNum(Long activityId,Long goodsId,Integer allNum){
		Integer result = 0;
		ReqData reqData = new ReqData();
		reqData.putValue("limit_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (list.size() > 0) {
			LimitGoods limitGoods = list.get(0);
			if (allNum > limitGoods.getLimitNum() || allNum > limitGoods.getAllNum()-limitGoods.getCompleteNum()) {
				result = 2;
			}
		}
		return result;
	}
	
	
	@Override
	public PointsGoods getpointsGoods(Long pointGoodsId){
		return pointsGoodsMapper.selectByPrimaryKey(pointGoodsId);
	}
	
}
