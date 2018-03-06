package com.zfj.xmy.activity.service.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcBuyandPresentDto;
import com.zfj.xmy.activity.service.pc.PcBuyandPresentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.util.StringUtils;
@Service
public class PcBuyandPresentServiceImpl extends BaseService implements PcBuyandPresentService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private BuyAndPresentMapper andPresentMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	/**
	 * 查询买即赠的全部商品
	 */
	@Override
	public List<PcBuyandPresentDto> findPcBuyandPresentDto(PageBean pageBean) {
		List<PcBuyandPresentDto> buyandPresentDtos = new ArrayList<PcBuyandPresentDto>();
		//查询前三条记录
		pageBean.setPageSize(3);
		ReqData reqData = new ReqData();
		List<BuyAndPresent> selectByExampleAndPage = andPresentMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		for (BuyAndPresent buyAndPresent : selectByExampleAndPage) {
			PcBuyandPresentDto presentDto = new PcBuyandPresentDto();
			presentDto.setId(buyAndPresent.getId());
			presentDto.setEndTime(buyAndPresent.getEndTime());
			presentDto.setStartTime(buyAndPresent.getStartTime());
			//加入购买商品信息
			List<Object> exchangeSplit = StringUtils.exchangeSplit(buyAndPresent.getMainGoodsIds());
			Long mianGoodsId = Long.parseLong((String)exchangeSplit.get(0));
			//要购买的商品
			Goods mianGoods = goodsMapper.selectByPrimaryKey(mianGoodsId);
			mianGoods.setImgPath(commonGoodsService.getFirstGoodsImg(mianGoods.getId()));
			//赠送的商品
			List<Object> exchangeSplit2 = StringUtils.exchangeSplit(buyAndPresent.getGiftGoodsIds());
			Long giftGodosId = Long.parseLong((String)exchangeSplit2.get(0));
			Goods giftGoods = goodsMapper.selectByPrimaryKey(giftGodosId);
			giftGoods.setImgPath(commonGoodsService.getFirstGoodsImg(giftGoods.getId()));
			presentDto.setBuyGoods(mianGoods);
			presentDto.setPresentGoods(giftGoods);
			buyandPresentDtos.add(presentDto);
		}
		return buyandPresentDtos;
	}
	
}
