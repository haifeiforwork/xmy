package com.zfj.xmy.order.persistence.app.pojo.dto;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * @Title: AppOrderDir.java 
 * @Package com.zfj.xmy.order.persistence.app.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年7月31日 下午7:13:33 
 */
public class AppOrderDir {

	
	private long id ;//订单id
	
	private String no; //订单编号
	
	private Integer isAcrossBorders ; // 是否含有跨境商品 0:是 1：否
	
	private Integer points ; //赠送积分
	
	private Integer usePoints;//消耗积分
	
	private BigDecimal totalMoney; //总金额
	
	private BigDecimal freight; //运费
	
	private BigDecimal voucher; //抵用券
	
	private BigDecimal remainMoney ; //余额
	
	private List<AppOrderGoodsDir> list = new  ArrayList<AppOrderGoodsDir>();  //订单商品集合
	
	private List<String> invoiceContents = new  ArrayList<String>(); //发票内容
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<AppOrderGoodsDir> getList() {
		return list;
	}

	public void setList(List<AppOrderGoodsDir> list) {
		this.list = list;
	}

	public Integer getIsAcrossBorders() {
		return isAcrossBorders;
	}

	public void setIsAcrossBorders(Integer isAcrossBorders) {
		this.isAcrossBorders = isAcrossBorders;
	}

	public List<String> getInvoiceContents() {
		return invoiceContents;
	}

	public void setInvoiceContents(List<String> invoiceContents) {
		this.invoiceContents = invoiceContents;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getVoucher() {
		return voucher;
	}

	public void setVoucher(BigDecimal voucher) {
		this.voucher = voucher;
	}

	public BigDecimal getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(BigDecimal remainMoney) {
		this.remainMoney = remainMoney;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(Integer usePoints) {
		this.usePoints = usePoints;
	}
	
	
	
	
}
  