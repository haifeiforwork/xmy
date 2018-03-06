package com.zfj.xmy.goods.service.app.impl;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.goods.persistence.app.dao.AppTermDataExMapper;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppAdImageDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppEnterpriseDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.persistence.common.dao.GoodsExMapper;
import com.zfj.xmy.goods.persistence.common.dao.GoodsSoldExMapper;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsDto;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsSoldDir;
import com.zfj.xmy.goods.service.app.AppCategoryService;
import com.zfj.xmy.goods.service.app.TermDataExService;
import com.zfj.xmy.goods.service.common.GoodsService;

/** 
 * @Title: CategoryServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.app 
 * @Description: 
 * @author hexw
 * @date 2017年7月21日 上午9:45:35 
 */
@Service
public class AppCategoryServiceImpl extends BaseService implements AppCategoryService {
	
	@Autowired
	private AppCategoryService appCategoryService;
	
	@Autowired
	private AppTermDataExMapper appTermDataExMapper;
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Autowired
	private CategoryWordSegMapper categoryWordSegMapper;
	
	@Autowired
	private GoodsSoldExMapper appGoodsSoldExMapper; 
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsImageMapper goodsImageMapper;
	
	@Autowired
	private GoodsExMapper goodsExMapper;
	
	@Autowired
	private TermDataExService termDataExService;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Override
	public List<AppTermDataDir> findCategory(){
		ReqData reqData = new ReqData();
		reqData.putValue("vid", termDataExService.getVocabularyIdByMark(SystemConstant.TERMDATA.CATEGORY), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.TERMDATA.SHOW, SystemConstant.REQ_PARAMETER_EQ);
		List<AppTermDataDir> list = appTermDataExMapper.findCategorys(ReqUtil.reqParameterToCriteriaParameter(reqData));
		List<AppTermDataDir> parentList = new  ArrayList<AppTermDataDir>();
		
		if (list.size() > 0){
			for (AppTermDataDir appTermDataDir : list) {
				if( appTermDataDir.getParentId() == 0 && 0 == appTermDataDir.getIsShow()){  //取出启用状态的一级分类
					appTermDataDir.setIcon(appTermDataDir.getAppIcon());  //一级分类图标使用app图标
					appTermDataDir.setAppOnIcon(appTermDataDir.getAppOnIcon()); //app一级分类选中的图标
					parentList.add(appTermDataDir); 
				}
			}
			for (AppTermDataDir appTermDataDir : list) {
				for (AppTermDataDir parentTermDataDir : parentList) {
					if (appTermDataDir.getParentId() == parentTermDataDir.getId() ){ //取出二级菜单
						List<CategoryWordSeg> wordSegList = findCategoryWordSegByCid(appTermDataDir.getId());  //查询二级菜单的分词
						if(wordSegList.size() > 0){
							appTermDataDir.setWordSegList(wordSegList);
						}
						parentTermDataDir.getChildList().add(appTermDataDir);
					}
				}
			}
		}
		// 分类top图
		for (AppTermDataDir appTermDataDir : parentList) {
			if (ObjectUtil.isNotNull(findCategoryTopImg(appTermDataDir.getId()))){
				appTermDataDir.setAdImgList(findCategoryTopImg(appTermDataDir.getId()));
			}
		}
		return parentList;
	}
	

