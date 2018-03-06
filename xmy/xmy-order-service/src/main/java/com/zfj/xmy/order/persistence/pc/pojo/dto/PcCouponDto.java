package com.zfj.xmy.order.persistence.pc.pojo.dto;  

import java.util.ArrayList;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.Goods;

/** 
 * @Title: PcCouponDto.java 
 * @Package com.zfj.xmy.order.persistence.pc.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年10月10日 下午2:06:21 
 */
public class PcCouponDto extends Coupon {
	
	private List<Goods> couponGoods = new ArrayList<Goods>();
	private Integer isGet;//是否领取了该优惠券  0.未领取 1.已领取 2.已领完

	public List<Goods> getCouponGoods() {
		return couponGoods;
	}

	public void setCouponGoods(List<Goods> couponGoods) {
		this.couponGoods = couponGoods;
	}

	public Integer getIsGet() {
		return isGet;
	}

	public void setIsGet(Integer isGet) {
		this.isGet = isGet;
	}
	
}
  