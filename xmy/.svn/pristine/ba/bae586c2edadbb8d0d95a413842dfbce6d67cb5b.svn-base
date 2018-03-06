package com.zfj.xmy.activity.service.cms.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.service.cms.IndexSettingService;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.ContainerMapper;
import com.zfj.xmy.common.persistence.dao.IndexConfigMapper;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;

@Service
public class IndexSettingServiceImpl implements IndexSettingService {

	@Autowired
	private ContainerMapper containerMapper;
	@Autowired
	private IndexConfigMapper indexConfigMapper;
	@Override
	public void insert(Container container){
		containerMapper.insertSelective(container);
	}
	@Override
	public void insert(IndexConfig indexConfig){
		indexConfigMapper.insertSelective(indexConfig);
	}
	@Override
	public IndexConfig getIndexConfig(){
		IndexConfig indexConfig=null;
		indexConfig = indexConfigMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.APPINDEXCONFIG);
		return indexConfig;
	}
	@Override
	public IndexConfig getPCIndexConfig(){
		IndexConfig indexConfig=null;
		indexConfig = indexConfigMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.PCINDEXCONFIG);
		return indexConfig;
	}
	@Override
	public IndexConfig getChoiceFruitSetting(){
		IndexConfig indexConfig=null;
		indexConfig = indexConfigMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.CHOICEFRUITSETTING);
		return indexConfig;
	}
	@Override
	public IndexConfig getCrossBorderSetting(){
		IndexConfig indexConfig=null;
		indexConfig = indexConfigMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.CROSSBORDERSETTING);
		return indexConfig;
	}
	@Override
	public List<Object> parseIndexConfigToList(IndexConfig indexConfig){
		List<Object> asList=null;
		if(ObjectUtils.isEmpty(indexConfig)){
			return null;
		}
		String containerIds = indexConfig.getContainerIds();
		if(!StringUtil.isEmpty(containerIds)){
			Object[] split = containerIds.split(",");
			asList= Arrays.asList(split);
		}
		return asList;
	}
	@Override
	public void updateIndexConfig(IndexConfig indexConfig){
		indexConfigMapper.updateByPrimaryKeySelective(indexConfig);
	}
	@Override
	public IndexConfig getIndexConfigById(Long id){
		IndexConfig selectByPrimaryKey = indexConfigMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
}
