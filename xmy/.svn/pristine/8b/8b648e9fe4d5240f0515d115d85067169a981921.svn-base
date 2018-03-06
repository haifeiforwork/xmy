package com.zfj.xmy.app.web.controller.user;  

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zfj.xmy.common.SystemConstant;

/** 
 * @Title: UploadUtil.java 
 * @Package com.zfj.xmy.app.web.controller.user 
 * @Description: 
 * @author hexw
 * @date 2017年9月6日 下午7:33:26 
 */
public class FileUploadUtil {
	
	public static Map<String,Object> upload(MultipartFile file,HttpServletRequest request,boolean isImage,String message){
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
		
		result.put("filePath",SystemConstant.USER.HTYP_XMY_APP+relativePath.replaceFirst("/", "")) ;
		return result ;
	}

	
	
	//新建路径
	private static void createPath(String path) {
		File file = new File(path) ;
		if(!file.exists()){
			file.mkdirs() ;
		}
	}
	
	//判断是否是图片
	private static boolean isImage(String fileName){
		fileName = fileName.toLowerCase() ;
		return !(-1 != fileName.indexOf(".jpg") && -1 != fileName.indexOf(".jpeg") && -1 != fileName.indexOf(".png") 
				&& -1 != fileName.indexOf(".gif")) ;
	}
	
	//判断是否是安装包
	private static boolean isApp(String fileName){
		return fileName.endsWith(".apk") ;
	}
	
	//判断是否是zip格式压缩包
	private static boolean isZip(String fileName){
		return fileName.endsWith(".zip");
	}
}
  