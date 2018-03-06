package com.zfj.xmy.activity.persistence.app.pojo.dto;

public class CouponSearchInDto {
	//查询类型：2已过期 ；1 使用记录；0 未使用
	private Integer useType;
	
	// 使用范围 （1：全场通用；2：分类使用；3：限定商品；4：排队商品）
	private Integer useRange;
	
	// 排序方法  1 过期时间 ； 2 最优惠
 	private Integer order;
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Integer getUseRange() {
		return useRange;
	}
	public void setUseRange(Integer useRange) {
		this.useRange = useRange;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
}