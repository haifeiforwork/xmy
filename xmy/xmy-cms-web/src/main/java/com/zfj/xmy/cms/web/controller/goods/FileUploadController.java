package com.zfj.xmy.cms.web.controller.goods;  

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.util.common.StringUtil;

/** 
 * @Title: FileUploadController.java 
 * @Package com.zfj.xmy.cms.web.controller.goods 
 * @Description:  图片上传
 * @author hexw
 * @date 2017年7月13日 下午1:48:37 
 */
@RequestMapping("/fileUpload")
@RestController
public class FileUploadController {
	
	/**
	 * 服务图标上传
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadImage")
	public RespData brandImage(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		RespData respData = new RespData();
		Map<String,Object> map = upload(file,request,true,"上传的文件类型不正确，只能上传图片！") ;
		respData.setData(map);
		return respData;
	}
	
	/**
	 * 商品图片压缩包上传
	 * @param file
	 * @param request
	 * @return    
	 * @return RespData    
	 * Date:2017年7月18日 下午4:10:03 
	 * @author hexw
	 */
	@RequestMapping("/uploadZip")
	public RespData imageZip(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		RespData respData = new RespData();
		Map<String,Object> map = uploadZip(file,request,true,"上传的文件类型不正确，只能上传zip格式的压缩包！") ;
		respData.setData(map);
		return respData;
	}
	
	
	
	private Map<String,Object> upload(MultipartFile file,HttpServletRequest request,boolean isImage,String message){
		BufferedInputStream bis = null ;
		FileOutputStream fos = null ;
		String relativePath = null ;
		Map<String,Object> result = new HashMap<String,Object>() ;
		try {
			bis = new BufferedInputStream(file.getInputStream()) ;
			String oldFileName = file.getOriginalFilename() ;
			boolean condition = isImage ? isImage(oldFileName) : isZip(oldFileName) ;
			if(condition){
				String basePath = request.getSession().getServletContext().getRealPath("") ;
				relativePath = File.separator + "file" + File.separator +oldFileName ;
				createPath(basePath + File.separator + "file") ;
				fos = new FileOutputStream(new File(basePath + relativePath)) ;
				
				fos.write(file.getBytes()) ;
				FileUtils.copyInputStreamToFile(bis,new File(basePath + relativePath));
				fos.flush() ;
				result.put("success",true) ;
				relativePath = relativePath.replaceAll("\\\\\\\\","/").replaceAll("\\\\","/") ;
			}else{
				result.put("success",false) ;
				result.put("msg",message) ;
			}
		} catch (IOException e) {
			e.printStackTrace();
			result.put("success",false) ;
			result.put("msg","上传失败！") ;
		} finally {
			if(null != bis){
				try {
					bis.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != fos){
				try {
					fos.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		result.put("filePath",relativePath.replaceFirst("/", "")) ;
		return result ;
	}
	
	
	
	private Map<String,Object> uploadZip(MultipartFile file,HttpServletRequest request,boolean isZip,String message){
		BufferedInputStream bis = null ;
		FileOutputStream fos = null ;
		String relativePath = null ;
		String filePath = null;
		Map<String,Object> result = new HashMap<String,Object>() ;
		try {
			bis = new BufferedInputStream(file.getInputStream()) ;
			String oldFileName = file.getOriginalFilename() ;
			boolean condition = isZip ? isZip(oldFileName) : isApp(oldFileName) ;
			if(condition){
				String  basePath = request.getSession().getServletContext().getRealPath("") ;
				relativePath = File.separator + "zipfile" + File.separator +  oldFileName ;
				createPath(basePath + File.separator + "zipfile") ;
				fos = new FileOutputStream(new File(basePath + relativePath)) ;
				
				fos.write(file.getBytes()) ;
				FileUtils.copyInputStreamToFile(bis,new File(basePath + relativePath));
				fos.flush() ;
				result.put("success",true) ;
				basePath = basePath.replaceAll("\\\\\\\\","/").replaceAll("\\\\","/");
				relativePath = relativePath.replaceAll("\\\\\\\\","/").replaceAll("\\\\","/") ;
				filePath = basePath+relativePath;
				result.put("filePath",filePath) ;
			}else{
				result.put("success",false) ;
				result.put("msg",message) ;
			}
		} catch (IOException e) {
			e.printStackTrace();
			result.put("success",false) ;
			result.put("msg","上传失败！") ;
		} finally {
			if(null != bis){
				try {
					bis.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != fos){
				try {
					fos.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result ;
	}
	
	//新建路径
	private void createPath(String path) {
		File file = new File(path) ;
		if(!file.exists()){
			file.mkdirs() ;
		}
	}
	
	//判断是否是图片
	private boolean isImage(String fileName){
		fileName = fileName.toLowerCase() ;
		return !(-1 != fileName.indexOf(".jpg") && -1 != fileName.indexOf(".jpeg") && -1 != fileName.indexOf(".png") 
				&& -1 != fileName.indexOf(".gif")) ;
	}
	
	//判断是否是安装包
	private boolean isApp(String fileName){
		return fileName.endsWith(".apk") ;
	}
	
	//判断是否是zip格式压缩包
	private boolean isZip(String fileName){
		return fileName.endsWith(".zip");
	}
	
	/**
	 * 服务图标上传
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/layuiEditUploadImage")
	public JSONObject layuiEditUploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		return  layuiEditupload(file,request,true,"上传的文件类型不正确，只能上传图片！") ;
	}
	
	private JSONObject layuiEditupload(MultipartFile file,HttpServletRequest request,boolean isImage,String message){
		BufferedInputStream bis = null ;
		FileOutputStream fos = null ;
		String relativePath = null ;
		JSONObject json = new  JSONObject();
		int code = 0; //上传状态  0:上传成功 1：上传失败
		String msg = "" ; //提示信息
		try {
			bis = new BufferedInputStream(file.getInputStream()) ;
			String oldFileName = file.getOriginalFilename() ;
			boolean condition = isImage ? isImage(oldFileName) : isZip(oldFileName) ;
			if(condition){
				String basePath = request.getSession().getServletContext().getRealPath("") ;
				relativePath = File.separator + "file" + File.separator +oldFileName ;
				createPath(basePath + File.separator + "file") ;
				fos = new FileOutputStream(new File(basePath + relativePath)) ;
				
				fos.write(file.getBytes()) ;
				FileUtils.copyInputStreamToFile(bis,new File(basePath + relativePath));
				fos.flush() ;
				relativePath = relativePath.replaceAll("\\\\\\\\","/").replaceAll("\\\\","/") ;
				code = 0;
				msg = "上传成功";
			}else{
				code = 1;
				msg = message;
			}
		} catch (IOException e) {
			e.printStackTrace();
			code = 1;
			msg = "上传失败";
		} finally {
			if(null != bis){
				try {
					bis.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != fos){
				try {
					fos.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		JSONObject obj = new  JSONObject();
		if ( code == 0){  //上传成功
			obj.put("src", relativePath.replaceFirst("/", ""));
		} 
		json.put("code", code);
		json.put("msg", msg);
		json.put("data", obj);
		return json ;
	}
	
}
  