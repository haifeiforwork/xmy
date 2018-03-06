package com.zfj.xmy.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.encryption.MD5Utils;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.SysMenuMapper;
import com.zfj.xmy.common.persistence.dao.SysRoleMapper;
import com.zfj.xmy.common.persistence.dao.SysRoleMenuMapper;
import com.zfj.xmy.common.persistence.dao.SysUserMapper;
import com.zfj.xmy.common.persistence.dao.SysUserRoleMapper;
import com.zfj.xmy.common.persistence.pojo.SysMenu;
import com.zfj.xmy.common.persistence.pojo.SysRoleMenu;
import com.zfj.xmy.common.persistence.pojo.SysUser;
import com.zfj.xmy.common.persistence.pojo.SysUserRole;
import com.zfj.xmy.sys.persistence.dao.SysUserExMapper;
import com.zfj.xmy.sys.persistence.pojo.dto.SysUserDto;
import com.zfj.xmy.sys.service.SysUserService;
/**
 * @author lij
 */
@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper ;
	
	@Autowired
	private SysUserExMapper exMapper ;

	@Autowired
	private SysUserRoleMapper roleMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	/**
	 * 添加用户信息
	 */
	@Override
	public SysUser add(SysUser sysUser) {
		sysUser.setPassword(MD5Utils.encodeString("123456")) ;//初始密码为123456
		sysUser.setCreateTime(new Date()) ;
		sysUser.setEditPassTime(new Date()) ;
		//添加到用户表
		sysUserMapper.insertSelective(sysUser) ;
		ReqData reqData = new ReqData();
		reqData.putValue("name", sysUser.getName(), SystemConstant.REQ_PARAMETER_EQ);
		//查询新添加的用户信息并返回
		List<SysUser> selectByExample = sysUserMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		SysUser sysUser2 = sysUserMapper.selectByPrimaryKey(selectByExample.get(0).getId());
		return sysUser2;
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public void update(SysUser sysUser) {
		SysUser old = sysUserMapper.selectByPrimaryKey(sysUser.getId()) ;
		if(null == old){
			throw new BusinessException("用户信息不存在") ;
		}
		old.setName(sysUser.getName()) ;
		old.setStatus(sysUser.getStatus()) ;
		old.setEditPassTime(new Date()) ;
		old.setPassword(sysUser.getPassword());
		sysUserMapper.updateByPrimaryKeySelective(old) ;
	}
	/**
	 * 删除用户信息
	 */
	@Override
	public void delete(Long id) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id) ;
		if(null == sysUser){
			throw new BusinessException("用户信息不存在") ;
		}
		sysUser.setStatus(SystemConstant.SYS_USER.STATUS_DELETE) ;//逻辑删除
		sysUser.setEditPassTime(new Date()) ;
		sysUserMapper.updateByPrimaryKeySelective(sysUser) ;
	}

	@Override
	public void deleteBatch(List<Object> idList) {
		SysUser newSysUser = new SysUser() ;
		newSysUser.setStatus(SystemConstant.SYS_USER.STATUS_DELETE) ;
		newSysUser.setEditPassTime(new Date()) ;
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		criteria.in("id",idList) ;
		sysUserMapper.updateByExample(newSysUser,parameter) ;
	}
	/**
	 * 分页查询用户信息
	 */
	@Override
	public List<SysUser> find(ReqData reqData) {
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		List<SysUser> sysUserList = sysUserMapper.selectByExampleAndPage(parameter,reqData.getRowBounds()) ;
		return sysUserList ;
	}
	/**
	 * 根据条件查询用户总条数
	 */
	@Override
	public int countByParameter(ReqData reqData) {
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		ReqUtil.reqParamterToCritera(criteria, reqData) ;
		return sysUserMapper.countByExample(parameter) ;
	}
	/**
	 * 分页根据条件查询扩展用户的信息
	 */
	@Override
	public List<SysUserDto> findTest(ReqData reqData,PageBean pageBean) {
		//List<SysUserDto> list = exMapper.findTest(ReqUtil.reqParameterToCriteriaParameter(reqData));//, pageBean.getRowBounds());
		//return list;
		List<SysUserDto> listDto = new ArrayList<SysUserDto>() ;
		//1.查询出用户的集合
		List<SysUser> sysUserList = sysUserMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		for (SysUser sysUser : sysUserList) {
			SysUserDto userDto = new SysUserDto();
			userDto.setCreateTime(sysUser.getCreateTime());
			userDto.setCreator(sysUser.getCreator());
			userDto.setEditPassTime(sysUser.getEditPassTime());
			userDto.setId(sysUser.getId());
			userDto.setLoginTime(sysUser.getLoginTime());
			userDto.setName(sysUser.getName());
			userDto.setPassword(sysUser.getPassword());
			userDto.setStatus(sysUser.getStatus());
			reqData.putValue("user_id", sysUser.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<SysUserRole> userRoleList = roleMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.removeValue("user_id");
			if(!CollectionUtils.isEmpty(userRoleList)){
				String roleId = "0";
				for (SysUserRole sysUserRole : userRoleList) {
					roleId += ","+sysUserRole.getRoleId();
				}
				
				reqData.putValue("id", roleId, SystemConstant.REQ_PARAMETER_IN);
				userDto.setRoles(sysRoleMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
				reqData.clearValue();
			}
			listDto.add(userDto);
		}
		
		
		return listDto;
	}
	/**
	 * 根据条件查询扩展用户
	 */
	@Override
	public List<SysUserDto> findTest(ReqData reqData) {
		List<SysUserDto> list = exMapper.findTest(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	/**
	 * 用户登录判断是否有该用户信息
	 */
	@Override
	public SysUser sysUserLogin(ReqData reqData) {
		reqData.putValue("status", SystemConstant.SYS_USER.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		SysUser sysUser = exMapper.selectByReqData(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return sysUser;
	}
	
	/**
	 * 根据用户查询出该用户的所有菜单
	 */
	@Override
	public List<SysMenu> findUserMenu(SysUser sysUser) {
		String roleids="0";
		String menuids="0";
		ReqData reqData = new ReqData();
		//1.查询该用户下的角色信息
		reqData.putValue("user_id", sysUser.getId(), SystemConstant.REQ_PARAMETER_EQ);
		List<SysUserRole> userRole = roleMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.removeValue("user_id");
		
		for (SysUserRole sysUserRole : userRole) {
			roleids += ","+sysUserRole.getRoleId();
		}
		//2.查询用户拥有角色的关联菜单信息
		reqData.putValue("role_id", roleids, SystemConstant.REQ_PARAMETER_IN);
		List<SysRoleMenu> roleMenu = sysRoleMenuMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.removeValue("role_id");
		
		for (SysRoleMenu sysRoleMenu : roleMenu) {
			menuids += ","+sysRoleMenu.getSysMenuId();
		}
		//3.得到用户的菜单信息
		reqData.putValue("status", SystemConstant.SYS_USER.STATUS_START, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("id", menuids, SystemConstant.REQ_PARAMETER_IN);
		List<SysMenu> menuList = sysMenuMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return menuList;
	}
	/**
	 * 查询欢迎页面的额数据
	 */
	@Override
	public Map<String, Integer> findWelcomeMessage() {
		Map<String, Integer> map = new HashMap<>();
		ReqData reqData = new ReqData();
		//1.查询超期未确认的商品
		//2.查询退货申请的订单
		reqData.putValue("return_status", 2, SystemConstant.REQ_PARAMETER_EQ);
		int returnOrderCount = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("returnOrderCount", returnOrderCount);
		reqData.removeValue("return_status");
		//3.咨询和投诉
		//4.库存预警商品
		int alarmCount = exMapper.findAlarmGoodsCount();
		map.put("alarmCount", alarmCount);
		//5.发货了未确认的商品
		//6.上架的商品
		reqData.putValue("is_putway", 0, SystemConstant.REQ_PARAMETER_EQ);
		int putway = goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("putway", putway);
		reqData.removeValue("is_putway");
		//7.下架商品
		reqData.putValue("is_putway", 1, SystemConstant.REQ_PARAMETER_EQ);
		int noPutway = goodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("noPutway", noPutway);
		reqData.removeValue("is_putway");
		//8.昨日的订单
		Date yesterday = DateUtil.yesterday();
		//昨天的开始时间
		Date beginDate = DateUtil.beginOfDay(yesterday);
		//昨天的结束时间
		DateTime endOfDay = DateUtil.endOfDay(yesterday);
		reqData.putValue("pay_time", beginDate, SystemConstant.REQ_PARAMETER_GE,SystemConstant.REQ_PARAMETER_LOGIC_AND);
		reqData.putValue("pay_time", endOfDay, SystemConstant.REQ_PARAMETER_LE);
		int tomarrOrder = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("tomarrOrder", tomarrOrder);
		reqData.removeValue("pay_time");
		reqData.removeValue("pay_time");
		//9.今日的订单
		reqData.putValue("pay_time", endOfDay, SystemConstant.REQ_PARAMETER_GE);
		int todayOrder = orderMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("todayOrder", todayOrder);
		return map;
	}
	
}
