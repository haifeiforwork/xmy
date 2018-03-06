package com.zfj.xmy.cms.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysRole;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.persistence.pojo.dto.SysMenuDto;
import com.zfj.xmy.sys.service.SysMenuService;
import com.zfj.xmy.sys.service.SysRoleMenuService;
import com.zfj.xmy.sys.service.SysRoleService;

/**
 * @author lij
 */
@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysMenuService menuService;

	@Autowired
	private SysRoleMenuService roleMenuService;

	/**
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:14:59 查询角色的总页数，和查询所有菜单
	 */
	@RequestMapping("/sys/findAllSysRole")
	public ModelAndView finAllSysUser(HttpServletRequest request, ReqData reqData) {
		// 查询角色总页数
		int count = sysRoleService.countByParameter(reqData);
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}

		// 得到启用的全部菜单
		reqData.putValue("status", SystemConstant.SYS_USER.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		List<SysMenu> sysMenus = menuService.findList(reqData);
		// 转换为层级关系的菜单
		List<SysMenuDto> dtolist = menuService.findListDto(sysMenus);
		request.setAttribute("countPage", countPage);
		request.setAttribute("sysMenus", dtolist);
		return new ModelAndView("/sys/sys_role_list");
	}

	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lji
	 * @date 2017年7月10日 下午4:16:18 分页查询全部角色信息
	 */
	@RequestMapping("/sys/sysRoleList/{pageIndex}")
	public ModelAndView finAllSysRole(ReqData reqData, HttpServletRequest request) {
		List<SysRole> list = sysRoleService.find(reqData);
		request.setAttribute("roles", list);
		return new ModelAndView("/sys/sys_role_list_page");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:17:21 查询单个角色信息，单个角色拥有的菜单信息（用户回现）,以及全部的菜单信息（用于修改）
	 */
	@RequestMapping("/sys/getRoleMenu/{id}")
	public ModelAndView getRoleMenus(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		reqData.putValue("id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<SysRole> list = sysRoleService.find(reqData);
		// 得到单个角色信息
		SysRole sysRole = list.get(0);
		// 查询原来这个角色拥有的菜单
		List<SysMenu> oldMenu = roleMenuService.findListSysMenuByRole(sysRole);
		reqData.removeValue("id");
		// 得到全部菜单
		reqData.putValue("status", SystemConstant.SYS_USER.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		List<SysMenu> sysMenus = menuService.findList(reqData);
		// 转换为层级关系的菜单
		List<SysMenuDto> dtolist = menuService.findListDto(sysMenus);
		request.setAttribute("sysRole", sysRole);
		request.setAttribute("sysMenus", dtolist);
		request.setAttribute("oldMenu", oldMenu);
		return new ModelAndView("/sys/sys_role_menu");
	}

	/**
	 * @param reqData
	 * @param request
	 * @param sysRole
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:18:48 修改角色信息
	 */
	@RequestMapping("/sys/updateRoleMenus")
	public ModelAndView updateRoleMenus(ReqData reqData, HttpServletRequest request, SysRole sysRole) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		sysRole.setUpdator(sysUser.getName());
		String menusId = request.getParameter("menusid");
		// 修改用户信息
		sysRoleService.update(sysRole);
		// 先删除以前的菜单信息
		roleMenuService.delete(sysRole);
		// 添加现有的菜单信息
		roleMenuService.add(menusId, sysRole);
		return new ModelAndView("redirect:/sys/findAllSysRole");
	}

	/**
	 * @param sysRole
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:19:14 添加单个角色信息
	 */
	@RequestMapping("/sys/addRoleMenus")
	public ModelAndView addRoleMenus(SysRole sysRole, ReqData reqData, HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		sysRole.setUpdator(sysUser.getName());
		String menusId = request.getParameter("menusid");
		// 添加角色信息并返回添加的对象用于添加菜单！
		SysRole role = sysRoleService.addRole(sysRole);
		// 添加现有的菜单信息
		roleMenuService.add(menusId, role);
		return new ModelAndView("redirect:/sys/findAllSysRole");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:19:40 删除角色信息
	 */
	@RequestMapping("/sys/deleteRole/{id}")
	public ModelAndView deleteRoleById(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		// 删除角色信息
		sysRoleService.delete(id);
		return new ModelAndView("redirect:/sys/findAllSysRole");
	}
}
