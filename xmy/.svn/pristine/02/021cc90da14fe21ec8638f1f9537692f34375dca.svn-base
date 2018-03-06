package com.zfj.xmy.common.persistence.dao;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsMapper extends BaseMapper<Goods> {
	/**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2017-07-19 15:50:44
     */
    List<GoodsWithBLOBs> selectByExampleWithBLOBs(CriteriaParameter example);
    
    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2017-07-19 15:50:44
     */
    int updateByExampleWithBLOBs(@Param("record") GoodsWithBLOBs record, @Param("example") CriteriaParameter example);
    
    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2017-07-19 15:50:44
     */
    List<GoodsWithBLOBs> selectByExampleWithBLOBsAndPage(CriteriaParameter example, RowBounds rowBound);
    
    
    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2017-07-19 15:50:44
     */
    int updateByPrimaryKeyWithBLOBs(GoodsWithBLOBs record);
    
    
    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2017-07-19 15:50:44
     */
    GoodsWithBLOBs selectByPrimaryKey(Long id); 
}