package com.zfj.xmy.redis.cache;


/**
 * <p>Description: 缓存名，对应失效时间</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2017-01-16 16:12 </p>
 * <p>Modification Record 1: </p>
 * 
 * <pre>
 *  Modified Date：
 *  Version：
 *  Modifier：
 *  Modification Content：
 * </pre>
 * 
 * <p>Modification Record 2：…</p>
 *
 * @author <a href="wubin3347@gmail.com">wub</a>
 * @version 1.0.0
 */
public enum CacheName {

  /**
   * 验证码缓存，5分钟失效
   */
  VERIFY_CODE(300),

  /**
   * 商家退款短信验证码缓存，5分钟失效
   */
  REFUND_VERIFY_CODE(300), WIDTH(300);



  /**
   * 默认失效时间，单位秒，不失效为 0
   */
  long defaultExpiresSecond;

  public long getDefaultExpiresSecond() {
    return defaultExpiresSecond;
  }

  CacheName(long cacheExpiresSecond) {
    this.defaultExpiresSecond = cacheExpiresSecond;
  }

}
