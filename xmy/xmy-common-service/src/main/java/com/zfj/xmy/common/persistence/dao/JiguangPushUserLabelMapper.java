package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.JiguangPushUserLabel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JiguangPushUserLabelMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<JiguangPushUserLabel> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") JiguangPushUserLabel record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") JiguangPushUserLabel record, @Param("example") CriteriaParameter example);

    List<JiguangPushUserLabel> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(JiguangPushUserLabel record);

    int insertSelective(JiguangPushUserLabel record);

    JiguangPushUserLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JiguangPushUserLabel record);

    int updateByPrimaryKey(JiguangPushUserLabel record);
}