package com.zfj.xmy.goods.persistence.wap;

import java.math.BigDecimal;

/**
 * 搜索商品条件传输对象
 * @author cj
 * @createDate 2017年10月28日
 *
 */
public class SearchGoodsDto {
	
	private String goodsName ;
	
	private Boolean priceOrder = true ;
	
	private Integer orderMethod  = 0 ;
	
	private Integer mianLand ;
	
	private String wordSeg ;
	
	private BigDecimal beginPrice ;
	
	private BigDecimal endPrice ;
	
	private Long typeId;
	
	private Long wordId;
	
	private String search;

	private Integer isDivle ;
	
	private String searchName;
	
	private String typeName;//限制分类名称
	
	private Long oneId;
	
	private String flag;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Boolean getPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(Boolean priceOrder) {
		this.priceOrder = priceOrder;
	}

	public Integer getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(Integer orderMethod) {
		this.orderMethod = orderMethod;
	}

	public Integer getMianLand() {
		return mianLand;
	}

	public void setMianLand(Integer mianLand) {
		this.mianLand = mianLand;
	}

	public String getWordSeg() {
		return wordSeg;
	}

	public void setWordSeg(String wordSeg) {
		this.wordSeg = wordSeg;
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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getIsDivle() {
		return isDivle;
	}

	public void setIsDivle(Integer isDivle) {
		this.isDivle = isDivle;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Long getOneId() {
		return oneId;
	}

	public void setOneId(Long oneId) {
		this.oneId = oneId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
