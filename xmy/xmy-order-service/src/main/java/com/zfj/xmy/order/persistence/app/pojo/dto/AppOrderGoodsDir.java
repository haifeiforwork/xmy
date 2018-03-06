package com.zfj.xmy.order.persistence.app.pojo.dto;  

import java.math.BigDecimal;

/** 
 * @Title: AppOrderGoodsDir.java 
 * @Package com.zfj.xmy.order.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 下午7:08:41 
 */


public class AppOrderGoodsDir {
		
	private long goodsId; //商品id
	
	private long activityId; //活动id
	
	private long presentGoodsId; //赠品id 
	
	private String presentGoodsName;//赠品名字
	
	private Long pointsActId;//积分活动id
	
	private Integer actPoints;//消耗积分
	
	private Integer sumActPoints;//消耗总积分
	
	private Integer activityType;  //活动类型
	
	private Integer num; //商品数量
	
	private String name;
	
	private String supplierName ; //供应商
	
	private String unitName; //单位名称
	
	private String standard; //商品规格
	
	private String img; //商品图片
	
	private String weight; //商品重量
	
	private BigDecimal price; //价格
	
	private String categoryName ; //一级分类名称
	
	private Integer points; //商品积分
	
	private BigDecimal sumPrice; //商品总价
	
	
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
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

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getPresentGoodsName() {
		return presentGoodsName;
	}

	public void setPresentGoodsName(String presentGoodsName) {
		this.presentGoodsName = presentGoodsName;
	}

	public Long getPointsActId() {
		return pointsActId;
	}

	public void setPointsActId(Long pointsActId) {
		this.pointsActId = pointsActId;
	}

	public Integer getActPoints() {
		return actPoints;
	}

	public void setActPoints(Integer actPoints) {
		this.actPoints = actPoints;
	}

	public Integer getSumActPoints() {
		return sumActPoints;
	}

	public void setSumActPoints(Integer sumActPoints) {
		this.sumActPoints = sumActPoints;
	}
	
	
	
}
  