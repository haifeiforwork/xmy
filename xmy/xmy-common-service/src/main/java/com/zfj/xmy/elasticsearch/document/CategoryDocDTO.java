package com.zfj.xmy.elasticsearch.document;

import java.util.ArrayList;
import java.util.List;

import com.zfj.base.util.collection.CollectionExtUtils;
/**
 * 类目数据对象
 * @author dengq
 * @createDate 2017年9月15日
 *
 */
public class CategoryDocDTO {

	/**
	 * 类目的id
	 */
	private Long id ;
	/**
	 * 类目名称
	 */
	private String name ;
	/**
	 * 分类子集
	 */
	private List<CategoryDocDTO> children ;
	/**
	 * 商品的id集合
	 */
	private List<Long> goodsIdList ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryDocDTO> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryDocDTO> children) {
		this.children = children;
	}

	public List<Long> getGoodsIdList() {
		return goodsIdList;
	}

	public void setGoodsIdList(List<Long> goodsIdList) {
		this.goodsIdList = goodsIdList;
	}
	
	public void addChild(CategoryDocDTO categoryDocDTO){
		if(CollectionExtUtils.isEmpty(children)){
			children = new ArrayList<>() ;
		}
		children.add(categoryDocDTO) ;
	}
	
	public void addGoodsId(Long goodsId){
		if(CollectionExtUtils.isEmpty(this.goodsIdList)){
			this.goodsIdList = new ArrayList<>() ;
		}
		goodsIdList.add(goodsId) ;
	}
	/**
	 * 根据id，获取其他对象信息(主要是在添加数据的时候使用)
	 * @param categoryDocDTOs
	 * @param id
	 * @return
	 */
	public static CategoryDocDTO getById(List<CategoryDocDTO> categoryDocDTOs,Long id){
		for(CategoryDocDTO categoryDocDTO : categoryDocDTOs){
			if(categoryDocDTO.getId().equals(id)){
				return categoryDocDTO ;
			}
		}
		return null ;
	}
	
}
