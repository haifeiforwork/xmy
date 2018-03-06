package com.zfj.xmy.activity.persistence.common.dao;

import java.util.List;

import com.zfj.xmy.activity.persistence.common.pojo.dto.WapContainerOutDto;

public interface ContainerExMapper {
	
	Integer checkIsLevelOne(Long categoryId);
	
	List<WapContainerOutDto> findAllContainer(String[] ids);
	
}
