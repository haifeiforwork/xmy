package com.zfj.xmy.goods.persistence.pc.pojo.dto;  

import java.util.ArrayList;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;

/** 
 * @Title: PcCategoryDir.java 
 * @Package com.zfj.xmy.goods.persistence.pc.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月14日 下午4:30:12 
 */
public class PcCategoryDir {

	private long id ; 
	
	private String name; 
	
	private String icon;
	
	private List<PcCategoryDir> chidleCategory = new  ArrayList<PcCategoryDir>(); //二级菜单

	private List<CategoryWordSeg> wordSegList = new  ArrayList<CategoryWordSeg>();

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

	public List<PcCategoryDir> getChidleCategory() {
		return chidleCategory;
	}

	public void setChidleCategory(List<PcCategoryDir> chidleCategory) {
		this.chidleCategory = chidleCategory;
	}

	public List<CategoryWordSeg> getWordSegList() {
		return wordSegList;
	}

	public void setWordSegList(List<CategoryWordSeg> wordSegList) {
		this.wordSegList = wordSegList;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
  