package com.zfj.xmy.goods.persistence.pc.pojo.dto;

import java.math.BigDecimal;

import com.zfj.xmy.common.persistence.pojo.Order;


public class PcOrderDto extends Order {
	
	private Integer goodsNum;//商品数量
	private BigDecimal price;//商品价格
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
