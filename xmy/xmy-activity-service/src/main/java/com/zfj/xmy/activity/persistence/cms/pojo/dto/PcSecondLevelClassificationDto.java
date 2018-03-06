package com.zfj.xmy.activity.persistence.cms.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.PcSecondLevelClassification;

/**
 * 二级分类页扩展类
 * @Description 
 * @Author liuw
 * @Date 2017年8月31日下午8:47:38
 */
public class PcSecondLevelClassificationDto {

	//二级分类配置记录表
	private PcSecondLevelClassification pcSecondLevelClassification;
	//二级分类对应的表
	private IndexConfig indexConfig;
	
	//二级分类对应的货柜
	private List<Container> containers;
	public List<Container> getContainers() {
		return containers;
	}
	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}
	public PcSecondLevelClassification getPcSecondLevelClassification() {
		return pcSecondLevelClassification;
	}
	public void setPcSecondLevelClassification(
			PcSecondLevelClassification pcSecondLevelClassification) {
		this.pcSecondLevelClassification = pcSecondLevelClassification;
	}
	public IndexConfig getIndexConfig() {
		return indexConfig;
	}
	public void setIndexConfig(IndexConfig indexConfig) {
		this.indexConfig = indexConfig;
	}
	
}
