package com.zfj.xmy.order.persistence.app.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情dto
 * @author wy
 *
 */
public class OrderOutDto {

	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 订单编号
	 */
	private String no;
	
	/**
	 * 订单状态
	 */
	private Integer status;
	
	/**
	 * 订单商品数量
	 */
	private Integer goodsNum;
	
	/**
	 * 订单总金额
	 */
	private BigDecimal total;
	
	/**
	 * 订单生成时间
	 */
	private Date createTime;
	
	/**
	 * 商品详情
	 */
	private List<GoodsDto> goods;
	
	/***
	 * 订单运费
	 */
	private BigDecimal freight ;
	
	/**
	 * 是否含有跨境商品字段
	 */
	private Integer isAcrossBorders;
	
	/**
	 * 支付方式  1.支付宝 2.微信 3.银联 4.购物卡 5.货到付款
	 */
	private Integer payType;
	
	private Integer isCancleOrder ; // 是否可以取消订单
	
	/**
	 * 订单是否取消  0已取消 1未取消
	 */
	private Integer isDel ; 
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<GoodsDto> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsDto> goods) {
		this.goods = goods;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getIsAcrossBorders() {
		return isAcrossBorders;
	}

	public void setIsAcrossBorders(Integer isAcrossBorders) {
		this.isAcrossBorders = isAcrossBorders;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getIsCancleOrder() {
		return isCancleOrder;
	}

	public void setIsCancleOrder(Integer isCancleOrder) {
		this.isCancleOrder = isCancleOrder;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	
	
	
	
	
	
}
