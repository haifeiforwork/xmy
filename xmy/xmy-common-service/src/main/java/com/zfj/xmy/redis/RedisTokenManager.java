package com.zfj.xmy.redis;

import java.nio.charset.Charset;
import java.util.Set;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisTokenManager extends AbstractTokenManager {
  
  
  public static final String WCprefix="WC";   //微信前缀
  

  /**
   * Redis中Key的前缀
   */
  private static final String REDIS_KEY_PREFIX = "AUTHORIZATION_KEY_";

  /**
   * Redis中Token的前缀
   */
  private static final String REDIS_TOKEN_PREFIX = "AUTHORIZATION_TOKEN_";

  /**
   * 登录用户
   */
  private static final String REDIS_LOGIN_PREFIX = "APP_PUSH_REDIS_LOGIN_USER";

  /**
   * Jedis连接池
   */
  protected JedisPool jedisPool;

  public void setJedisPool(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  protected void delSingleRelationshipByKey(byte[] key) {
    byte[] token = getToken(key);
    if (token != null) {
      delete(formatKey(key), formatToken(token));
    }
  }

  @Override
  public void delRelationshipByToken(byte[] token) {
    if (singleTokenWithUser) {
      byte[] key = getKey(token);
      if(key!=null){
        delete(formatKey(key), formatToken(token));
      }
    } else {
      delete(formatToken(token));
    }
  }

  @Override
  protected void createSingleRelationship(byte[] key, byte[] token) {
    byte[] oldToken = get(formatKey(key));
    if (oldToken != null) {
      delete(formatToken(oldToken));
    }
    set(formatToken(token), key, tokenExpireSeconds);
    set(formatKey(key), token, tokenExpireSeconds);

  }

  @Override
  protected void createMultipleRelationship(byte[] key, byte[] token) {
    set(formatToken(token), key, tokenExpireSeconds);
  }

  @Override
  protected byte[] getKeyByToken(byte[] token) {
    return get(formatToken(token));
  }

  @Override
  protected void flushExpireAfterOperation(byte[] key, byte[] token) {
    if (singleTokenWithUser) {
      expire(formatKey(key), tokenExpireSeconds);
    }
    expire(formatToken(token), tokenExpireSeconds);
  }

  @Override
  protected Set<String> getAllUser() {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.smembers(REDIS_LOGIN_PREFIX);
    }
  }

  private byte[] get(byte[] key) {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.get(key);
    }
  }

  private byte[] set(byte[] key, byte[] value, int expireSeconds) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.sadd(REDIS_LOGIN_PREFIX.getBytes(), key);
      return jedis.setex(key, expireSeconds, value).getBytes();
    }
  }

  private void expire(byte[] key, int seconds) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.expire(key, seconds);
    }
  }

  private void delete(byte[]... keys) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.del(keys);
    }
  }

  private byte[] getToken(byte[] key) {
    return get(formatKey(key));
  }

  private byte[] formatKey(byte[] key) {
    return REDIS_KEY_PREFIX.concat(new String(key)).getBytes();
  }

  private byte[] formatToken(byte[] token) {
    return REDIS_TOKEN_PREFIX.concat(new String(token)).getBytes();
  }

  @Override
  public void add(byte[] key, byte[] value) {
    createMultipleRelationship(value, key);
  }

  private String set(byte[] key, byte[] value) {
    try(Jedis jedis = jedisPool.getResource()){
      return jedis.set(key, value);
    }
  }

  private boolean exists(byte[] key) {
    try(Jedis jedis = jedisPool.getResource()){
      return jedis.exists(key);
    }
  }

  /**
   * 添加或修改信息
   *
   * @param key
   * @param json
   *        void
   *
   * @date 2016年12月2日下午4:37:05
   * @author lp
   */
  public void setKey(String key, String json) {
    this.setKey(key, json, tokenExpireSeconds) ;
  }
  
  /**
   * @author dengq
   * @param key
   * @param json
   * @param expireSecond
   */
  public void setKey(String key, String json ,int expireSecond) {
	  if (exists(key.getBytes())) {
		  delete(key.getBytes());
	  }
	  set(key.getBytes(), json.getBytes(Charset.forName("UTF-8")),expireSecond);
  }

  /**
   * 获取信息
   * 
   * @param areaCode
   * @return SysArea
   *
   * @date 2016年12月2日下午4:42:52
   * @author lp
   */
  @SuppressWarnings("rawtypes")
  public Object getKey(String key, Class clazz) {
    byte[] valueByte = get(key.getBytes());
    if (valueByte != null && valueByte.length > 0) {
      String jsonArea = new String(valueByte);
      JSONObject object = JSONObject.fromObject(jsonArea);
      return JSONObject.toBean(object, clazz);
    }
    return null;
  }

  @Override
  public String add(String key, String value) {
    try(Jedis jedis = jedisPool.getResource()){
      return jedis.setex(key,tokenExpireSeconds,value);
    }
  }

  @Override
  public String get(String key) {
    try(Jedis jedis = jedisPool.getResource()){
      return jedis.get(key);
    }
  }


}
