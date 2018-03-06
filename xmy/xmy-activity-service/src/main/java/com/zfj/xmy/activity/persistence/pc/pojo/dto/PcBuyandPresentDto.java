package com.zfj.xmy.activity.persistence.pc.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Goods;

public class PcBuyandPresentDto extends BuyAndPresent{
	
	private Goods buyGoods;//购买的额商品
	
	private Goods presentGoods;//赠送的商品

	public Goods getBuyGoods() {
		return buyGoods;
	}

	public void setBuyGoods(Goods buyGoods) {
		this.buyGoods = buyGoods;
	}

	public Goods getPresentGoods() {
		return presentGoods;
	}

	public void setPresentGoods(Goods presentGoods) {
		this.presentGoods = presentGoods;
	}

	
	
	
}
