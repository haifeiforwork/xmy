package com.zfj.xmy.redis.cache.manager;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheKey;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.zfj.xmy.redis.cache.Cache;
import com.zfj.xmy.redis.cache.CacheName;

/**
 * <p>Description: redis 缓存包装</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2017-01-19 9:30 </p>
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
@SuppressWarnings({"unchecked","rawtypes"})
public class RedisCacheWrapper implements Cache {

  private org.springframework.cache.Cache delegate;

  private byte[] prefix;

  private RedisOperations redisOperations;

  public RedisCacheWrapper(org.springframework.cache.Cache delegate,
                           byte[] prefix,
                           RedisOperations redisOperations) {
    this.delegate = delegate;
    this.prefix = prefix;
    this.redisOperations = redisOperations;
  }

  public <T extends Serializable> T get(Serializable key, Class<T> type) {
    return delegate.get(key, type);
  }

  public <T extends Serializable> T get(Serializable key) {
    ValueWrapper wrapper = delegate.get(key);
    return wrapper == null? null : (T) wrapper.get();
  }

  public <T extends Serializable> T get(Serializable key, Callable<T> valueLoader) {
    return delegate.get(key, valueLoader);
  }

  public void put(Serializable key, Serializable value) {
    delegate.put(key, value);
  }

  public <T extends Serializable> T putIfAbsent(Serializable key, T value) {
    ValueWrapper wrapper = delegate.putIfAbsent(key, value);
    return wrapper == null? null : (T) wrapper.get();
  }

  public void remove(Serializable key) {
    delegate.evict(key);
  }

  public void clear() {
    delegate.clear();
  }

  public CacheName getName() {
    return CacheName.valueOf(delegate.getName());
  }

  public Object getNativeCache() {
    return delegate.getNativeCache();
  }

  public Long getExpire(Serializable key, TimeUnit timeUnit){
    return redisOperations.getExpire(getTargetKey(key), timeUnit);
  }

  @SuppressWarnings({"unchecked","rawtypes"})
  public Boolean expire(Serializable key, long timeout, TimeUnit timeUnit){
    return redisOperations.expire(getTargetKey(key), timeout, timeUnit);
  }

  public Boolean expireAt(Serializable key, Date expireDate){
    return redisOperations.expireAt(getTargetKey(key), expireDate);
  }

  private Object getTargetKey(Object key){
    RedisCacheKey cacheKey = new RedisCacheKey(key).usePrefix(prefix).withKeySerializer(
        redisOperations.getKeySerializer());
    byte[] keyBytes = cacheKey.getKeyBytes();
    Object targetKey = keyBytes;
    RedisSerializer keySerializer =
        redisOperations.getKeySerializer();
    if(null != keySerializer){
      try {
        targetKey = keySerializer.deserialize(keyBytes);
      } catch (SerializationException ignored) {
      }
    }
    return targetKey;
  }
}
