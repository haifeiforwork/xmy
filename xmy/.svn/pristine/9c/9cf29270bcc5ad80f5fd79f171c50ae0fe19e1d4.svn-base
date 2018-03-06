package com.zfj.xmy.wap.web.controller.home;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.wap.web.controller.common.CommonController;
@Controller
@RequestMapping("/app")
public class AppController extends CommonController{
	/**下载页面*/
	private final static String DOWNLOAD = "index/download";
	
	/**
	 * 下载页面
	 * @param request
	 * @param response
	 * @param goodsId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/download")
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response, String fromWap) throws ParseException{
		request.setAttribute("fromWap", fromWap);
		return new ModelAndView(DOWNLOAD);
	}
}
