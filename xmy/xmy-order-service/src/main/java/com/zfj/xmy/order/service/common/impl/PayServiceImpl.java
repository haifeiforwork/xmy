package com.zfj.xmy.order.service.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.FreightUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.TradeChannelsMapper;
import com.zfj.xmy.common.persistence.dao.TradeProductionMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.TradeChannels;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppPayInDto;
import com.zfj.xmy.order.persistence.app.pojo.dto.CardInDto;
import com.zfj.xmy.order.service.common.PayNotifyAdapter;
import com.zfj.xmy.order.service.common.PayService;
import com.zfj.xmy.pay.persistence.pojo.vo.LaunchPayGoodsVo;
import com.zfj.xmy.pay.service.ali.AlipayClientSyncDto;
import com.zfj.xmy.pay.service.ali.impl.AlipayImpl;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.PayConstant;
import com.zfj.xmy.pay.service.pay.PayLaunch;
import com.zfj.xmy.pay.service.pay.dto.AvailblePayTypeDto;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.pay.vo.LaunchInVo;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;
import com.zfj.xmy.util.SendSMSUtil;

@Service
public class PayServiceImpl implements PayService {

	private static Logger logger = LoggerFactory
			.getLogger(PayServiceImpl.class);

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderGoodsMapper orderGoodsMapper ;
	@Autowired
	private PayConfigService payConfigService;

	@Autowired
	TradeChannelsMapper tradeChannelsMapper;

	@Autowired
	private PayLaunch payLaunch;
	
	@Autowired
	private PayNotifyAdapter payNotifyAdapter;


	@Autowired
	private PayBase payBase;
	
	@Autowired
	private ShoppingCardMapper shoppingCardMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	//---------------
	@Autowired
	private  TradeProductionMapper tradeProductionMapper;
	
	@Autowired
	private  CommonShopingCardService commonShopingCardService;
	
	/**
	 * wap端支付完成前台跳转地址
	 */
	private static String wapFrontUrl = BaseProp.BASE.getValue("SERVER_PATH")+"/payment/paySuccess?orderId=";
	
