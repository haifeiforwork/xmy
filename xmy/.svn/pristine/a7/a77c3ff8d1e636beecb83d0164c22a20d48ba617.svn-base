package com.zfj.xmy.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * 
 * <p>File Name: MoreAwareObjectMapper.java</p>
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
 * @author <a href="drakelee1221@gmail.com">ligeng</a>
 * @version 1.0.0
 */
public class MoreAwareObjectMapper extends ObjectMapper {
  private static final long serialVersionUID = 1L;

  public MoreAwareObjectMapper() {

    // this.registerModule(new GuavaModule());
    // this.registerModule(new JodaModule());

    this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
    this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }
}
