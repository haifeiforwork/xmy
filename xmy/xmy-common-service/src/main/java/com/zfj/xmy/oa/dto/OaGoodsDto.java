package com.zfj.xmy.oa.dto;  

import java.math.BigDecimal;

/** 
 * @Title: OaGoodsDto.java 
 * @Package com.zfj.xmy.oa 
 * @Description:  xmyOa系统 商品dto
 * @author hexw
 * @date 2018年1月15日 下午3:13:30 
 */
public class OaGoodsDto {

	private String goodsCode; //商品编码
	
	private String newName ; //商品名称
	
	private String newStandard ;// 商品规格
	
	private BigDecimal newMarketPrice ; //市场价
	
	private BigDecimal newCostPrice ; //成本价
	
	private BigDecimal newPrice ; //销售价

	

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewStandard() {
		return newStandard;
	}

	public void setNewStandard(String newStandard) {
		this.newStandard = newStandard;
	}

	public BigDecimal getNewMarketPrice() {
		return newMarketPrice;
	}

	public void setNewMarketPrice(BigDecimal newMarketPrice) {
		this.newMarketPrice = newMarketPrice;
	}

	public BigDecimal getNewCostPrice() {
		return newCostPrice;
	}

	public void setNewCostPrice(BigDecimal newCostPrice) {
		this.newCostPrice = newCostPrice;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}
	
	
}
  