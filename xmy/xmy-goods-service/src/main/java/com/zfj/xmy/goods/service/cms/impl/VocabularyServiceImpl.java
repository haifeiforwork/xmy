package com.zfj.xmy.goods.service.cms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.VocabularyMapper;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.goods.service.cms.VocabularyService;
/**
 * 
 * @Description 词汇表service
 * @Author liuw
 * @Date 2017年6月26日下午4:19:51
 */
@Service
public class VocabularyServiceImpl extends BaseService implements VocabularyService{
	
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
	@Override
	public Vocabulary getVocabularyByMark(ReqData reqData) {
		CriteriaParameter reqParameterToCriteriaParameter = ReqUtil.reqParameterToCriteriaParameter(reqData);
		Vocabulary selectByExampleWithBLOBs = vocabularyMapper.selectByExampleWithBLOBs(reqParameterToCriteriaParameter);
		return selectByExampleWithBLOBs;
	}
	
	@Override
	public long getVocabularyVid(String Mark){
		ReqData reqData =new  ReqData();
		reqData.putValue(SystemConstant.TERMDATA.MARK, Mark, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary   Vocabulary = vocabularyMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if( Vocabulary != null){
			return Vocabulary.getId();
		}else{
			return 0;
		}
	}
	
}
