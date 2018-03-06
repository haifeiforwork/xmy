package com.zfj.xmy.elasticsearch.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.zfj.xmy.elasticsearch.XmyIndex;

@Document(indexName= XmyIndex.CATEGORY_INDEX_NAME, type= XmyIndex.CATEGORY_TYPE)
public class CategoryDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品种类的分类关联id
	 */
	@Id
	private Long id ;
	
	/**
	 * 商品名称
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String fullName ;
	
	/**
	 * 商品分类
	 */
	@Field(type=FieldType.String,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String categoryName ;
	
	/**
	 * 商品品牌
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String brandName ;
	
	/**
	 * 种类的id
	 */
	@Field(type=FieldType.Long,index=FieldIndex.not_analyzed)
	private Long cid ;
	/**
	 * 商品的id
	 */
	@Field(type=FieldType.Long,index=FieldIndex.not_analyzed)
	private Long goodsId ;
	/**
	 * 种类的父级id
	 */
	@Field(type=FieldType.Long,index=FieldIndex.not_analyzed)
	private Long pid ;
	/**
	 * 种类的名称
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String name ;
	/**
	 * 种类的父级名称
	 */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed)
	private String pname ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	
}
