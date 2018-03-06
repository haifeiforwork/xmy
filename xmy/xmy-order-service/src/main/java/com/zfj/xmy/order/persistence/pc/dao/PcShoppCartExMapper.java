package com.zfj.xmy.order.persistence.pc.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;

public interface PcShoppCartExMapper {
	
	List<PcGoodsDto> findShoppingCart(CriteriaParameter criteriaParameter);
	
	List<PcGoodsDto> findShoppingCart(CriteriaParameter criteriaParameter,RowBounds rowBounds);
}
