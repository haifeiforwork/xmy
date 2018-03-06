package com.zfj.xmy.common;

import java.io.Serializable;
import java.util.List;

import com.zfj.xmy.freight.vo.FreightGoods;

/**
 * 公共数据VO对象
 * @author cj
 * @createDate 2017年10月26日
 *
 */
public class CommonVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//包含重量字段商品列表
	private List<FreightGoods> freightGoodsList;

	public List<FreightGoods> getFreightGoodsList() {
		return freightGoodsList;
	}

	public void setFreightGoodsList(List<FreightGoods> freightGoodsList) {
		this.freightGoodsList = freightGoodsList;
	}
	
	
}