	/**
	 * 一级分类top图
	 * @param id
	 * @return    
	 * @return List<AppAdimageDir>    
	 * Date:2017年8月15日 下午8:47:35 
	 * @author hexw
	 */
	@Override
	public List<AppAdImageDir> findCategoryTopImg(long id){
		ReqData reqData = new  ReqData();
		reqData.putValue("a.category_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("a.type", SystemConstant.AdInfoImage.TYPE_CLASSIFICATIONTOPIMG, SystemConstant.REQ_PARAMETER_EQ);
		return appTermDataExMapper.findAdImage(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	/**
	 * 根据分类id 查询分词
	 * @param cid
	 * @return    
	 * @return List<CategoryWordSeg>    
	 * Date:2017年7月21日 上午11:14:26 
	 * @author hexw
	 */
	public List<CategoryWordSeg> findCategoryWordSegByCid(long cid){
		ReqData reqData = new  ReqData();
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type",SystemConstant.CATEGORY_SPEC.TYPE_MENU , SystemConstant.REQ_PARAMETER_EQ); //首页菜单显示
		reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.PARENT_ID,  SystemConstant.REQ_PARAMETER_NE);
		return categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	
	/**
	 * 
	 * @param identifying 栏目类型     0：首页  1：精选水果 2：跨境专区
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月24日 下午2:01:15 
	 * @author hexw
	 */
	@Override
	public List<AppTermDataDir> findGoodsPrograma(String identifying){
		ReqData reqData = new ReqData();
		reqData.putValue("vid", termDataExService.getVocabularyIdByMark(SystemConstant.TERMDATA.CATEGORY), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_Show", SystemConstant.TERMDATA.SHOW, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);
		List<AppTermDataDir> list = null;
		if ("0".equals(identifying)) { //首页  查询的是全部分类下的商品
			reqData.putValue("parent_id", "0", SystemConstant.REQ_PARAMETER_EQ);
			list = appTermDataExMapper.findCategorys(ReqUtil.reqParameterToCriteriaParameter(reqData)); //一级分类
			for (AppTermDataDir appTermDataDir : list) {
				appTermDataDir.setGoodsList(goodsService.findGoodsSold(appTermDataDir.getId())); //获取销量前20的商品
			}
		} else { //其他栏目
			reqData.putValue("id", identifying, SystemConstant.REQ_PARAMETER_EQ);
			list = appTermDataExMapper.findCategorys(ReqUtil.reqParameterToCriteriaParameter(reqData)); //一级分类
			for (AppTermDataDir appTermDataDir : list) {
				appTermDataDir.setGoodsList(goodsService.findGoodsSold(appTermDataDir.getId())); //获取销量前20的商品
			}
		}
		return list;
	}
	
	
	
	/**
	 * 企业定制
	 * @param obj
	 * @return    
	 * @return List<AppTermDataDir>    
	 * Date:2017年7月25日 下午5:43:22 
	 * @author hexw
	 */
	@Override
	public List<AppEnterpriseDir> findEnterprise(){
		List<AppEnterpriseDir> result = new ArrayList<AppEnterpriseDir>();
		//1. 查询一级分类首页不显示的
		ReqData reqData = new ReqData();
		reqData.putValue("vid", termDataExService.getVocabularyIdByMark(SystemConstant.TERMDATA.CATEGORY), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_Show", SystemConstant.TERMDATA.NOT_SHOW, SystemConstant.REQ_PARAMETER_EQ);  //首页显示为否的分类
		reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", "0", SystemConstant.REQ_PARAMETER_EQ);
		List<TermData> parentTermDatas = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		//2. 查询出企业定制的分类
		TermData parentTermData = null;
		for (TermData termData : parentTermDatas) {
			if (ObjectUtil.isNotNull(termData.getName())) {
				if (termData.getName().contains("企业定制")) {
					parentTermData =  termData;
					break;
				}
			}
		}
		//3.查询二级分类即商品
		reqData.clearValue();
		if (ObjectUtil.isNotNull(parentTermData)) {
			reqData.putValue("parent_id", parentTermData.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);
			List<TermData> childTermData = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (TermData termData : childTermData) {
				ReqData goodsReqData = new ReqData();
				goodsReqData.putValue("b.cid", termData.getId(), SystemConstant.REQ_PARAMETER_EQ);
				goodsReqData.putValue("a.is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
				goodsReqData.putValue("a.is_recycle", SystemConstant.GoodsConstant.NOT_RECYCLE, SystemConstant.REQ_PARAMETER_EQ);
				List<Goods>	goodsList = goodsExMapper.findGoods(ReqUtil.reqParameterToCriteriaParameter(goodsReqData));
				for(Goods goods:goodsList){
					goods.setImgPath(appCategoryService.getCustomizationImage(goods.getId()));
				}
				AppEnterpriseDir appEnterpriseDir = new AppEnterpriseDir();
				appEnterpriseDir.setCategoryName(termData.getName());
				appEnterpriseDir.setGoodslist(goodsList);
				result.add(appEnterpriseDir);
			}
		}
		return result;
	}
	
	@Override
	public String getCustomizationImage(long goodsId){
		String path = "";
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id",goodsId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("seq");
		reqData.setSortorder("desc");
		//3. 获取商品图片
		List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (imglist.size() > 0){
			path = imglist.get(0).getPath();
		}
		return path;
	}
}
  