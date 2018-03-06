package com.zfj.xmy.activity.service.common.impl;  

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.service.common.SpecialActivityService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.PromotionActivityMapper;
import com.zfj.xmy.common.persistence.dao.PromotionGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.PromotionActivity;
import com.zfj.xmy.common.persistence.pojo.PromotionGoods;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;

/** 
 * @Title: SpecialActivityServiceImpl.java 
 * @Package com.zfj.xmy.activity.service.common.impl 
 * @Description: 
 * @author hexw
 * @date 2017年11月14日 上午11:35:07 
 */
@Service
public class SpecialActivityServiceImpl extends BaseService implements SpecialActivityService {
	
	@Autowired
	private PromotionActivityMapper promotionActivityMapper;
	
	@Autowired
	private PromotionGoodsMapper promotionGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsDocService docService;
	@Override
	public void updatePromotionActivityGoodsPutway(){
		// 1. 查询专题促销活动
		ReqData reqData = new ReqData();
		reqData.putValue("status", SystemConstant.PROMOTIONACTIVITY.USERING, SystemConstant.REQ_PARAMETER_EQ);
		List<PromotionActivity> list = promotionActivityMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (PromotionActivity promotionActivity : list) {
		// 2. 判断专题活动是否开始
			Long nowDate = new Date().getTime();
			if (promotionActivity.getBeginTime().getTime() <= nowDate && promotionActivity.getEndTime().getTime() > nowDate) { //活动已开始
				updateGoodsPutway(promotionActivity.getId(),SystemConstant.GoodsConstant.PUTWAY);
			} else { // 活动未开始 或 已结束
				updateGoodsPutway(promotionActivity.getId(),SystemConstant.GoodsConstant.NOT_PUTWAY);
			}
		}
		docService.promotionActivityPutway();
	}
	
	
	public void updateGoodsPutway(Long activityId,Integer putway){
		ReqData reqData = new ReqData();
		reqData.putValue("promotion_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
		List<PromotionGoods> list = promotionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (PromotionGoods promotionGoods : list) {
			Goods goods = goodsMapper.selectByPrimaryKey(promotionGoods.getGoodsId());
			if (ObjectUtil.isNotNull(goods)) {
				goods.setIsPutway(putway);
				goodsMapper.updateByPrimaryKeySelective(goods);
			}
		}
	}

}
  