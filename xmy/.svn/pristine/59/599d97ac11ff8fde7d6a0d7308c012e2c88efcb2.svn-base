package com.zfj.xmy.util.fileupload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;

public class FileUploadUtil {

	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @param uploadDir 上传目录
	 * @return
	 * @Description 
	 * @date 2018年1月26日  下午1:51:31
	 * @author wy
	 * 2018
	 * @return uploadResult
	 */
	public static uploadResult saveFile(MultipartFile file,HttpServletRequest request,String uploadDir){
		if (!file.isEmpty()) {
			try {
				
				String filetype = file.getContentType();
				System.out.println(filetype);
				String webroot = BaseProp.BASE.getValue("SERVER_PATH"); //网站根域名
				String basePath = request.getSession().getServletContext().getRealPath(""); //硬盘路径
				String fileOriginalname = file.getOriginalFilename(); //上传的文件名
				
				String local_path = File.separator + uploadDir + File.separator +fileOriginalname; //本地路径
				String uri_path = "/" + uploadDir + "/" +fileOriginalname;//uri部分
				
				String relativePath = basePath+ local_path;//本地访问路径
				String weburl = webroot+ uri_path; //网络访问路径
				createPathIfNotExit(relativePath);
				file.transferTo(new File(relativePath));
				return new uploadResult(weburl, relativePath);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException("文件上传失败");
			}
		}
		return null;
	}
	
	/**
	 * 上传apk文件
	 * @param file
	 * @param request
	 * @param uploadDir
	 * @return
	 * @Description 
	 * @date 2018年1月26日  下午2:53:37
	 * @author wy
	 * 2018
	 * @return uploadResult
	 */
	public static uploadResult saveApkFile(MultipartFile file,HttpServletRequest request,String uploadDir){
		if (!file.isEmpty()) {
			if (!file.getOriginalFilename().contains("apk")) {
				throw new BusinessException("对不起只能上传apk格式的文件");
			};
		}
		return saveFile(file, request, uploadDir);
	}
	//新建路径
	private static void createPathIfNotExit(String path) {
		File file = new File(path) ;
		if(!file.exists()){
			file.mkdirs() ;
		}
	}
}
