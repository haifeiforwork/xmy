package com.zfj.xmy.redis;

import java.util.Set;

import com.zfj.base.exception.BusinessException;

public abstract class AbstractTokenManager implements TokenManager {

  protected int tokenExpireSeconds = 7 * 24 * 3600;

  protected boolean singleTokenWithUser = false;

  protected boolean flushExpireAfterOperation = true;

  public void setTokenExpireSeconds(int tokenExpireSeconds) {
    this.tokenExpireSeconds = tokenExpireSeconds;
  }

  public void setSingleTokenWithUser(boolean singleTokenWithUser) {
    this.singleTokenWithUser = singleTokenWithUser;
  }

  public void setFlushExpireAfterOperation(boolean flushExpireAfterOperation) {
    this.flushExpireAfterOperation = flushExpireAfterOperation;
  }

  @Override
  public Set<String> getAllLoginUser() {
    return getAllUser();
  }

  @Override
  public void delRelationshipByKey(byte[] key) {
    // 如果是多个Token关联同一个Key，不允许直接通过Key删除所有Token，防止误操作
    if (!singleTokenWithUser) {
      throw new BusinessException("非单点登录时无法调用该方法");
    }
    delSingleRelationshipByKey(key);
  }

  /**
   * 一个用户只能绑定一个Token时通过Key删除关联关系
   * 
   * @param key
   */
  protected abstract void delSingleRelationshipByKey(byte[] key);

  @Override
  public void createRelationship(byte[] key, byte[] token) {
    // 根据设置的每个用户是否只允许绑定一个Token，调用不同的方法
    if (singleTokenWithUser) {
      createSingleRelationship(key, token);
    } else {
      createMultipleRelationship(key, token);
    }
  }

  /**
   * 一个用户可以绑定多个Token时创建关联关系
   * 
   * @param key
   * @param token
   */
  protected abstract void createMultipleRelationship(byte[] key, byte[] token);

  /**
   * 一个用户只能绑定一个Token时创建关联关系
   * 
   * @param key
   * @param token
   */
  protected abstract void createSingleRelationship(byte[] key, byte[] token);

  @Override
  public byte[] getKey(byte[] token) {
    byte[] key = getKeyByToken(token);
    // 根据设置，在每次有效操作后刷新过期时间
    if (key != null && flushExpireAfterOperation) {
      flushExpireAfterOperation(key, token);
    }
    return key;
  }

  /**
   * 通过Token获得Key
   * 
   * @param token
   * @return
   */
  protected abstract byte[] getKeyByToken(byte[] token);

  /**
   * 在操作后刷新Token的过期时间
   * 
   * @param key
   * @param token
   */
  protected abstract void flushExpireAfterOperation(byte[] key, byte[] token);

  /**
   * 获取登录信息
   * 
   * @return
   */
  protected abstract Set<String> getAllUser();

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
  @SuppressWarnings("rawtypes")
  public abstract Object getKey(String key, Class clazz);

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
  public abstract void setKey(String key, String json);
}
