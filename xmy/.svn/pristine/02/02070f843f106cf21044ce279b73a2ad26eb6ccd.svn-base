package com.zfj.xmy.sys.service;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysRole;
/**
 * @author lij
 */
public interface SysRoleMenuService {
	
	/**
	 * @param sysRole
	 * @return List<SysMenu>
	 * @author lij
	 * @date 2017年7月10日 下午4:39:51
	 * 查询单个角色拥有的菜单
	 */
	List<SysMenu> findListSysMenuByRole(SysRole sysRole);
	
	/**
	 * @param sysRole void
	 * @author lij
	 * @date 2017年7月10日 下午4:42:08
	 * 删除该角色所拥有的菜单
	 */
	void delete(SysRole sysRole);
	
	/**
	 * @param menusid
	 * @param sysRole void
	 * @author lij
	 * @date 2017年7月10日 下午4:43:45
	 * 添加角色的菜单
	 */
	void add(String menusid,SysRole sysRole);
}
