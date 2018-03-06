package com.zfj.xmy.goods.persistence.common.pojo.dto;  

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TermDataDir.java 
 * @Package com.zfj.xmy.goods.persistence.pojo.dto 
 * @Description:  商品分类数据结构对象
 * @author hexw
 * @date 2017年6月15日 上午10:41:09 
 */
public class TermDataDir {

	private Long id ;
	
	private String name ;
	
	private long parentId;
	
	private String parentName;
	
	private String sn;
	
	private String wordSeg;
	
	private String icon;
	
	private String des;
	
	private Integer weight;
    
	private Integer status;
	
	private List<TermDataDir> children = new ArrayList<TermDataDir>()  ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TermDataDir> getChildren() {
		return children;
	}

	public void setChildren(List<TermDataDir> children) {
		this.children = children;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getWordSeg() {
		return wordSeg;
	}

	public void setWordSeg(String wordSeg) {
		this.wordSeg = wordSeg;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
  