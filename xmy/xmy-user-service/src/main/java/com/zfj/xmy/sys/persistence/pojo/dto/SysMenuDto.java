package com.zfj.xmy.sys.persistence.pojo.dto;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.SysMenu;

public class SysMenuDto extends SysMenu{
	private List<SysMenu> childMenu;


	public List<SysMenu> getChildMenu() {
		return childMenu;
	}


	public void setChildMenu(List<SysMenu> childMenu) {
		this.childMenu = childMenu;
	}


	@Override
	public String toString() {
		return "SysMenuDto [childMenu=" + childMenu + "]";
	}


}
