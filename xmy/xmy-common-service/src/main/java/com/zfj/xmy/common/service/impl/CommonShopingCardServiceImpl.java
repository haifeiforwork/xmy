package com.zfj.xmy.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.LogicType;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCardRecordMapper;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.ShoppingCard;
import com.zfj.xmy.common.persistence.pojo.ShoppingCardRecord;
import com.zfj.xmy.common.persistence.pojo.app.AppShoppingCardOutDto;
import com.zfj.xmy.common.service.CommonShopingCardService;
@Service
public class CommonShopingCardServiceImpl extends BaseService implements CommonShopingCardService{
	
	@Autowired
	private ShoppingCardMapper shopingCardMapper;
	
	@Autowired
	private ShoppingCardRecordMapper shoppingCardRecordMapper;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	
	/**
	 * 为用户绑定购物卡
	 */
	@Override
	public Integer addShopingCardWithUserId(String cardNo, String cardPwd, Long userId) {
		//定义返回变量
		Integer reslut = 0;
		ReqData reqData = new ReqData();
		//1.判断购物卡的卡号密码是否正确
		reqData.putValue("card_num", cardNo, SystemConstant.REQ_PARAMETER_EQ,LogicType.AND);
		reqData.putValue("card_password", cardPwd, SystemConstant.REQ_PARAMETER_EQ);
		int cardCount = shopingCardMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(reslut.equals(cardCount)){
			reslut = 1;//卡号密码或编号错误
		}else{
			//1.2判断该卡是否已经被用户使用
			List<ShoppingCard> selectByExample = shopingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			ShoppingCard shopingCard = selectByExample.get(0);
			if(!ObjectUtils.isEmpty(shopingCard.getUserId())){
				reslut = 2;
			}else{
				//为用户绑定该购物卡
				ShoppingCard shopCard = selectByExample.get(0);
				shopCard.setUserId(userId);
				shopCard.setStatus(SystemConstant.SHOPPING_CARD.ACTIVE);
				shopCard.setActiveTime(new Date());
				shopingCardMapper.updateByPrimaryKey(shopCard);
			}
		}
		
		return reslut;
	}
	/**
	 * 扣除购物卡余额并存入购物卡消费记录
	 */
	@Override
	public void updateShopingCardBalance(Long userId, BigDecimal balancePay,Long orderId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.SHOPPING_CARD.ACTIVE, SystemConstant.REQ_PARAMETER_EQ);
		List<ShoppingCard> selectByExample = shopingCardMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (ShoppingCard shopingCard : selectByExample) {
			int to = balancePay.compareTo(shopingCard.getBalance());
			if(balancePay.compareTo(new BigDecimal("0")) == 0){
				break;
			}
			if(to == -1 || to == 0){//支付的余额小于或等于第一张购物卡的余额
				ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
				shoppingCardRecord.setShoppingCardId(shopingCard.getId());
				shoppingCardRecord.setUserId(userId);
				shoppingCardRecord.setUseTime(new Date());
				shoppingCardRecord.setValue(balancePay);
				shoppingCardRecord.setOrderId(orderId);
				shoppingCardRecord.setRemark("订单消费了"+balancePay+"元");
				shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
				shopingCard.setBalance(shopingCard.getBalance().subtract(balancePay));
				//存入购物卡消费记录
				shopingCardMapper.updateByPrimaryKey(shopingCard);
				break;
			}else if (to == 1 ){//支付的金额大于购物卡的余额
				if(shopingCard.getBalance().compareTo(new BigDecimal(0)) == 1){
					balancePay = balancePay.subtract(shopingCard.getBalance());//修改支付的金额
					//存入购物卡消费记录
					shopingCardMapper.updateByPrimaryKey(shopingCard);
					ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
					shoppingCardRecord.setShoppingCardId(shopingCard.getId());
					shoppingCardRecord.setUserId(userId);
					shoppingCardRecord.setUseTime(new Date());
					shoppingCardRecord.setOrderId(orderId);
					shoppingCardRecord.setValue(shopingCard.getBalance());
					shoppingCardRecord.setRemark("订单消费了"+shopingCard.getBalance()+"元");
					shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
					shopingCard.setBalance(BigDecimal.valueOf(0.00));//更改该购物卡的余额为0
					shopingCardMapper.updateByPrimaryKey(shopingCard);
				}
			}
		}
	}
	
	@Override
	public void updateShoppingCardBalanceByCards(List<ShoppingCard> cards, BigDecimal balancePay, Long orderId) {
		// TODO Auto-generated method stub
		for (ShoppingCard shopingCard : cards) {
			int to = balancePay.compareTo(shopingCard.getBalance());
			if(balancePay.compareTo(new BigDecimal("0")) == 0){
				break;
			}
			if(to == -1 || to == 0){//支付的余额小于或等于第一张购物卡的余额
				ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
				shoppingCardRecord.setShoppingCardId(shopingCard.getId());
				shoppingCardRecord.setUseTime(new Date());
				shoppingCardRecord.setValue(balancePay);
				shoppingCardRecord.setOrderId(orderId);
				shoppingCardRecord.setRemark("订单消费了"+balancePay+"元");
				shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
				shopingCard.setBalance(shopingCard.getBalance().subtract(balancePay));
				//存入购物卡消费记录
				shopingCardMapper.updateByPrimaryKey(shopingCard);
				balancePay=new BigDecimal(0);
				break;
			}else if (to == 1 ){//支付的金额大于购物卡的余额
				if(shopingCard.getBalance().compareTo(new BigDecimal(0)) == 1){
					balancePay = balancePay.subtract(shopingCard.getBalance());//修改支付的金额
					//存入购物卡消费记录
					shopingCardMapper.updateByPrimaryKey(shopingCard);
					ShoppingCardRecord shoppingCardRecord = new ShoppingCardRecord();
					shoppingCardRecord.setShoppingCardId(shopingCard.getId());
					shoppingCardRecord.setUseTime(new Date());
					shoppingCardRecord.setOrderId(orderId);
					shoppingCardRecord.setValue(shopingCard.getBalance());
					shoppingCardRecord.setRemark("订单消费了"+shopingCard.getBalance()+"元");
					shoppingCardRecordMapper.insertSelective(shoppingCardRecord);
					shopingCard.setBalance(BigDecimal.valueOf(0.00));//更改该购物卡的余额为0
					shopingCardMapper.updateByPrimaryKey(shopingCard);
				}
			}
		}
		if(balancePay.compareTo(new BigDecimal("0")) == 1){
			throw new BusinessException("购物卡余额不足不能支付！");
		}
	}
	
	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal("58.80");
		float floatValue = decimal.shortValue();
		System.out.println(floatValue);
	}
	@Override
	public List<AppShoppingCardOutDto> activateDetail(Long userId){
		List<AppShoppingCardOutDto> result=new ArrayList<AppShoppingCardOutDto>();
		CriteriaParameter para=new CriteriaParameter();
		para.setOrderByClause("create_time desc");
		Criteria createCriteria = para.createCriteria();
		createCriteria.equalTo("user_id", userId);
		 List<ShoppingCard> selectByExample = shopingCardMapper.selectByExample(para);
		for (ShoppingCard shoppingCardRecord : selectByExample) {
			AppShoppingCardOutDto appShoppingCardOutDto=new AppShoppingCardOutDto();
			BeanUtils.copyProperties(shoppingCardRecord, appShoppingCardOutDto);
			result.add(appShoppingCardOutDto);
		}
		return result;
	}
	
}
