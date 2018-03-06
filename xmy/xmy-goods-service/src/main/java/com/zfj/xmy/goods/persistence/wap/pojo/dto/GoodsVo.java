package com.zfj.xmy.goods.persistence.wap.pojo.dto;

import java.math.BigDecimal;

public class GoodsVo {
	
	private Long id; //表id
	private Long goodsId; //商品id
	private String name;    //收藏的商品名称
	private String imgPath;	//默认图片地址
	private String code;	//商品编码
	private String price;	//销售价
	private BigDecimal phonePriN;	//手机端销售价格
	private BigDecimal vipPricN;	//会员价
	private Integer sumDeal;	//商品成交量
	private Long maxId;
	private Long minId;
	
	public GoodsVo() {}

	public GoodsVo(Long id, String name, String imgPath, String code,
			String price, BigDecimal phonePriN, BigDecimal vipPricN,
			Integer sumDeal) {
		super();
		this.id = id;
		this.name = name;
		this.imgPath = imgPath;
		this.code = code;
		this.price = price;
		this.phonePriN = phonePriN;
		this.vipPricN = vipPricN;
		this.sumDeal = sumDeal;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public BigDecimal getPhonePriN() {
		return phonePriN;
	}

	public void setPhonePriN(BigDecimal phonePriN) {
		this.phonePriN = phonePriN;
	}

	public BigDecimal getVipPricN() {
		return vipPricN;
	}

	public void setVipPricN(BigDecimal vipPricN) {
		this.vipPricN = vipPricN;
	}

	public Integer getSumDeal() {
		return sumDeal;
	}

	public void setSumDeal(Integer sumDeal) {
		this.sumDeal = sumDeal;
	}


	public Long getMinId() {
		return minId;
	}

	public void setMinId(Long minId) {
		this.minId = minId;
	}

	public Long getMaxId() {
		return maxId;
	}

	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}
	
}
