package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.KdniaoExpCode;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface KdniaoExpCodeMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<KdniaoExpCode> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") KdniaoExpCode record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") KdniaoExpCode record, @Param("example") CriteriaParameter example);

    List<KdniaoExpCode> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(KdniaoExpCode record);

    int insertSelective(KdniaoExpCode record);

    KdniaoExpCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KdniaoExpCode record);

    int updateByPrimaryKey(KdniaoExpCode record);
}