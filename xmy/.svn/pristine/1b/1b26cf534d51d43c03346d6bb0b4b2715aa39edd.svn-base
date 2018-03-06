package com.zfj.xmy.pay.service.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfj.xmy.common.persistence.dao.TradeProductionMapper;
import com.zfj.xmy.common.persistence.pojo.TradeProduction;

public interface PayNotify {

	public double getAmountByOrderNum(String orderNum);
	
	void payNotify(HttpServletRequest request, HttpServletResponse response,
			int paytype, TradeProductionMapper tradeProductionMapper,
			PayBase payBase);

	void HandleBusiness(String orderNum, String amount, String attach,TradeProduction production);


	
}
