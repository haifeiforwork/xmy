package com.zfj.xmy.goods.service.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.VocabularyMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.goods.persistence.common.dao.TermDataExMapper;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDto;
import com.zfj.xmy.goods.service.app.TermDataExService;

@Service
public class TermDataExServiceImpl implements TermDataExService{
	
	@Autowired
	private TermDataExMapper termDataExMapper;
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Map<String, Object>> findCollectionCategorys(List<GoodsWithBLOBs> collectionGoods){
		Map<String, Integer> collectionCategorys=new HashMap<String, Integer>();
		Integer keyCount =null;
		int i=1;
		 for (GoodsWithBLOBs goodsWithBLOBs : collectionGoods) {
			 //默认key对应的value为1
			 keyCount=new Integer(1);
			// TermData categoryByGoodsCategoryIds = termDataExMapper.getCategoryByGoodsCategoryIds(goodsWithBLOBs.getId());
			 //一个商品可能属于多个分类，所以取出来可能是一个集合
			 //List<TermData> firstIds = termDataExMapper.getCategoryByGoodsCategoryIds(goodsWithBLOBs.getId());
			 Goods goods=goodsMapper.selectByPrimaryKey(goodsWithBLOBs.getId());
			//先查看是否含有name这个key，如果有就先取出来，key对应的value自加一次再存进去。
			 String categoryName=goods.getCategoryName().replace(",", "");
				 if(collectionCategorys.containsKey(categoryName)){
					 //value数值+1，代表同类别的数据加一次
					 keyCount= collectionCategorys.get(categoryName);
					 keyCount=keyCount+1;
					 collectionCategorys.put(categoryName, keyCount);
				}
				 //没有这个key，代表之前还没有put进去，那就再put进取一次，并且value为1，因为是第一个，keycount默认为1
				 else{
					 collectionCategorys.put(categoryName,keyCount);
				}
			
		 }
		 List<Map<String, Object>> categorysList=new ArrayList<Map<String, Object>>();
		 Set<String> keySet = collectionCategorys.keySet();
		 for (String string : keySet) {
			Map< String, Object> m=new HashMap<String, Object>();
			m.put("name", string);
			m.put("count", collectionCategorys.get(string));
			categorysList.add(m);
		}
		 return categorysList;
	}
	
	@Override
	public long getVocabularyIdByMark(String mark){
		ReqData reqData = new  ReqData();
		reqData.putValue(SystemConstant.TERMDATA.MARK, mark, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary	vocabulary = vocabularyMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (vocabulary != null ) {
			return vocabulary.getId();
		} else { return 0 ;}
		
	}
}
