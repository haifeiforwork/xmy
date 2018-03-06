package com.zfj.xmy.goods.persistence.common.pojo.dto;  

import com.zfj.xmy.common.persistence.pojo.TermData;

/** 
 * @Title: TermDataDto.java 
 * @Package com.zfj.xmy.goods.persistence.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年6月20日 下午2:00:46 
 */
public class TermDataDto extends TermData{
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
  