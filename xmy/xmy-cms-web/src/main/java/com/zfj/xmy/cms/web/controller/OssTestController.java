package com.zfj.xmy.cms.web.controller;  

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectAcl;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.oss.OSSManager;

/** 
 * @Title: OssTestController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description: 
 * @author hexw
 * @date 2018年1月8日 上午10:15:17 
 */
@Controller
@RequestMapping("ossTest")
public class OssTestController {
	private static OSSClient ossClient;
    
	@Autowired
    private OSSManager ossManager;
	@RequestMapping("/hexw")
	public RespData hexwOSSTest(HttpServletRequest request){
		RespData respData = new RespData();
		ossClient = ossManager.getOSSClient();
		// 上传参数
		String targetFile = "goods/goods/20180108/010108008701-003.jpg";
		File file = new File("C:/Users/Administrator/Desktop/010108008701-003.jpg"); //上传本地文件
		ossClient.putObject("cqzfj", targetFile, file);
		
		
		
		String callbackUrl = "http://wx.cqzfj.com/xmy-cms-web/oss/callback";  //回调地址
	/*	// 上传回调参数
		Callback callback = new Callback();
		callback.setCallbackUrl(callbackUrl);
		callback.setCallbackHost("oss-cn-qingdao.aliyuncs.com");
		callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
		callback.setCallbackBodyType(CallbackBodyType.JSON);
		callback.addCallbackVar("x:var1", "value1");
		callback.addCallbackVar("x:var2", "value2");
		putObjectRequest.setCallback(callback);
		PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
		// 读取上传回调返回的消息内容
		byte[] buffer = new byte[1024];
		putObjectResult.getCallbackResponseBody().read(buffer);
		// 一定要close，否则会造成连接资源泄漏
		putObjectResult.getCallbackResponseBody().close();*/
		 
		return respData;
	}

}
  