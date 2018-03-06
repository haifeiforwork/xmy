package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.ClientVersion;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientVersionMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<ClientVersion> selectByExampleWithBLOBs(CriteriaParameter example);

    List<ClientVersion> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") ClientVersion record, @Param("example") CriteriaParameter example);

    int updateByExampleWithBLOBs(@Param("record") ClientVersion record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") ClientVersion record, @Param("example") CriteriaParameter example);

    List<ClientVersion> selectByExampleWithBLOBsAndPage(CriteriaParameter example, RowBounds rowBound);

    List<ClientVersion> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(ClientVersion record);

    int insertSelective(ClientVersion record);

    ClientVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientVersion record);

    int updateByPrimaryKeyWithBLOBs(ClientVersion record);

    int updateByPrimaryKey(ClientVersion record);
}