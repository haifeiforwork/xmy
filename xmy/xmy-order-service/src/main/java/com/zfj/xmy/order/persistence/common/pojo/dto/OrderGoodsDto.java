package com.zfj.xmy.order.persistence.common.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
/**
 * 
 * 
 * @author ljie 6.16
 *
 */
public class OrderGoodsDto extends Goods{
	
	
	//根据商品编号分组后的总商品数量的名称
	private Integer sumGoodsNum;
	//活动名称
	private String activeName;
	//订单编号
	private List<ShoppingDto> sDto;
	//活动类型
	private Long activeType;
	//活动ID 
	private Long activeId;
	
	public Long getActiveType() {
		return activeType;
	}

	public void setActiveType(Long activeType) {
		this.activeType = activeType;
	}

	public Long getActiveId() {
		return activeId;
	}

	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	
	public List<ShoppingDto> getsDto() {
		return sDto;
	}

	public void setsDto(List<ShoppingDto> sDto) {
		this.sDto = sDto;
	}

	public Integer getSumGoodsNum() {
		return sumGoodsNum;
	}

	public void setSumGoodsNum(Integer sumGoodsNum) {
		this.sumGoodsNum = sumGoodsNum;
	}

	

}
