package com.zfj.xmy.goods.service.wap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.goods.service.wap.WapShoppingCardService;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
import com.zfj.xmy.order.service.wap.WapOrderService;

@Service
public class WapShoppingCardServiceImple implements WapShoppingCardService{
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Override
	public List<ShoppingCard> activateDetail(Long userId) {
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.equalTo("user_id", userId);
		List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(para);
		return selectByExample;
	}
	
}
