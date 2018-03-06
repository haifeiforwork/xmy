package com.zfj.xmy.activity.service.pc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.activity.persistence.pc.dao.PcTermDataExMapper;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.TermData;
@Service
public class PcColumServiceImpl implements PcColumService {
	@Autowired
	private PcTermDataExMapper pcTermDataExMapper;
	
	@Autowired
	private CategoryWordSegMapper wordSegMapper;
	
	@Autowired
	private TermDataMapper dataMapper;
	
	@Override
	public List<PcDownColumDto> findDownColum(int vid) {
		ReqData reqData = new ReqData();
		//1.查询栏目管理的所有父级
		reqData.putValue("vid", vid, SystemConstant.REQ_PARAMETER_EQ);//根据资源表的栏目管理vid的为栏目管理
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);//父级id为0的是所有的父级元素
		reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);
		List<PcDownColumDto> selectDownColum = pcTermDataExMapper.selectDownColum(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		//2.查询父级下的所有子级元素
		for (PcDownColumDto pcDownColumDto : selectDownColum) {
			reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);//查询启用的
			reqData.putValue("vid", vid, SystemConstant.REQ_PARAMETER_EQ);//根据资源表的栏目管理vid的为栏目管理
			reqData.putValue("parent_id", pcDownColumDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<PcDownColumDto> downColum = pcTermDataExMapper.selectDownColum(ReqUtil.reqParameterToCriteriaParameter(reqData));
			pcDownColumDto.setChildTermData(downColum);
			reqData.clearValue();
			//查询二级分类下面的所有分词
			for (PcDownColumDto cloum : downColum) {
				reqData.putValue("cid", cloum.getId(), SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_EQ);
				reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_NE);//借用系统常量0
				reqData.setSortname("seq");
				reqData.setSortorder("asc");
				List<CategoryWordSeg> containerCategory = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				cloum.setChildCategory(containerCategory);
			}
		}
		
		return selectDownColum;
	}

	@Override
	public String findTestString(Long id) {
		String str = "" ;
		ReqData reqData = new ReqData();
		TermData topTerm = dataMapper.selectByPrimaryKey(id);
		str+=topTerm.getName();//拼接
		if(topTerm.getParentId()!=0){//二级分类时查询父级分类名称
			TermData parentTerm = dataMapper.selectByPrimaryKey(topTerm.getParentId());
			str+=parentTerm.getName();
			reqData.putValue("cid", topTerm.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<CategoryWordSeg> byExample = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : byExample) {
				str += categoryWordSeg.getWordSeg();
			}
		}else{
			String cid = "0";
			reqData.putValue("parent_id", topTerm.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<TermData> list = dataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (TermData termData : list) {
				str+=termData.getName();
				cid+=","+termData.getId();
			}
			reqData.putValue("cid", cid, SystemConstant.REQ_PARAMETER_IN);
			List<CategoryWordSeg> byExample = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (CategoryWordSeg categoryWordSeg : byExample) {
				str += categoryWordSeg.getWordSeg();
			}
		}
		return str;
	}
	
}
