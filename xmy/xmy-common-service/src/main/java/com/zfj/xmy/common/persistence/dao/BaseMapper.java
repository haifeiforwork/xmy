package com.zfj.xmy.common.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;

public interface BaseMapper<T> {
	/**
	 * 按条件查询 记录数
	 * 
	 * @param para
	 * @return
	 */
	int countByExample(CriteriaParameter para);

	/**
	 * 按条件删除对应记录
	 * 
	 * @param para
	 * @return 删除记录数
	 */
	int deleteByExample(CriteriaParameter para);

	/**
	 * 按条件查询记录
	 * 
	 * @param para
	 * @return 查询结果
	 */
	List<T> selectByExample(CriteriaParameter para);

	/**
	 * 按实体内有值的属性 "选择性" 按条件更新记录
	 * 
	 * @param record
	 *            需要更新的实体
	 * @param para
	 *            更新条件
	 * @return 返回更新记录数
	 */
	int updateByExampleSelective(@Param("record") T record, @Param("example") CriteriaParameter para);

	/**
	 * 按条件更新记录
	 * 
	 * @param record
	 *            需要更新的实体
	 * @param para
	 *            更新条件
	 * @return 返回更新记录数
	 */
	int updateByExample(@Param("record") T record, @Param("example") CriteriaParameter para);

	/**
	 * 按条件分页查询
	 * 
	 * @param para
	 *            条件
	 * @param rowBound
	 *            分页RowBounds对象
	 * @return
	 */
	List<T> selectByExampleAndPage(CriteriaParameter para, RowBounds rowBound);

	/**
	 * 按主键删除
	 * 
	 * @param string
	 *            主键
	 * @return 返回删除记录
	 */
	int deleteByPrimaryKey(Object string);

	/**
	 * 插入一条记录
	 * 
	 * @param record
	 *            对应实体
	 * @return
	 */
	
	int insert(T record);

	/**
	 * "选择性"插入记录,对实体属性有值的插入
	 * 
	 * @param record
	 *            对应实体
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据主键查询一条记录
	 * 
	 * @param id
	 *            主键
	 * @return 查询对应实体
	 */
	T selectByPrimaryKey(Object id);

	/**
	 * 按主键"选择性"记录,对实体属性有值的更新
	 * 
	 * @param record
	 *            需要要更新的实体
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据主键更新实体
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);
	/**
	 * 根据主键更新实体(包括了blobs类型的)
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyWithBLOBs(T record);
}