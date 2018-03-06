package com.zfj.xmy.common.service.impl;  

import java.util.List;

import javax.print.attribute.standard.PresentationDirection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.dao.PromotionActivityMapper;
import com.zfj.xmy.common.persistence.dao.PromotionGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.PromotionActivity;
import com.zfj.xmy.common.persistence.pojo.PromotionGoods;
import com.zfj.xmy.common.persistence.pojo.app.CommonActivityGoodsOutDto;
import com.zfj.xmy.common.persistence.pojo.app.CommonActvivtyGoodsInDto;
import com.zfj.xmy.common.service.CommonActivityService;

/** 
 * @Title: CommonActivityServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description:  xmy b2c活动的一些公共业务
 * @author hexw
 * @date 2017年12月8日 下午1:50:54 
 */
@Service
public class CommonActivityServiceImpl extends BaseService implements CommonActivityService {

	
	@Autowired
	private BuyAndPresentMapper buyAndPresentMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private PromotionGoodsMapper promotionGoodsMapper;
	
	@Autowired
	private PromotionActivityMapper promotionActivityMapper;
	
	@Override
	public CommonActivityGoodsOutDto getActivityGoodsDto(CommonActvivtyGoodsInDto inDto) {
		CommonActivityGoodsOutDto dto = new CommonActivityGoodsOutDto();
		if (ObjectUtil.isNotNull(inDto.getActivityType()) && ObjectUtil.isNotNull(inDto.getActivityId()) 
				&& ObjectUtil.isNotNull(inDto.getGoodsId())) {
			switch (inDto.getActivityType()) {
				case SystemConstant.ACTIVITY.BYU_AND_PRESENT_ACTIVITY:
					// 买即赠
					dto = getBuyAndPresent(inDto);
					break;
				case SystemConstant.ACTIVITY.PROMOTION_ACTIVITY:
					// 专题促销
					dto = getPromActivityGoodsOutDto(inDto);
					break;
				case SystemConstant.ACTIVITY.LIMIT_ACTIVITY:
					// 限时限量活动
					dto = getLimitActivityGoodsOutDto(inDto);
					break;
				default:
					break;
			}
		}
		return dto;
	}
	
	public CommonActivityGoodsOutDto getBuyAndPresent(CommonActvivtyGoodsInDto inDto) {
		CommonActivityGoodsOutDto dto = new CommonActivityGoodsOutDto();
		BuyAndPresent  buyAndPresent = buyAndPresentMapper.selectByPrimaryKey(inDto.getActivityId());
		if (ObjectUtil.isNotNull(buyAndPresent)) {
			dto.setActivityName(buyAndPresent.getName());
			Goods goods = goodsMapper.selectByPrimaryKey(inDto.getGoodsId());
			if (ObjectUtil.isNotNull(goods)) {
				dto.setActivityPrice(goods.getPrice());
			} else {
				throw new BusinessException("查询商品404"+inDto.getGoodsId());
			}
		} else {
			throw new BusinessException("查询买即赠活动404"+inDto.getActivityId());
		}
		return dto;
	}
	
	public CommonActivityGoodsOutDto getPromActivityGoodsOutDto(CommonActvivtyGoodsInDto inDto) {
		CommonActivityGoodsOutDto dto = new CommonActivityGoodsOutDto();
		PromotionActivity promotionActivity = promotionActivityMapper.selectByPrimaryKey(inDto.getActivityId());
		if (ObjectUtil.isNotNull(promotionActivity)) {
			dto.setActivityName(promotionActivity.getName());
			ReqData reqData = new ReqData();
			reqData.putValue("promotion_id", inDto.getActivityId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", inDto.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
			List<PromotionGoods> list = promotionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (list.size() > 0) {
				PromotionGoods promotionGoods = list.get(0);
				dto.setActivityPrice(promotionGoods.getPrice());
				dto.setGoodsLimitNum(promotionGoods.getLimitNum());
				dto.setGoodsResidueNum(promotionGoods.getAllNum()-promotionGoods.getCompleteNum());
			} else {
				throw new BusinessException("查询专题促销商品404"+inDto.getActivityId()+inDto.getGoodsId());
			}
		} else {
			throw new BusinessException("查询专题促销活动404"+inDto.getActivityId());
		}
		return dto;
	}
	
	public CommonActivityGoodsOutDto getLimitActivityGoodsOutDto(CommonActvivtyGoodsInDto inDto) {
		CommonActivityGoodsOutDto dto = new CommonActivityGoodsOutDto();
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(inDto.getActivityId());
		if (ObjectUtil.isNotNull(limitActivity)) {
			dto.setActivityName(limitActivity.getName());
			dto.setUserLimitNum(limitActivity.getLimitNum());
			ReqData reqData = new ReqData();
			reqData.putValue("limit_id", inDto.getActivityId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", inDto.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
			List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (list.size() > 0) {
				LimitGoods limitGoods = list.get(0);
				dto.setActivityPrice(limitGoods.getPrice());
				dto.setGoodsLimitNum(limitGoods.getLimitNum());
				dto.setGoodsResidueNum(limitGoods.getAllNum()-limitGoods.getCompleteNum());
			} else {
				throw new BusinessException("查询限时限量活动商品404"+inDto.getActivityId()+inDto.getGoodsId());
			}
		} else {
			throw new BusinessException("查询限时限量活动404"+inDto.getActivityId());
		}
		return dto;
	}
	
}
  