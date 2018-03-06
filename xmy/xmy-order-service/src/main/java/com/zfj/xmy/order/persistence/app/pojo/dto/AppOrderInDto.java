package com.zfj.xmy.order.persistence.app.pojo.dto;  

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.ShoppingCart;

/** 
 * @Title: AppOrderInDto.java 
 * @Package com.zfj.xmy.order.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月21日 上午11:14:24 
 */
public class AppOrderInDto {
	
	
	private List<AppGoodsVo> appGoodsVo;

	public List<AppGoodsVo> getAppGoodsVo() {
		return appGoodsVo;
	}

	public void setAppGoodsVo(List<AppGoodsVo> appGoodsVo) {
		this.appGoodsVo = appGoodsVo;
	}
	
	

}
  