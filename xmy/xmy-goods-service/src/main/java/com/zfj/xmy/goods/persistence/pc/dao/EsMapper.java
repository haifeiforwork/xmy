package com.zfj.xmy.goods.persistence.pc.dao;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;

/**
 * @author lij
 * 搜索使用的分词查询
 */
public interface EsMapper {
	/**
	 * 查询二级分类的ID
	 * @param criteriaParameter
	 * @return List<String>
	 * @author lij
	 * @date 2017年10月20日 下午2:59:31
	 */
	List<String> findTermDataId(CriteriaParameter criteriaParameter);
	/**
	 * 查询商品的二级分类下的分词的父级名称并去重复
	 * @param criteriaParameter
	 * @return List<String>
	 * @author lij
	 * @date 2017年10月20日 下午3:00:16
	 */
	List<String> findTowName(CriteriaParameter criteriaParameter);
}
