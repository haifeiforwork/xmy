package com.zfj.xmy.activity.service.cms.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.xmy.activity.persistence.common.pojo.dto.ContainerOutDto;
import com.zfj.xmy.activity.service.cms.ContainerService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.AdImageMapper;
import com.zfj.xmy.common.persistence.dao.ContainerMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.util.StringUtils;

@Service
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	private ContainerMapper containerMapper;
	@Autowired
	private AdImageMapper adImageMapper;
	@Override
	public  List<Container> findsContainersWithContainerIds(List<Object> ids){
		if(!CollectionUtils.isEmpty(ids)){
			CriteriaParameter parameter=new CriteriaParameter();
			Criteria createCriteria = parameter.createCriteria();
			createCriteria.in("id", ids);
			List<Container> selectByExample = containerMapper.selectByExample(parameter);
			return selectByExample;
		}else{
			return null;
		}
		
	}
	@Override
	public Container getContainersById(Long id){
		Container selectByPrimaryKey = containerMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
	@Override
	public void updateContainer(Container container){
		containerMapper.updateByPrimaryKeySelective(container);
	}
	@Override
	public List<Container> findsContainerWithContainerIdString(String containerIds){
		Object[] split = containerIds.split(",");
		List<Object> idS = Arrays.asList(split);
		CriteriaParameter parameter=new CriteriaParameter();
		Criteria createCriteria = parameter.createCriteria();
		createCriteria.in("id", idS);
		List<Container> selectByExample = containerMapper.selectByExample(parameter);
		return selectByExample;
	}
}
