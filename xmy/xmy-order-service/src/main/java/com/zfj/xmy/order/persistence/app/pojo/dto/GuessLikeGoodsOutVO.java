package com.zfj.xmy.order.persistence.app.pojo.dto;

import java.math.BigDecimal;

public class GuessLikeGoodsOutVO {
	// 商品
	private Long id;
	//商品名称
	private String name;
	//商品价格
	private BigDecimal price;
	//手机价格
	private BigDecimal phonePrice;
	//vip价格
	private BigDecimal vipPrice;
	//图片地址
	private String imgPath;
	
	public GuessLikeGoodsOutVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GuessLikeGoodsOutVO(Long id, String name, BigDecimal price,
			BigDecimal phonePrice, BigDecimal vipPrice) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.phonePrice = phonePrice;
		this.vipPrice = vipPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPhoneprice() {
		return phonePrice;
	}
	public void setPhoneprice(BigDecimal phonePrice) {
		this.phonePrice = phonePrice;
	}
	public BigDecimal getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
