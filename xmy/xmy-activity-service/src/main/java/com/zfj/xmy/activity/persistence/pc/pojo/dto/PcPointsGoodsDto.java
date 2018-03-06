package com.zfj.xmy.activity.persistence.pc.pojo.dto;

import java.math.BigDecimal;

import com.zfj.xmy.common.persistence.pojo.PointsGoods;

public class PcPointsGoodsDto extends PointsGoods{
	
	private String goodsName;
	
	private BigDecimal price;
	
	private String imgPath;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
