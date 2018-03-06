package com.zfj.xmy.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.SysMenuMapper;
import com.zfj.xmy.common.persistence.dao.SysRoleMenuMapper;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.persistence.dao.SysMenuExMapper;
import com.zfj.xmy.sys.persistence.pojo.dto.SysMenuDto;
import com.zfj.xmy.sys.service.SysMenuService;
/**
 * @author lij
 */
@Service
public class SysMenuServiceImpl extends BaseService implements SysMenuService {
	
	@Autowired
	private SysMenuMapper menuMapper;
	
	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	
	@Autowired
	private SysMenuExMapper menuExMapper;
	/**
	 * 根据条件查询菜单集合
	 */
	@Override
	public List<SysMenu> findList(ReqData reqData) {
		return menuMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	/**
	 * 把菜单转换为层级关系的菜单
	 */
	@Override
	public List<SysMenuDto> findListDto(List<SysMenu> list) {
		//1.把原始菜单集合转换为扩展的菜单集合
		List<SysMenuDto> dtolist=new ArrayList<>();
		for (SysMenu sysMenu : list) {
			SysMenuDto dto=new SysMenuDto();
			dto.setId(sysMenu.getId());
			dto.setName(sysMenu.getName());
			dto.setParentId(sysMenu.getParentId());
			dto.setSn(sysMenu.getSn());
			dto.setIconUrl(sysMenu.getIconUrl());
			dto.setTargetUrl(sysMenu.getTargetUrl());
			dto.setSeq(sysMenu.getSeq());
			dtolist.add(dto);
		}
		//2.为父级菜单集合的子级赋值
		for (SysMenuDto sysMenuDto : dtolist) {
			List<SysMenu> childList = new ArrayList<>();
			for (SysMenu sysMenu : list) {
				//2.1根据菜单的父级id判断
				if(sysMenuDto.getId().equals(sysMenu.getParentId())){
					childList.add(sysMenu);
				}
			}
			//2.2添加到子级的集合中
			sysMenuDto.setChildMenu(childList);
		}
		
		return dtolist;
	}
	
	/**
	 * 根据所有的子级菜单，来匹配用户拥有的菜单并添加到层级关系
	 */
	@Override
	public List<SysMenuDto> findChildDto(List<SysMenu> list,SysUser sysUser) {
		//1.把原始菜单集合转换为扩展的菜单集合
		List<SysMenuDto> dtolist=new ArrayList<>();
		for (SysMenu sysMenu : list) {
			SysMenuDto dto=new SysMenuDto();
			dto.setId(sysMenu.getId());
			dto.setName(sysMenu.getName());
			dto.setParentId(sysMenu.getParentId());
			dto.setSn(sysMenu.getSn());
			dto.setIconUrl(sysMenu.getIconUrl());
			dto.setTargetUrl(sysMenu.getTargetUrl());
			dto.setSeq(sysMenu.getSeq());
			dtolist.add(dto);
		}
		//2.遍历集合菜单查找对应用户拥有的菜单
		for (SysMenuDto sysMenuDto : dtolist) {
			ReqData reqData = new ReqData();
			reqData.putValue("menu.id", sysUser.getId(), SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("sm.parent_id", sysMenuDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<SysMenu> byExample = menuExMapper.findMenuByUserId(ReqUtil.reqParameterToCriteriaParameter(reqData));
			sysMenuDto.setChildMenu(byExample);
		}
		return dtolist;
	}
	@Override
	public List<SysMenu> findMenuList(ReqData reqData, PageBean pageBean) {
		List<SysMenu> selectByExampleAndPage = menuMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		return selectByExampleAndPage;
	}
	/**
	 * 根据条件查询菜单总条数
	 */
	@Override
	public int countMenu(ReqData reqData) {
		int i = menuMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return i;
	}
	/**
	 * 根据id查询当菜单信息
	 */
	@Override
	public SysMenu getMenu(long id) {
		SysMenu sysMenu = menuMapper.selectByPrimaryKey(id);
		return sysMenu;
	}
	/**
	 * 修改菜单信息
	 */
	@Override
	public void update(SysMenu sysMenu) {
		//根据传入的菜单状态判断是否启用
		if(sysMenu.getStatus()==null){
			sysMenu.setStatus(1);
		}else{
			sysMenu.setStatus(0);
		}
		SysMenu old = menuMapper.selectByPrimaryKey(sysMenu.getId());
		old.setDes(sysMenu.getDes());
		old.setIconUrl(sysMenu.getIconUrl());
		old.setName(sysMenu.getName());
		old.setSeq(sysMenu.getSeq());
		old.setStatus(sysMenu.getStatus());
		old.setTargetUrl(sysMenu.getTargetUrl());
		old.setType(sysMenu.getType());
		old.setUpdateTime(new Date());
		menuMapper.updateByPrimaryKeySelective(old);
	}
	/**
	 * 添加菜单信息，添加根目录时传入的父级id为0
	 */
	@Override
	public void add(SysMenu sysMenu) {
		//根据传入的菜单状态判断是否启用
		if(sysMenu.getStatus()==null){
			sysMenu.setStatus(1);
		}else{
			sysMenu.setStatus(0);
		}
		sysMenu.setCreateTime(new Date());
		if(sysMenu.getParentId() == 0){
			//查询有几个根目录
			ReqData reqData = new ReqData();
			reqData.putValue("parent_id", sysMenu.getParentId(), SystemConstant.REQ_PARAMETER_EQ);
			int i = menuMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			//拼接菜单的sn属性
			if(i < 10){
				sysMenu.setSn("0"+(i+1));
			}else{
				sysMenu.setSn(""+(i+1));
			}
			menuMapper.insert(sysMenu);
		}else{
			//得到父级id
			Long id = sysMenu.getParentId();
			//查询父级得到父级sn
			SysMenu parent = menuMapper.selectByPrimaryKey(id);
			String sn = parent.getSn();
			//查询出父级下面的所有子级有多少个
			ReqData reqData = new ReqData();
			reqData.putValue("parent_id", id, SystemConstant.REQ_PARAMETER_EQ);
			int childCount = menuMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			//拼接菜单的sn属性
			if(childCount < 10){
				sysMenu.setSn(sn+"0"+(childCount+1));
			}else{
				sysMenu.setSn(sn+childCount+1);
			}
			menuMapper.insert(sysMenu);
		}
	}
	/**
	 * 删除菜单信息
	 */
	@Override
	public void delete(long id) {
		ReqData reqData = new ReqData();
		reqData.putValue("sys_menu_id", id, SystemConstant.REQ_PARAMETER_EQ);
		//物理删除菜单信息
		SysMenu sysMenu = menuMapper.selectByPrimaryKey(id);
		sysMenu.setStatus(SystemConstant.SYS_USER.STATUS_DELETE);
		menuMapper.updateByPrimaryKey(sysMenu);
		//根据菜单ID删除角色对应的菜单的信息
		roleMenuMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
}
