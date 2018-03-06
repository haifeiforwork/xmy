package com.zfj.xmy.wap.web.controller.index;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weChat")
public class WeChatLoginController {
	
	private static String callbackurl = "http%3A%2F%2Fwx.cqzfj.com%2Fxmy-wap-web%2FweChat%2FgetWeChatLogin";
	//http://wx.cqzfj.com/xmy-wap-web/index/weChat  http%3a%2f%2fwx.cqzfj.com%2fxmy-wap-web%2findex%2fweChat
	//http://m.xmyb2b.com/xmy-wap-web/index/weChat  http%3a%2f%2fm.xmyb2b.com%2fxmy-wap-web%2findex%2fweChat
	private static String appid = "wxe41266d25245a434";
	
	@RequestMapping("/getWeChatLogin")
	private String getWeChatLogin(){
		
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+appid+"&redirect_uri="+callbackurl+"&response_type=code&scope=snsapi_userinfo&state="+new Date().getTime()+"#wechat_redirect";
		
		return "redirect:"+url;
	
	}
}
