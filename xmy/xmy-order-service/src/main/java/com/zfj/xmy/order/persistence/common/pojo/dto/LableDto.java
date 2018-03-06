package com.zfj.xmy.order.persistence.common.pojo.dto;
/**
 * 导出标签使用的实体类
 * @author lij
 */
public class LableDto {
	private String name;//商品名称
	private String code;//商品编号
	private String no;//订单编号
	private Integer num;//数量
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
