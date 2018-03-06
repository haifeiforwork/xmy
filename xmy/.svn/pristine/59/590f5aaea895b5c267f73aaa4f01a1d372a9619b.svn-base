package com.zfj.xmy.cms.web.controller.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.zfj.base.encryption.MD5Utils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.elasticsearch.XmyIndex;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;
import com.zfj.xmy.sys.persistence.pojo.dto.SysMenuDto;
import com.zfj.xmy.sys.service.SysMenuService;
import com.zfj.xmy.sys.service.SysUserService;

@RestController
public class LoginController {

	
	@Autowired
	private SysMenuService menuService;

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private GoodsDocService goodsDocService;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@RequestMapping("/sys/login")
	public ModelAndView login(){
		
		return new ModelAndView("/sys/sysUserLogin");
	}
	
	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:24:50 用户登录
	 */
	@RequestMapping("/login")
	public ModelAndView userLogin(ReqData reqData, HttpServletRequest request,RedirectAttributes attributes) {
		String usename = request.getParameter("username");
		String pwd = request.getParameter("pwd"); 
		String m5pwd = MD5Utils.encodeString(pwd);
		reqData.putValue("name", usename, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("password", m5pwd, SystemConstant.REQ_PARAMETER_EQ);
		// 重新加载是清空session中的菜单数据
		request.getSession().removeAttribute("findListDto");
		request.getSession().removeAttribute("childList");
		// 查询判断用户输入的用户名密码是否正确
		// List<SysUser> find = userService.find(reqData);
		SysUser sysUser = userService.sysUserLogin(reqData);
		if (sysUser != null) {
			// 查询用户拥有的启用的
			List<SysMenu> findUserMenu = userService.findUserMenu(sysUser);
			List<SysMenuDto> findListDto = menuService.findListDto(findUserMenu);
			Map<String, Integer> map = userService.findWelcomeMessage();
			request.setAttribute("map", map);
			request.getSession().setAttribute("findListDto", findListDto);
			request.getSession().setAttribute("sysUser", sysUser);
			//登录时添加到elasticsearch以前商品全部添加后面新增的商品可以不用管
			//Client client = elasticsearchTemplate.getClient();
			//client.prepareIndex(XmyIndex.GOODS_INDEX_NAME, XmyIndex.GOODS_TYPE).get();
			//goodsDocService.rebuildAllIndex();
			return new ModelAndView("/sys/welcome");
		} else {
			attributes.addAttribute("errormsg", "登陆失败！账号或密码错误！");
			return new ModelAndView(new RedirectView("/sys/login"));
		}
	}
	
}
