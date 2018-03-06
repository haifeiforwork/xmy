package com.zfj.xmy.redis.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Key-Value 缓存接口</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2017-01-18 13:14 </p>
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
public interface Cache{

  /** 获取 cache 名称 */
  CacheName getName();

  /** 获取真正的缓存提供方案 */
  Object getNativeCache();

  /** 从缓存中获取 key 对应的指定类型的值*/
  <T extends Serializable> T get(Serializable key, Class<T> type);

  /** 从缓存中获取 key 对应的值*/
  <T extends Serializable> T get(Serializable key);

  /**
   * 从缓存中获取 key 对应的值，如果缓存没有命中，则添加缓存，
   * 此时可异步地从 valueLoader 中获取对应的值，
   * 注意：添加并未设置默认失效时间，该{@code  Callable#call}的返回值会一直存在于缓存中
   */
  @Deprecated
  <T extends Serializable> T get(Serializable key, Callable<T> valueLoader);

  /** 缓存 key-value，如果缓存中已经有对应的 key，则替换其 value */
  void put(Serializable key, Serializable value);

  /**
   * 缓存 key-value，如果缓存中已经有对应的 key，则返回已有的 value，不做替换，
   * 如果缓存中沒有对应的 key，则put新的值，返回null
   * <pre><code>
   * Object existingValue = cache.get(key);
   * if (existingValue == null) {
   *     cache.put(key, value);
   *     return null;
   * } else {
   *     return existingValue;
   * }
   * </code></pre>
   * @param key
   * @param value
   * @return
   */
  <T extends Serializable> T putIfAbsent(Serializable key, T value);

  /** 从缓存中移除对应的 key */
  void remove(Serializable key);

  /** 清空缓存 */
  void clear();

  /** 获取缓存中key-value的剩余失效时间*/
  Long getExpire(Serializable key, TimeUnit timeUnit);

  /** 设置缓存中key-value的失效时间 */
  Boolean expire(Serializable key, long timeout, TimeUnit timeUnit);

  /** 设置缓存中key-value在expireDate时间点失效 */
  Boolean expireAt(Serializable key, Date expireDate);
}
