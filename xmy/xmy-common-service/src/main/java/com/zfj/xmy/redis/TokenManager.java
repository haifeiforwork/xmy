package com.zfj.xmy.redis;

import java.util.Set;

public interface TokenManager {
  /**
   * 通过key删除关联关系
   * 
   * @param key
   */
  void delRelationshipByKey(byte[] key);

  /**
   * 通过token删除关联关系
   * 
   * @param token
   */
  void delRelationshipByToken(byte[] token);

  /**
   * 创建关联关系
   * 
   * @param key
   * @param token
   */
  void createRelationship(byte[] key, byte[] token);

  /**
   * 通过token获得对应的key
   * 
   * @param token
   * @return
   */
  byte[] getKey(byte[] token);

  /**
   * 获取当前登录的所有用户
   * 
   * @return
   */
  Set<String> getAllLoginUser();


  void add(byte[] key, byte[] value);
  
  
  String add(String key, String value);

  /**
   * 获取信息
   *
   * @param key
   * @param clazz
   * @return Object
   *
   * @date 2016年12月2日下午4:50:34
   * @author lp
   */
  public Object getKey(String key, Class<?> clazz);

  /**
   * 添加或修改信息
   *
   * @param key
   * @param json
   *        void
   *
   * @date 2016年12月2日下午4:50:23
   * @author lp
   */
  public void setKey(String key, String json);
  
  
  public String get(String key);
}
