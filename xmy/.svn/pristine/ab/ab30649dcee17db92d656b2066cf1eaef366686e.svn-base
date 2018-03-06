package com.zfj.xmy.activity.service.cms.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.persistence.cms.dao.PromotionActivityExMapper;
import com.zfj.xmy.activity.persistence.cms.dao.PromotionGoodsExMapper;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PromotionDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PromotionGoodsDto;
import com.zfj.xmy.activity.service.cms.PromotionService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsCategoryMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.PromotionActivityMapper;
import com.zfj.xmy.common.persistence.dao.PromotionGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsCategory;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.PromotionActivity;
import com.zfj.xmy.common.persistence.pojo.PromotionGoods;

/** 
 * @Title: PromotionServiceImpl.java 
 * @Package package com.zfj.xmy.activity.service.impl
 * @Description: 
 * @author zhangh
 * @date 2017年7月10日 下午8:27:03 
 */
@Service
public class PromotionServiceImpl extends BaseService implements PromotionService{

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Autowired
	private PromotionActivityMapper promotionActivityMapper;
	
	@Autowired
	private PromotionActivityExMapper promotionActivityExMapper;
	
	@Autowired
	private PromotionGoodsMapper promotionGoodsMapper;
	
	@Autowired
	private PromotionGoodsExMapper promotionGoodsExMapper;

	@Override
	public List<Goods> findGoods(String idStr) {
		String[] idArr=idStr.split("\\,");
		List<Goods> list=new ArrayList<Goods>();
		for(String id:idArr){
			list.add(goodsMapper.selectByPrimaryKey(Long.parseLong(id)));
		}
		return list;
	}

	@Override
	public Long insertPromotion(PromotionActivity promotionActivity) {
		promotionActivity.setStatus(0);
		promotionActivity.setDiscount(0);
		promotionActivityMapper.insert(promotionActivity);
		return promotionActivity.getId();
	}

