package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.TermData;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TermDataMapper extends BaseMapper<TermData> {
	List<TermData> selectByExampleWithBLOBs(CriteriaParameter para, RowBounds rowBound);
}