	/**
	 * Pc端支付完成前台跳转地址
	 */
	private static String pcFrontUrl = BaseProp.BASE.getValue("SERVER_PATH")+"/order/success?orderId=";
	
	
	@Override
	public void alipayClientSyncSign(AlipayClientSyncDto alipayClientSync) {
		AlipayImpl alipay = new AlipayImpl() {
			@Override
			public double getAmountByOrderNum(String orderNum) {
				return payNotifyAdapter.getTotalAmountBySerialnumber(orderNum);
			}
		};
		alipay.alipayClientSyncSign(alipayClientSync, payConfigService);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void payNotify(HttpServletRequest request,
			HttpServletResponse response, int paytype) {
		payNotifyAdapter.payNotify(request, response, paytype, tradeProductionMapper, payBase);
		
	}

	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Object beforebuyGoods(LaunchPayGoodsVo launchPayGoods,Long userid) {
		// 1.验证数据
		Integer paytype = launchPayGoods.getPaytype();
		String orderId = launchPayGoods.getOrderid();
		String ip = launchPayGoods.getClientIp();
//		if (ObjectUtil.isNull(paytype)) {
//			throw new BusinessException("支付方式不能为空");
//		}
		if (StringUtil.isEmpty(orderId)) {
			throw new BusinessException("支付订单不能为空");
		}
        BigDecimal payamount = launchPayGoods.getPayAmount(); //购物卡输入的 抵用金额;
        
        Order order = orderMapper.selectByPrimaryKey(new Long(orderId));
        if (ObjectUtil.isNull(order)) {
			throw new BusinessException("支付订单不存在");
		}
        BigDecimal actual = new BigDecimal(0); //实付金额
		
        //如果有userid 和抵用金额才启用混合支付 
        if (ObjectUtil.isNotNull(payamount) && ObjectUtil.isNotNull(userid)) {
        	if (payamount.doubleValue() < 0 ) {
        		throw new BusinessException("输入抵扣金额必须为正数");
			}
        	//0.查询用户已激活购物卡可用金额
        	BigDecimal balance = new BigDecimal("0.00");
        	//UserInfo user = userInfoMapper.selectByPrimaryKey(userid);
//    		ReqData reqData = new ReqData();
//    		reqData.putValue("user_id", userid, SystemConstant.REQ_PARAMETER_EQ);
//    		reqData.putValue("status", SystemConstant.SHOPPING_CARD.ACTIVE, SystemConstant.REQ_PARAMETER_EQ);//已激活的
//    		List<ShoppingCard> card = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
//    		if(!ObjectUtils.isEmpty(card)){
//    			for (ShoppingCard shopingCard : card) {
//    				if(shopingCard.getBalance() != null)
//    					balance = balance.add(shopingCard.getBalance());
//    			}
//    		}
        	balance = commonPayOrderService.getUserAllBalance(userid);
        	
    		//0.1 总金额小于输入值 报余额不足
    		if (balance.compareTo(payamount) == -1) {
    			throw new BusinessException("个人账户余额不足！");
			}
        	
			//1.2计算 Actual(第三方实付金额)
	        actual = order.getTotal().subtract(payamount);
	        
	        System.out.println("用户订单需要支付的金额:"+order.getTotal());
	        System.out.println("用户希望从购物卡扣款:"+payamount);
	        System.out.println("用户购物卡余额:"+balance);
	        System.out.println("实际第三方支付:"+actual);
	        
	        if (actual.doubleValue() > 0 ) {
	        	//1.3不能抵用完 余下的需要第三方支付
	        	/*if(11 == paytype){
	        		throw new BusinessException("个人账户余额不足！");
	        	}*/
	        	return buyGoods(launchPayGoods,actual,payamount);
	        }else if (actual.doubleValue() == 0) {
	        	//1.4如果能抵用完 则不需要第三方支付
	        	order.setPay(payamount);
	        	payNotifyAdapter.buyGoodsBusiness(order,actual.toString());
	        	return null;
			}else {
				//1.5 输入金额大于拥有购物卡总数
				throw new BusinessException("输入金额累积不能大于订单的总金额");
			}
		}else {
			//不使用购物卡
			//实际支付金额从订单里取
			return buyGoods(launchPayGoods,null, new BigDecimal(0));
		}
		
        
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Object buyGoods(LaunchPayGoodsVo launchPayGoods, BigDecimal actual, BigDecimal payamount) {

		// 1.验证数据
		Integer paytype = launchPayGoods.getPaytype();
		String orderId = launchPayGoods.getOrderid();
		String ip = launchPayGoods.getClientIp();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>clientIp:" + ip);
		String wapName = "香满圆";
		String wapUrl = "http://m.xmy365.com";
		
		
		if (ObjectUtil.isNull(paytype)) {
			throw new BusinessException("请选择其他支付方式");
		}
		if (ObjectUtil.isNull(orderId)) {
			throw new BusinessException("支付订单不能为空");
		}
		// 2.查询数据
		Long orderid;
		try {
			orderid = new Long(orderId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new BusinessException("传入订单ID不正确");
		}
		Order order = orderMapper.selectByPrimaryKey(orderid);
		if (ObjectUtil.isNull(order)) {
			throw new BusinessException("传入订单不存在");
		}
		if (!SystemConstant.ORDER.STATUS_UNPAID.equals(order.getStatus())) {
			throw new BusinessException("只有待付款状态下的订单才可以支付");
		}

		//如果传入为空，则使用总金额
		double payAmount = 0; //实际支付金额
		if (ObjectUtil.isNull(actual) || actual.doubleValue() == 0) {
			order.setActual(order.getTotal());
			payAmount = order.getTotal().doubleValue();
		}else {
			order.setActual(actual);
			payAmount = actual.doubleValue();
		}
		
		//3.更新抵扣金额
		order.setPay(payamount);
		
		//4.更新支付方式
		order.setThirdPartyPayType(paytype);
		
		String orderSourceStr = "";
		Integer orderSource = order.getOrderSource();
		if (SystemConstant.ORDER.ORDER_SOURCE_APP.equals(orderSource)) {
			orderSourceStr = "APP";
		}
		if (SystemConstant.ORDER.ORDER_SOURCE_PC.equals(orderSource)) {
			orderSourceStr = "PC";
		}
		if (SystemConstant.ORDER.ORDER_SOURCE_WAP.equals(orderSource)) {
			orderSourceStr = "WAP";
		}
		
		String subject = orderSourceStr +"-"+ order.getNo(); //支付订单描述
		
		String notifyUrl = getNotifyUrlByPayType(paytype);

		// 5.发起支付

		//String serialNumber = order.getSerialNumber();
		//String serialNumber = "B2C" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS")+RandomUtil.randomNumbers(4); //生成序列号 - 每次支付支成一次
		String serialNumber = orderSourceStr + order.getNo()+"X" + DateUtil.format(DateUtil.date(), "ddHHmmss"); //生成序列号 - 每次支付支成一次
		order.setSerialNumber(serialNumber);
		orderMapper.updateByPrimaryKeySelective(order);
		
		LaunchInVo launchIn = new LaunchInVo();
		launchIn.setPayType(paytype);
		launchIn.setFlowtype(SystemConstant.PAY.BUY_GOODS); // 购买商品
		launchIn.setPayAmount(payAmount);
		launchIn.setOrderNum(serialNumber);
		launchIn.setOrderid(orderid);
		
		launchIn.setSubject(subject);
		launchIn.setWapName(wapName);
		launchIn.setWapUrl(wapUrl);
		launchIn.setNotifyUrl(notifyUrl);
		launchIn.setIp(ip);
		launchIn.setOpenid(launchPayGoods.getWxOpenid());

		//5.1 设置前台跳转地址
		if (PayBaseImpl.TRADETYPE_UNIONPAY_WAP == paytype
				|| PayBaseImpl.TRADETYPE_ALIPAY_WAP == paytype) {
			launchIn.setFrontUrl(wapFrontUrl+orderid);
		}
		if (PayBaseImpl.TRADETYPE_UNIONPAY_GATEWAY == paytype
				|| PayBaseImpl.TRADETYPE_ALIPAY_PC_WEB == paytype) {
			launchIn.setFrontUrl(pcFrontUrl+orderid);
		}
		
		System.out.println("实际支付金额:"+payAmount);
		System.out.println("抵扣金额:"+payamount);
		
		String retStr = payLaunch.launchPay(launchIn);
		JSONObject jo = new JSONObject(retStr);
		return jo;
	}

	@Override
	public String getNotifyUrlByPayType(int paytype) {
		String jsonStr = payConfigService.getPayConfig(paytype);
		JSONObject jo = new JSONObject(jsonStr);
		return jo.getStr(PayConstant.PAY_CONSTANT.COMMON_NOTIFY_URL);
	}

	

	@Override
	public List<AvailblePayTypeDto> getAvailblePayType() {

		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		criteria.equalTo("status", PayConstant.TRADE_CHANNELS.STATUS_ENABLE);
		List<TradeChannels> tradeChannels = tradeChannelsMapper
				.selectByExample(para);

		List<AvailblePayTypeDto> payTypes = new ArrayList<AvailblePayTypeDto>();
		for (TradeChannels tradeChannelsin : tradeChannels) {
			AvailblePayTypeDto availblePayType = new AvailblePayTypeDto();
			availblePayType.setName(tradeChannelsin.getCname());
			availblePayType.setPaytype(tradeChannelsin.getCcode());
			payTypes.add(availblePayType);
		}
		return payTypes;
	}
	
	
	@Override
	public WxPayConfigVo getWechatConfig() {
		WxPayConfigVo wxPayConfig = payBase.getPayConfigObject(PayBaseImpl.TRADETYPE_WX_APP, WxPayConfigVo.class);
		WxPayConfigVo returnWxPayConfig = new WxPayConfigVo();
		returnWxPayConfig.setAppid(wxPayConfig.getAppid());
		return returnWxPayConfig;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cashondelivery(LaunchPayGoodsVo launchPayGoods) {
		String orderid = launchPayGoods.getOrderid();
		if (StringUtil.isEmpty(orderid)) {
			throw new BusinessException("订单ID不能为空");
		}
		Order order = orderMapper.selectByPrimaryKey(new Long(orderid));
		if (ObjectUtil.isNull(order)) {
			throw new BusinessException("订单不存在");
		}
		Integer status = order.getStatus();
		if (status != SystemConstant.ORDER.STATUS_UNPAID) {
			throw new BusinessException("待付款状态下的订单才可以发起交易");
		}
		if (!FreightUtil.checkMainCity(order.getProvince(), order.getArea())) {
			throw new BusinessException("重庆主城以外不支持货到付款！");
		}
		checkNonsupportCashondeliveryGoods(new Long(orderid)); //判断订单是否含有不支持货到付款的商品
		if(!ObjectUtils.isEmpty(order.getUserId()) && order.getUserId() != 0){
			BigDecimal deductAmount = launchPayGoods.getPayAmount();
			if (ObjectUtil.isNotNull(deductAmount) && deductAmount.compareTo(new BigDecimal("0")) != 0) {
				commonShopingCardService.updateShopingCardBalance(order.getUserId(), deductAmount,order.getId());
				if(deductAmount.compareTo(order.getTotal()) == 0 || deductAmount.compareTo(order.getTotal()) == 1){
					order.setPayType(4);//购物卡支付
				}else if(deductAmount.compareTo(order.getTotal()) == -1 && deductAmount.compareTo(new BigDecimal("0"))!=0){
					order.setPayType(SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY);//混合支付 
				}
			}else{
				launchPayGoods.setPayAmount(new BigDecimal("0"));
				order.setPayType(SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY);
			}
		}else{
			order.setPayType(SystemConstant.ORDER.PAY_TYPE_PAYDELIVERY);
		}
		//TODO: 包含跨境商品不支持货到付款
		order.setPay(launchPayGoods.getPayAmount());
		order.setActual(order.getTotal().subtract(launchPayGoods.getPayAmount()));
		order.setStatus(SystemConstant.ORDER.STATUS_DELIVERY);
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_AUDIT);
		order.setUpdateTime(DateUtil.date());
		order.setPayTime(DateUtil.date());
		orderMapper.updateByPrimaryKey(order);
		//10. 发送下单成功的短信
		try {
			SendSMSUtil.sendInsertOrderSuccess(order.getConsigneePhone(), order.getNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void shoppingcardPay(AppPayInDto inDto,UserInfo user,List<Order> orderList) {
		//1判断是什么支付方式
		if(inDto.getPayType()==1){//余额支付
			if(ObjectUtils.isEmpty(inDto.getUsername())){
				throw new BusinessException("余额支付时用户名称不能为空！");
			}
			if(ObjectUtils.isEmpty(inDto.getPassword())){
				throw new BusinessException("余额支付密码不能为空！");
			}
			//判断用户是否存在
			if(ObjectUtils.isEmpty(user)){
				throw new BusinessException("用户名或密码错误");
			}
			//2.判断是否带带订单号支付
			if(inDto.getWithOrderNo()){
				if(ObjectUtils.isEmpty(inDto.getOrderNo())){
					throw new BusinessException("订单编号不能为空！");
				}
				if(ObjectUtils.isEmpty(orderList)){
					throw new BusinessException("订单编号不正确！");
				}
				if(user.getBalance().compareTo(orderList.get(0).getTotal().subtract(orderList.get(0).getPay()))<1){//余额不够支付
					throw new BusinessException("用户余额不够支付该订单金额！");
				}
				commonShopingCardService.updateShopingCardBalance(user.getId(), orderList.get(0).getTotal().subtract(orderList.get(0).getPay()), orderList.get(0).getId());
			}else{
				if(ObjectUtils.isEmpty(inDto.getPayAmount())){
					throw new BusinessException("不带订单时支付金额不能为空！");
				}
				if(user.getBalance().compareTo(inDto.getPayAmount())<1){//余额不够支付
					throw new BusinessException("用户余额不够支付该订单金额！");
				}
				commonShopingCardService.updateShopingCardBalance(user.getId(), inDto.getPayAmount(), null);
			}
		}else if(inDto.getPayType()==2){//扫卡支付
			if(ObjectUtils.isEmpty(inDto.getCards())){
				throw new BusinessException("扫卡支付购物卡集合不能为空");
			}
			List<ShoppingCard> cardList = new ArrayList<ShoppingCard>();
			//查询购物卡集合
			List<CardInDto> cards = inDto.getCards();
			for (CardInDto cardInDto : cards) {
				ReqData reqData = new ReqData();
				reqData.putValue("card_num", cardInDto.getCardNum(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("card_password", cardInDto.getCardPasswrod(), SystemConstant.REQ_PARAMETER_EQ);
				List<ShoppingCard> selectByExample = shoppingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if(!ObjectUtils.isEmpty(selectByExample)){
					ShoppingCard shoppingCard = selectByExample.get(0);
					if(ObjectUtils.isEmpty(shoppingCard.getUserId())){
						cardList.add(shoppingCard);
					}
				}
			}
			if(ObjectUtils.isEmpty(cardList)){
				throw new BusinessException("可用购物卡不能为空！已绑定的购物卡不能使用扫码支付！");
			}
			if(inDto.getWithOrderNo()){
				if(ObjectUtils.isEmpty(inDto.getOrderNo())){
					throw new BusinessException("订单编号不能为空！");
				}
				if(ObjectUtils.isEmpty(orderList)){
					throw new BusinessException("订单编号不正确！");
				}
				commonShopingCardService.updateShoppingCardBalanceByCards(cardList, orderList.get(0).getTotal().subtract(orderList.get(0).getPay()), orderList.get(0).getId());
			}else{
				if(ObjectUtils.isEmpty(inDto.getPayAmount())){
					throw new BusinessException("不带订单时支付金额不能为空！");
				}
				commonShopingCardService.updateShoppingCardBalanceByCards(cardList, inDto.getPayAmount(), null);
			}
		}else{
			throw new BusinessException("支付方式不能为空！");
		}
	}

	/**
	 * 判断订单是否含有不支持货到付款的商品
	 * @param orderId    
	 * @return void    
	 * Date:2018年3月5日 下午3:25:49 
	 * @author hexw
	 */
	public void checkNonsupportCashondeliveryGoods(Long orderId){
		// 1. 查询订单
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (ObjectUtil.isNotNull(order)) {
		// 2. 查询订单商品
			ReqData reqData = new ReqData();
			reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
			List<OrderGoods> list = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (OrderGoods orderGoods : list) {
		// 3. 判断是否是不支持货到付款的商品
				Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;
				Long[] couponGoods = {SystemConstant.NEWACIVITY.COUPON_GOODS};
				Long[] goodsId = ArrayUtil.addAll(feeFreigthGoods,couponGoods);
				if (Arrays.asList(goodsId).contains(orderGoods.getGoodsId())) {
					throw new BusinessException("订单含有不支持货到付款的商品，请重新选着支付方式");
				}
			}
		}
		
	}
		
}
