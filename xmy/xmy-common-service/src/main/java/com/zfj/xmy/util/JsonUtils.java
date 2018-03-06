package com.zfj.xmy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 
 * <p>File Name: JsonUtils.java</p>
 * <p>Description: TODO</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved. </p>
 * <p>Other: </p>
 * <p>Date：Dec 3, 2016</p>
 * <p>Modification Record 1: </p>
 * 
 * <pre>
 *    Modified Date：
 *    Version：
 *    Modifier：
 *    Modification Content：
 * </pre>
 * 
 * <p>Modification Record 2：…</p>
 * 
 * @author <a href="wubin3347@gmail.com">wub</a>
 * @version 1.0.0
 */
public class JsonUtils {
  public static final ObjectMapper mapper = new MoreAwareObjectMapper();

  public static String toString(Object value) throws IOException {
    return mapper.writeValueAsString(value);
  }

  public static void write(java.io.Writer w, Object value) throws IOException {
    mapper.writeValue(w, value);
  }

  public static void write(java.io.OutputStream out, Object value) throws IOException {
    mapper.writeValue(out, value);
  }


  /**
   * JSON字符串转对象
   * @param content json字符串
   * @param valueType 目标对象类型
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> T reader(String content, Class<T> valueType) throws IOException {
    return mapper.readValue(content, valueType);
  }

  /**
   * JSON字符串转对象
   * @param src json字符串
   * @param valueTypeRef 目标对象类型
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> T reader(String src, TypeReference<T> valueTypeRef) throws IOException {
    return mapper.readValue(src, valueTypeRef);
  }

  /**
   * 转换实体对象
   * @param fromValue 实体对象，非JSON字符串
   * @param valueType 目标对象类型
   * @param <T>
   * @return
   */
  public static <T> T convert(Object fromValue, Class<T> valueType){
    return mapper.convertValue(fromValue, valueType);
  }

  /**
   * 转换实体对象
   * @param fromValue 实体对象，非JSON字符串
   * @param valueTypeRef 目标对象类型
   * @param <T>
   * @return
   */
  public static <T> T convert(Object fromValue, TypeReference<T> valueTypeRef){
    return mapper.convertValue(fromValue, valueTypeRef);
  }

}
