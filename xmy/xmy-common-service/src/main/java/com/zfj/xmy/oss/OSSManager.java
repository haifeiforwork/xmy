package com.zfj.xmy.oss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zfj.xmy.util.JsonUtils;


/**
 * OSS工具类
 * <p>File Name: yuan li</p>
 * <p>Description: TODO</p>
 * <p>Copyright(c) 2015-2016 cadyd.com Inc. All Rights Reserved. </p>
 * <p>Other: </p>
 * <p>Date：2016年12月1日</p>
 * <p>Modification Record 1: </p>
 * <p>
 * <pre>
 * Modified Date：
 * Version：
 * Modifier：
 * Modification Content：
 * </pre>
 * <p>
 * <p>Modification Record 2：…</p>
 *
 * @author <a href="287616944@qq.com">yuan li</a>
 * @version 1.0.0
 */
@Component
public class OSSManager {
  private static final Logger logger = LoggerFactory.getLogger(OSSManager.class);
  @Value("${protocol}${endpoint}")
  private String endpoint;
  @Value("${accessId}")
  private String accessId;
  @Value("${accessKey}")
  private String accessKey;
  @Value("${bucket}")
  private String bucket;
  private OSSClient ossClient;
  @Value("${oss.conf.requestTimeout}")
  private String requestTimeout ;

  private boolean initialization;
  /*
   * private OSSManager() {
   * init();
   * };
   */

