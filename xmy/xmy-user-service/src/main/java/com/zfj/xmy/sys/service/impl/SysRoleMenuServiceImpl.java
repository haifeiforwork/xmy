package com.zfj.xmy.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.SysMenuMapper;
import com.zfj.xmy.common.persistence.dao.SysRoleMenuMapper;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysRole;
import com.zfj.xmy.common.persistence.pojo.SysRoleMenu;
import com.zfj.xmy.common.persistence.pojo.SysUserRole;
import com.zfj.xmy.sys.service.SysRoleMenuService;

/**
 * @author lij
 */
@Service
public class SysRoleMenuServiceImpl extends BaseService implements SysRoleMenuService {
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysMenuMapper menuMapper;
	
	/**
	 * 查询单个角色所拥有的菜单
	 */
	@Override
	public List<SysMenu> findListSysMenuByRole(SysRole sysRole) {
		//角色ID用于拼接
		String roleIds = "0";
		ReqData reqData = new ReqData() ;
		reqData.putValue("role_id", sysRole.getId(), SystemConstant.REQ_PARAMETER_EQ);
		List<SysRoleMenu> list = sysRoleMenuMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.removeValue("role_id");
		for (SysRoleMenu sysRoleMenu : list) {
			roleIds+=","+sysRoleMenu.getSysMenuId();
		}
		reqData.putValue("id", roleIds, SystemConstant.REQ_PARAMETER_IN);
		List<SysMenu> list2 = menuMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list2;
	}
	/**
	 * 删除该角色下的所有菜单 
	 */
	@Override
	public void delete(SysRole sysRole) {
		ReqData reqData = new ReqData() ;
		reqData.putValue("role_id", sysRole.getId(), SystemConstant.REQ_PARAMETER_EQ);
		sysRoleMenuMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	/**
	 * 传入前台选的菜单ID，添加到改角色下
	 */
	@Override
	public void add(String menusid, SysRole sysRole) {
		//用来判断父级id是否已在改角色中存在
		int count;
		//角色ID以逗号分隔
		String[] strings = menusid.split(",");
		//拼接时在前面加了0 所以从1开始
		for(int i=1;i<strings.length;i++){
			SysRoleMenu role = new SysRoleMenu();
			role.setSysMenuId(Long.parseLong(strings[i]));
			role.setRoleId(sysRole.getId());
			sysRoleMenuMapper.insert(role);
			//得到该菜单的父级id
			long partenId = menuMapper.selectByPrimaryKey(Long.parseLong(strings[i])).getParentId();
			//查询该角色中是否已经存在该菜单的父级id
			ReqData reqData = new ReqData();
			reqData.putValue("role_id", sysRole.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("sys_menu_id", partenId, SystemConstant.REQ_PARAMETER_EQ);
			//查询是否有
			count = sysRoleMenuMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			//如果没有添加该菜单的父级id就添加到关系表中
			if(count < 1){
				role.setSysMenuId(partenId);
				role.setRoleId(sysRole.getId());
				sysRoleMenuMapper.insert(role);
			}
		}
		
	}

}
