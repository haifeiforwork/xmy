package com.zfj.xmy.elasticsearch.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.elasticsearch.XmyIndex;
import com.zfj.xmy.elasticsearch.document.CategoryDoc;
import com.zfj.xmy.elasticsearch.document.CategoryDocDTO;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.elasticsearch.persistence.dao.ElasticsearchExMapper;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter.WithType;
import com.zfj.xmy.elasticsearch.persistence.pojo.SearchConstant.SearchOperation;
import com.zfj.xmy.elasticsearch.persistence.pojo.SearchConstant;
import com.zfj.xmy.elasticsearch.repository.CategoryDocRepository;
import com.zfj.xmy.elasticsearch.service.CategoryDocService;
import com.zfj.xmy.util.DataPage;
@Service
public class CategoryDocServiceImpl extends BaseService implements CategoryDocService {

	@Autowired
	private ElasticsearchExMapper elasticsearchExMapper ;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate ;
	
	@Autowired
	private CategoryDocRepository categoryDocRepository ;

	@Override
	public DataPage<CategoryDoc> fullTextSearch(NativeSearchQueryParameter nativeSearchQueryParameter) {
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		return new DataPage<>(elasticsearchTemplate.queryForPage(nqb.build(),CategoryDoc.class));
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveIndex(List<Long> ids) {
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		List<Object> idList = new ArrayList<>() ;
		for(Long id : ids){
			idList.add(id) ;
		}
		criteria.in("gc.id",idList) ;
		List<CategoryDoc> gcList = elasticsearchExMapper.listCategoryDoc(parameter,null) ;
		if(!CollectionExtUtils.isEmpty(gcList)){
			categoryDocRepository.save(gcList) ;
			return gcList.size() ;
		}
		return 0 ;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int rebuildAllIndex() {
		// 先删除全部
		categoryDocRepository.deleteAll();
		// 重构已上架的
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.equalTo("goods.is_putway",SystemConstant.GoodsConstant.PUTWAY) ;
		int count = batchBulk(SearchOperation.SAVE,parameter) ;
		return count ;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateIndex(List<Long> ids) {
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		List<Object> idList = new ArrayList<>() ;
		for(Long id : ids){
			idList.add(id) ;
		}
		criteria.in("gc.id", idList) ;
		int count = batchBulk(SearchOperation.UPDATE,parameter) ;
		return count ;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateIndex(Long... ids) {
		return this.updateIndex(Arrays.asList(ids)) ;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int deleteIndex(List<Long> ids) {
		int count = 0 ;
		for(Long id : ids){
			categoryDocRepository.delete(id) ;
			count++ ;
		}
		return count ;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int deleteIndex(Long... ids) {
		return this.deleteIndex(Arrays.asList(ids)) ;
	}
	
	/**
	 * 批量操作索引
	 * @param searchOperation
	 * @param parameter
	 */
	private int batchBulk(SearchOperation searchOperation,CriteriaParameter parameter){
		List<CategoryDoc> gcList = elasticsearchExMapper.listCategoryDoc(parameter,null) ;
		int count = elasticsearchExMapper.countCategoryDoc(parameter) ;
		
		Client client = elasticsearchTemplate.getClient();
		BulkRequestBuilder bulk = client.prepareBulk().setRefresh(true);
		
		if(!CollectionExtUtils.isEmpty(gcList)){
			int pageIndex = 0 ;
			while (pageIndex * SearchConstant.limit < count){
				int len = (pageIndex + 1) * SearchConstant.limit ;
				len = len > count ? count : len ;
				List<CategoryDoc> addList = new ArrayList<>() ;
				for (int i = pageIndex * SearchConstant.limit ;i < len ;i++){
					if (searchOperation == SearchOperation.DELETE) {//删除
						bulk.add(client.prepareDelete(XmyIndex.CATEGORY_INDEX_NAME,XmyIndex.CATEGORY_TYPE,gcList.get(i) + "")) ;
					} else if (searchOperation == SearchOperation.UPDATE) {//修改
						bulk.add(client.prepareUpdate(XmyIndex.CATEGORY_INDEX_NAME,XmyIndex.CATEGORY_TYPE,gcList.get(i) + "")) ;
					} else if (searchOperation == SearchOperation.SAVE) {//新增
						addList.add(gcList.get(i)) ;
					}
				}
				if(searchOperation == SearchOperation.SAVE){
					categoryDocRepository.save(addList) ;
					addList.clear() ;
				} else if (searchOperation == SearchOperation.UPDATE || searchOperation == SearchOperation.DELETE) {
					bulk.execute().actionGet() ;
				}
				pageIndex++ ;
			}
		}
		return count ;
	}
	

//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public void initCategoryData() {
//		List<CategoryDoc> docList = elasticsearchExMapper.findCategoryDoc(null) ;
//		categoryDocRepository.deleteAll() ; 
//		categoryDocRepository.save(docList) ;
//	}

	@Override
	public List<CategoryDocDTO> listCategoryData(NativeSearchQueryParameter nativeSearchQueryParameter) {
		//聚合设置
		TermsBuilder allTermsBuilder = AggregationBuilders.terms("aggs").field("cid") ;
		
		TermsBuilder goodsIdBuilder = AggregationBuilders.terms("goodsIds").field("goodsId") ;
		TermsBuilder pidBuilder = AggregationBuilders.terms("pids").field("pid") ;
		TermsBuilder nameBuilder = AggregationBuilders.terms("names").field("name") ;
		TermsBuilder pnameBuilder = AggregationBuilders.terms("pnames").field("pname") ;
		allTermsBuilder.subAggregation(goodsIdBuilder).subAggregation(pidBuilder).subAggregation(nameBuilder).subAggregation(pnameBuilder) ;
		
		nativeSearchQueryParameter.setAggregationTermsBuilder(allTermsBuilder) ;
		
//		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		
		
		AggregatedPage<CategoryDoc> categoryPage = elasticsearchTemplate.queryForPage(nativeSearchQueryParameter.getNativeSearchQueryBuilder().build(),CategoryDoc.class) ;
		
		List<CategoryDoc> categoryList = categoryPage.getContent() ;
		System.out.println("*^_^*^_^*^_^*^_^*^_^*^_^*^_^*^_^*^_^*^_^*^_^*^_^------>" + categoryList.size()) ;
		Map<String,Aggregation> aggMap = categoryPage.getAggregations().asMap() ;
		LongTerms categoryNameTerms = (LongTerms)aggMap.get("aggs") ;
		List<Bucket> bucketList = categoryNameTerms.getBuckets() ;
		
		List<CategoryDocDTO> resultList = new ArrayList<>() ;
		
		for(Bucket bucket : bucketList){
			
			//1、获取父级
			LongTerms pidsTerms = (LongTerms) bucket.getAggregations().asMap().get("pids") ;
			Long pid = null ;
			for(Bucket pidBucket : pidsTerms.getBuckets()){
				pid = Long.parseLong(pidBucket.getKeyAsString()) ;
				System.out.println("--------pids------->" + pidBucket.getKey() + ",") ;
				break ;
			}
			
			String pname = null ;
			StringTerms pnamesTerms = (StringTerms) bucket.getAggregations().asMap().get("pnames") ;
			for(Bucket pnameBucket : pnamesTerms.getBuckets()){
				System.out.println("-----pname------>" + pnameBucket.getKeyAsString()) ;
				pname = pnameBucket.getKeyAsString() ;
				break ;
			}
			CategoryDocDTO parent = CategoryDocDTO.getById(resultList,pid) ;
			if(null == parent){
				parent = new CategoryDocDTO() ;
				parent.setId(pid) ;
				parent.setName(pname) ;
				resultList.add(parent) ;
			}
			//2、获取子级
			System.out.println("---------->" + bucket.getKey() + "," + bucket.getDocCount()) ;
			StringTerms namesTerms = (StringTerms) bucket.getAggregations().asMap().get("names") ;
			
			String name = null ;
			for(Bucket nameBucket : namesTerms.getBuckets()){
				System.out.println("-----name------>" + nameBucket.getKeyAsString()) ;
				name = nameBucket.getKeyAsString() ;
				break ;
			}
			CategoryDocDTO child = new CategoryDocDTO() ;
			child.setId(Long.parseLong(bucket.getKeyAsString())) ;
			child.setName(name) ;
			//3、获取子级下的商品id集合
			LongTerms strTerms = (LongTerms) bucket.getAggregations().asMap().get("goodsIds") ;
			List<Bucket> strList = strTerms.getBuckets() ;
			for(Bucket strBucket : strList){
				System.out.println("----goodsId----->" + strBucket.getKey() + ",") ;
				child.addGoodsId(Long.parseLong(strBucket.getKeyAsString())) ;
			}
			//4、设置父子级关系
			parent.addChild(child) ;
		}
		return resultList ;
	}
	
	private void defaultMethod(){
		SearchRequestBuilder srb = elasticsearchTemplate.getClient().prepareSearch(XmyIndex.CATEGORY_INDEX_NAME) ;
		
//		srb.setTypes(XmyIndex.GOODS_TYPE) ;
		srb.setTypes(XmyIndex.CATEGORY_TYPE) ;
//		srb.setSearchType(SearchType.) ;
//		srb.addField("fullName") ;
		
		TermsBuilder allTermsBuilder = AggregationBuilders.terms("aggs").field("cid") ;
		
		TermsBuilder goodsIdBuilder = AggregationBuilders.terms("goodsIds").field("goodsId") ;
		TermsBuilder pidBuilder = AggregationBuilders.terms("pids").field("pid") ;
		TermsBuilder nameBuilder = AggregationBuilders.terms("names").field("name") ;
		TermsBuilder pnameBuilder = AggregationBuilders.terms("pnames").field("pname") ;
		
		
		allTermsBuilder.subAggregation(goodsIdBuilder).subAggregation(pidBuilder).subAggregation(nameBuilder).subAggregation(pnameBuilder) ;
		
		srb.addAggregation(allTermsBuilder) ;
		
		SearchResponse sr = srb.execute().actionGet() ;
		
		Map<String,Aggregation> aggMap = sr.getAggregations().asMap() ;
//		
//		for(Map.Entry<String,Aggregation> aggregation : aggMap.entrySet()){
//			String key = aggregation.getKey() ;
//			Aggregation value = aggregation.getValue() ;
//			System.out.println(aggregation.getKey() + "," + value.getName()) ;
//		}
		
//		StringTerms categoryNameTerms = (StringTerms)aggMap.get("all_interests") ;
		LongTerms categoryNameTerms = (LongTerms)aggMap.get("aggs") ;
		List<Bucket> bucketList = categoryNameTerms.getBuckets() ;
		for(Bucket bucket : bucketList){
			System.out.println("---------->" + bucket.getKey() + "," + bucket.getDocCount()) ;
			LongTerms strTerms = (LongTerms) bucket.getAggregations().asMap().get("goodsIds") ;
			
			List<Bucket> strList = strTerms.getBuckets() ;
			for(Bucket strBucket : strList){
				System.out.println("----goodsId----->" + strBucket.getKey() + ",") ;
			}
			
			LongTerms pidsTerms = (LongTerms) bucket.getAggregations().asMap().get("pids") ;
			for(Bucket pidBucket : pidsTerms.getBuckets()){
				System.out.println("--------pids------->" + pidBucket.getKey() + ",") ;
			}
			
			StringTerms namesTerms = (StringTerms) bucket.getAggregations().asMap().get("names") ;
			for(Bucket nameBucket : namesTerms.getBuckets()){
				System.out.println("-----name------>" + nameBucket.getKeyAsString()) ;
			}
			
			StringTerms pnamesTerms = (StringTerms) bucket.getAggregations().asMap().get("pnames") ;
			for(Bucket pnameBucket : pnamesTerms.getBuckets()){
				System.out.println("-----pname------>" + pnameBucket.getKeyAsString()) ;
			}
			
		}
	}
}
