package com.zfj.xmy.goods.service.cms.impl;  

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.common.dao.CategoryWordSegExMapper;
import com.zfj.xmy.goods.persistence.common.dao.TermDataExMapper;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegVo;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;
import com.zfj.xmy.goods.service.cms.CategoryService;
import com.zfj.xmy.goods.service.cms.VocabularyService;

/** 
 * @Title: CategoryServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年6月19日 上午11:22:32 
 */
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
	
	@Autowired 
	private TermDataExMapper termDataExMapper; 
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Autowired
	private CategoryWordSegMapper categoryWordSegMapper;
	
	@Autowired
	private VocabularyService vocabularyService;

	@Override
	public List<TermDataDir> findCategory(Integer status) {
		ReqData reqData = new ReqData();
		reqData.putValue("vid", vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.CATEGORY), SystemConstant.REQ_PARAMETER_EQ);
		if (!ObjectUtils.isEmpty(status)) {
			reqData.putValue("status", status, SystemConstant.REQ_PARAMETER_EQ);
		}
		List<TermDataDir> list = termDataExMapper.findCategory(ReqUtil.reqParameterToCriteriaParameter(reqData));
		List<TermDataDir> perentlist = new ArrayList<TermDataDir>() ;
		for (TermDataDir termDataDir : list) {
			if( termDataDir.getParentId() == 0  ){  //取出  状态为启用的一级菜单 
				perentlist.add(termDataDir);
			}
		}
		for (TermDataDir childTermDataDir : list) {  //二级菜单
			for (TermDataDir termDataDir : perentlist) {
				if( childTermDataDir.getParentId() == termDataDir.getId()  ){
					termDataDir.getChildren().add(childTermDataDir);
				}
			}
		}
		return perentlist;
	}
	
	
	@Override
	public List<TermDataDir> selectCategory(ReqData reqData ){
		reqData.putValue("vid",vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.CATEGORY),SystemConstant.REQ_PARAMETER_EQ );
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		List<TermDataDir> perentlist = new ArrayList<TermDataDir>() ;
		List<TermDataDir> childlist = termDataExMapper.findCategoryList(parameter);  //分类总记录数
		for (TermDataDir termDataDir : childlist) {
			if( termDataDir.getParentId() == 0 ){  //取出一级菜单
				perentlist.add(termDataDir);
			}
		}
		for (TermDataDir childTermDataDir : childlist) {  //二级菜单
			for (TermDataDir termDataDir : perentlist) {
				if( childTermDataDir.getId() != 0 && childTermDataDir.getParentId() == termDataDir.getId()  ){
					termDataDir.getChildren().add(childTermDataDir);
				}
			}
		}
		return perentlist;
	}

	@Override
	public int updateCategoryStatus(long id, int status){
		TermData  termData = termDataMapper.selectByPrimaryKey(id);
		termData.setStatus(status);
		return termDataMapper.updateByPrimaryKey(termData);
	}
	
	
	@Override
	public void updateCategory(TermData termData){
		// 1.判断是否修改成了一级分类，若修改成一级分类则要删除它的规格及分词
		if (0 == termData.getParentId()) {
			ReqData reqData = new  ReqData();
			reqData.putValue("cid", termData.getId(), SystemConstant.REQ_PARAMETER_EQ);
			categoryWordSegMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}
		// 2. 修改类别
		termDataMapper.updateByPrimaryKeySelective(termData);
	}
	
	
	@Override
	public CategoryWordSeg insertCategoryWordSeg(CategoryWordSegVo vo){
		// 1. 先判断当前类别下是否已存在种类这个规格,没有则创建一条
		ReqData reqData = new  ReqData();
		reqData.putValue("cid", vo.getCid(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.PARENT_ID, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type",SystemConstant.CATEGORY_SPEC.TYPE_MENU ,SystemConstant.REQ_PARAMETER_EQ);
		List<CategoryWordSeg> list = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		CategoryWordSeg  parentWordSeg = null;
		if (list.size() > 0) {
			parentWordSeg = list.get(0);
		} else {
			parentWordSeg = new  CategoryWordSeg();
			parentWordSeg.setCid(vo.getCid());
			parentWordSeg.setWordSeg(SystemConstant.CATEGORY_SPEC.KIND);
			parentWordSeg.setParentId(SystemConstant.CATEGORY_SPEC.PARENT_ID);
			parentWordSeg.setCreateTime(new  Date());
			parentWordSeg.setType(SystemConstant.CATEGORY_SPEC.TYPE_MENU);
			categoryWordSegMapper.insert(parentWordSeg);
		}
		// 2. 再添加子集
		CategoryWordSeg  child = new  CategoryWordSeg();
		child.setCid(vo.getCid());
		child.setWordSeg(vo.getWordSeg());
		child.setImgPath(vo.getImgPath());
		child.setCreateTime(new Date());
		child.setParentId(parentWordSeg.getId());
		child.setSeq(vo.getSeq());
		child.setType(SystemConstant.CATEGORY_SPEC.TYPE_MENU); // 0首页菜单  1代表规格分类
		categoryWordSegMapper.insert(child);
		return child;
	}
	
	@Override
	public int updateCategoryWordSeg(CategoryWordSeg categoryWordSeg){
		return categoryWordSegMapper.updateByPrimaryKeySelective(categoryWordSeg);
	}
	
	@Override
	public List<CategoryWordSeg> findCategoryWordSeg(long id){
		ReqData reqData = new ReqData();
		reqData.putValue("cid", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.PARENT_ID, SystemConstant.REQ_PARAMETER_NE);
		return categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	@Override
	public int deleteCategoryWordSeg(long id){
		return categoryWordSegMapper.deleteByPrimaryKey(id);
	}
	
}
  