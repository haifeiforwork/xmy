package com.zfj.xmy.elasticsearch.persistence.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Field;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.fetch.fielddata.FieldDataFieldsContext.FieldDataField;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;

import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;

/**
 * 搜索查询参数对象
 * @author dengq
 * @createDate 2017年8月22日
 *
 */
public class ElasticParam {
	/**
	 * 查询名称
	 */
	public static final String FIELD_NAME = "field_name" ;
	/**
	 * 查询值
	 */
	public static final String FIELD_VALUE = "field_value" ;
	/**
	 * 查询运算符
	 */
	public static final String OPERATION = "op" ;
	
	public static final int PAGE_SIZE = 20 ;
	
	
	public static final String ELASTIC_OP_CN = "cn" ;
	public static final String ELASTIC_OP_EQ = "eq" ;
	public static final String ELASTIC_OP_NE = "ne" ;
	public static final String ELASTIC_OP_IN = "in" ;
	public static final String ELASTIC_OP_NI = "not_in" ;
	public static final String ELASTIC_OP_GT = "gt" ;
	public static final String ELASTIC_OP_GTE = "gte" ;
	public static final String ELASTIC_OP_LT = "lt" ;
	public static final String ELASTIC_OP_LTE = "lte" ;
	public static final String ELASTIC_OP_IS_NULL = "is_null" ;
	public static final String ELASTIC_OP_IS_NOT_NULL = "is_not_null" ;
	

	/**
	 * 查询参数条件
	 */
	private List<Map<String,Object>> paramMap = new ArrayList<>() ;
	/**
	 * 排序参数条件
	 */
	private Map<String,SortOrder> sortMap = new HashMap<>() ;
	
	private Integer pageIndex ;
	
	private Integer pageSize ;
	
	private ElasticParam(){}
	
	public static ElasticParam create(){
		return new ElasticParam() ;
	}
	
	public ElasticParam putParam(String key,Object value){
		return putParam(key, value, ELASTIC_OP_EQ) ;
	}
	public ElasticParam putParam(String key,Object value,String op){
		Map<String,Object> map = new HashMap<>() ;
		map.put(FIELD_NAME,key) ;
		map.put(FIELD_VALUE,value) ;
		map.put(OPERATION,op) ;
		this.paramMap.add(map) ;
		return this ;
	}
	
