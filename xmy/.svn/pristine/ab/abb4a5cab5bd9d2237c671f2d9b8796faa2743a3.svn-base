package com.zfj.xmy.elasticsearch.document;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.zfj.xmy.elasticsearch.XmyIndex;
/**
 * 商品索引文档
 * @author dengq
 * @createDate 2017年8月7日
 * @desription
 */
@Document(indexName= XmyIndex.GOODS_INDEX_NAME, type= XmyIndex.GOODS_TYPE)
public class GoodsDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品的Id
	 */
	@Id
	private Long id ;
	
	/**
	 * 商品名称
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String fullName ;
	/**
	 * 商品名称拼音专用
	 */
	@Field(type=FieldType.String,analyzer="pinyin",searchAnalyzer="pinyin")
	private String pyName;
	/**
	 * 商品分类
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String categoryName ;
	/**
	 * 关键字
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String keywords ;
	/**
	 * 拼音关键字
	 */
	@Field(type=FieldType.String,analyzer="pinyin",searchAnalyzer="pinyin")
	private String pyKeywords ;

	/**
	 * 商品品牌
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String brandName ;
	
	/**
	 * 是否国产
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer mainLand ;
	
	/**
	 * 商品的销售价
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer price ;
	
	/**
	 * 商品图片
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String imgPath ;
	
	/**
	 * 商品成交量
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer sumDeal ;
	
	/**
	 * 商品评论总量
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer sumComment ;
	
	/**
	 * 上架时间
	 */
	@Field(type=FieldType.Date,index=FieldIndex.not_analyzed)
	private Date publishTime ;
	/**
	 * 配置范围：0：重庆城区，1：全国配置
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer distrRange ;
	/**
	 * 商品对应分词
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String wordSeg;
	/**
	 * 商品规格
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String standard;
	
	/**
	 * 商品单位
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String unitName;
	
	/**
	 * 一级分类ID
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String topId;
	
	/**
	 * 二级分类ID
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String typeId;
	
	/**
	 * 分词Id
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String wordsId;
	
	/**
	 * 活动类型 0买即赠 1促销活动 2 限时活动
	 */
	@Field(type=FieldType.Integer,index=FieldIndex.not_analyzed)
	private Integer activityType;
	
	/**
	 * 活动id 
	 */
	@Field(type=FieldType.Long,index=FieldIndex.not_analyzed)
	private Long activityId;
	
	/**
	 * 活动图标
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String activityImg;
	
	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getActivityImg() {
		return activityImg;
	}

	public void setActivityImg(String activityImg) {
		this.activityImg = activityImg;
	}

	public String getTopId() {
		return topId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getWordsId() {
		return wordsId;
	}

	public void setWordsId(String wordsId) {
		this.wordsId = wordsId;
	}

	public String getPyKeywords() {
		return pyKeywords;
	}

	public void setPyKeywords(String pyKeywords) {
		this.pyKeywords = pyKeywords;
	}
	
	public String getPyName() {
		return pyName;
	}

	public void setPyName(String pyName) {
		this.pyName = pyName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getWordSeg() {
		return wordSeg;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public void setWordSeg(String wordSeg) {
		this.wordSeg = wordSeg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getSumDeal() {
		return sumDeal;
	}

	public void setSumDeal(Integer sumDeal) {
		this.sumDeal = sumDeal;
	}

	public Integer getSumComment() {
		return sumComment;
	}

	public void setSumComment(Integer sumComment) {
		this.sumComment = sumComment;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getMainLand() {
		return mainLand;
	}

	public void setMainLand(Integer mainLand) {
		this.mainLand = mainLand;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getDistrRange() {
		return distrRange;
	}

	public void setDistrRange(Integer distrRange) {
		this.distrRange = distrRange;
	}
	
}
