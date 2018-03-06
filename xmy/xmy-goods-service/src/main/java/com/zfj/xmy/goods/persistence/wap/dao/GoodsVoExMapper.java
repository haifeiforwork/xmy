package com.zfj.xmy.goods.persistence.wap.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsQueryVo;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsVo;

public interface GoodsVoExMapper {
	
	List<GoodsVo> findGoodsByUserId(Long id);
	
	List<GoodsVo> findGoodsByUserIdAndCategoryName(GoodsQueryVo vo);
	
	Goods roundGoods(Integer i,@Param("goodsId")Long goodsId) ;
	
	GoodsVo getGoodsId();
}
