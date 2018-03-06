package com.zfj.xmy.order.persistence.common.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcOrderGoodsDto;
/**
 * @author lij
 *
 */
public class OrderDto extends Order{
	//订单对应商品实体类
	private List<OrderGoods> orderGoods;
	
	private List<PcOrderGoodsDto> orderGoodsDto;//pc使用的
	
	private List<OrderGoodsDto> goodsList;//导出商品时使用
	
	private String couponName; //抵用券名称
	
	private BigDecimal couponValue; //抵用面值
	
	private BigDecimal goodsTotalPrice; //商品总金额
	
	private Integer isPaySuccess ; //是否支付成功  0 支付成功 1 支付失败
	
	private Date cancelEndTime ; //取消截止时间
	
	private Date nowTime;
	
	public List<OrderGoodsDto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OrderGoodsDto> goodsList) {
		this.goodsList = goodsList;
	}

	public List<PcOrderGoodsDto> getOrderGoodsDto() {
		return orderGoodsDto;
	}

	public void setOrderGoodsDto(List<PcOrderGoodsDto> orderGoodsDto) {
		this.orderGoodsDto = orderGoodsDto;
	}

	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(BigDecimal couponValue) {
		this.couponValue = couponValue;
	}

	public BigDecimal getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}

	public Integer getIsPaySuccess() {
		return isPaySuccess;
	}

	public void setIsPaySuccess(Integer isPaySuccess) {
		this.isPaySuccess = isPaySuccess;
	}

	public Date getCancelEndTime() {
		return cancelEndTime;
	}

	public void setCancelEndTime(Date cancelEndTime) {
		this.cancelEndTime = cancelEndTime;
	}

	public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	
}
