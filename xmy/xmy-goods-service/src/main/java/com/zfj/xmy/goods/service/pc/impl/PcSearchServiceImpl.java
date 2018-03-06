package com.zfj.xmy.goods.service.pc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.LogicType;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.dao.GoodsCategoryMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.GoodsSpecCategoryMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsCategory;
import com.zfj.xmy.common.persistence.pojo.GoodsSpecCategory;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppScreenOutDto;
import com.zfj.xmy.goods.persistence.pc.dao.EsMapper;
import com.zfj.xmy.goods.persistence.pc.dao.PcWrodExMapper;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcSearchDto;
import com.zfj.xmy.goods.service.pc.PcSearchService;
import com.zfj.xmy.util.StringUtils;
@Service
public class PcSearchServiceImpl extends BaseService implements PcSearchService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Autowired
	private PcWrodExMapper termDataExMapper;
	
	@Autowired
	private CategoryWordSegMapper categoryWordSegMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private GoodsSpecCategoryMapper goodsSpecCategoryMapper;
	
	@Autowired
	private EsMapper esMapper;
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 根据商品名称查询相关的筛选条件
	 */
	@Override
	public List<PcSearchDto> findSearchDto(String name) {
		List<PcSearchDto> reslut = new ArrayList<>();
		ReqData reqData = new ReqData();
		String cids = "0";
		/*//1.根据商品名称查询相关的分类
		reqData.putValue("name", name, SystemConstant.REQ_PARAMETER_CN);
		List<Goods> goodsList = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (Goods goods : goodsList) {
			goodsId += ","+goods.getId();
		}
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsCategory> selectByExample = goodsCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsCategory goodsCategory : selectByExample) {
			cids += ","+goodsCategory.getCid();
		}*/
		//2.查询
		reqData.putValue("word_seg", name, SystemConstant.REQ_PARAMETER_CN);
		List<CategoryWordSeg> selectByExample = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : selectByExample) {
			cids+=","+categoryWordSeg.getCid();
		}
		//3.查询二级分类的分词的父级名称
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("cid", cids, SystemConstant.REQ_PARAMETER_IN);
		List<String> findTowName = termDataExMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (String string : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(string);
			reslut.add(searchDto);
		}
		//4.查询父级ID下的所有子级名称并去除重复
		for (PcSearchDto searchDto : reslut) {
			List<PcSearchDto> chidList = new ArrayList<>();
			String cId = "1";
			//reqData.putValue("cid", cids, SystemConstant.REQ_PARAMETER_IN);
			reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("word_seg", searchDto.getSearchName(), SystemConstant.REQ_PARAMETER_EQ);
			List<CategoryWordSeg> selectByExample3 = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : selectByExample3) {
				cId += ","+categoryWordSeg.getId();
			}
			reqData.putValue("parent_id", cId, SystemConstant.REQ_PARAMETER_IN);
			List<String> findTowName2 = termDataExMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (String string : findTowName2) {
				PcSearchDto testDto = new PcSearchDto();
				testDto.setSearchName(string);
				chidList.add(testDto);
			}
			searchDto.setChildList(chidList);
		}
		
		return reslut;
	}
	/**
	 * 根据商品名称查询商品信息
	 */
	@Override
	public List<Goods> findSearchGoods(String name, Integer orderMethod,PageBean pageBean,String sreach,Integer isDelvi,BigDecimal beginPrice,BigDecimal endPrice,Boolean priceOrder) {
		List<Goods> goodsList = null;
		String goodsId = "0";
		ReqData reqData = new ReqData();
		if(!ObjectUtils.isEmpty(sreach)){
			String cid = "0";
			reqData.putValue("word_seg", sreach, SystemConstant.REQ_PARAMETER_IN);
			List<CategoryWordSeg> selectByExample = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : selectByExample) {
				cid+=","+categoryWordSeg.getId();
			}
			reqData.putValue("spec_category_id", cid, SystemConstant.REQ_PARAMETER_IN);
			List<GoodsSpecCategory> selectByExample2 = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (GoodsSpecCategory goodsSpecCategory : selectByExample2) {
				goodsId += ","+goodsSpecCategory.getGoodsId();
			}
			reqData.putValue("id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		}
		if(!ObjectUtils.isEmpty(isDelvi)){
			if(isDelvi>0){
				reqData.putValue("is_delivery", isDelvi, SystemConstant.REQ_PARAMETER_EQ);
			}
		}
		if(!ObjectUtils.isEmpty(beginPrice)){
			reqData.putValue("price", beginPrice, SystemConstant.REQ_PARAMETER_GE);
		}
		if(!ObjectUtils.isEmpty(endPrice)){
			reqData.putValue("price", endPrice, SystemConstant.REQ_PARAMETER_LE);
		}
		reqData.putValue("name", name, SystemConstant.REQ_PARAMETER_CN);
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.YES, SystemConstant.REQ_PARAMETER_EQ);
		switch (orderMethod) {
		case 0:
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			break;
		case 1:
			reqData.setSortname("sum_comment");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			break;
		case 2:
			reqData.setSortname("sum_deal");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			break;
		case 3:
			if(priceOrder){
				reqData.setSortname("price");
				reqData.setSortorder("desc");
			}else{
				reqData.setSortname("price");
				reqData.setSortorder("asc");
			}
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			break;
		case 4:
			reqData.setSortname("putway_time");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			break;
		default:
			break;
		}
		//加载图片
		for (Goods goods : goodsList) {
			goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
		}
		return goodsList;
	}
	/**
	 * 查询销量前五商品信息
	 */
	@Override
	public List<Goods> findTopFiveGoods() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(4);;
		ReqData reqData = new ReqData();
		reqData.setSortname("sum_deal");
		reqData.setSortorder("asc");
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_recycle", SystemConstant.GoodsConstant.NO, SystemConstant.REQ_PARAMETER_EQ);
		List<Goods> selectByExampleAndPage = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		for (Goods goods : selectByExampleAndPage) {
			goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
		}
		return selectByExampleAndPage;
	}
	/**
	 * 查询总页数
	 */
	@Override
	public Integer findCountGoods(String goodsName) {
		ReqData reqData = new ReqData();
		reqData.putValue("name", goodsName, SystemConstant.REQ_PARAMETER_CN);
		int count = goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}
		return countPage;
	}
	/**
	 * 根据二级分类ID查询筛选条件
	 */
	@Override
	public List<PcSearchDto> findSearchDtoByTypeId(Long typeId) {
		/*ReqData reqData = new ReqData();
		List<PcSearchDto> listSearch = new ArrayList<PcSearchDto>();
		//2.查询出分词的父级名称
		reqData.putValue("cid", typeId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			listSearch.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : listSearch) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		for (PcSearchDto pcSearchDto : listSearch) {
			reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
			reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
			List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
			for (String childName : list) {
				PcSearchDto child = new PcSearchDto();
				child.setSearchName(childName);
				childDto.add(child);
			}
			pcSearchDto.setChildList(childDto);
		}
		return listSearch;*/
		
		List<PcSearchDto> searchList = new ArrayList<>();
		//全部
		PcSearchDto parentSearchDto = new PcSearchDto();
		parentSearchDto.setSearchName("类目");
		searchList.add(parentSearchDto);
		//分类
		PcSearchDto typeSearchDto = new PcSearchDto();
		typeSearchDto.setSearchName("分类");
		searchList.add(typeSearchDto);
		String goodsId = "0";
		String wordId = "0";
		String cid = "0";
		String termId = "0";
		String parentId="0";
		//1.查询出商品对应的分词id
		ReqData reqData = new ReqData();
		/*CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.like("name", "%"+name+"%", LogicType.OR);
		criteria.like("keyword","%"+name+"%", LogicType.OR);*/
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql = "typeId:*"+typeId+"*";
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> goodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		reqData.clearValue();
		for (GoodsDoc goods : goodsList) {
			goodsId +=","+goods.getId();
		}
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsSpecCategory> selectByExample = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsSpecCategory goodsSpecCategory : selectByExample) {
			wordId+=","+goodsSpecCategory.getSpecCategoryId();
		}
		
		//2.根据分词ID查询查询对应的二级分类
		reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
		List<CategoryWordSeg> cateGory = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : cateGory) {
			cid+=","+categoryWordSeg.getCid();
			parentId+=","+categoryWordSeg.getParentId();
		}
		
		//2.1查询出分词的父级名称
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			searchList.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : searchList) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		int index = 0;
		for (PcSearchDto pcSearchDto : searchList) {
			index++;
			if(index>2){
				reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
				reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
				List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
				for (String childName : list) {
					PcSearchDto child = new PcSearchDto();
					child.setSearchName(childName);
					childDto.add(child);
				}
				pcSearchDto.setChildList(childDto);
			}
		}
		
		//3.查询二级分类
		reqData.putValue("id", cid, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> termDataList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		PcSearchDto typeDto = searchList.get(1);
		List<PcSearchDto> typeChildList = new ArrayList<>();
		for (TermData termData : termDataList) {
			termId+=","+termData.getParentId();
			PcSearchDto pcSearchDto = new PcSearchDto();
			pcSearchDto.setSearchName(termData.getName());
			pcSearchDto.setSearchId(termData.getId());
			typeChildList.add(pcSearchDto);
		}
		typeDto.setChildList(typeChildList);
		//4.查询一级分类名称
		reqData.putValue("id", termId, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> parentList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		PcSearchDto pcSearchDto = searchList.get(0);
		List<PcSearchDto> firstChildList = new ArrayList<>();
		PcSearchDto searchDtos = new PcSearchDto();
		searchDtos.setSearchName("全部");
		firstChildList.add(searchDtos);
		for (TermData termData : parentList) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(termData.getName());
			searchDto.setSearchId(termData.getId());
			firstChildList.add(searchDto);
		}
		pcSearchDto.setChildList(firstChildList);
		return searchList;
	}
	
	
	
	/**
	 * 根据二级分类ID查询商品信息
	 */
	@Override
	public List<Goods> findSearchGoodsByTypeId(Long typeId,Long wordId,Integer orderMethod, PageBean pageBean, String sreach,boolean priceOrder,Integer isdelivery,BigDecimal beginPice,BigDecimal endPrice) {
		//1.查询二级分类包含的商品
		List<Goods> goodsList = null;
		String goodsId = "0";
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql = "typeId:*"+typeId+"*";
		if(!ObjectUtils.isEmpty(wordId) && ObjectUtils.isEmpty(sreach) || "undefined".equals(sreach)){
			CategoryWordSeg category = categoryWordSegMapper.selectByPrimaryKey(wordId);
			if(!ObjectUtils.isEmpty(category)){
				String all = category.getWordSeg().replaceAll("/", "");
				esql+=" AND wordSeg:*"+all+"*";
			}
		}
		if(!ObjectUtils.isEmpty(isdelivery)){
			if(isdelivery<=0 && isdelivery != -1){
				esql+=" AND mainLand:"+isdelivery+"";
			}
		}
		if(!ObjectUtils.isEmpty(sreach)){
			String newTypeName = sreach.replaceAll("/", "");
			String[] split = newTypeName.split(",");
			for (String string : split) {
				if(!"undefined".equals(string)){
					esql+=" AND wordSeg:*"+string+"*";
				}
			}
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> esGoodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		for (GoodsDoc goodsDoc : esGoodsList) {
			goodsId+=","+goodsDoc.getId();
		}
		ReqData reqData = new ReqData();
		reqData.putValue("is_recycle", SystemConstant.GoodsConstant.NOT_RECYCLE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_search", SystemConstant.GoodsConstant.YES, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		if(!ObjectUtils.isEmpty(beginPice)){
			reqData.putValue("price", beginPice, SystemConstant.REQ_PARAMETER_GE);
		}
		if(!ObjectUtils.isEmpty(endPrice)){
			reqData.putValue("price", endPrice, SystemConstant.REQ_PARAMETER_LE);
		}
		switch (orderMethod) {
		case 0:
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 1:
			reqData.setSortname("putway_time");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 2:
			reqData.setSortname("sum_deal");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 3:
			if(priceOrder){
				reqData.setSortname("price");
				reqData.setSortorder("desc");
			}else{
				reqData.setSortname("price");
				reqData.setSortorder("asc");
			}
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 4:
			reqData.setSortname("putway_time");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		default:
			break;
		}
		//加载图片
		for (Goods goods : goodsList) {
			goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
		}
		return goodsList;
	}
	/**
	 * 返回总页数
	 */
	@Override
	public int countPage(int count) {
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}
		return countPage;
	}
	/**
	 * 筛选分类
	 */
	@Override
	public List<PcSearchDto> testSearchDto(String name) {
		String termDataIds="0";//二级分类id
		ReqData reqData = new ReqData();
		//1.查询出二级分类的id
		reqData.putValue("name", name, SystemConstant.REQ_PARAMETER_CN);
		List<String> findTermDataId = esMapper.findTermDataId(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (String termId : findTermDataId) {
			String[] split = termId.split(",");
			String id = split[1];
			termDataIds += ","+id;//添加二级分类的ID
		}
		List<PcSearchDto> listSearch = new ArrayList<PcSearchDto>();
		//2.查询出分词的父级名称
		reqData.putValue("cid", termDataIds, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			listSearch.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : listSearch) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		for (PcSearchDto pcSearchDto : listSearch) {
			reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
			reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
			List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
			for (String childName : list) {
				PcSearchDto child = new PcSearchDto();
				child.setSearchName(childName);
				childDto.add(child);
			}
			pcSearchDto.setChildList(childDto);
		}
		
		/*for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			listSearch.add(searchDto);
		}*/
		return listSearch;
	}
	/*public static void main(String[] args) {
		String str =",135,";
		String[] split = str.split(",");
		String string2 = split[1];
		System.out.println(string2);
		for (String string : split) {
			
			System.out.println(string+"---");
		}
	}*/
	/**
	 * 根据分词ID查分类条件
	 */
	@Override
	public List<PcSearchDto> findSearchDtoByWord(Long typeId,Long wordIds) {
		/*ReqData reqData = new ReqData();
		List<PcSearchDto> searchDto = new ArrayList<>();
		//1.查询出该分词的所属二级分类下的所有父级
		CategoryWordSeg wordSeg = categoryWordSegMapper.selectByPrimaryKey(wordId);
		reqData.putValue("cid", wordSeg.getCid(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<CategoryWordSeg> cateGoryList = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : cateGoryList) {
			PcSearchDto parentDto = new PcSearchDto();
			parentDto.setSearchId(categoryWordSeg.getId());
			parentDto.setSearchName(categoryWordSeg.getWordSeg());
			searchDto.add(parentDto);
		}
		//2.为每个父级添加子级
		for (PcSearchDto pcSearchDto : searchDto) {
			List<PcSearchDto> childLists = new ArrayList<>();
			reqData.putValue("parent_id", pcSearchDto.getSearchId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("cid", wordSeg.getCid(), SystemConstant.REQ_PARAMETER_EQ);
			List<CategoryWordSeg> childList = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : childList) {
				PcSearchDto childDto = new PcSearchDto();
				childDto.setSearchName(categoryWordSeg.getWordSeg());
				childLists.add(childDto);
			}
			pcSearchDto.setChildList(childLists);
		}
		
		return searchDto;*/
		List<PcSearchDto> searchList = new ArrayList<>();
		//全部
		PcSearchDto parentSearchDto = new PcSearchDto();
		parentSearchDto.setSearchName("类目");
		searchList.add(parentSearchDto);
		//分类
		PcSearchDto typeSearchDto = new PcSearchDto();
		typeSearchDto.setSearchName("分类");
		searchList.add(typeSearchDto);
		String goodsId = "0";
		String wordId = "0";
		String cid = "0";
		String termId = "0";
		String parentId="0";
		//1.查询出商品对应的分词id
		ReqData reqData = new ReqData();
		/*CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.like("name", "%"+name+"%", LogicType.OR);
		criteria.like("keyword","%"+name+"%", LogicType.OR);*/
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql ="typeId:*"+typeId+"*"+" AND wordsId:*"+wordIds+"*";
		CategoryWordSeg cate = categoryWordSegMapper.selectByPrimaryKey(wordIds);
		if(!ObjectUtils.isEmpty(cate)){
			String all = cate.getWordSeg().replaceAll("/", "");
			esql+=" AND wordSeg:*"+all+"*";
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> goodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		reqData.clearValue();
		for (GoodsDoc goods : goodsList) {
			goodsId +=","+goods.getId();
		}
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsSpecCategory> selectByExample = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsSpecCategory goodsSpecCategory : selectByExample) {
			wordId+=","+goodsSpecCategory.getSpecCategoryId();
		}
		
		//2.根据分词ID查询查询对应的二级分类
		reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
		List<CategoryWordSeg> cateGory = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : cateGory) {
			cid+=","+categoryWordSeg.getCid();
			parentId+=","+categoryWordSeg.getParentId();
		}
		
		//2.1查询出分词的父级名称
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			searchList.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : searchList) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		int index = 0;
		for (PcSearchDto pcSearchDto : searchList) {
			index++;
			if(index>2){
				reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
				reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
				List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
				for (String childName : list) {
					PcSearchDto child = new PcSearchDto();
					child.setSearchName(childName);
					childDto.add(child);
				}
				pcSearchDto.setChildList(childDto);
			}
		}
		
		//3.查询二级分类
		reqData.putValue("id", cid, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> termDataList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		PcSearchDto typeDto = searchList.get(1);
		List<PcSearchDto> typeChildList = new ArrayList<>();
		for (TermData termData : termDataList) {
			termId+=","+termData.getParentId();
			PcSearchDto pcSearchDto = new PcSearchDto();
			pcSearchDto.setSearchName(termData.getName());
			pcSearchDto.setSearchId(termData.getId());
			typeChildList.add(pcSearchDto);
		}
		typeDto.setChildList(typeChildList);
		//4.查询一级分类名称
		reqData.putValue("id", termId, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> parentList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		PcSearchDto pcSearchDto = searchList.get(0);
		List<PcSearchDto> firstChildList = new ArrayList<>();
		PcSearchDto searchDtos = new PcSearchDto();
		searchDtos.setSearchName("全部");
		firstChildList.add(searchDtos);
		for (TermData termData : parentList) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(termData.getName());
			searchDto.setSearchId(termData.getId());
			firstChildList.add(searchDto);
		}
		pcSearchDto.setChildList(firstChildList);
		return searchList;
	}
	/**
	 * 根据商品名称反查分类
	 */
	@Override
	public List<PcSearchDto> querySearchDto(String name,String typeName,Integer mianLand) {
		List<PcSearchDto> searchList = new ArrayList<>();
		//全部
		PcSearchDto parentSearchDto = new PcSearchDto();
		parentSearchDto.setSearchName("类目");
		searchList.add(parentSearchDto);
		//分类
		PcSearchDto typeSearchDto = new PcSearchDto();
		typeSearchDto.setSearchName("分类");
		searchList.add(typeSearchDto);
		String goodsId = "0";
		String wordId = "0";
		String cid = "0";
		String termId = "0";
		String parentId="0";
		//1.查询出商品对应的分词id
		ReqData reqData = new ReqData();
		/*CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.like("name", "%"+name+"%", LogicType.OR);
		criteria.like("keyword","%"+name+"%", LogicType.OR);*/
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql = "";
		if(ObjectUtils.isEmpty(name)){
			esql += "fullName: * *";
		}else{
			String newName = name.replaceAll("/", "");
			if(StringUtils.isContainChinese(newName)){
				esql+="(fullName:"+newName+" keywords:"+newName+" fullName:*"+newName+"*)";
			}else{
				esql+="(pyKeywords:"+newName+")";
			}
		}
		if(!ObjectUtils.isEmpty(mianLand)){
			esql+=" AND mainLand:"+mianLand+"";
		}
		if(!ObjectUtils.isEmpty(typeName)){
			String newTypeName = typeName.replaceAll("/", "");
			String[] split = newTypeName.split(",");
			for (String string : split) {
				if(!"undefined".equals(string)){
					esql+=" AND wordSeg:*"+string+"*";
				}
			}
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> goodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		reqData.clearValue();
		for (GoodsDoc goods : goodsList) {
			goodsId +=","+goods.getId();
		}
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsSpecCategory> selectByExample = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsSpecCategory goodsSpecCategory : selectByExample) {
			wordId+=","+goodsSpecCategory.getSpecCategoryId();
		}
		
		//2.根据分词ID查询查询对应的二级分类
		reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
		List<CategoryWordSeg> cateGory = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : cateGory) {
			cid+=","+categoryWordSeg.getCid();
			parentId+=","+categoryWordSeg.getParentId();
		}
		
		//2.1查询出分词的父级名称
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			searchList.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : searchList) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		int index = 0;
		for (PcSearchDto pcSearchDto : searchList) {
			index++;
			if(index>2){
				reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
				reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
				List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
				for (String childName : list) {
					PcSearchDto child = new PcSearchDto();
					child.setSearchName(childName);
					childDto.add(child);
				}
				pcSearchDto.setChildList(childDto);
			}
		}
		
		//3.查询二级分类
		reqData.putValue("id", cid, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> termDataList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		PcSearchDto typeDto = searchList.get(1);
		List<PcSearchDto> typeChildList = new ArrayList<>();
		for (TermData termData : termDataList) {
			termId+=","+termData.getParentId();
			PcSearchDto pcSearchDto = new PcSearchDto();
			pcSearchDto.setSearchName(termData.getName());
			pcSearchDto.setSearchId(termData.getId());
			typeChildList.add(pcSearchDto);
		}
		typeDto.setChildList(typeChildList);
		//4.查询一级分类名称
		reqData.putValue("id", termId, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> parentList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		PcSearchDto pcSearchDto = searchList.get(0);
		List<PcSearchDto> firstChildList = new ArrayList<>();
		PcSearchDto searchDtos = new PcSearchDto();
		searchDtos.setSearchName("全部");
		firstChildList.add(searchDtos);
		for (TermData termData : parentList) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(termData.getName());
			searchDto.setSearchId(termData.getId());
			firstChildList.add(searchDto);
		}
		pcSearchDto.setChildList(firstChildList);
		return searchList;
	}
	/**
	 * 根据一级分类ID查询筛选条件
	 */
	@Override
	public List<PcSearchDto> findSearchDtoByoneId(Long oneId) {
		List<PcSearchDto> searchList = new ArrayList<>();
		String cid = "0";//二级分类ID
		//全部
		PcSearchDto parentSearchDto = new PcSearchDto();
		parentSearchDto.setSearchName("类目");
		//添加一级分类名称
		TermData topTerm = termDataMapper.selectByPrimaryKey(oneId);
		List<PcSearchDto> firstChildList = new ArrayList<>();
		PcSearchDto oneChild = new PcSearchDto();
		oneChild.setSearchName(topTerm.getName());
		firstChildList.add(oneChild);
		parentSearchDto.setChildList(firstChildList);
		searchList.add(parentSearchDto);
		//分类
		PcSearchDto typeSearchDto = new PcSearchDto();
		typeSearchDto.setSearchName("分类");
		ReqData reqData = new ReqData();
		reqData.putValue("parent_id", oneId, SystemConstant.REQ_PARAMETER_EQ);
		List<TermData> scendList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		List<PcSearchDto> scendChildList = new ArrayList<>();
		for (TermData termData : scendList) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchId(termData.getId());
			searchDto.setSearchName(termData.getName());
			scendChildList.add(searchDto);
			cid+=","+termData.getId();
		}
		typeSearchDto.setChildList(scendChildList);
		searchList.add(typeSearchDto);
		//2.1查询出分词的父级名称
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			searchList.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : searchList) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		int index = 0;
		for (PcSearchDto pcSearchDto : searchList) {
			index++;
			if(index>2){
				reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
				List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
				for (String childName : list) {
					PcSearchDto child = new PcSearchDto();
					child.setSearchName(childName);
					childDto.add(child);
				}
				pcSearchDto.setChildList(childDto);
			}
		}
		return searchList;
	}
	@Override
	public List<Goods> findSearchGoodsByOneId(Long oneId, Integer orderMethod, PageBean pageBean,String typeName,String sreach, boolean priceOrder, Integer isdelivery, BigDecimal beginPice, BigDecimal endPrice) {
		//1.查询一级分类包含的商品
		List<Goods> goodsList = null;
		String goodsId = "0";
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql = "topId:*"+oneId+"*";
		if(!ObjectUtils.isEmpty(isdelivery)){
			if(isdelivery<=0 && isdelivery != -1){
				esql+=" AND mainLand:"+isdelivery+"";
			}
		}
		if(!ObjectUtils.isEmpty(sreach)){
			String newTypeName = sreach.replaceAll("/", "");
			String[] split = newTypeName.split(",");
			for (String string : split) {
				if(!"undefined".equals(string)){
					esql+=" AND wordSeg:*"+string+"*";
				}
			}
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> esGoodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		for (GoodsDoc goodsDoc : esGoodsList) {
			goodsId+=","+goodsDoc.getId();
		}
		ReqData reqData = new ReqData();
		//不变的条件
		reqData.putValue("is_recycle", SystemConstant.GoodsConstant.NOT_RECYCLE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_search", SystemConstant.GoodsConstant.YES, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		if(!ObjectUtils.isEmpty(beginPice)){
			reqData.putValue("price", beginPice, SystemConstant.REQ_PARAMETER_GE);
		}
		if(!ObjectUtils.isEmpty(endPrice)){
			reqData.putValue("price", endPrice, SystemConstant.REQ_PARAMETER_LE);
		}
		switch (orderMethod) {
		case 0:
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 1:
			reqData.setSortname("putway_time");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 2:
			reqData.setSortname("sum_deal");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 3:
			if(priceOrder){
				reqData.setSortname("price");
				reqData.setSortorder("desc");
			}else{
				reqData.setSortname("price");
				reqData.setSortorder("asc");
			}
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		case 4:
			reqData.setSortname("putway_time");
			reqData.setSortorder("desc");
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			break;
		default:
			break;
		}
		//加载图片
		for (Goods goods : goodsList) {
			goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
		}
		return goodsList;
	}
	/**
	 * 判断ID属于几级分类
	 */
	@Override
	public Integer checkId(Long id) {
		TermData termData = termDataMapper.selectByPrimaryKey(id);
		if(termData.getParentId()==0){
			return 1;
		}else{
			return 2;
		}
	}
	/**
	 * 根据分词或二级分类ID查询筛选条件
	 */
	@Override
	public String queryWordByTypeIdOrwordId(String	words,Long typeId) {
		ReqData reqData = new ReqData();
		String str = "";
		//1.判断是二级分类ID或分词ID
		reqData.putValue("name", words, SystemConstant.REQ_PARAMETER_EQ);
		List<TermData> byExample = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		TermData termData = null;
		if(!ObjectUtils.isEmpty(byExample)){
			termData = byExample.get(0);
		}
		if(!ObjectUtils.isEmpty(termData)){
			//2二级分类的处理
			str+=termData.getName();
			//2.1查询父级信息
			TermData parentData = termDataMapper.selectByPrimaryKey(termData.getParentId());
			str+=parentData.getName();
			reqData.putValue("cid", termData.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<CategoryWordSeg> example = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : example) {
				str+=categoryWordSeg.getWordSeg();
			}
		}else{
			String goodsId = "0";
			String cid = "0";
			reqData.putValue("cid", typeId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("word_seg", words, SystemConstant.REQ_PARAMETER_IN);
			List<CategoryWordSeg> selectByExample1 = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : selectByExample1) {
				cid+=","+categoryWordSeg.getId();
			}
			reqData.putValue("spec_category_id", cid, SystemConstant.REQ_PARAMETER_IN);
			List<GoodsSpecCategory> selectByExample2 = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (GoodsSpecCategory goodsSpecCategory : selectByExample2) {
				goodsId += ","+goodsSpecCategory.getGoodsId();
			}
			reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
			List<GoodsSpecCategory> example = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			String spId = "0";
			for (GoodsSpecCategory goodsSpecCategory : example) {
				spId += ","+goodsSpecCategory.getSpecCategoryId();
			}
			reqData.putValue("id", spId, SystemConstant.REQ_PARAMETER_IN);
			List<CategoryWordSeg> selectByExample = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : selectByExample) {
				str+=categoryWordSeg.getWordSeg();
			}
		}
		return str;
	}
	/**
	 * 根据分词查询筛选条件
	 */
	@Override
	public List<AppScreenOutDto> queryWrodsOutDto(String words,Long typeId,Long wrodsId) {
		List<AppScreenOutDto> outList = new ArrayList<AppScreenOutDto>();
		String goodsId = "0";
		String wordsId = "0";
		String esql = "";
		ReqData reqData = new ReqData();
		if(!ObjectUtils.isEmpty(typeId)){
			TermData parentData = termDataMapper.selectByPrimaryKey(typeId);
			if(parentData.getParentId()==0){
				esql+="topId:*"+parentData.getId()+"*";
			}else{
				esql+="typeId:*"+parentData.getId()+"*";
			}
		}else{
			CategoryWordSeg categoryWordSeg = categoryWordSegMapper.selectByPrimaryKey(wrodsId);
			esql+="typeId:*"+categoryWordSeg.getCid()+"*";
		}
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		if(!ObjectUtils.isEmpty(words)){
			String newTypeName = words.replaceAll("/", "");
			String[] split = newTypeName.split(",");
			for (String string : split) {
				if(!"undefined".equals(string)){
					esql+=" AND wordSeg:*"+string+"*";
				}
			}
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> esGoodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		for (GoodsDoc goodsDoc : esGoodsList) {
			goodsId+=","+goodsDoc.getId();
		}
		//3.根据商品ID返查分词ID
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsSpecCategory> selectByExample2 = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsSpecCategory goodsSpecCategory : selectByExample2) {
			wordsId +=","+goodsSpecCategory.getSpecCategoryId();
		}
		//4.封装筛选条件
		reqData.putValue("id", wordsId, SystemConstant.REQ_PARAMETER_IN);
		List<CategoryWordSeg> wordsList = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (CategoryWordSeg categoryWordSeg : wordsList) {
			//5.1查询每个的父级名称
			CategoryWordSeg wordSeg = categoryWordSegMapper.selectByPrimaryKey(categoryWordSeg.getParentId());
			if(ObjectUtils.isEmpty(outList)){
				AppScreenOutDto parenDto = new AppScreenOutDto();
				AppScreenOutDto childDto = new AppScreenOutDto();
				List<AppScreenOutDto> childList = new ArrayList<AppScreenOutDto>();
				parenDto.setName(wordSeg.getWordSeg());
				//5.2查询单个子集
				childDto.setName(categoryWordSeg.getWordSeg());
				childList.add(childDto);
				parenDto.setChildDto(childList);
				outList.add(parenDto);
			}else{
				boolean flag = false;
				for (AppScreenOutDto appScreenOutDto : outList) {
					if(wordSeg.getWordSeg().equals(appScreenOutDto.getName())){
						flag = true;
					}
				}
				for (AppScreenOutDto appScreenOutDto : outList) {
					if(flag){
						if(wordSeg.getWordSeg().equals(appScreenOutDto.getName())){
							List<AppScreenOutDto> childDto = appScreenOutDto.getChildDto();
							AppScreenOutDto child = new AppScreenOutDto();
							child.setName(categoryWordSeg.getWordSeg());
							childDto.add(child);
							appScreenOutDto.setChildDto(childDto);
							break;
						}
					}else{
						AppScreenOutDto parenDto = new AppScreenOutDto();
						parenDto.setName(wordSeg.getWordSeg());
						AppScreenOutDto childDto = new AppScreenOutDto();
						childDto.setName(categoryWordSeg.getWordSeg());
						List<AppScreenOutDto> childDto2 = new ArrayList<AppScreenOutDto>();
						childDto2.add(childDto);
						parenDto.setChildDto(childDto2);
						outList.add(parenDto);
						break;
					}
				}
			}
			
		}
		
		return outList;
	}
	/**
	 * es根据分类Id查询筛选条件
	 */
	@Override
	public List<PcSearchDto> querySearchDto(Long typeId, Long oneId, String words) {
		List<PcSearchDto> searchList = new ArrayList<>();
		//全部
		PcSearchDto parentSearchDto = new PcSearchDto();
		parentSearchDto.setSearchName("类目");
		searchList.add(parentSearchDto);
		//分类
		PcSearchDto typeSearchDto = new PcSearchDto();
		typeSearchDto.setSearchName("分类");
		searchList.add(typeSearchDto);
		String goodsId = "0";
		String wordId = "0";
		String cid = "0";
		String termId = "0";
		String parentId="0";
		//1.查询出商品对应的分词id
		ReqData reqData = new ReqData();
		/*CriteriaParameter parameter = new CriteriaParameter();
		Criteria criteria = parameter.createCriteria();
		criteria.like("name", "%"+name+"%", LogicType.OR);
		criteria.like("keyword","%"+name+"%", LogicType.OR);*/
		NativeSearchQueryParameter nativeSearchQueryParameter = new NativeSearchQueryParameter();
		nativeSearchQueryParameter.setPageSize(5000);
		NativeSearchQueryBuilder nqb = nativeSearchQueryParameter.getNativeSearchQueryBuilder() ;
		QueryStringQueryBuilder queryStringQuery = null;
		String esql = "";
		if(!ObjectUtils.isEmpty(oneId)){
			esql+= "topId:*"+oneId+"*";
		}
		if(!ObjectUtils.isEmpty(typeId)){
			esql+= "typeId:*"+typeId+"*";
		}
		if(!ObjectUtils.isEmpty(words)){
			String newTypeName = words.replaceAll("/", "");
			String[] split = newTypeName.split(",");
			for (String string : split) {
				if(!"undefined".equals(string)){
					esql+=" AND wordSeg:*"+string+"*";
				}
			}
		}
		queryStringQuery = QueryBuilders.queryStringQuery(esql);
		nqb.withQuery(queryStringQuery);
		List<GoodsDoc> goodsList = elasticsearchTemplate.queryForList(nqb.build(), GoodsDoc.class);
		reqData.clearValue();
		for (GoodsDoc goods : goodsList) {
			goodsId +=","+goods.getId();
		}
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_IN);
		List<GoodsSpecCategory> selectByExample = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (GoodsSpecCategory goodsSpecCategory : selectByExample) {
			wordId+=","+goodsSpecCategory.getSpecCategoryId();
		}
		
		//2.根据分词ID查询查询对应的二级分类
		reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
		List<CategoryWordSeg> cateGory = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg categoryWordSeg : cateGory) {
			cid+=","+categoryWordSeg.getCid();
			parentId+=","+categoryWordSeg.getParentId();
		}
		
		//2.1查询出分词的父级名称
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<String> findTowName = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (String parentName : findTowName) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(parentName);
			searchDto.setIds("0");
			searchList.add(searchDto);
		}
		List<CategoryWordSeg> cateGoryWordSeg = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (CategoryWordSeg category : cateGoryWordSeg) {
			for (PcSearchDto pcSearchDto : searchList) {
				if(category.getWordSeg().equals(pcSearchDto.getSearchName())){//如果相等了
					String strids =pcSearchDto.getIds();
					strids +=","+category.getId();
					pcSearchDto.setIds(strids);
				}
			}
		}
		int index = 0;
		for (PcSearchDto pcSearchDto : searchList) {
			index++;
			if(index>2){
				reqData.putValue("parent_id", pcSearchDto.getIds(), SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_NE);
				reqData.putValue("id", wordId, SystemConstant.REQ_PARAMETER_IN);
				List<String> list = esMapper.findTowName(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				List<PcSearchDto> childDto=new ArrayList<PcSearchDto>();
				for (String childName : list) {
					PcSearchDto child = new PcSearchDto();
					child.setSearchName(childName);
					childDto.add(child);
				}
				pcSearchDto.setChildList(childDto);
			}
		}
		//3.查询二级分类
		reqData.putValue("id", cid, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> termDataList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		PcSearchDto typeDto = searchList.get(1);
		List<PcSearchDto> typeChildList = new ArrayList<>();
		for (TermData termData : termDataList) {
			termId+=","+termData.getParentId();
			PcSearchDto pcSearchDto = new PcSearchDto();
			pcSearchDto.setSearchName(termData.getName());
			pcSearchDto.setSearchId(termData.getId());
			typeChildList.add(pcSearchDto);
		}
		typeDto.setChildList(typeChildList);
		//4.查询一级分类名称
		reqData.putValue("id", termId, SystemConstant.REQ_PARAMETER_IN);
		List<TermData> parentList = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		PcSearchDto pcSearchDto = searchList.get(0);
		List<PcSearchDto> firstChildList = new ArrayList<>();
		PcSearchDto searchDtos = new PcSearchDto();
		searchDtos.setSearchName("全部");
		firstChildList.add(searchDtos);
		for (TermData termData : parentList) {
			PcSearchDto searchDto = new PcSearchDto();
			searchDto.setSearchName(termData.getName());
			searchDto.setSearchId(termData.getId());
			firstChildList.add(searchDto);
		}
		pcSearchDto.setChildList(firstChildList);
		return searchList;
	}
}
