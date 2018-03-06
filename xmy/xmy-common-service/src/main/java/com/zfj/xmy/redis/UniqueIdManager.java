package com.zfj.xmy.redis;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Random;

/**
 * <p>Description: 幂等性唯一ID管理</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2016-12-21 14:43 </p>
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
public class UniqueIdManager {

  public static final String HEADER_UNIQUE_ID_KEY = "uniqueId_";

  public static final ThreadLocal<String> currentInvokeUniqueId =
      new InheritableThreadLocal<>(); // 保存每次请求的唯一ID

  private static final long lockTimeout = 5000;

  private static final String split = "|";

  public static final Random random = new Random();

  @Value("${sch.retry.uniqueTimeout:300000}")
  private int uniqueTimeout;
  /**
   * Jedis连接池
   */
  protected JedisPool jedisPool;

  public void setJedisPool(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 将uniqueId放入redis中
   * @param uniqueId
   * @return 成功返回 RUNNING
   */
  public String putRunning(String uniqueId, String serverHost){
    String insert = UniqueStatus.RUNNING.toString() + split + serverHost;
    try (Jedis jedis = jedisPool.getResource()) {
      String key = HEADER_UNIQUE_ID_KEY + uniqueId;
//      long begin = System.currentTimeMillis();
      try {
//        while ((System.currentTimeMillis() - begin) < lockTimeout) {
          jedis.watch(key);
          String val = jedis.get(key);
          if(val == null){
            Transaction tran = jedis.multi();
            tran.setex(key, uniqueTimeout / 1000 , insert);
            if(tran.exec() != null){
              return insert;
            }
          }
//          Thread.sleep(3, random.nextInt(500));
//        }
      } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalArgumentException("UniqueId ( "+ uniqueId +" ) was locked! ", e);
      }finally {
        jedis.unwatch();
      }
    }
    return null;
  }

  /**
   * 查看uniqueId是否存在
   * @param uniqueId
   * @return
   */
  public String existUniqueId(String uniqueId){
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.get(HEADER_UNIQUE_ID_KEY+uniqueId);
    }
  }

  public enum UniqueStatus{
    RUNNING,

    SUCCESS,

    FAIL
  }
}
