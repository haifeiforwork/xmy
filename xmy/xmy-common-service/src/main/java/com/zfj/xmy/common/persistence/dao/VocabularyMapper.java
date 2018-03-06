package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VocabularyMapper extends BaseMapper<Vocabulary> {
	public Vocabulary selectByExampleWithBLOBs(CriteriaParameter para);
}