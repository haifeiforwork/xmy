package com.zfj.xmy.cms.web.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.oss.OSSManager;
import com.zfj.xmy.util.ConvertUtil;
import com.zfj.xmy.util.JsonUtils;

/**
 * <p>Description: 用户文件管理</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved.</p>
 * <p>Other: </p>
 * <p>Date：2016-12-28 13:24 </p>
 * <p>Modification Record 1: </p>
 * <pre>
 *  Modified Date：
 *  Version：
 *  Modifier：
 *  Modification Content：
 * </pre>
 * <p>Modification Record 2：…</p>
 *
 * @author <a href="drakelee1221@mail.com">ligeng</a>
 * @version 1.0.0
 */
@RestController
public class UserOssController {

  private static final Logger logger = LoggerFactory.getLogger(UserOssController.class);

  @Value("${oss-callback}")
  private String ossCallback;
  
  @Value("${oss-OSS_ImgPath}")
  private String ossImgPath;

  @Value("${oss-dynamic-prefix}")
  private String[] ossDynamicPrefixes;

  @Autowired
  private OSSManager ossManager;

  private static final String filePrefix = "/";
  
  private static final String REGION_CN_HANGZHOU = "cn-hangzhou";
  private static final String STS_API_VERSION = "2015-04-01";

