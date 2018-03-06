package com.zfj.xmy.cms.web.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.zfj.xmy.sys.persistence.pojo.dto.SysMenuDto;
import com.zfj.xmy.sys.service.SysMenuService;
import com.zfj.xmy.sys.service.SysUserService;

/**
 * @author lij
 */
@RestController
public class UserLoginController {

	@Autowired
	private SysMenuService menuService;

	@Autowired
	private SysUserService userService;

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:24:50 用户登录
	 *//*
	@RequestMapping("login")
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
			return new ModelAndView("/sys/welcome");
		} else {
			attributes.addAttribute("errormsg", "登陆失败！账号或密码错误！");
			return new ModelAndView(new RedirectView("/sys/sysUserLogin.jsp"));
		}
		// 查询全部订单
		// List<SysMenu> findList = menuService.findList(reqData);
		// List<SysMenuDto> findListDto = menuService.findListDto(findList);
		// request.getSession().setAttribute("findListDto", findListDto);
	}*/

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:25:22 查询标题菜单下的子菜单
	 */
	@RequestMapping("/sys/findChilMenu/{id}")
	public ModelAndView findChileMenu(@PathVariable("id") long id, ReqData reqData,@Param("topMenuid") Long topMenuid, HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		// 查询父级菜单下面的所有子菜单
		reqData.putValue("parent_id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<SysMenu> childList = menuService.findList(reqData);
		SysMenu menu = menuService.getMenu(id);
		// 转换为层级关系的类型
		List<SysMenuDto> findListDto = menuService.findChildDto(childList, sysUser);
		
		Map<String, Integer> map = userService.findWelcomeMessage();
		request.setAttribute("map", map);
		request.getSession().setAttribute("childList", findListDto);
		return new ModelAndView("/sys/welcome");
		// return findListDto;
	}

	/**
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月11日 下午5:49:35 修改系统用户密码
	 */
	@RequestMapping("/sys/updatePwd")
	public ModelAndView updateSysUserPwd(HttpServletRequest request, ReqData reqData) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		if (sysUser.getPassword().equals(MD5Utils.encodeString(oldpwd))) {
			sysUser.setPassword(MD5Utils.encodeString(newpwd));
			userService.update(sysUser);
			return new ModelAndView("redirect:/sys/login");
		} else {
			return new ModelAndView("redirect:/sys/login");
		}
	}
	/**
	 * @param id
	 * @param request
	 * @return String
	 * @author lij
	 * @date 2017年7月14日 下午4:33:03
	 * 设置左边菜单的效果
	 */
	@RequestMapping("/sys/loadLeftMenu/{id}")
	public String loadMenu(@PathVariable("id") int id,HttpServletRequest request){
		request.getSession().removeAttribute("leftMenu");
		request.getSession().setAttribute("leftMenu", id);
		return null;
	}
	/**
	 * @param id
	 * @param request
	 * @return String
	 * @author ***
	 * @date 2017年7月14日 下午4:33:42
	 * 设置上方菜单的效果
	 */
	@RequestMapping("/sys/loadTopMenu/{id}")
	public String loadTopMenu(@PathVariable("id") int id,HttpServletRequest request){
		request.getSession().removeAttribute("topMenu");
		request.getSession().setAttribute("topMenu", id);
		return null;
	}
}
