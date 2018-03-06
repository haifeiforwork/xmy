package com.zfj.xmy.pc.web.controller.shopingcard;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.order.service.pc.PcOrderService;
import com.zfj.xmy.pc.web.Lijie;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
import com.zfj.xmy.user.service.pc.PcUserService;
@RestController
@RequestMapping("/card")
@Lijie
public class ShopingCardController extends BaseController{
	
	@Autowired
	private CommonShopingCardService commonShopingCardService;
	
	@Autowired
	private PcOrderService pcOrderService;
	
	@Autowired
	private PcUserService pcUserService;
	/**
	 * @param cardNo
	 * @param cardPwd
	 * @param orderId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月10日 下午2:19:02
	 * 激活购物卡
	 */
	@RequestMapping("/addShopingCard/{cardNo}/{cardPwd}/{orderId}")
	public Integer addShopingCard(@PathVariable("cardNo") String cardNo,@PathVariable("cardPwd") String cardPwd,
			@PathVariable("orderId") Long orderId,HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		//绑定购物卡
		Integer status = commonShopingCardService.addShopingCardWithUserId(cardNo, cardPwd, sessionInfo.getUserId());
		//查询订单信息
		Order paOrder = pcOrderService.findOrderByOrderId(orderId);
		UserInfo userInfo = pcUserService.getUserInfo(sessionInfo.getUserId());
		request.setAttribute("payOrder", paOrder);
		request.setAttribute("userInfo", userInfo);
		return status;
	}
}