  /**
   * 获得上传OSS的鉴权信息
   * <p>前端直接上传OSS前，先调用此接口获得鉴权信息
   * <p>详细信息请参考：
   * <a href="https://help.aliyun.com/document_detail/31927.html?spm=5176.doc32015.6.624.nH50wK">《服务端签名直传并设置上传回调》</a>
   *
   * @param paramMap -
   * <p> dir        - Nullable - Str - 文件路径，可空
   * <p> prefix     - Nullable - Str - 文件路径前缀，可空
   *
   * @return Map&lt;String, String&gt;
   * <p>accessid，callback，dir，expire，host，policy，signature
   * <p> dir = /[prefix]/[userId]/[dir]
   */
  @RequestMapping(path = "/oss/getWebUploadParams", method = RequestMethod.GET,
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Map<String, String>> getWebUploadParamsFromUser(
      @RequestParam Map<String,String> paramMap) {
    String userId = "" ;
//    String userId = getUserId();
//    Assert.notNull(userId, "您尚未登录，请先登录");
    String dir = ConvertUtil.convert(paramMap.get("dir"), "");
    String prefix = paramMap.get("prefix");
    paramMap.remove("dir");
    paramMap.remove("prefix");
    return new ResponseEntity<>(
        ossManager.getWebUploadParams(
            ossCallback,
            checkPrefix(prefix) + userId + checkDir(dir),
            ossManager.getBucketUrl(),
            paramMap
        ),
        HttpStatus.OK);
  }


  /**
   * 获得当前用户文件列表
   * @param dir 文件目录
   * @return Map&lt;String, String&gt;
   * <p>host，dir，files
   */
  @RequestMapping(path = "/oss/getUserFiles", method = RequestMethod.GET,
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<ObjectNode> getUserFiles(String dir, String nextMarker) {
    String userId = "" ;
    //Assert.notNull(userId, "您尚未登录，请先登录");
    String preDir = checkDir(dir);
    String userDir = userId + preDir;
    ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossManager.getBucket());
    listObjectsRequest.setDelimiter(filePrefix);
    listObjectsRequest.setPrefix(userDir);
    listObjectsRequest.setMaxKeys(1000);
    if(StringUtils.isNotBlank(nextMarker)){
      listObjectsRequest.setMarker(nextMarker);
    }
    ObjectListing list = ossManager.getOSSClient().listObjects(listObjectsRequest);
    List<String> files = Lists.newArrayList(list.getCommonPrefixes());
    for (OSSObjectSummary ossObjectSummary : list.getObjectSummaries()) {
      if( ossObjectSummary != null && !userDir.equals(ossObjectSummary.getKey()) ){
        files.add(ossObjectSummary.getKey());
      }
    }
    return new ResponseEntity<>(
        JsonUtils.mapper.createObjectNode().put("host", ossManager.getBucketUrl())
                        .put("dir", preDir).putPOJO("files", files)
                        .put("nextMarker", list.getNextMarker()), HttpStatus.OK);
  }

  @RequestMapping(path = "/oss/createDir", method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<ObjectNode> createDir(@RequestBody Map<String,String> map){
    String dir = map.get("dir");
    String userId = "";
    //Assert.notNull(userId, "您尚未登录，请先登录");
    dir = checkDir(dir);
    Assert.isTrue(dir.length() > 1, "文件夹名不能为空");
    dir = userId + dir;
    try(InputStream ip = new ByteArrayInputStream(new byte[]{})){
      PutObjectResult result =
          ossManager.getOSSClient().putObject(ossManager.getBucket(), dir, ip);
      Assert
          .isTrue(result != null && StringUtils.isNotBlank(result.getRequestId()), dir + " 文件夹创建失败！");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(JsonUtils.mapper.createObjectNode()
                                                .put("host", ossManager.getBucketUrl())
                                                .put("dir", dir), HttpStatus.CREATED);
  }

  /**
   * 删除当前用户文件或文件夹
   * @param map
   * <p>file 文件目录或文件夹
   */
  @RequestMapping(path = "/oss/delUserFile", method = RequestMethod.DELETE,
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity delUserFile(@RequestBody Map<String,String> map) {
    String file = map.get("file");
//    String userId = getUserId();
    String userId = "";
    //Assert.notNull(userId, "您尚未登录，请先登录");
    Assert.isTrue(!Strings.isNullOrEmpty(file));
    if (file.startsWith("/")) {
      file = file.substring(0, 1);
    }
    file = file.replaceAll(userId, ""); //删除文件的userId
    file = file.replaceAll(ossManager.getBucketUrl(), ""); //删除文件的userId
    List<String> keys = Lists.newArrayList();
    if (file.endsWith("/")) {
      ObjectListing list =
          ossManager.getOSSClient().listObjects(ossManager.getBucket(), userId + file);
      keys.addAll(
          Lists.transform(list.getObjectSummaries(), new Function<OSSObjectSummary, String>() {
            public String apply(@Nullable OSSObjectSummary ossObjectSummary) {
              return ossObjectSummary == null ? null : ossObjectSummary.getKey();
            }
          }));
      keys.addAll(list.getCommonPrefixes());
    }
    keys.add(userId + file);
    ossManager.getOSSClient()
              .deleteObjects(new DeleteObjectsRequest(ossManager.getBucket()).withKeys(keys));
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  /**
   * OSS的回调验证接口
   * <p>前端直接上传OSS提交后阻塞等待，OSS端回调此接口验证后返回信息给前端
   * <p>详细信息请参考：
   * <a href="https://help.aliyun.com/document_detail/31927.html?spm=5176.doc32015.6.624.nH50wK">《服务端签名直传并设置上传回调》</a>
   */
  @RequestMapping(path = "/oss/callback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Map<String, String>> callback(HttpServletRequest request) {
    Map<String, String> result = ossManager.ossWebCallback(request);
    if (result.get("status").equals("ok")) {
      String userExtras = result.get("userExtras");
      if (StringUtils.isNotBlank(userExtras)) {
        logger.info("OSS回调，userExtras = " + userExtras);
      }
      return new ResponseEntity<>(result, HttpStatus.OK);
    }else {
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }

//  private String getUserId() {
//    String userId = null;
////    try {
////      UserEmployeeDto empUser = UserSessionManager.SESSION.getUserEmployee();
////      if (empUser != null) {
////        userId = empUser.getUserId() + "/";
////      }
////    } catch (Exception ignored) {
////    }
////    try {
////      UserToken appUser = UserSessionManager.SESSION.getAppUserId();
////      if (appUser != null) {
////        userId = appUser.getUserId() + "/";
////      }
////    } catch (Exception ignored) {
////    }
//    return userId;
//  }

  private String checkDir(String dir) {
    if (Strings.isNullOrEmpty(dir)) {
      return "";
    }
    if(dir.contains("//")){
      return checkDir(dir.replaceAll("//","/"));
    }
    if (dir.startsWith("/")) {
      dir = dir.substring(1);
    }
    if (!dir.endsWith("/")) {
      dir += "/";
    }
    return dir;
  }

  private String checkPrefix(String prefix){
    if(StringUtils.isNotBlank(prefix)){
      for (String ossDynamicPrefix : ossDynamicPrefixes) {
        if(ossDynamicPrefix.equalsIgnoreCase(prefix)){
          return checkDir(prefix);
        }
      }
    }
    return "";
  }



















  @RequestMapping(path = "/oss/ossAuthentication", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity getAppOSSauthentication(){
    // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
    // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
    // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
    String accessKeyId ="LTAIrP6lyUiJIHtU";
    String accessKeySecret = "NvjJhK6s5BtudHuAQr6GmG6EMaQiCj";
    // RoleArn 需要在 RAM 控制台上获取
    String roleArn = "acs:ram::1714848006241960:role/aliyunosstokengeneratorrole";
    long durationSeconds =900;   //Token的失效时间，注意，最少是900s
    String policy ="{\"Statement\": [{\"Action\": [\"oss:*\"],\"Effect\": \"Allow\",\"Resource\": [\"acs:oss:*:*:*\"]}],\"Version\": \"1\"}";    //oss请求参数
    // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
    // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
    // 具体规则请参考API文档中的格式要求
    String roleSessionName = "alice-001";
    // 此处必须为 HTTPS
    ProtocolType protocolType = ProtocolType.HTTPS;
    try {
      final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
          policy, protocolType, durationSeconds);
      Map<String, String> respMap = new LinkedHashMap<String, String>();
      respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
      respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
      respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
      respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
      respMap.put("OSS_BucketName", ossManager.getBucket()); 
      respMap.put("OSS_Url", ossManager.getEndpoint()); 
      respMap.put("OSS_Callback", ossCallback); 
      respMap.put("OSS_ImgPath", ossImgPath);   //图片上传路径
      return new ResponseEntity(respMap, HttpStatus.OK);
    } catch (Exception e) {
      throw new BusinessException("请求OSS出错");
    }
  }
  
  private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
      String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws ClientException 
  {
    // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
    IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
    DefaultAcsClient client = new DefaultAcsClient(profile);
    // 创建一个 AssumeRoleRequest 并设置请求参数
    final AssumeRoleRequest request = new AssumeRoleRequest();
    request.setVersion(STS_API_VERSION);
    request.setMethod(MethodType.POST);
    request.setProtocol(protocolType);
    request.setRoleArn(roleArn);
    request.setRoleSessionName(roleSessionName);
    request.setPolicy(policy);
    request.setDurationSeconds(durationSeconds);
    // 发起请求，并得到response
    final AssumeRoleResponse response = client.getAcsResponse(request);
    return response;
  }


  /**
   * 带特殊header的，获得上传OSS的鉴权信息
   * <p>前端直接上传OSS前，先调用此接口获得鉴权信息
   * <p>详细信息请参考：
   * <a href="https://help.aliyun.com/document_detail/31927.html?spm=5176.doc32015.6.624.nH50wK">《服务端签名直传并设置上传回调》</a>
   *
   * @param paramMap -
   * <p> dir        - Nullable - Str - 文件路径，可空
   * <p> prefix     - Nullable - Str - 文件路径前缀，可空
   *
   * @return Map&lt;String, String&gt;
   * <p>accessid，callback，dir，expire，host，policy，signature
   * <p> dir = /[prefix]/[dir]
   */
  @RequestMapping(path = "/oss/getWebUploadParamsFromPwd", method = RequestMethod.GET,
                  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                  headers = "pwd=7002e04a01a94c6785e69d82e63d50a4")
  public ResponseEntity<Map<String, String>> getWebUploadParamsFromPwd(
      @RequestParam Map<String,String> paramMap) {
    String dir = ConvertUtil.convert(paramMap.get("dir"), "");
    String prefix = paramMap.get("prefix");
    paramMap.remove("dir");
    paramMap.remove("prefix");
    return new ResponseEntity<>(
        ossManager.getWebUploadParams(
            ossCallback,
            checkPrefix(prefix) + checkDir(dir),
            ossManager.getBucketUrl(),
            paramMap
        ),
        HttpStatus.OK);
  }

}

