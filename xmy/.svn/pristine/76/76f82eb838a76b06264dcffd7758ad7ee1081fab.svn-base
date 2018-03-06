package com.zfj.xmy.elasticsearch.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.util.DataPage;
public interface BaseSearchService<T extends Serializable, PK extends Serializable>{

  /**
   * 全文搜索查询索引分页
   * @param paramMap
   * <p> key            - Notnull  - Str - 查询字符串
   * <p> pageNum        - Nullable - Str - 当前页数，默认1
   * <p> pageSize       - Nullable - Str - 每页行数，默认20
   * @return DataPage < T >
   */
  DataPage<T> fullTextSearch(NativeSearchQueryParameter nativeSearchQueryParameter);


  /**
   * 根据字段查询索引分页
   * @param paramMap
   * <p> pageNum        - Nullable - Str - 当前页数，默认1
   * <p> pageSize       - Nullable - Str - 每页行数，默认10
   * <p> 索引的其它字段
   * @return DataPage < T >
   */
//  DataPage<T> fieldSearch(Map<String, String> paramMap);

  /**
   * 增量索引数据
   * @return 更新行数
   */
  int saveIndex(List<Long> ids) ;

  /**
   * 重构全部索引，会全部删除再重构
   * @return 更新行数
   */
  int rebuildAllIndex();


  /**
   * 更新索引 By Id
   * @param ids
   * @return 更新行数
   */
  int updateIndex(List<PK> ids);

  /**
   * 更新索引 By Id
   * @param ids
   * @return 更新行数
   */
  int updateIndex(PK... ids);

  /**
   * 删除索引 By Id
   * @param ids
   * @return 删除行数
   */
  int deleteIndex(List<PK> ids);

  /**
   * 删除索引 By Id
   * @param ids
   * @return 删除行数
   */
  int deleteIndex(PK... ids);
}
