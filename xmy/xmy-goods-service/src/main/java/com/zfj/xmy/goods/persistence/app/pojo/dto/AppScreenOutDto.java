package com.zfj.xmy.goods.persistence.app.pojo.dto;

import java.util.List;

/**
 * @author lij
 * 筛选列表的实体类
 */
public class AppScreenOutDto {
	
	private String name;//名称
	
	
	private List<AppScreenOutDto> childDto;//子级分词

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppScreenOutDto> getChildDto() {
		return childDto;
	}

	public void setChildDto(List<AppScreenOutDto> childDto) {
		this.childDto = childDto;
	}

	
}