	@Override
	public void addPromotionGoods(String proId, String proGoods) {
		PromotionGoods progoods=new PromotionGoods();
		progoods.setPromotionId(Long.parseLong(proId));
		String[] idArr=proGoods.split("\\,");
		if(idArr.length>=4){
			ReqData reqData=new ReqData();
			reqData.putValue("goods_id", Long.parseLong(idArr[0]),SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("promotion_id", Long.parseLong(proId),SystemConstant.REQ_PARAMETER_EQ);
			List<PromotionGoods> promotionGoods=promotionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if(promotionGoods.size()>0){
				for(int i=0;i<promotionGoods.size();i++){
					promotionGoodsMapper.deleteByPrimaryKey(promotionGoods.get(i).getId());
				}
			}
			progoods.setGoodsId(Long.parseLong(idArr[0]));
			progoods.setPrice(new BigDecimal(idArr[1]));
			progoods.setLimitNum(Integer.parseInt(idArr[2]));
			progoods.setAllNum(Integer.parseInt(idArr[3]));
			progoods.setCompleteNum(Integer.parseInt(idArr[4]));
			promotionGoodsMapper.insert(progoods);
		}
	}
	
	@Override
	public void addCodeGoods(String proId, String codeIds) {
		List<GoodsCategory> list=new ArrayList<GoodsCategory>();
		PromotionGoods progoods=new PromotionGoods();
		progoods.setPromotionId(Long.parseLong(proId));
		String[] codeArr=codeIds.split("\\,");
		if(codeArr.length>=1){
			for(String code:codeArr){
				ReqData reqData=new ReqData();
				reqData.putValue("cid",Long.parseLong(code),SystemConstant.REQ_PARAMETER_EQ);
				List<GoodsCategory> cateList=goodsCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				list.addAll(cateList);
			}
		}
		//去除重复商品
		List<Long> listTemp = new ArrayList<Long>();  
        for(int i=0;i<list.size();i++){  
            if(!listTemp.contains(list.get(i).getGoodsId())){  
                listTemp.add(list.get(i).getGoodsId());  
            }  
        } 
		
		for(Long id:listTemp){
			progoods.setGoodsId(id);
			progoods.setPrice(new BigDecimal(0));
			progoods.setLimitNum(0);
			progoods.setAllNum(0);
			progoods.setCompleteNum(0);
			promotionGoodsMapper.insert(progoods);
		}
		
	}
	
	@Override
	public void addSupGoods(String proId, String supIds) {
		PromotionGoods progoods=new PromotionGoods();
		List<Goods> list=new ArrayList<Goods>();
		progoods.setPromotionId(Long.parseLong(proId));
		String[] supArr=supIds.split("\\,");
		if(supArr.length>=1){
			for(String sup:supArr){
				ReqData reqData=new ReqData();
				reqData.putValue("supplier_id",Long.parseLong(sup),SystemConstant.REQ_PARAMETER_EQ);
				List<Goods> goodslist=goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				list.addAll(goodslist);
			}
		}
		
		Goods goods=new Goods();
		for(int i=0;i<list.size();i++){
			goods=list.get(i);
			progoods.setGoodsId(goods.getId());
			progoods.setPrice(new BigDecimal(0));
			progoods.setLimitNum(0);
			progoods.setAllNum(0);
			progoods.setCompleteNum(0);
			promotionGoodsMapper.insert(progoods);
		}
	}

	@Override
	public void selectPromotionList(ReqData reqData, PageBean pageBean) {
		reqData.putValue("status", 0,SystemConstant.REQ_PARAMETER_EQ);
		List<PromotionDto> list=promotionActivityExMapper.findList(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		Date date=new Date();
		for (PromotionDto promotionDto : list) {
			if( promotionDto.getBeginTime() != null && promotionDto.getEndTime() != null){
				if(promotionDto.getBeginTime().getTime() > date.getTime()  ){
					promotionDto.setSaleStatus("未开始");
				}
				if(promotionDto.getBeginTime().getTime() < date.getTime() && promotionDto.getEndTime().getTime() > date.getTime() ){
					promotionDto.setSaleStatus("活动进行中");
				}
				if( promotionDto.getEndTime().getTime() < date.getTime()){
					promotionDto.setSaleStatus("活动已结束");
				}
			}
		}
		pageBean.setData(list);
		pageBean.setTotalCount(promotionActivityMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
	}

	@Override
	public int delPromotion(String id) {
		int state=0;
		PromotionActivity promotionActivity=promotionActivityMapper.selectByPrimaryKey(Long.parseLong(id));
		if(promotionActivity!=null){
			promotionActivity.setStatus(1);
			state=promotionActivityMapper.updateByPrimaryKey(promotionActivity);
		}
		return state;
	}

	@Override
	public PromotionActivity getPromotionActivity(String id) {
		return promotionActivityMapper.selectByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public List<PromotionGoodsDto> selectProGoodsByActId(String id) {
		ReqData reqData=new ReqData();
		reqData.putValue("p.promotion_id", Long.parseLong(id),SystemConstant.REQ_PARAMETER_EQ);
		List<PromotionGoodsDto> list=promotionGoodsExMapper.selectPromotionGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}

	@Override
	public long updatePromotion(PromotionActivity promotionActivity) {
		promotionActivity.setDiscount(0);
		promotionActivityMapper.updateByPrimaryKey(promotionActivity);
		return promotionActivity.getId();
	}

	@Override
	public void delPromotionGoods(String goodsId, String proId) {
		ReqData reqData=new ReqData();
		reqData.putValue("promotion_id",Long.parseLong(proId), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id",Long.parseLong(goodsId), SystemConstant.REQ_PARAMETER_EQ);
		List<PromotionGoods> list=promotionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				promotionGoodsMapper.deleteByPrimaryKey(list.get(i).getId());
			}
		}
	}

}
