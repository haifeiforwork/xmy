package com.zfj.xmy.activity.service.pc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.activity.persistence.pc.dao.PcContainerExMapper;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcContainerDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcIndexDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcIndexGoodsDto;
import com.zfj.xmy.activity.service.pc.PcIndexConfigService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.AdImageMapper;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.dao.ContainerMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.GoodsSpecCategoryMapper;
import com.zfj.xmy.common.persistence.dao.IndexConfigMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsSpecCategory;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.goods.service.cms.TermDataService;
@Service
public class PcIndexConfigServiceImpl extends BaseService implements PcIndexConfigService{
	
	@Autowired
	private IndexConfigMapper configMapper;
	
	@Autowired
	private AdImageMapper adImageMapper;
	
	@Autowired
	private PcContainerExMapper containerExMapper;
	
	@Autowired
	private CategoryWordSegMapper wordSegMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private GoodsSpecCategoryMapper goodsSpecCategoryMapper;
	
	@Autowired
	private ContainerMapper containerMapper;
	
	@Autowired
	private TermDataService dataService;
	
	/**
	 * 查询首页广告图片
	 */
	@Override
	public PcIndexDto findHomeAd() {
		IndexConfig indexConfig = configMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.PCINDEXCONFIG);
		//1.查询顶部广告图片
		ReqData reqData = new ReqData();
		reqData.putValue("ad_id", indexConfig.getTopAdId(), SystemConstant.REQ_PARAMETER_EQ);
		List<AdImage> topImages = adImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		PcIndexDto indexDto = new PcIndexDto();
		indexDto.setTopImages(topImages);
		//2.查询1号广告位图片
		reqData.putValue("ad_id", indexConfig.getFirstAdId(), SystemConstant.REQ_PARAMETER_EQ);
		List<AdImage> firstImages = adImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		indexDto.setFirstImages(firstImages);
		return indexDto;
	}
	/**
	 * 未使用
	 */
	@Override
	public List<PcContainerDto> findPcContainer() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(12);
		//查询首页配置信息
		List<IndexConfig> selectByExample = configMapper.selectByExample(new CriteriaParameter());
		IndexConfig indexConfig = selectByExample.get(0);
		//1.查询配置的全部货柜信息
		ReqData reqData = new ReqData();
		reqData.putValue("id", indexConfig.getContainerIds(), SystemConstant.REQ_PARAMETER_IN);
		List<PcContainerDto> containerList = containerExMapper.selectContainer(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (PcContainerDto pcContainerDto : containerList) {
			//1.1查询货柜种类的所有分词
			reqData.putValue("cid", pcContainerDto.getCategoryId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_NE);//借用系统常量0
			List<CategoryWordSeg> containerCategory = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			pcContainerDto.setCategoryName(containerCategory);
			/*//1.2查询出货柜的前12个商品
			reqData.putValue("id", pcContainerDto.getGoodsIds(), SystemConstant.REQ_PARAMETER_IN);
			List<Goods> containerGoods = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			for (Goods goods : containerGoods) {
				String firstGoodsImg = commonGoodsService.getFirstGoodsImg(goods.getId());
				goods.setImgPath(firstGoodsImg);
			}
			reqData.clearValue();*/
			//pcContainerDto.setContainerGoods(containerGoods);
		}
		return containerList;
	}
	/**
	 * 未使用
	 */
	@Override
	public List<PcContainerDto> findContainerDtos() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(10);
		//查询首页配置信息
		List<IndexConfig> selectByExample = configMapper.selectByExample(new CriteriaParameter());
		IndexConfig indexConfig = selectByExample.get(0);
		//1.查询配置的全部货柜信息
		ReqData reqData = new ReqData();
		reqData.putValue("id", indexConfig.getContainerIds(), SystemConstant.REQ_PARAMETER_IN);
		List<PcContainerDto> containerList = containerExMapper.selectContainer(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (PcContainerDto pcContainerDto : containerList) {
			//配置货柜广告图片
			reqData.putValue("ad_id", pcContainerDto.getAdId(), SystemConstant.REQ_PARAMETER_EQ);
			PageBean pageBean2 = new PageBean();
			pageBean2.setPageSize(2);
			pcContainerDto.setImageList(adImageMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean2.getRowBounds()));
			reqData.clearValue();
			if(!ObjectUtils.isEmpty(pcContainerDto.getGoodsIds())){
				//查询该货柜商品中拥有的所有分词
				PageBean bean = new PageBean();
				bean.setPageSize(9);//只取九个分类标识
				String specId = "0";
				reqData.putValue("goods_id", pcContainerDto.getGoodsIds(), SystemConstant.REQ_PARAMETER_IN);
				List<GoodsSpecCategory> selectByExample2 = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				for (GoodsSpecCategory goodsSpecCategory : selectByExample2) {
					specId += ","+goodsSpecCategory.getSpecCategoryId();
				}
				reqData.putValue("id", specId, SystemConstant.REQ_PARAMETER_IN);
				List<CategoryWordSeg> selectByExample3 = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				pcContainerDto.setCategoryName(selectByExample3);
			}
		}
		//为每个分词下设置对应的商品
		for (PcContainerDto pcContainerDto : containerList) {
			if(!ObjectUtils.isEmpty(pcContainerDto.getGoodsIds())){
				List<CategoryWordSeg> categoryName = pcContainerDto.getCategoryName();
				List<PcIndexGoodsDto> testDto = new ArrayList<PcIndexGoodsDto>();
				for (CategoryWordSeg categoryWordSeg : categoryName) {
					String goodsId = "0";
					PcIndexGoodsDto dto = new PcIndexGoodsDto();
					dto.setCategoryId(categoryWordSeg.getId());
					dto.setCategoryName(categoryWordSeg.getWordSeg());
					dto.setParentId(categoryWordSeg.getCid());
					reqData.putValue("spec_category_id", categoryWordSeg.getId(), SystemConstant.REQ_PARAMETER_EQ);
					List<GoodsSpecCategory> selectByExample2 = goodsSpecCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
					reqData.clearValue();
					for (GoodsSpecCategory goodsSpecCategory : selectByExample2) {
						goodsId += ","+goodsSpecCategory.getGoodsId();
					}
					reqData.putValue("id", goodsId, SystemConstant.REQ_PARAMETER_IN);
					List<Goods> selectByExampleAndPage = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
					for (Goods goods : selectByExampleAndPage) {
						goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
					}
					reqData.clearValue();
					dto.setGoodsList(selectByExampleAndPage);
					testDto.add(dto);
				}
				pcContainerDto.setTestList(testDto);
			}
		}
		
		return containerList;
	}
	/**
	 * 使用中
	 */
	@Override
	public List<PcContainerDto> findCOntainers() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(10);
		//查询首页配置信息
		IndexConfig indexConfig = configMapper.selectByPrimaryKey(SystemConstant.INDEXCONFIG.APPINDEXCONFIG);
		//1.查询配置的全部货柜信息
		ReqData reqData = new ReqData();
		reqData.putValue("id", indexConfig.getContainerIds(), SystemConstant.REQ_PARAMETER_IN);
		List<PcContainerDto> containerList = containerExMapper.selectContainer(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (PcContainerDto pcContainerDto : containerList) {
			//配饰货柜关键字
			List<String> keyList = new ArrayList<String>();
			String keyWords = pcContainerDto.getKeyWords();
			String[] split = keyWords.split(",");
			for(int i=0;i<split.length;i++){
				keyList.add(split[i]);
			}
			pcContainerDto.setKeyWordsList(keyList);
			//配置货柜广告图片
			reqData.putValue("ad_id", pcContainerDto.getPcAdId(), SystemConstant.REQ_PARAMETER_EQ);
			PageBean pageBean2 = new PageBean();
			pageBean2.setPageSize(2);
			pcContainerDto.setImageList(adImageMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean2.getRowBounds()));
			reqData.clearValue();
		}
		return containerList;
	}
	/**
	 * 查询货柜商品
	 */
	@Override
	public List<Goods> findContainerGoods(Long containerId, Integer pageIndex,Integer consize,PageBean pageBean) {
		List<Goods> goodsList = null;
		pageBean.setPageIndex(1);
		//1.先查询货柜的所有商品
		ReqData reqData = new ReqData();
		Container contain = containerMapper.selectByPrimaryKey(containerId);
		if(!ObjectUtils.isEmpty(contain.getGoodsIds())){
			reqData.putValue("id", contain.getGoodsIds(), SystemConstant.REQ_PARAMETER_IN);
			reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
			goodsList = goodsMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
			Collections.shuffle(goodsList);
			for (Goods goods : goodsList) {
				goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
			}
		}
		List<Goods> subList = null;
		if(consize==0){
			subList = goodsList.subList(0, 10);
		}else{
			subList = goodsList.subList(0, 4);
		}
		pageBean.setTotalCount(goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		return subList;
	}
	/**
	 * 查询搜索关键字配置
	 */
	@Override
	public List<String> findSearchKeyWords() {
		List<String> strList = new ArrayList<>();
		ReqData reqData = new ReqData();
		reqData.putValue("vid", SystemConstant.TERMDATA.PC_SEARCHVID, SystemConstant.REQ_PARAMETER_EQ);
		List<TermData> list = dataService.selectTermDataByVid(reqData);
		if(!ObjectUtils.isEmpty(list)){
			TermData termData = list.get(0);
			String name = termData.getName();
			String[] split = name.split(",");
			for (String string : split) {
				strList.add(string);
			}
		}
		return strList;
	}

}
