package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.JiguangPushRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JiguangPushRecordMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<JiguangPushRecord> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") JiguangPushRecord record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") JiguangPushRecord record, @Param("example") CriteriaParameter example);

    List<JiguangPushRecord> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(String id);

    int insert(JiguangPushRecord record);

    int insertSelective(JiguangPushRecord record);

    JiguangPushRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JiguangPushRecord record);

    int updateByPrimaryKey(JiguangPushRecord record);
}