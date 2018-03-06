package com.zfj.xmy.goods.persistence.pc.pojo.dto;  
/** 
 * @Title: PcGoodsPath.java 
 * @Package com.zfj.xmy.goods.persistence.pc.pojo.dto 
 * @Description: 
 * @author hexw
 * @date 2017年8月27日 上午10:01:41 
 */
public class PcGoodsPath {
	
	private String parentCategory; //一级分类名称
	
	private String category; //二级分类名称
	
	private long categoryId; //二级分类id
	
	private String wordSeg; //所属分词
	
	private long wordSegId; //分词id

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getWordSeg() {
		return wordSeg;
	}

	public void setWordSeg(String wordSeg) {
		this.wordSeg = wordSeg;
	}

	public long getWordSegId() {
		return wordSegId;
	}

	public void setWordSegId(long wordSegId) {
		this.wordSegId = wordSegId;
	}
	
	

}
  