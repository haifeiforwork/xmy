package com.zfj.xmy.util;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;

/**
 * <p>Description: 基本类型转换工具</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2016-12-06 11:48 </p>
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
public class ConvertUtil {
  private static final ConvertUtilsBean convert = new ConvertUtilsBean();
  static {
    convert.register(false, true, 0);
  }

  /**
   * 类型转换
   * 
   * @param src
   *        需要转换的源对象
   * @param targetClass
   *        转换目标类型，如：Integer、Double等
   * @return 转换失败时返回 Null
   */
  @SuppressWarnings({"unchecked"})
  public static <T> T convert(Object src, Class<T> targetClass) {
    if (targetClass == null)
      return null;
    try {
      return (T) convert.convert(src, targetClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 类型转换
   * 
   * @param src
   *        需要转换的源对象
   * @param defaultValue
   *        转换目标类型失败时的默认值
   * @return 转换失败时返回 defaultValue
   */
  @SuppressWarnings({"unchecked"})
  public static <T> T convert(Object src, T defaultValue) {
    if (defaultValue == null)
      return null;
    try {
      Object afterValue = convert.convert(src, defaultValue.getClass());
      if (afterValue != null) {
        return (T) afterValue;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return defaultValue;
  }


  public static <T> T checkNotNull(Map<String,?> paramMap, String key, String errorMassage,
                                  Class<T> targetClass){
    errorMassage = StringUtils.isEmpty(errorMassage) ? key + " can not be null" : errorMassage;
    Object val = paramMap.get(key);
    Assert.isTrue(null != val && StringUtils.isNotEmpty(val.toString()), errorMassage);
    T re = convert(val, targetClass);
    Assert.notNull(re, errorMassage);
    return re;
  }

  /**
   * 将对象列表转换为以某字段分组的Multimap：Map &lt; 对象分组字段, 对象 &gt;
   * @param list
   * @param keyFunction 得到分组字段的方法
   * @param <ITEM> 对象类型
   * @param <KEY> 对象ID类型
   * @return
   */
  @NotNull
  public static <ITEM, KEY> Multimap<KEY, ITEM> transformMultiMap(@NotNull Collection<ITEM> list,
                                                                  @NotNull Function<ITEM, KEY> keyFunction){
    Multimap<KEY, ITEM> map = ArrayListMultimap.create();
    if(list != null && keyFunction != null){
      for (ITEM item : list) {
        map.put(keyFunction.apply(item), item);
      }
    }
    return map;
  }

  /**
   * 将对象列表转换为以唯一字段分组的Map：Map &lt; 对象分组字段, 对象 &gt;
   * @param list
   * @param keyFunction 得到分组字段的方法
   * @param <ITEM> 对象类型
   * @param <KEY> 对象ID类型
   * @return
   * @throws IllegalArgumentException 当存在相同的唯一字段时
   */
  @NotNull
  public static <ITEM, KEY> Map<KEY, ITEM> transformMap(@NotNull Collection<ITEM> list,
                                                        @NotNull Function<ITEM, KEY> keyFunction){
    Map<KEY, ITEM> map = Maps.newLinkedHashMap();
    if(list != null && keyFunction != null){
      for (ITEM item : list) {
        KEY key = keyFunction.apply(item);
        ITEM exist = map.get(key);
        Assert.isNull(exist, "Multiple entries with same key: " + key + " value: " + item + " and "
                             + exist);
        map.put(key, item);
      }
    }
    return map;
  }
}