	public ElasticParam putSortParam(String orderName){
		return putSortParam(orderName,SortOrder.DESC) ;
	}
	public ElasticParam putSortParam(String orderName,SortOrder sortOrder){
		this.sortMap.put(orderName,sortOrder) ;
		return this ;
	}
	/**
	 * 获取查询条件
	 * @return
	 */
	public CriteriaQuery getCriteriaQuery(){
		Sort sort = null ;
		if(!CollectionUtils.isEmpty(this.sortMap)){
			List<Order> orderList = new ArrayList<>() ;
			for(Map.Entry<String,SortOrder> entry : this.sortMap.entrySet()){
				Order order = new Order(getDirection(entry.getValue()),entry.getKey()) ;
				orderList.add(order) ;
			}
			sort = new Sort(orderList) ;
		}
		return new CriteriaQuery(paramToCriteria(this.paramMap),getPageRequest(sort)) ;
	}
	/**
	 * 参数转换，目前只支持and连接，or连接未扩展
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Criteria paramToCriteria(List<Map<String,Object>> paramMap){
		Criteria criteria = new Criteria() ;
		if(CollectionUtils.isEmpty(paramMap)){
			return criteria ;
		}
		for(Map<String,Object> param : paramMap){
			Criteria andCriteria = new Criteria((String)param.get(FIELD_NAME)) ;
			switch ((String)param.get(OPERATION)) {
			case ELASTIC_OP_CN:
//				andCriteria.fuzzy((String)param.get(FIELD_VALUE)) ;
				andCriteria.contains((String)param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_EQ:
				andCriteria.is((String)param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_NE:
				andCriteria.not().is((String)param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_IN:
				andCriteria.in(((List<Object>)param.get(FIELD_VALUE)).iterator()) ;
				break;
			case ELASTIC_OP_NI:
				andCriteria.notIn(((List<Object>)param.get(FIELD_VALUE)).iterator()) ;
				break;
			case ELASTIC_OP_GT:
				andCriteria.greaterThan(param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_GTE:
				andCriteria.greaterThanEqual(param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_LT:
				andCriteria.lessThan(param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_LTE:
				andCriteria.lessThanEqual(param.get(FIELD_VALUE)) ;
				break;
			case ELASTIC_OP_IS_NULL:
				andCriteria.is(null) ;
				break;
			case ELASTIC_OP_IS_NOT_NULL:
				andCriteria.not().is(null) ;
				break;
			default:
				andCriteria = null ;
				break ;
			}
			if(null != andCriteria){
				criteria.and(andCriteria) ;
			}
		}
		return criteria ;
	}
	
	private Direction getDirection(SortOrder sortOrder){
		return null == sortOrder || sortOrder == SortOrder.ASC ? Direction.ASC : Direction.DESC ;
	}
	
	
	/**
	 * 按指定分词查询
	 * @param key 关键字
	 * @param fields 指定字段，如果没有指定，则从对象中指定的分词来查询
	 * @return
	 */
	public NativeSearchQuery getKeyWord(String key,String... fields){
		if(StringUtil.isEmpty(key)){
			throw new BusinessException("查询关键字不能为空") ;
		}
		PageRequest pageRequest = getPageRequest() ;
		NativeSearchQueryBuilder nqb = new NativeSearchQueryBuilder().withPageable(pageRequest) ;
		/**
		 * 设置排序
		 */
		if(CollectionUtils.isEmpty(this.sortMap)){
			nqb.withSort(SortBuilders.scoreSort().order(SortOrder.DESC)) ;//按精度排序
		}else{
			for(Map.Entry<String,SortOrder> entry : this.sortMap.entrySet()){
				nqb.withSort(SortBuilders.fieldSort(entry.getKey()).order(entry.getValue())) ;
			}
		}
		/**
		 * 参数设置
		 */
		QueryStringQueryBuilder qsqb = QueryBuilders.queryStringQuery(key) ;
		qsqb.analyzer("ik_max_word") ;
		if(null != fields){
			for(String field : fields){
				qsqb.field(field) ;
			}
		}
		HighlightBuilder builder = new HighlightBuilder() ;
		builder.field("fullName") ;
		nqb.withQuery(qsqb) ;
//		nqb.withFilter(QueryBuilders.fuzzyQuery("fullName","红心")) ;
		nqb.withFilter(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("fullName","[超奇果业] 越南红心火龙果 1个/盒(约500g)"))) ;
		return nqb.build() ;
	}
	
	public PageRequest getPageRequest(Sort sort){
		return new PageRequest(this.getPageIndex(),this.getPageSize(),sort) ; 
	}
	public PageRequest getPageRequest(){
		return getPageRequest(null) ;
	}

	public List<Map<String, Object>> getParamMap() {
		return paramMap;
	}

	public void setParamMap(List<Map<String, Object>> paramMap) {
		this.paramMap = paramMap;
	}

	public Integer getPageIndex() {
		return null == this.pageIndex || 0 > this.pageIndex ? 0 : this.pageIndex - 1 ;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return null == this.pageSize ? PAGE_SIZE : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void clearAll(){
		clearParam() ;
		clearSort() ;
		this.pageIndex = 1 ;
		this.pageSize = PAGE_SIZE ;
	}
	
	public void clearParam(){
		if(!CollectionUtils.isEmpty(this.paramMap)){
			this.paramMap.clear() ;
		}
	}
	
	public void clearSort(){
		if(!CollectionUtils.isEmpty(this.sortMap)){
			this.sortMap.clear() ;
		}	
	}
}
