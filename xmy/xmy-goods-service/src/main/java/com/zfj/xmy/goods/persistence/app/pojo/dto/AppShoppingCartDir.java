package com.zfj.xmy.goods.persistence.app.pojo.dto;  

import java.math.BigDecimal;

/** 
 * @Title: AppShoppingCartDir.java 
 * @Package com.zfj.xmy.goods.persistence.app.pojo.dto 
 * @Description:   购物车
 * @author hexw
 * @date 2017年7月27日 下午3:22:17 
 */
public class AppShoppingCartDir {

	private long userId;
	
	private long goodsId; 
	
	private long activityId; //活动id
	
	private Integer activityType; //活动类型
	
	private long presentGoodsId; //赠品id
	
	private String presentName; //赠品名称
	
	private BigDecimal activityPrice; //商品活动价格
	
	private String name; //商品名称
	
	private Integer remarkNum; //数量备注
	
	private String unitName; //单位名称
	
	private String weight;
	
	private String standard; //商品规格
	
	private BigDecimal price; //销售价
	
	private BigDecimal vipPrice; //会员价
	
	private String supplierName ; //供应商
	
	private String imgPath;  //商品图片
	
	private Integer isPutway ;  //上架状态 0 ： 上架 1： 下架
	
	private Integer num;  //添加到购物车的数量
	
	private Integer goodsNum; //库存
	
	private Long actId;//积分活动id
	
	private Integer actPoints;//兑换需要的积分
	
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRemarkNum() {
		return remarkNum;
	}

	public void setRemarkNum(Integer remarkNum) {
		this.remarkNum = remarkNum;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getIsPutway() {
		return isPutway;
	}

	public void setIsPutway(Integer isPutway) {
		this.isPutway = isPutway;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}


	public long getPresentGoodsId() {
		return presentGoodsId;
	}

	public void setPresentGoodsId(long presentGoodsId) {
		this.presentGoodsId = presentGoodsId;
	}

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Long getActId() {
		return actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	public Integer getActPoints() {
		return actPoints;
	}

	public void setActPoints(Integer actPoints) {
		this.actPoints = actPoints;
	}
	
}
  