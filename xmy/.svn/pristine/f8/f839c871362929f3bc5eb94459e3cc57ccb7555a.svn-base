package com.zfj.xmy.redis.cache.manager;


import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.zfj.xmy.redis.cache.Cache;
import com.zfj.xmy.redis.cache.CacheName;
import com.zfj.xmy.redis.cache.DefaultCacheManager;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * <p>Description: redis缓存管理</p>
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
public class DefaultRedisCacheManager extends RedisCacheManager implements DefaultCacheManager {

  public DefaultRedisCacheManager(RedisOperations redisOperations) {
    super(redisOperations);
  }

  @Override
  public void afterPropertiesSet() {
    buildCacheExpires();
    RedisSerializer redisSerializer =
        getRedisOperations().getKeySerializer();
    if(checkStringSerializer(redisSerializer)){
      prefix = new DefaultRedisCachePrefix();
      setCachePrefix(prefix);
      setUsePrefix(true);
    }
    super.afterPropertiesSet();
  }

  private RedisCachePrefix prefix = null;

  private final Map<String, Cache> cacheMap = Maps.newConcurrentMap();

  public Cache getDefaultCache() {
    return getCacheFromCacheMap(DEFAULT_CACHE_NAME);
  }

  public Cache getCache(CacheName cacheName){
    return null == cacheName ? getDefaultCache() : getCacheFromCacheMap(cacheName.toString());
  }

  public String getManagerName() {
    return "defaultRedisCacheManager";
  }

  private void buildCacheExpires() {
    Map<String, Long> expiresMap = Maps.newConcurrentMap();
    expiresMap.put(DEFAULT_CACHE_NAME, 0L);
    for (CacheName name : CacheName.values()) {
      expiresMap.put(name.toString(), name.getDefaultExpiresSecond());
    }
    setExpires(expiresMap);
    setCacheNames(expiresMap.keySet());
  }

  private Cache getCacheFromCacheMap(String cacheName){
    synchronized (cacheMap){
      Cache cache = cacheMap.get(cacheName);
      if(null == cache){
        org.springframework.cache.Cache springCache = super.getCache(cacheName);
        if(null != springCache){
          cache = new RedisCacheWrapper(springCache, null == prefix ? null : prefix.prefix(cacheName),
                                        getRedisOperations());
          cacheMap.put(cacheName, cache);
        }
      }
      return cache;
    }
  }

  private boolean checkStringSerializer(RedisSerializer redisSerializer){
    try {
      if(null != redisSerializer) {
        Type type =
            TypeToken.of(redisSerializer.getClass()).getSupertype(RedisSerializer.class).getType();
        if (null != type && type.getTypeName().contains(String.class.getName())) {
          return true;
        }
      }
    } catch (Exception ignored) {
    }
    return false;
  }
}
