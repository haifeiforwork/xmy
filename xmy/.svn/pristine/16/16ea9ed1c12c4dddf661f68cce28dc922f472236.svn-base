package com.zfj.xmy.cms.web.controller.sys;

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
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.service.SysMenuService;

/**
 * @author lji
 */
@RestController
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	/**
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lijie
	 * @date 2017年7月10日 下午4:06:19 查询全部的菜单信息的总页数
	 */
	@RequestMapping("/sys/findAllSysMenu")
	public ModelAndView finAllsys(HttpServletRequest request, ReqData reqData) {
		// 查询角色总页数
		int count = menuService.countMenu(reqData);
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}
		request.setAttribute("countPage", countPage);
		return new ModelAndView("/sys/sys_menu_list");
	}

	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:07:21 分页查询全部菜单信息
	 */
	@RequestMapping("/sys/menuList/{pageIndex}")
	public ModelAndView findMenuList(@PathVariable("pageIndex") Integer pageIndex, ReqData reqData, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		List<SysMenu> findMenuList = menuService.findMenuList(reqData, pageBean);
		request.setAttribute("menuList", findMenuList);
		return new ModelAndView("/sys/sys_menu_list_page");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:08:02 根据菜单ID查询单个菜单信息
	 */
	@RequestMapping("/sys/getMenu/{id}")
	public ModelAndView getMenu(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		SysMenu sysMenu = menuService.getMenu(id);

		List<SysMenu> findMenuList = menuService.findList(reqData);

		request.setAttribute("sysMenu", sysMenu);
		request.setAttribute("sysMenuList", findMenuList);
		return new ModelAndView("/sys/menu_detail");
	}

	/**
	 * @param sysMenu
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:09:06 修改单个菜单信息，其父级id不做修改
	 */
	@RequestMapping("/sys/updateMenu")
	public ModelAndView updateMenu(SysMenu sysMenu, ReqData reqData, HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		sysMenu.setUpdator(sysUser.getName());
		menuService.update(sysMenu);
		return new ModelAndView("redirect:/sys/findAllSysMenu");
	}

	/**
	 * @param sysMenu
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月10日 下午4:12:26 添加菜单信息
	 */
	@RequestMapping("/sys/addMenu")
	public ModelAndView addMenu(SysMenu sysMenu, ReqData reqData, HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		sysMenu.setCreator(sysUser.getName());
		menuService.add(sysMenu);
		return new ModelAndView("redirect:/sys/findAllSysMenu");
	}

	/**
	 * @param id
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lji
	 * @date 2017年7月10日 下午4:12:51 根据菜单id删除该菜单，和相关联的角色信息
	 */
	@RequestMapping("/sys/deleteMenu/{id}")
	public ModelAndView deleteMenuById(@PathVariable("id") long id, ReqData reqData, HttpServletRequest request) {
		menuService.delete(id);
		return new ModelAndView("redirect:/sys/findAllSysMenu");
	}
}
