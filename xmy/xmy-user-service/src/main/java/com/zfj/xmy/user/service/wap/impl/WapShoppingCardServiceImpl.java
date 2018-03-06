package com.zfj.xmy.user.service.wap.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.user.persistence.dao.ShoppingCartExMapper;
import com.zfj.xmy.user.persistence.wap.dto.ShoppingCartOutDto;
import com.zfj.xmy.user.service.wap.WapShoppingCardService;
@Service
public class WapShoppingCardServiceImpl implements WapShoppingCardService{
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private ShoppingCartExMapper shoppingCartExMapper;
	
	/**
	 * 根据用户ID查询改用户所有绑定的购物卡
	 */
	@Override
	public List<ShoppingCard> findShoppingCardByUserId(Long userId) {
		ReqData reqData = new  ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	/**
	 * 修改
	 */
	@Override
	public void updateShoppingCard(Long id, Integer type, BigDecimal money) {
		ShoppingCard oldCard = shoppingCardMapper.selectByPrimaryKey(id);
		if (type == SystemConstant.userSpendPoints.SPEND_TYPE_SAVE) {//存入余额
			oldCard.setBalance(oldCard.getBalance().add(money));//+ userSpendPoints.getMoneyPoint());
		} else {
			oldCard.setBalance(oldCard.getBalance().subtract(money));//- userSpendPoints.getMoneyPoint());
		}
		shoppingCardMapper.updateByPrimaryKey(oldCard);
	}
	@Override
	public Integer findCountByUserId(Long userId) {
		List<ShoppingCartOutDto> findShoppingCartGoodsCount = shoppingCartExMapper.findShoppingCartGoodsCount(userId);
		return findShoppingCartGoodsCount.size();
	}
	

}
