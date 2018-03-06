package com.zfj.xmy.goods.service.pc;

import java.util.List;

import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.util.DataPage;


public interface PcElasticGoodsService {
	/**
	 * es检索商品
	 * @param orderMethod 排序方式
	 * @param goodsName 检索名称
	 * @param pageIndex 页数
	 * @param priceOrder 价格排序方式
	 * @param mainLand 配送范围
	 * @param wordSeg 分词筛选
	 * @param beginPrice 最低价格
	 * @param endPrice 最高价格
	 * @param typeName 显示分类名称
	 * @return DataPage<GoodsDoc>
	 * @author lij
	 * @date 2017年11月4日 上午10:49:46
	 */
	DataPage<GoodsDoc> searchGoods(Integer orderMethod,String goodsName,Integer pageIndex,boolean priceOrder,
			Integer mainLand,String wordSeg,Integer beginPrice,Integer endPrice,String typeName);
	
	List<String> searchTopGoods(String goodsName);
	
}
