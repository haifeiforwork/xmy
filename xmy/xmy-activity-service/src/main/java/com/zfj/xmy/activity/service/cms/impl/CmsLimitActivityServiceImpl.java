package com.zfj.xmy.activity.service.cms.impl;  

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.cms.dao.CmsLimitActivityExMapper;
import com.zfj.xmy.activity.persistence.cms.dao.CmsLimitGoodsExMapper;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.LimitActivityDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.LimitGoodsDto;
import com.zfj.xmy.activity.service.cms.CmsLimitActivityService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;

/** 
 * @Title: LimitActivityServiceImpl.java 
 * @Package com.zfj.xmy.activity.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年7月5日 下午8:27:03 
 */
@Service
public class CmsLimitActivityServiceImpl extends BaseService implements CmsLimitActivityService{
	
	@Autowired
	private GoodsMapper goodsMapper; 
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private CmsLimitActivityExMapper limitActivityExMapper;
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	
	@Autowired
	private CmsLimitGoodsExMapper limitGoodsExMapper;
	
	
	@Override
	public List<Goods> findGoods(String idStr) {
		String [] idArry = idStr.split("\\,");
		List<Goods> list = new ArrayList<Goods>();
		for (String  id : idArry) {
			Goods goods = goodsMapper.selectByPrimaryKey(Long.parseLong(id));
			list.add(goods);
		}
		return list;
	}

	@Override
	public void insertActivity(LimitActivity limitActivity, String[] goodsId,
			String[] price, String[] limitNum, String[] allNum, String[] completeNum,String[] activityTime) {
		limitActivity.setStatus(0);
		limitActivityMapper.insert(limitActivity);
		addActivityGoods(limitActivity.getId(),goodsId,price,limitNum,allNum,completeNum,activityTime);
	}
	
	public void addActivityGoods(long limitId, String[] goodsId, String[] price,
			String[] limitNum, String[] allNum, String[] completeNum,String[] activityTime) {
		if(goodsId != null ){
			if(goodsId.length > 0){
				for (int i = 0; i < goodsId.length; i++) {
					ReqData reqData = new ReqData();
					reqData.putValue("goods_id",Long.parseLong(goodsId[i]), SystemConstant.REQ_PARAMETER_EQ);
					reqData.putValue("limit_id",limitId, SystemConstant.REQ_PARAMETER_EQ);
					List<LimitGoods> limGoods = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
					if(limGoods.size()>0){     //防止同一个活动 添加相同的商品
						for (int j = 0; j < limGoods.size(); j++) {
							limitGoodsMapper.deleteByPrimaryKey(limGoods.get(j).getId());
						}
					}
					LimitGoods lg = new  LimitGoods();
					lg.setLimitId(limitId);
					lg.setGoodsId(Long.parseLong(goodsId[i]));
					if (!StringUtil.isEmpty(price[i])) {
						lg.setPrice(new BigDecimal(price[i]));
					}
					if (!StringUtil.isEmpty(limitNum[i])) {
						lg.setLimitNum(Integer.parseInt(limitNum[i]));
					}
					if (!StringUtil.isEmpty(allNum[i])) {
						lg.setAllNum(Integer.parseInt(allNum[i]));			
					}
					if (!StringUtil.isEmpty(completeNum[i])) {
						lg.setCompleteNum(Integer.parseInt(completeNum[i]));
					}
					if (ObjectUtil.isNotNull(activityTime)) {  //专题促销活动活动商品没有时间
						if (!StringUtil.isEmpty(activityTime[i])) {
							Date date = DateUtil.parse(activityTime[i], "yyyy-MM-dd HH:mm:ss");
							lg.setActivityTime(date);
						}
					}
					
					limitGoodsMapper.insert(lg);
				}
			}
		}
	}
	
	
	@Override
	public void selectActivityList(ReqData reqData , PageBean pageBean){
		reqData.putValue("status", 0, SystemConstant.REQ_PARAMETER_EQ);
		//List<LimitActivity> list = limitActivityMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		List<LimitActivityDto> list = limitActivityExMapper.findList(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		Date date = new Date();
		for (LimitActivityDto limitActivityDto : list) {
			if( limitActivityDto.getBeginTime() != null && limitActivityDto.getEndTime() != null){
				if(limitActivityDto.getBeginTime().getTime() > date.getTime()  ){
					limitActivityDto.setSaleStatus("未开始");
				}
				if(limitActivityDto.getBeginTime().getTime() < date.getTime() && limitActivityDto.getEndTime().getTime() > date.getTime() ){
					limitActivityDto.setSaleStatus("活动进行中");
				}
				if( limitActivityDto.getEndTime().getTime() < date.getTime()){
					limitActivityDto.setSaleStatus("活动已结束");
				}
			}
		}
		pageBean.setData(list);
		pageBean.setTotalCount(limitActivityMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
	}
	
	@Override
	public int  delActivity(String id){
		int state = 0;
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(Long.parseLong(id));
		if( limitActivity != null ){
			limitActivity.setStatus(1);
			state = limitActivityMapper.updateByPrimaryKey(limitActivity);
		}
		return state;
	}
	
	@Override
	public LimitActivity getLimitActivity(String id){
		LimitActivity limitActivity = limitActivityMapper.selectByPrimaryKey(Long.parseLong(id));
		return limitActivity;
	}
	
	@Override
	public List<LimitGoodsDto> selectLimitGoodsByActId(String id){
		ReqData reqData = new ReqData();
		reqData.putValue("l.limit_id",Long.parseLong(id), SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("l.activity_time");
		reqData.setSortorder("desc");
		List<LimitGoodsDto> list = limitGoodsExMapper.selectLimitGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public void updateActivity(LimitActivity limitActivity, String[] goodsId,
			String[] price, String[] limitNum, String[] allNum, String[] completeNum,String[] activityTime){
		limitActivityMapper.updateByPrimaryKey(limitActivity);
		addActivityGoods(limitActivity.getId(),goodsId,price,limitNum,allNum,completeNum,activityTime);
	}
	
	@Override
	public void delActivityGoods(String goodsId,String actId){
		ReqData reqData = new ReqData();
		reqData.putValue("limit_id",Long.parseLong(actId), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id",Long.parseLong(goodsId), SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> limGoods = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if( limGoods.size()>0 ){
			for (int i = 0; i < limGoods.size(); i++) {
				limitGoodsMapper.deleteByPrimaryKey(limGoods.get(i).getId());
			}
		}
	}
	
}
  