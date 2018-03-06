package com.zfj.xmy.user.service.common;

import java.math.BigDecimal;
import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;

public interface ShoppingCardRecordService {

	/**
	 * 查找购物卡消费明细
	 * @Description 
	 * @param criteriaParameter
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日下午8:17:09
	 */
	List<ShoppingCardRecord> findUserShoppingCardRecord(	CriteriaParameter criteriaParameter);
	
	void addShoppingCardRecord(String remark,Long id,BigDecimal money,Integer sendType);
}
