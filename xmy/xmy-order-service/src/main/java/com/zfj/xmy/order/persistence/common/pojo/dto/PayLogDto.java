package com.zfj.xmy.order.persistence.common.pojo.dto;

import com.zfj.xmy.common.persistence.pojo.PayLog;

public class PayLogDto extends PayLog{

	private String payName ;

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	
}
