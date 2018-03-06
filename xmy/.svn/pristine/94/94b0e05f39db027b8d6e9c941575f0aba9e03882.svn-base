package com.zfj.xmy.goods.service.wap.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.common.service.OnLineActivityService;
import com.zfj.xmy.goods.persistence.pc.dao.PcGoodsExMapper;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;
import com.zfj.xmy.goods.persistence.wap.dao.GoodsVoExMapper;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsVo;
import com.zfj.xmy.goods.service.common.GoodsService;
import com.zfj.xmy.goods.service.wap.WapGoodsService;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;

@Service
public class WapGoodsServiceImpl implements WapGoodsService{
	
	@Autowired
	private LimitGoodsMapper limitGoodsMapper;
	@Autowired
	private PcGoodsExMapper goodsExMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private CommonGoodsService commonGoodsService;
	@Autowired
	private GoodsVoExMapper goodsVoExMapper;
	
	
	@Override
	public LimitGoods getActivityNum(Long activityId,Long goodsId){
		
		ReqData reqData = new ReqData();
		reqData.putValue("limit_id", activityId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<LimitGoods> list = limitGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(list.size() > 0) return list.get(0);
		
		return null;
	}
	
	@Override
	public List<PcGoodsDir> findBorderGoods(String name) {
		ReqData reqData=new ReqData();
		List<PcGoodsDir> boder=new ArrayList<PcGoodsDir>();
		reqData.putValue("category_name", name,SystemConstant.REQ_PARAMETER_CN);
		List<Goods> list=goodsExMapper.findBorderGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for(Goods goods:list){
			reqData.clearValue();
			reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
			PcGoodsDir goodsDir=new PcGoodsDir();
			goodsDir.setGoodsId(goods.getId());
			goodsDir.setGoodsName(goods.getName());
			String img = "";
			if (name.contains("购物卡")) {
				img = commonGoodsService.getCustomizationImageWap(goodsDir.getGoodsId());
			} else {
				img = commonGoodsService.getSecondGoodsImg(goodsDir.getGoodsId());
			}
			if(StringUtil.isEmpty(img)){
				img="resource/commons/images/defaultgoods.jpg";
			}
			goodsDir.setImgPath(img);
			goodsDir.setPrice(goods.getPrice());
			boder.add(goodsDir);
		}
		return boder;
	}

	@Override
	public Boolean isPutaway(Long goodsId) {
		
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(goods != null && goods.getIsPutway() == 1) return true;
		else return false;
		
	}

	@Override
	public List<Goods> roundGoods(Integer num, Long goodsId) {
		
		List<Goods> list = new ArrayList<Goods>();
		for(int i=0;i<num;i++ ){
			GoodsVo vo= goodsVoExMapper.getGoodsId();
			if(vo.getMinId() != null && vo.getMaxId() != null){
				Long id = (long)((Math.random()*(vo.getMaxId() - vo.getMinId()))) + vo.getMinId() ;
				Goods goods = goodsMapper.selectByPrimaryKey(id);
				if(goods != null && goods.getIsPutway() != 1){
					boolean repeat = false;
					if(goodsId != null){
						if(goodsId.longValue() == goods.getId().longValue()){
							i--;
							continue;
						}
					}
					if(list.size() <= 0)list.add(goods);
					else{
						for (Goods g : list) {
							if(g.getId().longValue() == goods.getId().longValue()){
								repeat = true;
							}
						}
						if(!repeat) list.add(goods);
						else i--;
					}
				}else{
					i--;
				}
			}
		}
		if (list.size() > 0) {
		    Iterator<Goods> it = list.iterator();  
		    while(it.hasNext()){  
		    	Goods goods = it.next(); 
		    	goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
		        if(StringUtil.isEmpty(goods.getImgPath())){    //排除没有图片的商品
		            it.remove();  
		        }  
		     } 
		}
		return list;
	}
	
}
