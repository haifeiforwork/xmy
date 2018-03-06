package com.zfj.xmy.wap.web.controller.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.user.service.common.OpenImService;
import com.zfj.xmy.wap.web.common.SessionInfo;
import com.zfj.xmy.wap.web.controller.BaseController;

@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {
	
	@Autowired
	private OpenImService openImService;
	
	/**模块*/
	private final static String MODEL = "model";
	
	
	@RequestMapping("qianniu")
	@ResponseBody
	public Map<String,Object> testQianniu(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		SessionInfo sessionInfo = SessionInfo.get();
		Object users = openImService.getOpenImUsers(sessionInfo.getUserId());
		String appkey = openImService.getOpenImAppkey();
		String customid = openImService.getOpenImCustomid();
		map.put("users", users);
		map.put("appkey", appkey);
		map.put("customid", customid);
		map.put("sessionInfo", sessionInfo);
		request.setAttribute(MODEL, "service");
		return map;
	}
	
	@RequestMapping("qian")
	public ModelAndView toqian(HttpServletRequest request){
		
		return new ModelAndView("home/qianniu");
	}
	
	
}
