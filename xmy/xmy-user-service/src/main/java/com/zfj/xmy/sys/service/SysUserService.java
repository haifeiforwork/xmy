package com.zfj.xmy.sys.service;

import java.util.List;
import java.util.Map;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.sys.persistence.pojo.dto.SysUserDto;
/**
 * @author lij
 */
public interface SysUserService {
	/**
	 * @param sysUser
	 * @return SysUser
	 * @author lij
	 * @date 2017年7月10日 下午5:02:53
	 * 添加用户信息并返回新添加的用户
	 */
	SysUser add(SysUser sysUser) ;
	/**
	 * @param sysUser void
	 * @author lij
	 * @date 2017年7月10日 下午5:04:11
	 * 修改用户信息
	 */
	void update(SysUser sysUser) ;
	/**
	 * @param id void
	 * @author lij
	 * @date 2017年7月10日 下午5:04:33
	 * 删除用户信息
	 */
	void delete(Long id) ;
	
	void deleteBatch(List<Object> idList) ;
	/**
	 * @param reqData
	 * @return List<SysUser>
	 * @author lij
	 * @date 2017年7月10日 下午5:05:12
	 * 根据条件查询用户信息
	 */
	List<SysUser> find(ReqData reqData) ;
	/**
	 * @param reqData
	 * @return int
	 * @author lij
	 * @date 2017年7月10日 下午5:05:51
	 * 根据条件查询用户总条数
	 */
	int countByParameter(ReqData reqData) ;
	/**
	 * @param reqData
	 * @param pageBean
	 * @return List<SysUserDto>
	 * @author lij
	 * @date 2017年7月10日 下午5:06:27
	 * 根据条件分页查询扩展用户的信息
	 */
	List<SysUserDto> findTest(ReqData reqData,PageBean pageBean) ;
	/**
	 * @param reqData
	 * @return List<SysUserDto>
	 * @author lij
	 * @date 2017年7月10日 下午5:11:43
	 * 根据条件查询扩展的用户
	 */
	List<SysUserDto> findTest(ReqData reqData) ;
	/**
	 * @param reqData
	 * @return SysUser
	 * @author lij
	 * @date 2017年7月10日 下午5:13:58
	 * 用户登录
	 */
	SysUser sysUserLogin(ReqData reqData);
	/**
	 * @param sysUser
	 * @return List<SysMenu>
	 * @author lij
	 * @date 2017年7月10日 下午5:14:57
	 * 根据用户信息查询所拥有的菜单信息
	 */
	List<SysMenu> findUserMenu(SysUser sysUser);
	/**
	 * 查询欢迎页面的额信息
	 * @return Map<String,String>
	 * @author lij
	 * @date 2017年7月19日 下午7:48:14
	 */
	Map<String, Integer> findWelcomeMessage();
}
