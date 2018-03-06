package com.zfj.xmy.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.pojo.LogicType;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.util.common.StringUtil;

/**
 * 通查请求参数的对象
 * @author dengq
 * @createDate 2015年11月19日
 * @desription
 */
public class ReqData {
	
	public static final String FIELD = "field" ;
	public static final String VALUE = "value" ;
	public static final String OP = "op" ;
	public static final String LOGIC = "logic" ;
	public static final String GROUP = "group" ;
	
	public static final String GROUP_ONLY = "onlyOne" ;
	/**
	 * 条件过滤的集合
	 */
	private List<Map<String,Object>> rule ;
	/**
	 * 字段名排序
	 */
	private String sortname ;
	/**
	 * 排序方式
	 */
	private String sortorder ;
	/**
	 * 请求参数
	 */
	private String sortmix ;
	/**
	 * 请求分页尺寸
	 */
	private Integer pageSize ;
	/**
	 * 当前页数
	 */
	private Integer pageIndex ;
	
	public ReqData() {
	}
	
	public ReqData(String field,Object value,String op,String group){
		super() ;
		this.putValue(field, value, op, group) ;
	}
	public ReqData(String field,Object value,String op,LogicType logic){
		super() ;
		this.putValue(field, value, op, GROUP_ONLY, logic) ;
	}
	public ReqData(String field,Object value,String op,String group,LogicType logic){
		super() ;
		this.putValue(field, value, op, group, logic) ;
	}
	public ReqData(String field,Object value,String op){
		super() ;
		this.putValue(field, value, op) ;
	}
	public ReqData(String field,Object value){
		super() ;
		this.putValue(field, value) ;
	}
	
	public List<Map<String, Object>> getRule() {
		return rule;
	}
	public void setRule(List<Map<String, Object>> rule) {
		this.rule = rule;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	
	public void putValue(String field,Object value,String op,String group,LogicType logic){
		if(null == this.rule) this.rule = new ArrayList<Map<String,Object>>() ;
		Map<String,Object> data = new HashMap<String,Object>() ;
		data.put(FIELD,field) ;
		data.put(VALUE,value) ;
		data.put(OP, op) ;
		data.put(LOGIC,logic) ;
		data.put(GROUP,group) ; //or条件的组别
		this.rule.add(data) ;
	}
	public void putValue(String field,Object value,String op,String group){
		this.putValue(field, value, op, group, LogicType.AND);
	}
	public void putValue(String field,Object value,String op,LogicType logic){
		this.putValue(field, value, op, GROUP_ONLY, LogicType.AND);
	}
	public void putValue(String field,Object value,String op){
		this.putValue(field, value, op,GROUP_ONLY) ;
	}
	
	public void putValue(String field,Object value){
		this.putValue(field, value,SystemConstant.REQ_PARAMETER_EQ) ;
	}
	
	public Object getValue(String field){
		if(isEmpty(field) || isEmpty(this.rule)) return null ;
		
		for(Map<String,Object> map : this.rule){
			if(map.get(FIELD).equals(field)) return map.get(VALUE) ;
		}
		return null ;
	}
	
	public void removeValue(String field){
		if(isEmpty(field) || isEmpty(this.rule)) return ;
		
		Map<String,Object> removeMap = null ;
		for(Map<String,Object> map:this.rule){
			if(map.get(FIELD).equals(field)) removeMap = map ;
		}
		if(null == removeMap) return ;
		this.rule.remove(removeMap) ;
	}
	
	public void removeList(List<String> fieldList){
		if(isEmpty(fieldList)) return ;
		
		for(String field:fieldList){
			this.removeValue(field) ;
		}
	}
	
	public void clearValue(){
		if(isEmpty(this.rule)) return ;
		
		this.rule.clear() ;
	}
	
	@SuppressWarnings("unchecked")
	private boolean isEmpty(Object object){
		if(null == object) return true ;
		if(object instanceof String){
			if(StringUtil.isEmpty((String)object)) return true ;
		}else{
			if(((List<Map<String,Object>>)object).isEmpty()) return true ;
		}
		return false ;
	}

	public String getSortmix() {
		return sortmix;
	}

	public void setSortmix(String sortmix) {
		this.sortmix = sortmix;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public RowBounds getRowBounds(int pageIndex){
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex) ;
		pageBean.setPageSize(null == this.pageSize || 0 >= this.pageSize ? SystemConstant.PAGE_SIZE : this.pageSize) ;
		return pageBean.getRowBounds() ;
	}
	
	public RowBounds getRowBounds(){
		return this.getRowBounds(null == this.pageIndex || 0 >= this.pageIndex ? 1 : this.pageIndex) ;
	}

}
