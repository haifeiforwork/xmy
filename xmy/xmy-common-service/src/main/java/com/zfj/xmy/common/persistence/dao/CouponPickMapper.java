package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.CouponPick;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CouponPickMapper {
    int countByExample(CriteriaParameter example);

    int deleteByExample(CriteriaParameter example);

    List<CouponPick> selectByExample(CriteriaParameter example);

    int updateByExampleSelective(@Param("record") CouponPick record, @Param("example") CriteriaParameter example);

    int updateByExample(@Param("record") CouponPick record, @Param("example") CriteriaParameter example);

    List<CouponPick> selectByExampleAndPage(CriteriaParameter example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(CouponPick record);

    int insertSelective(CouponPick record);

    CouponPick selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CouponPick record);

    int updateByPrimaryKey(CouponPick record);
}