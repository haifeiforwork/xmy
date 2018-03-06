package com.zfj.xmy.elasticsearch.persistence.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.zfj.base.util.common.StringUtil;

/**
 * native搜索条件对象
 * @author dengq
 * @createDate 2017年9月5日
 *
 */
public class NativeSearchQueryParameter {

	/**
	 * 查询关键词
	 */
	private String key ;
	
	private final Map<String,Object> queryMap = new LinkedHashMap<>() ;
	
	private final Map<String,Object> filterMap = new LinkedHashMap<>() ;
	/**
	 * 排序
	 */
	private final Map<String,String> orderMap = new LinkedHashMap<>() ;
	/**
	 * 聚合信息
	 */
	private TermsBuilder aggregationTermsBuilder ;
	/**
	 * 起始页
	 */
	private int pageIndex = 0 ;
	/**
	 * 分页尺寸
	 */
	private int pageSize = 20 ;
	
	public NativeSearchQueryParameter() {
	}
	
	public NativeSearchQueryParameter(int pageIndex,int pageSize) {
		this.pageIndex = 0 >= pageIndex ? 0 : pageIndex - 1 ;
		this.pageSize = pageSize ;
	}
	
	public void setKey(String key){
		this.key = key ;
	}
	public String getKey() {
		return key;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = 0 >= pageIndex ? 0 : pageIndex - 1 ;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public TermsBuilder getAggregationTermsBuilder() {
		return aggregationTermsBuilder;
	}
	
	public void setAggregationTermsBuilder(TermsBuilder aggregationTermsBuilder) {
		this.aggregationTermsBuilder = aggregationTermsBuilder;
	}
	/**
	 * 放入查询条件值
	 * @param field
	 * @param value
	 * @param withType
	 * @param searchConstant
	 */
	public NativeSearchQueryParameter put(String field,Object value,WithType withType,String searchConstant){
		field += "_" + searchConstant ;
		if(WithType.WITH_QUERY == withType){
			this.queryMap.put(field,value) ;
		}else{
			this.filterMap.put(field,value) ;
		}
		return this ;
	}
	
	public NativeSearchQueryParameter setOrder(String field,String orderCase){
		orderMap.put(field,orderCase) ;
		return this ;
	}
	
	
	public NativeSearchQueryBuilder getNativeSearchQueryBuilder(){
		NativeSearchQueryBuilder nqb = new NativeSearchQueryBuilder() ;
		//设置分页
		Sort sort = null ;
		if(!orderMap.isEmpty()){
			List<Order> orderList = new ArrayList<>() ;
			for(Map.Entry<String,String> entry : orderMap.entrySet()){
				Order order = new Order(SearchConstant.ELASTIC_ORDER_ASC.equals(entry.getValue()) ? Direction.ASC : Direction.DESC,entry.getKey()) ;
				orderList.add(order) ;
			}
			sort = new Sort(orderList) ;
		}
		PageRequest pageable = new PageRequest(this.pageIndex,this.pageSize, sort) ;
		nqb.withPageable(pageable) ;
		//设置查询关键字
		if(!StringUtil.isEmpty(this.key)){
			nqb.withQuery(QueryBuilders.queryStringQuery(this.key)) ;
		}
		if(!this.queryMap.isEmpty()){
			for(Map.Entry<String,Object> entry : queryMap.entrySet()){
				with(entry.getKey(),entry.getValue(),WithType.WITH_QUERY,nqb) ;
			}
		}
		if(!this.filterMap.isEmpty()){
			for(Map.Entry<String,Object> entry : filterMap.entrySet()){
				with(entry.getKey(),entry.getValue(),WithType.WITH_FILTER,nqb) ;
			}
		}
		if(null != aggregationTermsBuilder){
			nqb.addAggregation(aggregationTermsBuilder) ;
		}
		return nqb ;
	}
	//目前只支持以下几种查询
	private void with(String key,Object value,WithType withType,NativeSearchQueryBuilder nqb){
		String[] keyArr = key.split("_") ;
		String field = keyArr[0] ;
		String searchConstant = keyArr[1] ;
		switch (searchConstant) {
		case SearchConstant.ELASTIC_OP_EQ://相等
			TermQueryBuilder termqueryBuilder = QueryBuilders.termQuery(field,value) ;
			if(withType == WithType.WITH_QUERY){
				nqb.withQuery(termqueryBuilder) ;
			}else{
				nqb.withFilter(termqueryBuilder) ;
			}
			break;
		case SearchConstant.ELASTIC_OP_CN://模糊
			FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery(field,value) ;
			if(withType == WithType.WITH_QUERY){
				nqb.withQuery(fuzzyQueryBuilder) ;
			}else{
				nqb.withFilter(fuzzyQueryBuilder) ;
			}
			break ;
		case SearchConstant.ELASTIC_OP_IN://in查询
			Collection<?> collection = (Collection<?>) value ;
			TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery(field,collection) ;
			if(withType == WithType.WITH_QUERY){
				nqb.withQuery(termsQueryBuilder) ;
			}else{
				nqb.withFilter(termsQueryBuilder) ;
			}
			break ;
//		case SearchConstant.ELASTIC_OP_GTE:
//			nqb.withFilter(QueryBuilders.rangeQuery("price").gte(from))
//			break ;
		}
	}
	
	public static enum WithType{
		WITH_QUERY ,WITH_FILTER ;
	}
}
