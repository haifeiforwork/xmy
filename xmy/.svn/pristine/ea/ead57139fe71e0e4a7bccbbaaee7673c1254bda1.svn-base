package com.zfj.xmy.cms.web.controller.payconfig;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.persistence.pojo.PayConfig;
import com.zfj.xmy.common.persistence.pojo.TradeChannels;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;
import com.zfj.xmy.quartz.dto.PushReturnDto;

@RequestMapping("/payconfig")
@CheckLogin
@RestController
public class PayconfigController {

	@Autowired
	private PayConfigService payConfigService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request){
		List<TradeChannels> channels = payConfigService.findAllTradeChannelsMapper();
		request.setAttribute("channels", channels);
		return new ModelAndView("/payconfig/payconfig_list");
	}
	
	/**
	 * 查询渠道
	 * @param request
	 * @param paytype
	 * @return
	 * @Description 
	 * @date 2017年12月22日  上午11:06:14
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/findchannel")
	public ModelAndView findPayConfigByPayType(HttpServletRequest request,
			@RequestParam(value="paytype",required=true)Integer paytype){
		
		request.setAttribute("configs", payConfigService.findPayConfigByPayType(paytype));
		return new ModelAndView("/payconfig/payconfig");
	}
	
	
	
	@RequestMapping("/modify")
	public RespData modify(HttpServletRequest request,
			@RequestParam(value="form",required=true)String form,
			@RequestParam(value="paytype",required=true)Integer paytype){
		RespData respData = new RespData();
		try {
			payConfigService.modify(form,paytype);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "修改失败");
		}
		return respData;
	}
}
