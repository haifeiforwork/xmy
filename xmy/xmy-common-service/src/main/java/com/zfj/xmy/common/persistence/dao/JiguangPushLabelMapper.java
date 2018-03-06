package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.JiguangPushLabel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JiguangPushLabelMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<JiguangPushLabel> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") JiguangPushLabel record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") JiguangPushLabel record, @Param("example") CriteriaParameter example);

    List<JiguangPushLabel> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(JiguangPushLabel record);

    int insertSelective(JiguangPushLabel record);

    JiguangPushLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JiguangPushLabel record);

    int updateByPrimaryKey(JiguangPushLabel record);
}