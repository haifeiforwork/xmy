package com.zfj.xmy.goods.persistence.pc.dao;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDto;

public interface PcGoodsExMapper {
	
	PcGoodsDto getPcGoodsDto(long id);
	
	PcGoodsDto selectByPrimaryKey(long id);
	/**
	 * @return List<Integer>
	 * @author lij
	 * @date 2017年8月1日 下午5:00:03
	 * 查询全部商品销售的排行前2名的商品id
	 */
	List<Integer> findGoodsDeal();
	/**
	 * @return List<Integer>
	 * @author lij
	 * @date 2017年8月1日 下午6:02:07
	 * 查询分类商品销售排行
	 */
	List<Integer> findTypeGoodsDeal(CriteriaParameter criteriaParameter);
	/**
	 * @param criteriaParameter
	 * @return List<String>
	 * @author lij
	 * @date 2017年8月11日 下午3:02:27
	 * 查询商品的一级分类名称
	 */
	List<String> findGoodsIsOut(CriteriaParameter criteriaParameter);
	/**
	 * @return List<Integer>
	 * @author lij
	 * @date 2017年8月1日 下午5:00:03
	 * 查询跨境商品前10商品
	 */
	List<Goods> findBorderGoods(CriteriaParameter criteriaParameter);
}
