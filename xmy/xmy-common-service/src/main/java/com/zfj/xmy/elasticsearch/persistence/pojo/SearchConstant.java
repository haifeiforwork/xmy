package com.zfj.xmy.elasticsearch.persistence.pojo;
/**
 * 常量信息
 * @author dengq
 * @createDate 2017年9月14日
 *
 */
public class SearchConstant {

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
	
	public static final String ELASTIC_ORDER_ASC = "asc" ;
	public static final String ELASTIC_ORDER_DESC = "desc" ;
	
	public static final int limit = 100 ;
	
	public enum SearchOperation{
		UPDATE ,DELETE ,SAVE ;
	}
}
