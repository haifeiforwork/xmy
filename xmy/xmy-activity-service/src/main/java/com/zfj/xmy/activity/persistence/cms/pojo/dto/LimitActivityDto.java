package com.zfj.xmy.activity.persistence.cms.pojo.dto;  

import com.zfj.xmy.common.persistence.pojo.LimitActivity;

/** 
 * @Title: LimitActivityDto.java 
 * @Package com.zfj.xmy.activity.persistence.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年7月10日 下午7:14:40 
 */
public class LimitActivityDto extends LimitActivity {
	
	private String saleStatus;  //促销状态   0: 未开始   1：进行中   2 ：已结束

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	
}
  