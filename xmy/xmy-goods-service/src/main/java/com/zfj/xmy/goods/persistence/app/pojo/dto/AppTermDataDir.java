package com.zfj.xmy.goods.persistence.app.pojo.dto;  

import java.util.ArrayList;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsDto;

/** 
 * @Title: TermDataDir.java 
 * @Package com.zfj.xmy.goods.persistence.app 
 * @Description: 
 * @author hexw
 * @date 2017年7月21日 上午10:01:21 
 */
public class AppTermDataDir {
	
	private long id;
	
	private String name;  //名称
	
	private long parentId; 
	
	private String icon; //图标
	
	private String appIcon; //一级分类app图标
	
	private String appOnIcon; // 一级分类选中状态的app图标
	
	private int status ;
	
	private int isShow;  //是否首页显示
	
	private List<AppAdImageDir> adImgList = new  ArrayList<AppAdImageDir>(); //一级分类图片集合
	
	private List<CategoryWordSeg> wordSegList = new  ArrayList<CategoryWordSeg>();  //二级分类分词集合
	
	private List<AppTermDataDir> childList = new  ArrayList<AppTermDataDir>();  //子分类集合
	
	private List<Goods>  goodsList = new ArrayList<Goods>();   //商品栏目列表
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<CategoryWordSeg> getWordSegList() {
		return wordSegList;
	}

	public void setWordSegList(List<CategoryWordSeg> wordSegList) {
		this.wordSegList = wordSegList;
	}

	public List<AppTermDataDir> getChildList() {
		return childList;
	}

	public void setChildList(List<AppTermDataDir> childList) {
		this.childList = childList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public List<AppAdImageDir> getAdImgList() {
		return adImgList;
	}

	public void setAdImgList(List<AppAdImageDir> adImgList) {
		this.adImgList = adImgList;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAppOnIcon() {
		return appOnIcon;
	}

	public void setAppOnIcon(String appOnIcon) {
		this.appOnIcon = appOnIcon;
	}

	
	
	
	
	
}
  