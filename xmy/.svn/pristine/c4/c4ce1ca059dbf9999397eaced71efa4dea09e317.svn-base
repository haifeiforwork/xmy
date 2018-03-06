package com.zfj.xmy.order.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.KdniaoExpCodeMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.KdniaoExpCode;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.order.persistence.app.dao.AppOrderExMapper;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppGoodsVo;
import com.zfj.xmy.order.service.common.CommonOrderService;
import com.zfj.xmy.util.BaiduUtil;
import com.zfj.xmy.util.LogisticsUtil;
import com.zfj.xmy.util.kdniao.KdniaoTrackQueryAPI;

@Service
public class CommonOrderServiceImpl implements CommonOrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AppOrderExMapper appOrderExMapper;
	
	@Autowired
	private KdniaoExpCodeMapper kdniaoExpCodeMapper;
	
	@Override
	public JSONObject getOrderLogisticsInfo(Long orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (ObjectUtil.isNull(order)) {
			throw new BusinessException("订单信息不正确") ;
		}
		
		String logisticsNo = order.getLogisticsNo();
		if (StringUtil.isEmpty(logisticsNo)) {
			throw new BusinessException("物流信息不存在") ;
		}
		
		Long logisticsType = order.getLogisticsType();
		
		JSONObject bodyjo = new JSONObject();
		
		if (SystemConstant.ORDER.LOGISTICS_TYPE_NOMARL.equals(logisticsType)) {
			bodyjo.put("logisticsType", SystemConstant.ORDER.LOGISTICS_TYPE_NOMARL);
			try {
				String res = LogisticsUtil.getOrderStatus(logisticsNo);
				if (StringUtil.isEmpty(res)) {
					throw new BusinessException("查询物流信息出错") ;
				}
				JSONObject jo = new JSONObject(res);
				jo.put("logisticsNo", logisticsNo);
				JSONObject gps = jo.getJSONObject("gps");
				//地址信息不空则获取地址
				if (ObjectUtil.isNotNull(gps)) {
					String lat = gps.getStr("lat");
					String lng = gps.getStr("lon");
					String baidures = BaiduUtil.getAdressByGeo(lat, lng);
					try {
						JSONObject baiduJo = new JSONObject(baidures);
						if (0 == baiduJo.getInt("status")) {
							jo.put("address", baiduJo.getJSONObject("result").getStr("formatted_address"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				bodyjo.put("logisticsData", jo);
				return bodyjo;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("查询物流信息出错了") ;
			}
		}else if (SystemConstant.ORDER.LOGISTICS_TYPE_OTHER.equals(logisticsType)) {
			bodyjo.put("logisticsType", SystemConstant.ORDER.LOGISTICS_TYPE_OTHER);
			String KdniaoExpCode = order.getKdniaoExpCode();
			try {
				String kdniaoJson = KdniaoTrackQueryAPI.getOrderTracesByJson(KdniaoExpCode, logisticsNo);
				JSONObject jo = new JSONObject(kdniaoJson);
				jo.put("logisticsNo", logisticsNo);
				jo.put("name", getByExpCode(KdniaoExpCode).getName());
				bodyjo.put("logisticsData", jo);
				return bodyjo;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("快递鸟物流信息查询失败") ;
			}
		}else {
			throw new BusinessException("物流类型查询出错") ;
		}
		
	}
	
	/**
	 * 根据物流编号查询记录
	 * @param KdniaoExpCode
	 * @return
	 * @Description 
	 * @date 2017年12月15日  下午5:05:44
	 * @author wy
	 * 2017
	 * @return KdniaoExpCode
	 */
	private KdniaoExpCode getByExpCode(String KdniaoExpCode){
		CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.equalTo("code", KdniaoExpCode);
		List<KdniaoExpCode> kdniaoExpCodes = kdniaoExpCodeMapper.selectByExample(parameter);
		if (!CollectionUtils.isEmpty(kdniaoExpCodes)) {
			return kdniaoExpCodes.get(0);
		}else {
			return new KdniaoExpCode();
		}
		
	}
	
	@Override
	public Boolean checkOrderGoodsIsAcrossBorders(List<OrderGoods> list){
		boolean flag = true ;
		ReqData reqData = new  ReqData();
		for (OrderGoods orderGoods : list) {
			reqData.clearValue();
			reqData.putValue("a.goods_id", orderGoods.getGoodsId(), SystemConstant.REQ_PARAMETER_EQ);
			List<String> categoryNames = appOrderExMapper.findCategoryName(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (String categoryName : categoryNames) {
				if (!StringUtils.isEmpty(categoryName)) {
					if(categoryName.indexOf(SystemConstant.TERMDATA.ACROSSBORDERS)!=-1){ //包含跨境两个字
						return false ;
					}
				}
			}
		}
		return flag;
		
	}

}
