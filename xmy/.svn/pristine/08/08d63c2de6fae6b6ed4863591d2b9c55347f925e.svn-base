package com.zfj.xmy.activity.service.wap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.activity.persistence.common.dao.ContainerExMapper;
import com.zfj.xmy.activity.persistence.common.pojo.dto.WapContainerOutDto;
import com.zfj.xmy.activity.service.wap.WapContainerService;

@Service
public class WapContainerServiceImpl implements WapContainerService{
	
	@Autowired
	private ContainerExMapper exMapper;
	
	@Override
	public boolean checkIsLevelOne(Long categoryId) {
		Integer result = exMapper.checkIsLevelOne(categoryId);
		if(result > 0 )
			return true;
		else 
			return false;
	}
	
	@Override
	public List<WapContainerOutDto> findAllContainer(String ids) {
		return exMapper.findAllContainer(ids.split(","));
	}
	
}
