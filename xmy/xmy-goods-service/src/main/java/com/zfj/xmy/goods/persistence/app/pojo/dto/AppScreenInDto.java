package com.zfj.xmy.goods.persistence.app.pojo.dto;

import java.math.BigDecimal;

/**
 * @author lij
 * 筛选商品的实体类（根据该实体类条件筛选商品）
 */
public class AppScreenInDto {
	
	private String screenName;//商品名称
	private BigDecimal beginPrice;//价格区间 开始价格
	private BigDecimal endPrice;//价格区间 结束价格
	private Integer isDelivery;//是否支持全国配送 0; 是 1：否
	private Integer pageIndex = 1;//页数
	private Long typeId;//分类ID
	private Long wordsId;//分词Id
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getWordsId() {
		return wordsId;
	}
	public void setWordsId(Long wordsId) {
		this.wordsId = wordsId;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public BigDecimal getBeginPrice() {
		return beginPrice;
	}
	public void setBeginPrice(BigDecimal beginPrice) {
		this.beginPrice = beginPrice;
	}
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	
}
