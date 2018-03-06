package com.zfj.xmy.cms.web.controller.versioncontroll;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.cms.web.common.ims.annotation.SystemControllerLog;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.persistence.pojo.ClientVersion;
import com.zfj.xmy.common.service.CommonVersionService;
import com.zfj.xmy.util.fileupload.FileUploadUtil;
import com.zfj.xmy.util.fileupload.uploadResult;

@RequestMapping("/version")
@CheckLogin
@RestController
public class VersionControllController extends BaseController {

	@Autowired
	private CommonVersionService commonVersionService;
	
	/**
	 * 版本控制页面
	 * @return
	 * @Description 
	 * @date 2018年1月9日  上午11:23:30
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		request.setAttribute("version", commonVersionService.get());
		return new ModelAndView("/versioncontroll/index");
	}
	
	/**
	 * 修改版本配置
	 * @param request
	 * @param clientVersion
	 * @return
	 * @Description 
	 * @date 2018年1月9日  下午3:20:02
	 * @author wy
	 * 2018
	 * @return RespData
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/update")
	@SystemControllerLog(description="修改版本配置")
	public ModelAndView update(HttpServletRequest request,@RequestParam("file") MultipartFile file,ClientVersion clientVersion) throws UnsupportedEncodingException{
		try {
			//上传
			uploadResult result = FileUploadUtil.saveApkFile(file, request, "file");
			System.out.println(result);
			if (ObjectUtil.isNotNull(result)) { 
				clientVersion.setAndroidUrl(result.getUrl());
			}
			//更新
			commonVersionService.update(clientVersion);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("redirect:index?msg="+URLEncoder.encode(e.getMessage(), "UTF-8"));
		}
		return new ModelAndView("redirect:index");
	}
}
