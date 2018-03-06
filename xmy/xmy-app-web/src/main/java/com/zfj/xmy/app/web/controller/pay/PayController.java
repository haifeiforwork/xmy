package com.zfj.xmy.app.web.controller.pay;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.ReqSecurityVo;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppPayInDto;
import com.zfj.xmy.order.service.app.AppOrderService;
import com.zfj.xmy.order.service.common.PayService;
import com.zfj.xmy.pay.persistence.pojo.vo.LaunchPayGoodsVo;
import com.zfj.xmy.pay.service.ali.AlipayClientSyncDto;
import com.zfj.xmy.pay.service.pay.dto.AvailblePayTypeDto;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;
import com.zfj.xmy.user.service.common.UserService;
import com.zfj.xmy.user.service.pc.PcUserService;

/**
 * 支付相关
 * @author wy
 *
 */
@RequestMapping(value="/pay")
@RestController
public class PayController  extends BaseController {

	@Autowired
	private PayService payService;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AppOrderService appOrderService;
	
	@Autowired
	private PcUserService pcUserService;
	
	/**
	 * 发起支付(购买商品) (匿名)
	 * @param appScreenInDto
	 * @return
	 * @Description 
	 * @date 2017年8月9日  上午10:02:57
	 * @author wy
	 * 2017
	 * @return ResponseEntity<List<AppGoodsOut>>
	 */
	@RequestMapping(value="/goodsAnonymous",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	@Exclusion
	public ResponseEntity<AppResp> payAnonymous(HttpServletRequest request,@RequestBody ReqSecurityVo reqSecurity){
		LaunchPayGoodsVo launchPayGoods = reqSecurity.get(LaunchPayGoodsVo.class);
		String ip = WebUtil.getRealIp();
		launchPayGoods.setClientIp(ip);
		Object returnStr = payService.beforebuyGoods(launchPayGoods,null);
		return AppResp.get(returnStr);
	}
	/***
	 * 发起支付(购买商品) (登录)
	 * @param request
	 * @param reqSecurity
	 * @return
	 * @Description 
	 * @date 2017年11月30日  下午4:09:17
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/goods",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	public ResponseEntity<AppResp> pay(HttpServletRequest request,@RequestBody ReqSecurityVo reqSecurity){
		LaunchPayGoodsVo launchPayGoods = reqSecurity.get(LaunchPayGoodsVo.class);
		String ip = WebUtil.getRealIp();
		launchPayGoods.setClientIp(ip);
		Object returnStr = payService.beforebuyGoods(launchPayGoods,AppLocalInfo.getUserId());
		return AppResp.get(returnStr);
	}
	
	
	/**
	 * 支付宝客户端同步返回验签
	 * @param request
	 * @param reqSecurity
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午3:32:21
	 * @author wy
	 * 2017
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value="/alipaySync",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	@Exclusion
	public ResponseEntity<AppResp> alipayClientSync(HttpServletRequest request,@RequestBody ReqSecurityVo reqSecurity){
		AlipayClientSyncDto alipayClientSync = reqSecurity.get(AlipayClientSyncDto.class);
		payService.alipayClientSyncSign(alipayClientSync);
		return AppResp.get();
	}
	
	/**
	 * 获取可用的支付方式
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午3:54:42
	 * @author wy
	 * 2017
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value="/payType",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	@Exclusion
	public ResponseEntity<AppResp> getAvailblePayType(HttpServletRequest request){
		List<AvailblePayTypeDto> availblePayTypeDtos = payService.getAvailblePayType();
		return AppResp.get(availblePayTypeDtos);
	}
	
	
	/**
	 * 获取微信APP配置 - ios SDK需要
	 * @return
	 * @Description 
	 * @date 2017年8月23日  上午10:25:28
	 * @author wy
	 * 2017
	 * @return WxPayConfigVo
	 */
	@RequestMapping(value="/wechat",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	@Exclusion
	public ResponseEntity<AppResp> getWechatConfig(HttpServletRequest request){
		WxPayConfigVo wxConfig = payService.getWechatConfig();
		return AppResp.getEncr(wxConfig);
	}
	 

	/***
	 * 货到付款
	 * @param request
	 * @param reqSecurity
	 * @return
	 * @Description 
	 * @date 2017年10月24日  下午3:49:19
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping(value="/cashondelivery",method=RequestMethod.POST,params=SystemConstant.MOBILE_VERSION_V10)
	@Exclusion
	public ResponseEntity<AppResp> cashondelivery(HttpServletRequest request,@RequestBody ReqSecurityVo reqSecurity){
		LaunchPayGoodsVo launchPayGoods = reqSecurity.get(LaunchPayGoodsVo.class);
		String ip = WebUtil.getRealIp();
		launchPayGoods.setClientIp(ip);
		payService.cashondelivery(launchPayGoods);
		return AppResp.get();
	}
	/**
	 * 购物卡订单支付
	 * @param appPayInDto
	 * @return ResponseEntity<AppResp>
	 * @author lij
	 * @date 2018年1月25日 下午2:31:10
	 */
	@RequestMapping(value="/shoppingcardPay",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> shppingcardPay(@RequestBody AppPayInDto appPayInDto){
		UserInfo userInfo = null;
		List<Order> orderByOrderNo=new ArrayList<Order>();
		//判断是什么支付方式
		if(appPayInDto.getPayType()==1){//余额支付
			User user = service.getUser(appPayInDto.getUsername(), appPayInDto.getPassword(), true);
			if(!ObjectUtils.isEmpty(user)){
				userInfo = pcUserService.getUserInfo(user.getId());
			}
		}
		if(appPayInDto.getWithOrderNo()){
			orderByOrderNo = appOrderService.getOrderByOrderNo(appPayInDto.getOrderNo());
		}
		payService.shoppingcardPay(appPayInDto, userInfo, orderByOrderNo);
		return AppResp.get("支付成功！");
	}
	
	
	
}
