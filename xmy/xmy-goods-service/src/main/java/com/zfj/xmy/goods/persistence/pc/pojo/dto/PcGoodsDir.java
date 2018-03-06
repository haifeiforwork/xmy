package com.zfj.xmy.goods.persistence.pc.pojo.dto;  

import java.math.BigDecimal;

import com.zfj.xmy.common.persistence.pojo.Goods;


/**  分类属性和商品属性
 * @Title: CategoryGoodsDir.java 
 * @Package com.zfj.xmy.goods.persistence.pc.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月10日 下午4:53:56 
 */
public class PcGoodsDir {
	
	private long goodsId; //商品id
	
	private String goodsName ; //商品名称
	
	private BigDecimal price ; //商品价格
	
	private String imgPath ; //商品图片 主图



	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

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
  