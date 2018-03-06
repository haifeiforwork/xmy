package com.zfj.xmy.activity.persistence.pc.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Goods;

public class PcIndexGoodsDto{
	
	private String categoryName;//分词名称
	private Long categoryId;//分词Id
	private List<Goods> goodsList;//商品集合
	private Long parentId;//父级id
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
