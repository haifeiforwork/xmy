package com.zfj.xmy.order.service.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.TradeProductionMapper;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.TradeProduction;
import com.zfj.xmy.common.service.CommonCouponService;
import com.zfj.xmy.common.service.CommonShopingCardService;
import com.zfj.xmy.order.service.common.PayNotifyAdapter;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.impl.PayNotifyImpl;
import com.zfj.xmy.util.SendSMSUtil;

@Service
public class PayNotifyAdapterImpl extends PayNotifyImpl implements PayNotifyAdapter {

	private static Logger logger = LoggerFactory
			.getLogger(PayNotifyAdapterImpl.class);
	
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private  CommonShopingCardService commonShopingCardService;
	
	@Autowired
	private TradeProductionMapper tradeProductionMapper;
	
	@Autowired
	private CommonCouponService commonCouponService;
	
	@Override
	public double getAmountByOrderNum(String orderNum) {
		return getTotalAmountBySerialnumber(orderNum);
	}


	@Override
	public double getTotalAmountBySerialnumber(String ordernum) {
		
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		criteria.equalTo("tranflowno", ordernum);
		List<TradeProduction> tradeProductions = tradeProductionMapper.selectByExample(para);
		if (CollectionUtils.isEmpty(tradeProductions)) {
			String msg = "商户订单号不正确";
			logger.error(msg, new BusinessException(msg));
		}
		Order order = orderMapper.selectByPrimaryKey(tradeProductions.get(0).getBusinessOrderId());
		return order.getActual().doubleValue();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)//不管有没有做完成功业务，上层交易记录都会被保存
	public void HandleBusiness(String orderNum, String amount, String attach,TradeProduction production) {
		// 0.目前这里都是 商品购买 业务 - 暂时不作区分
		// 1.处理订单状态
		CriteriaParameter para = new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		criteria.equalTo("tranflowno", orderNum);
		List<TradeProduction> tradeProductions = tradeProductionMapper.selectByExample(para);
		if (CollectionUtils.isEmpty(tradeProductions)) {
			String msg = "商户订单号不正确";
			logger.error(msg, new BusinessException(msg));
		}
		Order order = orderMapper.selectByPrimaryKey(tradeProductions.get(0).getBusinessOrderId());
		// 2.如果是未支付状态才做
		logger.info("订单ID:"+order.getId());
		logger.info("订单流水号:"+orderNum);
		if (SystemConstant.ORDER.STATUS_UNPAID.equals(order.getStatus())) {
			//2.1 购买商品业务
			Integer flowtype = new Integer(production.getFlowtype());
			if (SystemConstant.PAY.BUY_GOODS.equals(flowtype)) {
				//修改支付方式
				updateOrderPayType(production);
				
				buyGoodsBusiness(order,amount);
			}
		}
	}
	
	

	@Override
	public void buyGoodsBusiness(Order order,String autotal) {
		//1.1 扣除用户 购物卡金额 如果有的话
		BigDecimal auPay = new BigDecimal(autotal);
		BigDecimal deductAmount = order.getTotal().subtract(auPay); //用户输入的抵扣金额
		if (ObjectUtil.isNotNull(deductAmount)) {
			commonShopingCardService.updateShopingCardBalance(order.getUserId(), deductAmount,order.getId());
			if(deductAmount.compareTo(order.getTotal()) == 0 || deductAmount.compareTo(order.getTotal()) == 1){
				order.setPayType(4);//购物卡支付
			}else if(deductAmount.compareTo(order.getTotal()) == -1 && deductAmount.compareTo(new BigDecimal("0"))!=0){
				order.setPayType(6);//混合支付 
			}
		}
		order.setPay(deductAmount);
		order.setPayTime(DateUtil.date());
		order.setStatus(SystemConstant.ORDER.STATUS_DELIVERY); // 已支付
		order.setShipStatus(SystemConstant.ORDER.SHIP_STATUS_SUPPLY);
		order.setUpdateTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(order);
		// 判断用户是否购买的抵用券商品 绑定抵用券
		commonCouponService.bindBuyCouponGoods(order.getId());
		//10. 发送下单成功的短信
		try {
			SendSMSUtil.sendInsertOrderSuccess(order.getConsigneePhone(), order.getNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * lij
	 */
	@Override
	public void updateOrderPayType(TradeProduction production) {
		if(!ObjectUtils.isEmpty(production)){
			Order order = orderMapper.selectByPrimaryKey(production.getBusinessOrderId());
			if(production.getChanid().equals("0") || production.getChanid().equals("1") 
					|| production.getChanid().equals("6") || production.getChanid().equals("7")){
				order.setPayType(2);
			}
			if(production.getChanid().equals("2") || production.getChanid().equals("8") || production.getChanid().equals("10")){
				order.setPayType(1);
			}
			if(production.getChanid().equals("3") || production.getChanid().equals("4") || production.getChanid().equals("5")){
				order.setPayType(3);
			}
			orderMapper.updateByPrimaryKey(order);
		}
	}
}
