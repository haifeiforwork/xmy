package com.zfj.xmy.goods.service.cms.impl;  

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.goods.persistence.common.dao.CategoryWordSegExMapper;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDto;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegVo;
import com.zfj.xmy.goods.service.cms.CategoryWordSegService;

/** 
 * @Title: CategoryWordSegServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.cms.impl 
 * @Description: 
 * @author hexw
 * @date 2017年8月8日 下午8:02:27 
 */
@Service
public class CategoryWordSegServiceImpl extends BaseService implements CategoryWordSegService {

	@Autowired
	private CategoryWordSegMapper categoryWordSegMapper;
	
	@Autowired
	private CategoryWordSegExMapper categoryWordSegExMapper;
	
	@Override
	public CategoryWordSeg insertCategorySpec(CategoryWordSegVo vo){
		// 1. 先生成父级的规格
		CategoryWordSeg categoryWordSeg = new  CategoryWordSeg();
		categoryWordSeg.setWordSeg(vo.getWordSeg());
		categoryWordSeg.setIsSearch(vo.getIsShow());
		categoryWordSeg.setIsShow(vo.getIsShow());
		categoryWordSeg.setCid(vo.getCid());
		categoryWordSeg.setCreateTime(new Date());
		categoryWordSeg.setType(SystemConstant.CATEGORY_SPEC.TYPE_SPEC);
		categoryWordSeg.setSeq(vo.getSeq());
		categoryWordSeg.setParentId(0L);
		categoryWordSegMapper.insertSelective(categoryWordSeg);
		// 2. 再将规格可选值单独存
		String[] valArray = vo.getVal().split("\\,");
		if (valArray.length > 0 ) {
			for (String val : valArray) {
				CategoryWordSeg childCategoryWordSeg = new  CategoryWordSeg();
				childCategoryWordSeg.setCid(vo.getCid());
				childCategoryWordSeg.setWordSeg(val);
				childCategoryWordSeg.setParentId(categoryWordSeg.getId());
				childCategoryWordSeg.setCreateTime(new Date());
				childCategoryWordSeg.setType(SystemConstant.CATEGORY_SPEC.TYPE_SPEC);
				categoryWordSegMapper.insertSelective(childCategoryWordSeg);
			}
			
		}
		return categoryWordSeg;
	}
	
	@Override
	public List<CategoryWordSegDto> findCategoryWordSegDto(long cid){
		// 1. 查询类别分词
		List<CategoryWordSegDto> dtoList = new  ArrayList<CategoryWordSegDto>();
		ReqData reqData = new  ReqData();
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id",0, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_SPEC,SystemConstant.REQ_PARAMETER_EQ);
		List<CategoryWordSeg> list = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		// 2. 查出子集
		if (list.size() > 0) {
			for (CategoryWordSeg categoryWordSeg : list) {
				String val = "";
				ReqData childreqData = new  ReqData();
				childreqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_EQ);
				childreqData.putValue("parent_id",categoryWordSeg.getId(), SystemConstant.REQ_PARAMETER_EQ);
				childreqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_SPEC,SystemConstant.REQ_PARAMETER_EQ);
				List<CategoryWordSeg> chlid = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(childreqData));
				if (chlid.size() > 0) {
					for (CategoryWordSeg childWordSeg : chlid) {
						val = val+childWordSeg.getWordSeg()+",";
					}
				}
				// 3. 组装数据
				CategoryWordSegDto dto = new CategoryWordSegDto();
				dto.setId(categoryWordSeg.getId());
				dto.setWordSeg(categoryWordSeg.getWordSeg());
				dto.setIsSearch(categoryWordSeg.getIsSearch());
				dto.setIsShow(categoryWordSeg.getIsShow());
				dto.setVal(val);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	@Override
	public int deleteSpec(long id){
		//1.  先删除分类规格的子集
		ReqData reqData = new  ReqData();
		reqData.putValue("parent_id", id, SystemConstant.REQ_PARAMETER_EQ);
		int result = categoryWordSegMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (result > 0) {
			categoryWordSegMapper.deleteByPrimaryKey(id);
		}
		//2. 再删除自己
		return result;
	}
	
