package com.zfj.xmy.goods.persistence.pc.pojo.dto;

import java.util.List;

public class PcSearchDto{
	private String searchName;//筛选条件名称
	private Long searchId;//筛选条件ID
	private List<PcSearchDto> childList;//子级集合
	private String ids;//自己的ID
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public Long getSearchId() {
		return searchId;
	}
	public void setSearchId(Long searchId) {
		this.searchId = searchId;
	}
	public List<PcSearchDto> getChildList() {
		return childList;
	}
	public void setChildList(List<PcSearchDto> childList) {
		this.childList = childList;
	}
	
}
