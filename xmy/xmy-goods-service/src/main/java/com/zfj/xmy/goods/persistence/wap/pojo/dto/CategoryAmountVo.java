package com.zfj.xmy.goods.persistence.wap.pojo.dto;

public class CategoryAmountVo {
	
	//分类id
	private Long id;
	
	//分类名称
	private String cateName;
	
	//分类数量
	private Long amount;
	
	public CategoryAmountVo() {}

	public CategoryAmountVo(Long id, String cateName, Long amount) {
		super();
		this.id = id;
		this.cateName = cateName;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
}
