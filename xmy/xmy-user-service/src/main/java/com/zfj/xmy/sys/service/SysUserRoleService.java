package com.zfj.xmy.sys.service;

import com.zfj.xmy.common.persistence.pojo.SysUser;
/**
 * @author lij
 */
public interface SysUserRoleService {
	/**
	 * @param sysUser void
	 * @author lij
	 * @date 2017年7月10日 下午4:58:13
	 * 根据用删除用户下的所有角色
	 */
	void delete (SysUser sysUser);
	/**
	 * @param sysUser
	 * @param roleIds void
	 * @author lij
	 * @date 2017年7月10日 下午4:59:25
	 * 添加用户的角色
	 */
	void add(SysUser sysUser,String roleIds);
	
	/**
	 * @param id void
	 * @author lij
	 * @date 2017年7月10日 下午4:59:54
	 * 根据用户id删除用户下的角色信息
	 */
	void deleteById(long id);
}
