package com.zfj.xmy.redis.cache;


import com.zfj.xmy.util.SpringContextUtil;

/**
 * <p>Description: 缓存工厂</p>
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
 * @author <a href="drakelee1221@gmail.com">ligeng</a>
 * @version 1.0.0
 */
public class CacheFactory {

  public static class Holder {
    private Holder(){}
    private static DefaultCacheManager _defaultCacheManager =
        SpringContextUtil.getBean("defaultCacheManager", DefaultCacheManager.class);
  }
  public static DefaultCacheManager getDefaultCacheManager() {
    return Holder._defaultCacheManager;
  }

  /**
   * 得到默认缓存对象(缓存时间为永久)
   *
   * @return Cache
   */
  public static Cache getDefaultCache() {
    return getDefaultCacheManager().getDefaultCache();
  }

  /**
   * 根据缓存名称得到对应的缓存对象
   * @param cacheName
   * @return
   */
  public static Cache getCache(CacheName cacheName) {
    return getDefaultCacheManager().getCache(cacheName);
  }
}