	@Override
	public CategoryWordSegDto getCategoryWordSegDto(long id){
		CategoryWordSegDto dto = new  CategoryWordSegDto();
		//1. 先得到父类规格
		CategoryWordSeg parent = categoryWordSegMapper.selectByPrimaryKey(id);
		//2. 查询子集
		ReqData reqData = new  ReqData();
		reqData.putValue("parent_id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<CategoryWordSeg> list = categoryWordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		String val = "";
		if (list.size() >0 ) {
			for (CategoryWordSeg child : list) {
				val =val+child.getWordSeg()+",";
			}
		}
		//3. 组装数据
		dto.setId(parent.getId());
		dto.setSeq(parent.getSeq());
		dto.setWordSeg(parent.getWordSeg());
		dto.setIsSearch(parent.getIsSearch());
		dto.setIsShow(parent.getIsShow());
		dto.setVal(val);
		return dto;
	}
	
	
	@Override
	public int updateCategoryWordSeg(CategoryWordSegVo vo ){
		int result = 0;
		CategoryWordSeg parent = categoryWordSegMapper.selectByPrimaryKey(Long.parseLong(vo.getId()));
		if ( parent != null ) {
			//1. 修改父类
			parent.setWordSeg(vo.getWordSeg());
			parent.setSeq(vo.getSeq());
			parent.setIsSearch(vo.getIsSearch());
			parent.setIsShow(vo.getIsShow());
			result = categoryWordSegMapper.updateByPrimaryKeySelective(parent);
			//2. 修改子集
			ReqData reqData = new  ReqData();
			reqData.putValue("parent_id", vo.getId(), SystemConstant.REQ_PARAMETER_EQ);
			//2.1  先删除存在的子集
			categoryWordSegMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			//2.2 再添加新的子集
			String[] valArray = vo.getVal().split("\\,");
			if (valArray.length > 0 ) {
				for (String val : valArray) {
					CategoryWordSeg childCategoryWordSeg = new  CategoryWordSeg();
					childCategoryWordSeg.setCid(parent.getCid());
					childCategoryWordSeg.setWordSeg(val);
					childCategoryWordSeg.setParentId(parent.getId());
					childCategoryWordSeg.setCreateTime(new Date());
					childCategoryWordSeg.setType(SystemConstant.CATEGORY_SPEC.TYPE_SPEC);
					categoryWordSegMapper.insertSelective(childCategoryWordSeg);
				}
				
			}
		}
		return result;
	}
	
	@Override
	public List<CategoryWordSegDir> findCategoryWordSegByCid(long cid){
		//1. 查询一级分词
		ReqData reqData = new  ReqData();
		reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<CategoryWordSegDir> parentList = categoryWordSegExMapper.findCategoryWordSeg(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (parentList.size() > 0) {
			for (CategoryWordSegDir categoryWordSegDir : parentList) {
				ReqData childReqData = new  ReqData();
				childReqData.putValue("cid",  cid, SystemConstant.REQ_PARAMETER_EQ);
				childReqData.putValue("parent_id", categoryWordSegDir.getId(), SystemConstant.REQ_PARAMETER_EQ);
				List<CategoryWordSegDir> child = categoryWordSegExMapper.findCategoryWordSeg(ReqUtil.reqParameterToCriteriaParameter(childReqData));
				if (child.size() > 0) {
					categoryWordSegDir.setChild(child);
				}
			}
			
		}
		return parentList;
	}
	
	@Override
	public List<String> findGoodsSpec(long goodsId){
		ReqData reqData = new  ReqData();
		reqData.putValue("a.goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		return categoryWordSegExMapper.findGoodsSpec(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	@Override
	public CategoryWordSeg getCategoryWordSeg(long id){
		CategoryWordSeg  categoryWordSeg = categoryWordSegMapper.selectByPrimaryKey(id);
		return categoryWordSeg;
	}
	
}
  