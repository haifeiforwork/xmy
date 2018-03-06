package com.zfj.xmy.elasticsearch.persistence.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.elasticsearch.document.CategoryDoc;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;

public interface ElasticsearchExMapper {
	
	/**
	 * 查询所有的商品类别信息
	 * @param parameter
	 * @return
	 */
	List<CategoryDoc> listCategoryDoc(CriteriaParameter parameter,RowBounds rowBounds) ;
	/**
	 * 统计商品类别数量
	 * @param parameter
	 * @return
	 */
	int countCategoryDoc(CriteriaParameter parameter) ;
	/**
	 * 查询所有的商品信息
	 * @param parameter
	 * @param rowBounds
	 * @return
	 */
	List<GoodsDoc> listGoodsDoc(CriteriaParameter parameter) ;
	/**
	 * 统计所有的商品数量
	 * @param parameter
	 * @return
	 */
	int countGoodsDoc(CriteriaParameter parameter) ;
	
}
