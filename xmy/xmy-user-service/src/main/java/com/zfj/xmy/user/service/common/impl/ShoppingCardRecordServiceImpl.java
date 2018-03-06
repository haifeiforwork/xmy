package com.zfj.xmy.user.service.common.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.dao.ShoppingCardRecordMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.user.service.common.ShoppingCardRecordService;

/**
 * 购物卡service
 * @Description 
 * @Author liuw
 * @Date 2017年8月3日下午8:05:55
 */
@Service
public class ShoppingCardRecordServiceImpl implements ShoppingCardRecordService {
	@Autowired
	private ShoppingCardRecordMapper shoppingCardRecordMapper;
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Override
	public List<ShoppingCardRecord> findUserShoppingCardRecord(CriteriaParameter criteriaParameter){
		List<ShoppingCardRecord> selectByExample = shoppingCardRecordMapper.selectByExample(criteriaParameter);
		return selectByExample;
	}
	/**
	 * 添加购物卡记录
	 */
	@Override
	public void addShoppingCardRecord(String remark, Long id,BigDecimal money,Integer sendType) {
		ShoppingCardRecord record = new ShoppingCardRecord();
		record.setRemark(remark);
		record.setValue(money);
		record.setType(sendType);
		record.setShoppingCardId(id);
		record.setUseTime(new Date());
		shoppingCardRecordMapper.insertSelective(record);
		
	}
}
