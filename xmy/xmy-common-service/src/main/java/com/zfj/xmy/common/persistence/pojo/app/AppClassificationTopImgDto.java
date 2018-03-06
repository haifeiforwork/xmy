package com.zfj.xmy.common.persistence.pojo.app;

import com.zfj.xmy.common.SystemConstant.AdInfoImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;

public class AppClassificationTopImgDto {
	/**
	 * 广告名称
	 */
	private String name;
	/**
	 * 图片地址
	 */
	private String imgPath;
	/**
	 * 跳转页面
	 */
	private String data;
	/**
	 * 广告对应的分类
	 */
	private Long categoryId;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	public String getUrl() {
		return data;
	}
	public void setUrl(String data) {
		this.data = data;
	}
	
}
