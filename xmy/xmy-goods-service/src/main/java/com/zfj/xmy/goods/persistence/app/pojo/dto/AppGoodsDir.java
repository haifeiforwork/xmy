package com.zfj.xmy.goods.persistence.app.pojo.dto;  

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zfj.xmy.common.persistence.pojo.Goods;

/** 
 * @Title: AppGoodsDto.java 
 * @Package com.zfj.xmy.goods.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年7月25日 下午7:33:27 
 */
@JsonInclude(value=Include.NON_NULL)
public class AppGoodsDir {

	
	/***********************************************商品详情用到的字段********************************************/
	
	private long activityId;  //活动id
	
	private Integer activityType ; //活动类型  主要用于区分活动属于那张表
	
	private Date  endTime; //活动时间
	
	private Date currentTime; // 当前时间
	
	private long presentGoodsId; //赠品id
	
	private String presentName; //赠品名称
	
	private String presentFullName; //赠品全名称
	
	private BigDecimal activityPrice; //活动价格
	
	private Integer isCollect; //是否收藏 0：已收藏 1 ：未收藏
	
	private long goodsId;
	
	private String name;
	
	private String code; //编码
	
	private Integer remarkNum; //数量备注
	
	private String unitName; //单位名称
	
	private String standard; //商品规格
	
	private String weight;
	
	private BigDecimal price; //销售价
	
	private BigDecimal vipPrice; //会员价
	
	private String supplierName ; //供应商
	
	private Integer defaultBuynum; //默认购买数量
	
	private Integer num; //库存
	
	private Integer sumDeal; //商品成交量
	
	private Integer points; //商品积分
	
	private Integer isDelivery; //是否全国配送
	
	private String area; //产地
	
	private String delivery ; //配送范围 
	
	private String storeageMethod ; //存储方式
	
	private String packMethod ; //包装方式
	
	private String phIntroduced; //手机端详细介绍
	
	private List<Map<String,Object>> Goodsstandards = new ArrayList<Map<String,Object>>(); //商品规格
	
	private List<String>  images = new ArrayList<String>(); //商品图片
	
	private Float goodsComment;  //商品评价
	
	private Integer spendPoints;  //购买商品所需的积分 (积分兑换商品)
	
	private long spendPointsActivityId; //购买商品所需的积分 对应的活动id

	
	private Integer isPutway; //商品是否下架 (0.正常，1 下架)
	
	private String shareUrl;  //商品分享地址(wap)
	
	private String categoryDescriptionImg; //商品分类描述图
	
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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

	public Integer getDefaultBuynum() {
		return defaultBuynum;
	}

	public void setDefaultBuynum(Integer defaultBuynum) {
		this.defaultBuynum = defaultBuynum;
	}

	public Integer getSumDeal() {
		return sumDeal;
	}

	public void setSumDeal(Integer sumDeal) {
		this.sumDeal = sumDeal;
	}

	public String getPhIntroduced() {
		return phIntroduced;
	}

	public void setPhIntroduced(String phIntroduced) {
		this.phIntroduced = phIntroduced;
	}

	public List<Map<String, Object>> getGoodsstandards() {
		return Goodsstandards;
	}

	public void setGoodsstandards(List<Map<String, Object>> goodsstandards) {
		Goodsstandards = goodsstandards;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}



	public Float getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(Float goodsComment) {
		this.goodsComment = goodsComment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public String getStoreageMethod() {
		return storeageMethod;
	}

	public void setStoreageMethod(String storeageMethod) {
		this.storeageMethod = storeageMethod;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPackMethod() {
		return packMethod;
	}

	public void setPackMethod(String packMethod) {
		this.packMethod = packMethod;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getSpendPoints() {
		return spendPoints;
	}

	public void setSpendPoints(Integer spendPoints) {
		this.spendPoints = spendPoints;
	}

	public String getPresentFullName() {
		return presentFullName;
	}

	public void setPresentFullName(String presentFullName) {
		this.presentFullName = presentFullName;
	}

	public long getSpendPointsActivityId() {
		return spendPointsActivityId;
	}

	public void setSpendPointsActivityId(long spendPointsActivityId) {
		this.spendPointsActivityId = spendPointsActivityId;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public Integer getIsPutway() {
		return isPutway;
	}

	public void setIsPutway(Integer isPutway) {
		this.isPutway = isPutway;
	}

	public String getCategoryDescriptionImg() {
		return categoryDescriptionImg;
	}

	public void setCategoryDescriptionImg(String categoryDescriptionImg) {
		this.categoryDescriptionImg = categoryDescriptionImg;
	}
	
	
}
  