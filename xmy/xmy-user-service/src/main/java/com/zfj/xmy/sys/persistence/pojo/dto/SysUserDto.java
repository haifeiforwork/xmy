package com.zfj.xmy.sys.persistence.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.SysRole;
import com.zfj.xmy.common.persistence.pojo.SysUser;

public class SysUserDto extends SysUser{
	private List<SysRole> roles;

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
}
