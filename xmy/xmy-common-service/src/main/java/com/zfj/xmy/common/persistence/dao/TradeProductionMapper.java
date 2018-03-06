package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.TradeProduction;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradeProductionMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<TradeProduction> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") TradeProduction record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") TradeProduction record, @Param("example") CriteriaParameter example);

    List<TradeProduction> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(TradeProduction record);

    int insertSelective(TradeProduction record);

    TradeProduction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeProduction record);

    int updateByPrimaryKey(TradeProduction record);
}