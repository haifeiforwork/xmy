package com.zfj.xmy.goods.service.cms.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.service.cms.TermDataService;

/** 
 * @Title: TermDataServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年6月23日 上午9:53:07 
 */
@Service
public class TermDataServiceImpl extends BaseService implements TermDataService{
	
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	
	@Override
	public void insertTermData(TermData termData){
		termDataMapper.insert(termData);
	}

	
	@Override
	public TermData getTermData(long id){
		TermData  termData = termDataMapper.selectByPrimaryKey(id);
		return termData;
	}
	
	@Override
	public int getCountTermData(ReqData reqData){
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		return termDataMapper.countByExample(parameter);
		
	}
	
	
	@Override
	public void selectTermDataList(ReqData reqData ,long vid ,PageBean pageBean){
		reqData.putValue("vid",vid,SystemConstant.REQ_PARAMETER_EQ) ;
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		List<TermData> list = termDataMapper.selectByExampleAndPage(parameter,pageBean.getRowBounds());
		pageBean.setData(list);
		pageBean.setTotalCount(termDataMapper.countByExample(parameter));
	}
	
	@Override
	public int updateStatus(String[] idArry, String status){
		int state = 0;
		for (int i = 0; i < idArry.length; i++) {
			TermData termData = termDataMapper.selectByPrimaryKey(Long.parseLong(idArry[i]));
			termData.setStatus(Integer.parseInt(status));
			state = state+termDataMapper.updateByPrimaryKey(termData);
		}
		return state;
	}
	
	@Override
	public int updateStatus(long id, String status){
		TermData termData = termDataMapper.selectByPrimaryKey(id);
		termData.setStatus(Integer.parseInt(status));
		return termDataMapper.updateByPrimaryKey(termData);
	}
	
	@Override
	public void updateTermData(TermData termData){
		termDataMapper.updateByPrimaryKeySelective(termData);
		//termDataMapper.updateByPrimaryKeyWithBLOBs(termData);
	}


	@Override
	public List<TermData> selectTermDataByVid(ReqData reqData) {
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		return termDataMapper.selectByExample(parameter);
	}
	
	
	@Override
	public void findTerm(ReqData reqData, PageBean pageBean) {
		List<TermData> data = termDataMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(data) ;
	}
	
	@Override
	public int deleteByPrimaryKey(Object string) {
		int deleteByPrimaryKey = termDataMapper.deleteByPrimaryKey(string);
		return deleteByPrimaryKey;
	}


	@Override
	public void findTermWithBlob(ReqData reqData, PageBean pageBean) {
		List<TermData> selectByExampleAndPageWithBlob = termDataMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(selectByExampleAndPageWithBlob) ;
	}
	
}
  