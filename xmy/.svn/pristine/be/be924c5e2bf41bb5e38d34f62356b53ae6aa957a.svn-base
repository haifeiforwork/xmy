package com.zfj.xmy.common.service.impl;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CollectionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.app.CommonCollectionGoodsDto;
import com.zfj.xmy.common.service.CommonCollectionGoodsService;
import com.zfj.xmy.common.service.CommonGoodsService;

/** 
 * @Title: CommonCollectionGoodsServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @date 2017年12月1日 上午11:41:13 
 */
@Service
public class CommonCollectionGoodsServiceImpl extends BaseService implements CommonCollectionGoodsService {

	@Autowired
	private CollectionGoodsMapper collectionGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Override
	public void findGoodsCollectPage(Long id, PageBean pageBean,String category,String goodsName) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", id,SystemConstant.REQ_PARAMETER_EQ);
		if(category!=null&&!"null".equals(category)){  //根据分类搜索
			reqData.putValue("goods_category_name", category,SystemConstant.REQ_PARAMETER_CN);
		}
		List<CollectionGoods> list=collectionGoodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		List<CommonCollectionGoodsDto> data = new  ArrayList<CommonCollectionGoodsDto>();
		for(CollectionGoods collect:list){
			Goods goods = goodsMapper.selectByPrimaryKey(collect.getGoodsId());
			if (ObjectUtil.isNotNull(goods)) {
				CommonCollectionGoodsDto dto = new CommonCollectionGoodsDto();
				dto.setGoodsImg(commonGoodsService.getFirstGoodsImg(collect.getGoodsId()));
				dto.setGoodsId(collect.getGoodsId());
				dto.setName(goods.getName());
				dto.setGoodsPrice(goods.getPrice());
				dto.setActivityId(collect.getActivityId());
				dto.setActivityType(collect.getActivityType());
				if (!StringUtil.isEmpty(goodsName)) { //根据商品名称搜索
					if (goods.getName().contains(goodsName)) {
						data.add(dto);
					}
				} else {
					data.add(dto);
				}
				if (ObjectUtil.isNotNull(collect.getActivityId()) && 0 != collect.getActivityId()) { // 收藏的活动商品
					if (SystemConstant.ACTIVITY.LIMIT_ACTIVITY == collect.getActivityType()) {
						Map<String,Object> result = getLimitActivityGoods(collect.getActivityId(),collect.getGoodsId());
						BigDecimal activityPrice = (BigDecimal) result.get("activityPrice");
						dto.setGoodsPrice(activityPrice);
						String object = (String) result.get("waterImg");
						dto.setWaterImg(object);
						String activityTimeStr = (String) result.get("activityTimeStr");
						dto.setActivityGoodsTimeStr(activityTimeStr);
					}
				}
			}
		}
		pageBean.setData(data);
		int count=collectionGoodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}
	
	
	/**
	 *  获取限时限量活动商品的 活动价格 水印图片 冰点价活动时间字符串
	 * @param activityId
	 * @param goodsId
	 * @return    
	 * @return Map<String,Object>    
	 * Date:2017年12月1日 下午2:22:32 
	 */
	public Map<String,Object> getLimitActivityGoods(Long activityId,Long goodsId){
		Map<String,Object> result = new  HashMap<String, Object>();
		//1. 查询活动
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(activityId);
		if (ObjectUtil.isNotNull(limitActivity)) {
		//2. 查寻活动商品	
			ReqData reqData = new ReqData();
			reqData.putValue("limit_id", limitActivity.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
			List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (list.size() > 0) {
				LimitGoods limitGoods = list.get(0);
				result.put("activityPrice", limitGoods.getPrice()); //活动价格
				if (SystemConstant.LIMITACTIVITY.FREEZING == limitActivity.getType()) { //冰点价
					result.put("activityTimeStr", DateUtils.getMothAndDay(limitGoods.getActivityTime()));	
				} else {
					result.put("waterImg", limitActivity.getImgPath());//活动商品水印
				}
			}
		}
		return result;
	}
	
}
  