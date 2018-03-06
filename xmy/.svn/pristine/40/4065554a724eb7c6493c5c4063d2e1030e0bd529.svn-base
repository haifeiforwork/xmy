package com.zfj.xmy.cms.web.controller.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.SysRole;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.persistence.pojo.dto.SysUserDto;
import com.zfj.xmy.sys.service.SysRoleService;
import com.zfj.xmy.sys.service.SysUserRoleService;
import com.zfj.xmy.sys.service.SysUserService;

/**
 * @author lij
 */
@RestController
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService roleService;

	/**
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lji
	 * @date 2017年7月10日 下午4:20:03 查询用户总页数和所有启用的角色信息
	 */
	@RequestMapping("/sys/findAllSysUser")
	public ModelAndView finAllSysUser(HttpServletRequest request, ReqData reqData) {
		// 查询用户总页数
		int count = sysUserService.countByParameter(reqData);
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}
		// 查询启用全部角色
		reqData.putValue("status", SystemConstant.SYS_ROLE.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		List<SysRole> roles = roleService.find(reqData);
		// 前台显示td不足
		int td = roles.size() % 5;
		List tds = new ArrayList<>();
		for (int i = 0; i < 5 - td; i++) {
			tds.add(i);
		}
		request.setAttribute("roles", roles);
		request.setAttribute("td", tds);
		request.setAttribute("countPage", countPage);
		return new ModelAndView("/sys/sys_user_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:21:51 分页查询系统用户信息
	 */
	@RequestMapping("/sys/list/{pageIndex}")
	public ModelAndView findSysUserList(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		// 根据用户ID查询对应角色名称
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(10);
		List<SysUserDto> findTest = sysUserService.findTest(reqData, pageBean);
		request.setAttribute("user", findTest);
		return new ModelAndView("/sys/sys_user_list_page");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:22:27 查询单个用户信息，和启用的用户
	 */
	@RequestMapping("/sys/getUserRole/{id}")
	public ModelAndView getUserRole(@PathVariable("id") Integer id, ReqData reqData, HttpServletRequest request) {
		reqData.putValue("su.id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<SysUserDto> list = sysUserService.findTest(reqData);
		// 得到单个用户的角色信息
		SysUserDto sysUserDto = list.get(0);
		reqData.removeValue("su.id");
		// 启用的全部角色
		reqData.putValue("status", SystemConstant.SYS_ROLE.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		List<SysRole> roles = roleService.find(reqData);
		// 前台显示td不足
		int td = roles.size() % 5;
		List tds = new ArrayList<>();
		for (int i = 0; i < 5 - td; i++) {
			tds.add(i);
		}
		request.setAttribute("userRole", sysUserDto);
		request.setAttribute("roles", roles);
		request.setAttribute("td", tds);
		return new ModelAndView("/sys/sys_user_role");
	}

	/**
	 * @param sysuser
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:23:21 修改用户信息
	 */
	@RequestMapping("/sys/updateUserRole")
	public ModelAndView updateUserRole(SysUser sysuser, ReqData reqData, HttpServletRequest request) {
		String roleIds = request.getParameter("roleids");
		// 修改用户信息
		sysUserService.update(sysuser);
		// 删除用户以前相关角色记录
		sysUserRoleService.delete(sysuser);
		// 添加修改后的相关记录
		sysUserRoleService.add(sysuser, roleIds);
		return new ModelAndView("redirect:/sys/findAllSysUser");
	}

	/**
	 * @param reqData
	 * @param request
	 * @param sysUser
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:23:38 添加用户信息
	 */
	@RequestMapping("/sys/addSysUserRole")
	public ModelAndView addSysUserRoles(ReqData reqData, HttpServletRequest request, SysUser sysUser) {
		SysUser SessionUser = (SysUser) request.getSession().getAttribute("sysUser");
		SessionUser.setCreator(sysUser.getName());
		String roleIds = request.getParameter("roleids");
		// 添加用户后返回新添加的用户对象用于添加角色
		SysUser sysUser2 = sysUserService.add(sysUser);
		sysUserRoleService.add(sysUser2, roleIds);
		return new ModelAndView("redirect:/sys/findAllSysUser");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:24:00 删除用户信息
	 */
	@RequestMapping("/sys/deleteSysUser/{id}")
	public ModelAndView deleteSysUserById(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		// 根据ID删除用户
		sysUserService.delete(id);
		// 根据用户id删除对应的用户角色记录
		sysUserRoleService.deleteById(id);

		return new ModelAndView("redirect:/sys/findAllSysUser");
	}
}
