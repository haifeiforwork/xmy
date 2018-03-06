package com.zfj.xmy.pc.web.controller.pay;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.order.service.common.PayService;
import com.zfj.xmy.pay.persistence.pojo.vo.LaunchPayGoodsVo;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
@RestController
public class PayController extends BaseController{
	
	@Autowired
	private PayService payService;
	
	@RequestMapping(value="/pay/goods",method=RequestMethod.POST)
	public Object pay(HttpServletRequest request,LaunchPayGoodsVo goodsVo){
		String ip = WebUtil.getRealIp();
		goodsVo.setClientIp(ip);
		Long userId = null;
		SessionInfo sessionInfo = SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo)) {
			userId = sessionInfo.getUserId();
		}
		Object returnStr = payService.beforebuyGoods(goodsVo, userId);
		return returnStr;
	}
	/**
	 * 第三方支付新开页面
	 * @param goodsVo
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年12月4日 下午2:31:52
	 */
	@RequestMapping(value="/pay/appliy",method=RequestMethod.GET)
	public ModelAndView payAppliy(@Param("orderid") Long orderid,@Param("paytype") Integer paytype,@Param("payAmount") String payAmount,HttpServletRequest request){
		request.setAttribute("orderid", orderid);
		request.setAttribute("paytype", paytype);
		request.setAttribute("payAmount", payAmount);
		return new ModelAndView("/order/pay");
	}
}
