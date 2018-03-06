package com.zfj.xmy.pc.web.controller.user;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.impl.PcShoppingCartServiceImpl;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
import com.zfj.xmy.user.service.common.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private PcShoppingCartServiceImpl cartServiceImpl;
	
	@Autowired
	private PcColumService columService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月10日 下午8:24:19
	 * 登录成功后查询
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		SessionInfo sessionInfo = SessionInfo.get();
		HttpSession session = WebUtil.getSession();
		session.setAttribute("userName", sessionInfo.getUserName());
		return new ModelAndView("redirect:/index") ;
	}
	
	@RequestMapping("/out")
	public ModelAndView userOut(){
		HttpSession session = WebUtil.getSession();
		session.setAttribute("userName", "");
		session.removeAttribute(SystemConstant.PC_SESSION_INFO);
		return new ModelAndView("redirect:/index");
	}
	
	/*@RequestMapping("/test")
	public ModelAndView test(){
		userService.updateUserTest();
		return null;
	}*/
}
