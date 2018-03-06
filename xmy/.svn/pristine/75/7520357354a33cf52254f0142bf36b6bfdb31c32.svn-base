package com.zfj.xmy.sys.service;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.SysRole;
/**
 * @author lij
 */
public interface SysRoleService {
	/**
	 * @param sysRole void
	 * @author lij
	 * @date 2017年7月10日 下午4:47:46
	 * 添加单个角色信息
	 */
	void add(SysRole sysRole) ;
	/**
	 * @param sysRole void
	 * @author lij
	 * @date 2017年7月10日 下午4:48:17
	 * 修改角色信息
	 */
	void update(SysRole sysRole) ;
	/**
	 * @param id void
	 * @author lij
	 * @date 2017年7月10日 下午4:48:46
	 * 根据ID删除角色信息
	 */
	void delete(Long id) ;
	
	void deleteBatch(List<Object> idList) ;
	/**
	 * @param reqData
	 * @return List<SysRole>
	 * @author lij
	 * @date 2017年7月10日 下午4:49:24
	 * 根据条件查询角色信息
	 */
	List<SysRole> find(ReqData reqData) ;
	/**
	 * @param reqData
	 * @return int
	 * @author lij
	 * @date 2017年7月10日 下午4:50:12
	 * 查询角色总条数
	 */
	int countByParameter(ReqData reqData) ;
	/**
	 * @param sysRole
	 * @return SysRole
	 * @author lij
	 * @date 2017年7月10日 下午4:50:27
	 * 添加单个角色后返回改角色信息
	 */
	SysRole addRole(SysRole sysRole);
	
}
