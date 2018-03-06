package com.zfj.xmy.activity.service.cms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.persistence.cms.dao.PointsActivityExMapper;
import com.zfj.xmy.activity.persistence.cms.dao.PointsGoodsExMapper;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsActivityDto;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.activity.service.cms.PointsActivityService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.PointsActivityMapper;
import com.zfj.xmy.common.persistence.dao.PointsGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;

/** 
 * @Title: PointsActivityServiceImpl.java 
 * @Package com.zfj.xmy.activity.service.impl 
 * @Description: 
 * @author zhangh
 * @date 2017年7月11日 下午8:27:03 
 */
@Service
public class PointsActivityServiceImpl extends BaseService implements PointsActivityService{

	@Autowired
	private GoodsMapper goodsMapper; 
	
	@Autowired
	private PointsActivityMapper pointsActivityMapper;
	
	@Autowired
	private PointsActivityExMapper pointsActivityExMapper;
	
	@Autowired
	private PointsGoodsMapper pointsGoodsMapper;
	
	@Autowired
	private PointsGoodsExMapper pointsGoodsExMapper;
	@Override
	public List<Goods> findGoods(String idStr) {
		String[] idArry=idStr.split("\\,");
		List<Goods> list=new ArrayList<Goods>();
		for(String id:idArry){
			list.add(goodsMapper.selectByPrimaryKey(Long.parseLong(id)));
		}
		return list;
	}

	@Override
	public Long insertActivity(PointsActivity pointsActivity) {
		pointsActivity.setStatus(0);
		pointsActivityMapper.insert(pointsActivity);
		return pointsActivity.getId();
	}

	@Override
	public void addActivityGoods(String activityId, String pointsGoods) {
		PointsGoods pg = new PointsGoods();
		pg.setPointsId(Long.parseLong(activityId));
		String [] idArry = pointsGoods.split("\\,");
		if(idArry.length >= 4){
			ReqData reqData = new ReqData();
			reqData.putValue("goods_id",Long.parseLong(idArry[0]), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("points_id",Long.parseLong(activityId), SystemConstant.REQ_PARAMETER_EQ);
			List<PointsGoods> poiGoods = pointsGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if(poiGoods.size()>0){     //防止同一个活动 添加相同的商品
				for (int i = 0; i < poiGoods.size(); i++) {
					pointsGoodsMapper.deleteByPrimaryKey(poiGoods.get(i).getId());
				}
			}
			pg.setGoodsId(Long.parseLong(idArry[0]));
			pg.setLimitNum(Integer.parseInt(idArry[1]));
			pg.setAllNum(Integer.parseInt(idArry[2]));
			pg.setCompleteNum(Integer.parseInt(idArry[3]));
			pg.setPoints(Integer.parseInt(idArry[4]));
			pointsGoodsMapper.insert(pg);
		}
	}

	@Override
	public void selectActivityList(ReqData reqData, PageBean pageBean) {
		reqData.putValue("status", 0, SystemConstant.REQ_PARAMETER_EQ);
		//List<PointsActivity> list = pointsActivityMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		List<PointsActivityDto> list = pointsActivityExMapper.findList(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		Date date = new Date();
		for (PointsActivityDto pointsActivityDto : list) {
			if(pointsActivityDto.getBeginTime().getTime() > date.getTime()  ){
				pointsActivityDto.setSaleStatus("未开始");
			}
			if(pointsActivityDto.getBeginTime().getTime() < date.getTime() && pointsActivityDto.getEndTime().getTime() > date.getTime() ){
				pointsActivityDto.setSaleStatus("活动进行中");
			}
			if(pointsActivityDto.getEndTime().getTime() < date.getTime()){
				pointsActivityDto.setSaleStatus("活动已结束");
			}
		}
		pageBean.setData(list);
	}

	@Override
	public int delActivity(String id) {
		int state = 0;
		PointsActivity pointsActivity = pointsActivityMapper.selectByPrimaryKey(Long.parseLong(id));
		if( pointsActivity != null ){
			pointsActivity.setStatus(1);
			state = pointsActivityMapper.updateByPrimaryKey(pointsActivity);
		}
		return state;
	}

	@Override
	public PointsActivity getPointsActivity(String id) {
		return pointsActivityMapper.selectByPrimaryKey(Long.parseLong(id));
	}

	@Override
	public List<PointsGoodsDto> selectPointsGoodsByActId(String id) {
		ReqData reqData = new ReqData();
		reqData.putValue("p.points_id",Long.parseLong(id), SystemConstant.REQ_PARAMETER_EQ);
		List<PointsGoodsDto> list = pointsGoodsExMapper.selectPointsGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}

	@Override
	public long updateActivity(PointsActivity pointsActivity) {
		pointsActivityMapper.updateByPrimaryKey(pointsActivity);
		return pointsActivity.getId();
	}

	@Override
	public void delActivityGoods(String goodsId, String actId) {
		ReqData reqData = new ReqData();
		reqData.putValue("points_id",Long.parseLong(actId), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id",Long.parseLong(goodsId), SystemConstant.REQ_PARAMETER_EQ);
		List<PointsGoods> poiGoods = pointsGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if( poiGoods.size()>0 ){
			for (int i = 0; i < poiGoods.size(); i++) {
				pointsGoodsMapper.deleteByPrimaryKey(poiGoods.get(i).getId());
			}
		}
	}

}