  public String getBucketUrl(){
    String endpointStr = endpoint.replace("http://","").replace("https://","");
    return "https://"+bucket+"."+endpointStr;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getBucket() {
    return bucket;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public void setAccessId(String accessId) {
    this.accessId = accessId;
  }

  public String getAccessId() {
    return accessId;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public void setBucket(String bucket) {
    this.bucket = bucket;
  }

  /*
   * public static OSSManager newBuilder() {
   * return ossManager;
   * }
   */

  public synchronized void init() {
    if(initialization){
      return;
    }
    /*
     * endpoint = PropertiesUtil.PROP.getValue("endpoint");
     * accessId = PropertiesUtil.PROP.getValue("accessId");
     * accessKey = PropertiesUtil.PROP.getValue("accessKey");
     * bucket = PropertiesUtil.PROP.getValue("bucket");
     */
    //配置超时时间
    requestTimeout = (String) (null == requestTimeout || "0".equals(requestTimeout) ? ClientConfiguration.DEFAULT_REQUEST_TIMEOUT : requestTimeout) ;
    ClientConfiguration configuration = new ClientConfiguration() ;
    configuration.setRequestTimeoutEnabled(true) ;
    configuration.setRequestTimeout(Integer.valueOf(requestTimeout)) ;
    ossClient = new OSSClient(endpoint, accessId, accessKey,configuration) ;
    try {
      ossClient.getBucketAcl(bucket);
      initialization = true;
    } catch (OSSException e) {
      String errorCode = e.getErrorCode();
      if (OSSErrorCode.INVALID_ACCESS_KEY_ID.equals(errorCode)) {
        Assert.isTrue(false, "请指定正确的accessId。\n");
      } else if (OSSErrorCode.SIGNATURE_DOES_NOT_MATCH.equals(errorCode)) {
        Assert.isTrue(false, "请指定正确的accessKey。");
      } else if (OSSErrorCode.NO_SUCH_BUCKET.equals(errorCode)) {
        Assert.isTrue(false, "请指定一个存在的Bucket。");
      } else {
        Assert.isTrue(false, errorCode);
      }
    } catch (ClientException e) {
      e.printStackTrace();
    }
  }

  public OSSClient getOSSClient() {
    return this.ossClient;
  }


  /**
   * web页面上传图片前，获取鉴权数据
   *
   * @param callbackUrl OSS web 回调地址
   * @param dir         文件路径
   * @param host        重新定义请求地址，可以为null，默认值是：http://schimages.oss-cn-hangzhou.aliyuncs.com
   * @return Map<String,String>
   * @date 2016年12月1日下午3:37:45
   * @author yuan li
   */
  public Map<String, String> getWebUploadParams(String callbackUrl, String dir, String host, Map<String,String> extras) {
    Assert.hasText(callbackUrl, "回调地址为空");
    long expireTime = 60;
    long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
    Date expiration = new Date(expireEndTime);
    PolicyConditions policyConds = new PolicyConditions();
    policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
    policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
    logger.info("OSS请求上传鉴权，host = {}，callbackUrl = {}，dir = {}，extras = {}",
                host, callbackUrl, dir, extras);
    String postPolicy = this.ossClient.generatePostPolicy(expiration, policyConds);
    Map<String, String> respMap = new LinkedHashMap<String, String>();
    try {
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String postSignature = this.ossClient.calculatePostSignature(postPolicy);
      respMap.put("accessid", accessId);
      respMap.put("policy", encodedPolicy);
      respMap.put("signature", postSignature);
      respMap.put("dir", dir);
      respMap.put("host", host);
      if (StringUtils.isEmpty(host)) {
        respMap.put("host", getBucketUrl());
      }
      respMap.put("expire", String.valueOf(expireEndTime / 1000));

      String callback_param = buildCallbackBody(callbackUrl, extras);
      byte[] callbackBytes = callback_param.getBytes("UTF-8");
      String callback = BinaryUtil.toBase64String(callbackBytes);
      respMap.put("callback", callback);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return respMap;
  }

  private String buildCallbackBody(String callbackUrl, Map<String,String> extras){
    StringBuffer sb = new StringBuffer("{\"callbackUrl\":\"")
        .append(callbackUrl)
        .append("\",\"callbackBody\":\"{'userExtras':'");
    if(extras != null && !extras.isEmpty()){
      try {
        sb.append(URLEncoder.encode(JsonUtils.toString(extras), "UTF-8"));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    sb.append("','etag'=${etag},'bucket'=${bucket},'filename'=${object},'size'=${size},'mimeType'=${mimeType}}\",")
      .append("\"callbackBodyType\":\"application/json\"}");
    return sb.toString();
  }

  /**
   * OSS web 回调方法
   *
   * @param request
   * @return Map<String,String>
   * @date 2016年12月1日下午3:18:27
   * @author yuan li
   */
  public Map<String, String> ossWebCallback(HttpServletRequest request) {
    Map<String, String> result = new HashMap<String, String>();
    result.put("status", "verdify not ok");
    try {
      String length = request.getHeader("content-length");
      if(StringUtils.isBlank(length)){
        return result;
      }
      String ossCallbackBody = GetPostBody(request.getInputStream(),
                                           Integer.parseInt(request.getHeader("content-length")));
      logger.info("OSS回调：" + ossCallbackBody);
      boolean flag = VerifyOSSCallbackRequest(request, ossCallbackBody);
      if (flag) { // 验证成功处理
        JSONObject callbackJson = JSONObject.fromObject(ossCallbackBody);
        String url = getBucketUrl() + callbackJson.get("filename");
        result.put("status", "ok");
        result.put("url", url);
        Object userExtras = callbackJson.get("userExtras");
        if(userExtras != null && StringUtils.isNotBlank(userExtras.toString())){
          result.put("userExtras", URLDecoder.decode(userExtras.toString(), "UTF-8"));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }



  private void response(HttpServletRequest request, HttpServletResponse response, String results,
                        int status) throws IOException {
    String callbackFunName = request.getParameter("callback");
    response.addHeader("Content-Length", String.valueOf(results.length()));
//    response.setHeader("Content-type", "text/plane;charset=UTF-8");
    if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
      response.getWriter().println(results);
    else
      response.getWriter().println(callbackFunName + "( " + results + " )");
    response.setStatus(status);
    response.flushBuffer();
  }

  private boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody)
      throws NumberFormatException, IOException {
    boolean ret = false;
    String autorizationInput = new String(request.getHeader("Authorization"));
    String pubKeyInput = request.getHeader("x-oss-pub-key-url");
    byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
    byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
    String pubKeyAddr = new String(pubKey);
    if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr
        .startsWith("https://gosspublic.alicdn.com/")) {
      return false;
    }
    String retString = executeGet(pubKeyAddr);
    retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
    retString = retString.replace("-----END PUBLIC KEY-----", "");
    String queryString = request.getQueryString();
    String uri = request.getRequestURI();
    String decodeUri = URLDecoder.decode(uri, "UTF-8");
    String authStr = decodeUri;
    if (queryString != null && !queryString.equals("")) {
      authStr += "?" + queryString;
    }
    authStr += "\n" + ossCallbackBody;
    ret = doCheck(authStr, authorization, retString);
    return ret;
  }

  private String executeGet(String url) {
    BufferedReader in = null;

    String content = null;
    try {
      // 定义HttpClient
      // 首先设置全局的标准cookie策略
//      RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
      RequestConfig config = RequestConfig.custom().setCookieSpec("").build();
      CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(config).build();
      // 实例化HTTP方法
      HttpGet request = new HttpGet();
      request.setURI(new URI(url));
      HttpResponse response = client.execute(request);

      in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      StringBuffer sb = new StringBuffer("");
      String line = "";
      String NL = System.getProperty("line.separator");
      while ((line = in.readLine()) != null) {
        sb.append(line + NL);
      }
      in.close();
      content = sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (in != null) {
        try {
          in.close();// 最后要关闭BufferedReader
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return content;
  }

  private boolean doCheck(String content, byte[] sign, String publicKey) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
      PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
      java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
      signature.initVerify(pubKey);
      signature.update(content.getBytes());
      boolean bverify = signature.verify(sign);
      return bverify;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  private String GetPostBody(InputStream is, int contentLen) {
    if (contentLen > 0) {
      int readLen = 0;
      int readLengthThisTime = 0;
      byte[] message = new byte[contentLen];
      try {
        while (readLen != contentLen) {
          readLengthThisTime = is.read(message, readLen, contentLen - readLen);
          if (readLengthThisTime == -1) {// Should not happen.
            break;
          }
          readLen += readLengthThisTime;
        }
        return new String(message, "utf-8");
      } catch (IOException e) {
      }
    }
    return "";
  }

}
