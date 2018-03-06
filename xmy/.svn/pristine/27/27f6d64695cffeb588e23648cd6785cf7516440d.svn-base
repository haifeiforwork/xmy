package com.zfj.xmy.sys.service;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.persistence.pojo.dto.SysMenuDto;
/**
 * @author lij
 */
public interface SysMenuService {
	/**
	 * @param reqData
	 * @return List<SysMenu>
	 * @author lij
	 * @date 2017年7月10日 下午4:30:02
	 * 根据条件查询菜单集合
	 */
	List<SysMenu> findList(ReqData reqData);
	
	/**
	 * @param list
	 * @return List<SysMenuDto>
	 * @author lij
	 * @date 2017年7月10日 下午4:30:35
	 * 将原菜单集合转换为扩展的菜单集合
	 */
	List<SysMenuDto> findListDto(List<SysMenu> list);
	/**
	 * @param list
	 * @return List<SysMenuDto>
	 * @author ***
	 * @date 2017年7月12日 上午10:31:33
	 * 将子菜单集合查询出下面的所有子菜单
	 */
	List<SysMenuDto> findChildDto(List<SysMenu> list,SysUser sysUser);
	
	/**
	 * 
	 * @param reqData
	 * @param pageBean
	 * @return List<SysMenu>
	 * @author lijie
	 * @date 2017年7月6日 下午2:56:27
	 * 分页查询全部菜单
	 */
	List<SysMenu> findMenuList(ReqData reqData,PageBean pageBean);
	
	/**
	 * @param reqData
	 * @return int
	 * @author lij
	 * @date 2017年7月10日 下午4:32:19
	 * 根据条件查询菜单总条数
	 */
	int countMenu(ReqData reqData);
	/**
	 * @param id
	 * @return SysMenu
	 * @author ***
	 * @date 2017年7月10日 下午4:33:13
	 * 根据菜单id查询单个菜单信息
	 */
	SysMenu getMenu(long id);
	/**
	 * @param sysMenu void
	 * @author lij
	 * @date 2017年7月10日 下午4:34:05
	 * 修改菜单信息
	 */
	void update(SysMenu sysMenu);
	/**
	 * @param sysMenu void
	 * @author lij
	 * @date 2017年7月10日 下午4:34:46
	 * 添加菜单信息
	 */
	void add(SysMenu sysMenu);
	/**
	 * @param id void
	 * @author lij
	 * @date 2017年7月10日 下午4:37:00
	 * 删除菜单信息
	 */
	void delete(long id);
}
