package com.zfj.xmy.common.persistence.pojo.app;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.AdImage;

public class AppAdvertisementOutDto {
	// 广告名称
	private String name;
	//位置id
	private Long positionId;
	//广告绑定的分类id
	private Long categoryId;
	//图片集合
	private List<AdImage> adImages;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<AdImage> getAdImages() {
		return adImages;
	}
	public void setAdImages(List<AdImage> adImages) {
		this.adImages = adImages;
	}
	
}
