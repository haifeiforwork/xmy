package com.zfj.xmy.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.SysUserRoleMapper;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.common.persistence.pojo.SysUserRole;
import com.zfj.xmy.sys.service.SysUserRoleService;

/**
 * @author lij
 */
@Service
public class SysUserRoleServiceImpl extends BaseService implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	/**
	 * 删除用户下的所有角色信息
	 */
	@Override
	public void delete(SysUser sysUser) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", sysUser.getId(), SystemConstant.REQ_PARAMETER_EQ);
		sysUserRoleMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	/**
	 * 添加用户角色信息
	 */
	@Override
	public void add(SysUser sysUser, String roleIds) {
		//角色ID以逗号分隔
		String[] strings = roleIds.split(",");
		//拼接时在前面加了0 所以从1开始
		for(int i=1;i<strings.length;i++){
			SysUserRole role=new SysUserRole();
			role.setRoleId(Long.parseLong(strings[i]));
			role.setUserId(sysUser.getId());
			sysUserRoleMapper.insert(role);
		}
	}
	/**
	 * 根据用户ID删除用户下的角色id
	 */
	@Override
	public void deleteById(long id) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", id, SystemConstant.REQ_PARAMETER_EQ);
		sysUserRoleMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
}
