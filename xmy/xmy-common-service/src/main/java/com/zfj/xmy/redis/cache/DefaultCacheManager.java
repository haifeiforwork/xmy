package com.zfj.xmy.redis.cache;

import java.util.Collection;

/**
 * <p>Description: 默认缓存管理接口</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2017-01-16 16:12 </p>
 * <p>Modification Record 1: </p>
 * <pre>
 *  Modified Date：
 *  Version：
 *  Modifier：
 *  Modification Content：
 * </pre>
 * <p>Modification Record 2：…</p>
 *
 * @author <a href="wubin3347@gmail.com">wub</a>
 * @version 1.0.0
 */
public interface DefaultCacheManager {

  /**
   * 默认缓存名称，保存的永久不失效
   */
  String DEFAULT_CACHE_NAME = "SCH_DEFAULT_CACHE";

  /**
   * 得到默认缓存对象(缓存时间为永久)
   *
   * @return
   */
  Cache getDefaultCache();

  Cache getCache(CacheName cacheName);

  Collection<String> getCacheNames();

}
