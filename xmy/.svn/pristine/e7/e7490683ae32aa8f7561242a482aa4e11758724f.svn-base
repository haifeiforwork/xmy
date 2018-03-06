package com.zfj.xmy.goods.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.PcGoodsSetingMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.PcGoodsSeting;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.goods.persistence.common.dao.GoodsExMapper;
import com.zfj.xmy.goods.persistence.pc.dao.PcGoodsExMapper;
import com.zfj.xmy.goods.service.cms.PcGoodsSetService;
@Service
public class PcGoodsSetServiceImpl extends BaseService implements PcGoodsSetService{
	
	@Autowired
	private PcGoodsSetingMapper goodsSetingMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	/**
	 * 分页查询PC商品设置的信息
	 */
	@Override
	public List<PcGoodsSeting> findPcGoodsSeting(ReqData reqData, PageBean pageBean) {
		List<PcGoodsSeting> selectByExampleAndPage = goodsSetingMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		for (PcGoodsSeting pcGoodsSeting : selectByExampleAndPage) {
			Goods goods = goodsMapper.selectByPrimaryKey(pcGoodsSeting.getGoodsId());
			if (ObjectUtil.isNotNull(goods)) {
				pcGoodsSeting.setGoodsName(goods.getName());
				pcGoodsSeting.setGoodsPrice(goods.getPrice());
				pcGoodsSeting.setStandard(goods.getStandard());
				pcGoodsSeting.setGoodsImage(commonGoodsService.getFirstGoodsImg(goods.getId()));
			}
		}
		int countpage = goodsSetingMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(countpage);
		return selectByExampleAndPage;
	}
	/**
	 * 查询pc商品总条数
	 */
	@Override
	public Integer countPcgoods(ReqData reqData) {
		int countByExample = goodsSetingMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return countByExample;
	}
	/**
	 * 添加商品
	 */
	@Override
	public void addPcGoods(Integer type, Long goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		PcGoodsSeting goodsSeting = new PcGoodsSeting();
		goodsSeting.setType(type);
		goodsSeting.setGoodsId(goods.getId());
/*		goodsSeting.setGoodsName(goods.getName());
		goodsSeting.setGoodsPrice(goods.getPrice());
		goodsSeting.setGoodsImage(commonGoodsService.getFirstGoodsImg(goods.getId()));
		goodsSeting.setStandard(goods.getStandard());*/
		if(type.equals(SystemConstant.PCGOODSSET.HOTGOODS)){
			goodsSeting.setName("热卖商品");
		}else if(type.equals(SystemConstant.PCGOODSSET.NEWGOODS)){
			goodsSeting.setName("新品上市");
		}else{
			goodsSeting.setName("热销商品");
		}
		goodsSetingMapper.insertSelective(goodsSeting);
	}
	/**
	 * 删除单个商品
	 */
	@Override
	public void deletePcGoods(Integer typeid, Long goodsid) {
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", goodsid, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", typeid, SystemConstant.REQ_PARAMETER_EQ);
		goodsSetingMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	/**
	 * 删除多个商品
	 */
	@Override
	public void deletePcGood(String ids) {
		ReqData reqData = new ReqData();
		reqData.putValue("id", ids, SystemConstant.REQ_PARAMETER_IN);
		goodsSetingMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}

}
