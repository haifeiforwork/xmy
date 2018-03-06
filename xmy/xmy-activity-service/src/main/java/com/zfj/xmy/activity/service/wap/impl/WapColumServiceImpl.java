package com.zfj.xmy.activity.service.wap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.activity.persistence.pc.dao.PcTermDataExMapper;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.wap.WapColumService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CategoryWordSegMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
@Service
public class WapColumServiceImpl implements WapColumService {
	@Autowired
	private PcTermDataExMapper pcTermDataExMapper;
	
	@Autowired
	private CategoryWordSegMapper wordSegMapper;
	
	@Override
	public List<PcDownColumDto> findParentColum(int vid) {
		
		ReqData reqData = new ReqData();
		//1.查询栏目管理的所有父级
		reqData.putValue("vid", vid, SystemConstant.REQ_PARAMETER_EQ);//根据资源表的栏目管理vid的为栏目管理
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);//父级id为0的是所有的父级元素
		reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);
		List<PcDownColumDto> selectDownColum = pcTermDataExMapper.selectDownColum(ReqUtil.reqParameterToCriteriaParameter(reqData));
		
		return selectDownColum;
	}

	@Override
	public List<PcDownColumDto> findChildrenColum(String parentId, int vid) {
		
		ReqData reqData = new ReqData();
		reqData.putValue("status", SystemConstant.TERMDATA.YES, SystemConstant.REQ_PARAMETER_EQ);//查询启用的
		reqData.putValue("vid", vid, SystemConstant.REQ_PARAMETER_EQ);//根据资源表的栏目管理vid的为栏目管理
		reqData.putValue("parent_id", parentId, SystemConstant.REQ_PARAMETER_EQ);
		List<PcDownColumDto> downColum = pcTermDataExMapper.selectDownColum(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		//查询二级分类下面的所有分词
		for (PcDownColumDto cloum : downColum) {
			reqData.putValue("cid", cloum.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("type", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("parent_id", SystemConstant.CATEGORY_SPEC.TYPE_MENU, SystemConstant.REQ_PARAMETER_NE);//借用系统常量0
			reqData.putValue("type", SystemConstant.REQ_PARAMETER_EQ, "0");//TODO 暂设为0
			List<CategoryWordSeg> containerCategory = wordSegMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			cloum.setChildCategory(containerCategory);
		}
		
		return downColum;
	}
	
}
