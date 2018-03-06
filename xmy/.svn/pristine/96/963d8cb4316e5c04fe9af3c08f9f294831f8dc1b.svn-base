package com.zfj.xmy.wap.web.controller.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.log.Log4jUtils;
import com.zfj.xmy.wap.web.common.AjaxResult;
import com.zfj.xmy.wap.web.controller.BaseController;

@Controller
public class CommonController extends BaseController{
	
	protected final Logger logger = Log4jUtils.getLogger(this.getClass()) ;
	
	private final static String MODEL = "model";
	/**
	 * 设置session信息
	 * @param user
	 */

	
	/**
	 * 统一跳转页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/gotoPage/{model}/{page}")
	public ModelAndView gotoPage(HttpServletRequest request, HttpServletResponse response,@PathVariable(name="model") String model, @PathVariable(name="page") String page){
		
		request.setAttribute(MODEL, model);
		
		return new ModelAndView(model + File.separator + page);
	}
	
	/**
	 * 检查是否是冰点价时间
	 */
	@RequestMapping("/checkBingDian")
	@ResponseBody
	public AjaxResult checkBingDian() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			String str = sdf.format(new Date());
			logger.info("当前日期:" + str);
			Long currTime = sdf.parse(str).getTime();
			logger.info("当前毫秒:" + currTime);
			
			//午夜的日期
			Long midnightTime = (long) -28800000;
//			Date midnight = new Date(0, 0, 0, 0, 0, 0);
//			logger.info("午夜的日期:" + sdf.format(midnight));
//			Long midnightTime = sdf.parse(sdf.format(midnight)).getTime();
//			logger.info("午夜的毫秒:" + midnightTime);
			
			//中午的日期
			Long noonTime = (long) 14400000;
//			Date noon = new Date(0, 0, 0, 12, 0, 0);
//			logger.info("中午的日期:" + sdf.format(noon));
//			Long noonTime = sdf.parse(sdf.format(noon)).getTime();
//			logger.info("中午的毫秒:" + noonTime);
			
			if(currTime >= midnightTime && currTime <= noonTime) {
				return new AjaxResult(1, null, 1);
			} else {
				return new AjaxResult(1, null, 0);
			}
		} catch(Exception e) {
			return new AjaxResult(0, "系统错误", null);
		}
	}
}
