package com.zfj.xmy.goods.persistence.pc.pojo.dto;  

import java.util.ArrayList;
import java.util.List;

/** 
 * @Title: PcCategoryDir.java 
 * @Package com.zfj.xmy.goods.persistence.pc.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月11日 下午2:10:48 
 */
public class PcCategoryGoodsDir {
	
	
	private long categoryId; //二级分类id

	private String categoryName; //二级分类名称
	
	private String adImg ; //广告图片
	
	private String adUrl ; //广告跳转链接
	
	private Integer type ; // 广告类型 0 存的商品ID 1 存的跳转地址 
	
	private List<PcGoodsDir> list = new  ArrayList<PcGoodsDir>(); //商品集合
	

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAdImg() {
		return adImg;
	}

	public void setAdImg(String adImg) {
		this.adImg = adImg;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public List<PcGoodsDir> getList() {
		return list;
	}

	public void setList(List<PcGoodsDir> list) {
		this.list = list;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	
	
	
	

}
  