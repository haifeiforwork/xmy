package com.zfj.xmy.activity.service.pc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.persistence.pc.dao.PcLimitActivityExMapper;
import com.zfj.xmy.activity.persistence.pc.dao.PcLimitGoodsExMapper;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcLimitActivityDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcLimitGoodsDto;
import com.zfj.xmy.activity.service.pc.PcLimitActivityService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.service.CommonGoodsService;
@Service
public class PcLimitActivityServiceImpl extends BaseService implements PcLimitActivityService {

	@Autowired
	private PcLimitActivityExMapper activityExMapper;
	
	@Autowired
	private PcLimitGoodsExMapper goodsExMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Override
	public List<PcLimitActivityDto> findActivityGoods(Integer limitType,PageBean pageBean) {
		List<PcLimitActivityDto> list = new ArrayList<PcLimitActivityDto>();
		PcLimitActivityDto dto =null;
		ReqData reqData = new ReqData();
		if(limitType.equals(SystemConstant.LIMITACTIVITY.FREEZING)){
			pageBean.setPageSize(3);//取前三个活动
			reqData.putValue("type", limitType, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("port", SystemConstant.LIMITACTIVITY.PC, SystemConstant.REQ_PARAMETER_CN);
			List<PcLimitActivityDto> selectLimitActivity = activityExMapper.selectLimitActivity(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds());
			reqData.clearValue();
			if(!ObjectUtils.isEmpty(selectLimitActivity)){
				for (PcLimitActivityDto pcLimitActivityDto : selectLimitActivity) {
					dto = pcLimitActivityDto;
					reqData.clearValue();
					reqData.putValue("lg.limit_id", dto.getId(), SystemConstant.REQ_PARAMETER_EQ);
					List<PcLimitGoodsDto> selectLimitGoods = goodsExMapper.selectLimitGoods(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds());
					reqData.clearValue();
					for (PcLimitGoodsDto pcLimitGoodsDto : selectLimitGoods) {
						String firstGoodsImg = commonGoodsService.getFirstGoodsImg(pcLimitGoodsDto.getGoodsId());
						pcLimitGoodsDto.setGoodsImage(firstGoodsImg);
					}
					dto.setActivityGoods(selectLimitGoods);
					list.add(dto);
				}
			}
		}
		if(limitType.equals(SystemConstant.LIMITACTIVITY.DAYDAY)){
			pageBean.setPageSize(6);
			reqData.putValue("type", limitType, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("port", SystemConstant.LIMITACTIVITY.PC, SystemConstant.REQ_PARAMETER_CN);
			List<PcLimitActivityDto> selectLimitActivity = activityExMapper.selectLimitActivity(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			if(!ObjectUtils.isEmpty(selectLimitActivity)){
				for (PcLimitActivityDto pcLimitActivityDto : selectLimitActivity) {
					pageBean.setPageSize(1);//一个天天特价活动只取一个商品
					dto = pcLimitActivityDto;
					reqData.clearValue();
					reqData.putValue("lg.limit_id", dto.getId(), SystemConstant.REQ_PARAMETER_EQ);
					List<PcLimitGoodsDto> selectLimitGoods = goodsExMapper.selectLimitGoods(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds());
					reqData.clearValue();
					for (PcLimitGoodsDto pcLimitGoodsDto : selectLimitGoods) {
						String firstGoodsImg = commonGoodsService.getFirstGoodsImg(pcLimitGoodsDto.getGoodsId());
						pcLimitGoodsDto.setGoodsImage(firstGoodsImg);
					}
					dto.setActivityGoods(selectLimitGoods);
					list.add(dto);
				}
			}
		}
		if(limitType.equals(SystemConstant.LIMITACTIVITY.WEEKWEEK)){
			pageBean.setPageSize(1);
			reqData.putValue("type", limitType, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("port", SystemConstant.LIMITACTIVITY.PC, SystemConstant.REQ_PARAMETER_CN);
			List<PcLimitActivityDto> selectLimitActivity = activityExMapper.selectLimitActivity(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			if(!ObjectUtils.isEmpty(selectLimitActivity)){
				dto = selectLimitActivity.get(0);
				reqData.clearValue();
				reqData.putValue("lg.limit_id", dto.getId(), SystemConstant.REQ_PARAMETER_EQ);
				List<PcLimitGoodsDto> selectLimitGoods = goodsExMapper.selectLimitGoods(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds());
				for (PcLimitGoodsDto pcLimitGoodsDto : selectLimitGoods) {
					String firstGoodsImg = commonGoodsService.getFirstGoodsImg(pcLimitGoodsDto.getGoodsId());
					pcLimitGoodsDto.setGoodsImage(firstGoodsImg);
				}
				dto.setActivityGoods(selectLimitGoods);
				list.add(dto);
			}
		}
		
		/*reqData.putValue("status", SystemConstant.LIMITACTIVITY.STATUS_YES, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", limitType, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("port", SystemConstant.LIMITACTIVITY.PC, SystemConstant.REQ_PARAMETER_CN);
		String format = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		reqData.putValue("begin_time", format, SystemConstant.REQ_PARAMETER_LT);
		reqData.putValue("end_time", format, SystemConstant.REQ_PARAMETER_GT);
		List<PcLimitActivityDto> selectLimitActivity = activityExMapper.selectLimitActivity(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!ObjectUtils.isEmpty(selectLimitActivity)){
			dto = selectLimitActivity.get(0);
			reqData.clearValue();
			reqData.putValue("lg.limit_id", dto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<PcLimitGoodsDto> selectLimitGoods = goodsExMapper.selectLimitGoods(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds());
			for (PcLimitGoodsDto pcLimitGoodsDto : selectLimitGoods) {
				String firstGoodsImg = commonGoodsService.getFirstGoodsImg(pcLimitGoodsDto.getGoodsId());
				pcLimitGoodsDto.setGoodsImage(firstGoodsImg);
			}
			dto.setActivityGoods(selectLimitGoods);
		}*/
		pageBean.setPageSize(6);
		return list;
	}
	
}
