package com.zfj.xmy.common.service;

import java.math.BigDecimal;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.app.AppShoppingCardOutDto;
/**
 * @author lij
 * PC提交订单时
 */
public interface CommonShopingCardService {
	
	/**
	 * @param CardNo
	 * @param CardPwd
	 * @param userId
	 * @return Integer 0:绑定成功 1:账号密码不正确 2:改卡已绑定
	 * @author lij
	 * @date 2017年8月10日 上午11:34:39
	 * 为用户绑定购物卡
	 */
	Integer addShopingCardWithUserId(String cardNo,String cardPwd,Long userId);
	
	/**
	 * @param userId
	 * @param balancePay void
	 * @author lij
	 * @date 2017年8月10日 下午3:39:30
	 * 扣除购物卡余额并存入购物卡消费记录
	 */
	void updateShopingCardBalance(Long userId,BigDecimal balancePay,Long orderId);

	void updateShoppingCardBalanceByCards(List<ShoppingCard> cards,BigDecimal balancePay,Long orderId);
	
	/**
	 * 购物卡激活明细
	 * @Description 
	 * @param userId
	 * @return
	 * @Author liuw
	 * @Date 2017年8月25日上午11:18:15
	 */
	List<AppShoppingCardOutDto> activateDetail(Long userId);
	
}
