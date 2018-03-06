package com.zfj.xmy.app.web.controller.shoppingcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.activity.service.cms.ShoppingCardService;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;

@RequestMapping("shoppingCard")
@RestController
public class ShoppingCardController extends BaseController {
	
	@Autowired
	private ShoppingCardService cardService;
	
	@RequestMapping(value="/getShoppingCard",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getShoppingCard(@RequestBody ShoppingCard shoppingCard){
		List<ShoppingCard> shoppingcCards = cardService.getShoppingcCards(shoppingCard.getCardNum(), shoppingCard.getCardPassword());
		if(ObjectUtils.isEmpty(shoppingcCards)){
			throw new BusinessException("你的购物卡号码或者密码不正确！");
		}else{
			ShoppingCard shoppingCard2 = shoppingcCards.get(0);
			shoppingCard2.setCardPassword("*******");
			if(ObjectUtils.isEmpty(shoppingCard2.getUserId())){
				return AppResp.get(shoppingCard2);
			}else{
				throw new BusinessException("您输入的购物卡已经被别人绑定了不能使用！");
			}
		}
	}
	
}
