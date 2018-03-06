package com.zfj.xmy.oa.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.oa.XmyOaGoodsService;
import com.zfj.xmy.oa.dto.OaGoodsDto;

/** 
 * @Title: XmyOaGoodsServiceImpl.java 
 * @Package com.zfj.xmy.oa.manager 
 * @Description: 
 * @author hexw
 * @date 2018年1月15日 下午3:27:16 
 */
@Service
public class XmyOaGoodsServiceImpl extends BaseService implements XmyOaGoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public int xmyOaUpdateGoods(OaGoodsDto dto){
		int result = 1;
		if (ObjectUtil.isNotNull(dto)) {
			if (!StringUtil.isEmpty(dto.getGoodsCode())) {
				ReqData reqData = new ReqData();
				reqData.putValue("code", dto.getGoodsCode(), SystemConstant.REQ_PARAMETER_EQ);
				List<GoodsWithBLOBs> list = goodsMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData));
				System.out.println("查询结果："+list.size());
				if (list.size() > 0) {
					GoodsWithBLOBs goods = list.get(0);
					if (ObjectUtil.isNotNull(goods)) {
						goods.setName(dto.getNewName());
						goods.setStandard(dto.getNewStandard());
						goods.setMarketPrice(dto.getNewMarketPrice());
						goods.setCostPrice(dto.getNewCostPrice());
						goods.setPrice(dto.getNewPrice());
						goodsMapper.updateByPrimaryKeySelective(goods);
						System.out.println("修改过后的成本价："+goods.getCostPrice());
						result= 0 ;
					}
				}
			}
		}
		return result ;
	}
	
	
}
  