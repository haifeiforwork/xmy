package com.zfj.xmy.goods.persistence.pc.pojo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;

public class PcGoodsDto extends Goods {

	private String pcIntroduced;
	private String phIntroduced;
	private List<String> imageList;//商品图片路径
	
	private List<Map<String,Object>> goodsstandards = new ArrayList<Map<String,Object>>(); //商品规格
	
	private String activityName;//活动名称
	
	private Integer activityType;//活动类型
	
	private BigDecimal acitivityPrice;//活动价格
	
	private Date endTime;//活动结束时间
	
	private Integer sealNum;//活动剩余数量
	
	private Integer limitNum;//限购数量
	
	private Long activityId;//活动ID
	
	private String firstTypeName;//一级分类名称
	
	private String scendTypeName;//二级分类名称
	
	private String thirdTypeName;//三级分类名称
	
	private String prentsentName;//赠品名称
	
	private String waterImg ; //商品水印图片
	
	private String freezingActivityTime ; //冰点价商品活动时间  12月13日
	
	private String descriptionImg; //一级分类描述图
	
	public String getPrentsentName() {
		return prentsentName;
	}

	public void setPrentsentName(String prentsentName) {
		this.prentsentName = prentsentName;
	}

	public String getFirstTypeName() {
		return firstTypeName;
	}

	public void setFirstTypeName(String firstTypeName) {
		this.firstTypeName = firstTypeName;
	}

	public String getScendTypeName() {
		return scendTypeName;
	}

	public void setScendTypeName(String scendTypeName) {
		this.scendTypeName = scendTypeName;
	}

	public String getThirdTypeName() {
		return thirdTypeName;
	}

	public void setThirdTypeName(String thirdTypeName) {
		this.thirdTypeName = thirdTypeName;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getSealNum() {
		return sealNum;
	}

	public void setSealNum(Integer sealNum) {
		this.sealNum = sealNum;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public BigDecimal getAcitivityPrice() {
		return acitivityPrice;
	}

	public void setAcitivityPrice(BigDecimal acitivityPrice) {
		this.acitivityPrice = acitivityPrice;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPcIntroduced() {
		return pcIntroduced;
	}

	public void setPcIntroduced(String pcIntroduced) {
		this.pcIntroduced = pcIntroduced;
	}

	public String getPhIntroduced() {
		return phIntroduced;
	}

	public void setPhIntroduced(String phIntroduced) {
		this.phIntroduced = phIntroduced;
	}


	public List<Map<String, Object>> getGoodsstandards() {
		return goodsstandards;
	}

	public void setGoodsstandards(List<Map<String, Object>> goodsstandards) {
		this.goodsstandards = goodsstandards;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public String getWaterImg() {
		return waterImg;
	}

	public void setWaterImg(String waterImg) {
		this.waterImg = waterImg;
	}

	public String getFreezingActivityTime() {
		return freezingActivityTime;
	}

	public void setFreezingActivityTime(String freezingActivityTime) {
		this.freezingActivityTime = freezingActivityTime;
	}

	public String getDescriptionImg() {
		return descriptionImg;
	}

	public void setDescriptionImg(String descriptionImg) {
		this.descriptionImg = descriptionImg;
	}
	
	
	
	
}
