package com.zfj.xmy.goods.persistence.wap.dao;

import java.util.List;

import com.zfj.xmy.goods.persistence.wap.pojo.dto.CategoryAmountVo;

public interface CategoryAmountVoExMapper {
	
	List<CategoryAmountVo> findCateAndAmount(Long id);
	
}
