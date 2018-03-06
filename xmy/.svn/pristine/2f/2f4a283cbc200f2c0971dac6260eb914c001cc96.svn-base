package com.zfj.xmy.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.LogicType;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.base.util.common.StringUtil;

/**
 * 通查请求参数的转换工具
 * 
 * @author dengq
 * @createDate 2015年11月19日
 * @desription
 */
public final class ReqUtil {

	private ReqUtil() {
	}

	/**
	 * 将请求参数转换成条件,条件符号：eq:等于，ne：不等于，cn：like，le:小于等于，ge:大于等于，lt：小于，gt：大于，regexp：正则表达式
	 * 
	 * @param criteria
	 * @param reqData
	 * @return 返回排序的条件(xxx.xxx desc(or asc))
	 */
	public static void reqParamterToCritera(Criteria criteria, ReqData reqData) {
		if (null == reqData || CollectionExtUtils.isEmpty(reqData.getRule()))
			return;
		List<Map<String, Object>> rule = reqData.getRule();
		for (Map<String, Object> req : rule) {
			LogicType logic = (LogicType) req.get(ReqData.LOGIC);
			logic = null == logic ? LogicType.AND : logic ;
			// LogicType logic = getType(req.get("logic")) ;
			String field = req.get(ReqData.FIELD).toString();
			String value = null == req.get("value") ? "" : req.get(ReqData.VALUE).toString();
			switch (req.get(ReqData.OP).toString()) {
			case SystemConstant.REQ_PARAMETER_EQ:// 等于
				criteria.equalTo(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_NE:// 不等于
				criteria.notEqualTo(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_IN:// in
				criteria.in(field, getInNi(value), logic);
				break;
			case SystemConstant.REQ_PARAMETER_NI:// not in
				criteria.notIn(field, getInNi(value), logic);
				break;
			case SystemConstant.REQ_PARAMETER_GT:// 大于
				criteria.greaterThan(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_GE:// 大于等于
				criteria.greaterThanOrEqualTo(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_LT:// 小于
				criteria.lessThan(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_LE:// 小于等于
				criteria.lessThanOrEqualTo(field, value, logic);
				break;
			case SystemConstant.REQ_PARAMETER_CN:// like
				criteria.like(field, "%" + value + "%", logic);
				break;
			case SystemConstant.REQ_PARAMETER_IS_NULL:// is null
				criteria.isNull(field, logic);
				break;
			case SystemConstant.REQ_PARAMETER_IS_NOT_NULL:// is not null
				criteria.isNotNull(field, logic);
				break;
			case SystemConstant.REQ_PARAMETER_REGEXP:// REGEXP
				criteria.regexp(field,value,logic) ;
			}
		}
	}

	/**
	 * 将请求转换成CriteriaParameter
	 * 
	 * @param reqData
	 * @return
	 */
	public static CriteriaParameter reqParameterToCriteriaParameter(ReqData reqData) {
		CriteriaParameter parameter = null;
		if (null != reqData) {
			parameter = new CriteriaParameter();
			Criteria criteria = parameter.createCriteria();
			reqParamterToCritera(criteria, reqData);
			if (!StringUtil.isEmpty(reqData.getSortmix())) {
				parameter.setOrderByClause(reqData.getSortmix());
				return parameter;
			}
			String field = "";
			if (!StringUtil.isEmpty(reqData.getSortname())) {
				field = " " + reqData.getSortname();
				if (!StringUtil.isEmpty(reqData.getSortorder()))
					field = field + " " + reqData.getSortorder();
				else
					field = field + " desc";
				parameter.setOrderByClause(field);
			}
		}
		return parameter;
	}

	public static CriteriaParameter reqParameterToCriteriaParameter(List<ReqData> reqList) {
		CriteriaParameter parameter = null;
		if (CollectionExtUtils.isEmpty(reqList))
			return parameter;
		parameter = new CriteriaParameter();
		for (ReqData reqData : reqList) {
			if (null != reqData) {
				Criteria criteria = parameter.createCriteria();
				reqParamterToCritera(criteria, reqData);
			}
		}
		if (!StringUtil.isEmpty(reqList.get(0).getSortmix())) {
			parameter.setOrderByClause(reqList.get(0).getSortmix());
			return parameter;
		}
		String field = "";
		if (!StringUtil.isEmpty(reqList.get(0).getSortname())) {
			field = " " + reqList.get(0).getSortname();
			if (!StringUtil.isEmpty(reqList.get(0).getSortorder()))
				field = field + " " + reqList.get(0).getSortorder();
			else
				field = field + " desc";
			parameter.setOrderByClause(field);
		}
		return parameter;
	}

	/**
	 * 请求参数返回map，这里的map参数全为相等逻辑
	 * 
	 * @param reqData
	 * @return
	 */
	public static Map<String, Object> reqParameterToMap(ReqData reqData) {
		if (null == reqData || CollectionExtUtils.isEmpty(reqData.getRule()))
			return null;
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String, Object>> rule = reqData.getRule();
		for (Map<String, Object> req : rule) {
			String field = req.get("field").toString();
			String value = req.get("value").toString();
			param.put(field, value);
		}
		String field = reqData.getSortname();
		if (!StringUtil.isEmpty(field)) {
			param.put("field", field);
			if (!StringUtil.isEmpty(reqData.getSortorder()))
				param.put("sortorder", reqData.getSortorder());
			else
				param.put("sortorder", "desc");
		}
		return param;
	}

	public static List<Object> getInNi(String value) {
		return getInNi(Object.class, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getInNi(Class<T> clz, String value) {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		String[] strs = value.split(",");
		for (String str : strs) {
			if(Object.class.equals(clz)){
				Object tmp = (Object) str ;
				list.add((T) tmp) ;
			}else if(String.class.equals(clz)){
				list.add((T) str) ;
			}else if(Integer.class.equals(clz)){
				Integer tmp = Integer.parseInt(str) ;
				list.add((T) tmp);
			}else if(Long.class.equals(clz)){
				Long tmp = Long.parseLong(str) ;
				list.add((T) tmp) ;
			}else if(Byte.class.equals(clz)){
				Byte tmp = Byte.parseByte(str) ;
				list.add((T) tmp) ;
			}else if(Short.class.equals(clz)){
				Short tmp = Short.parseShort(str) ;
				list.add((T) tmp) ;
			}
		}
		return list;
	}

}
