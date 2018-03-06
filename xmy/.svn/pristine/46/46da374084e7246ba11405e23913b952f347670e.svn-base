package com.zfj.xmy.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.commons.mini.constant.BaseConstant;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.SysRoleMapper;
import com.zfj.xmy.common.persistence.dao.SysRoleMenuMapper;
import com.zfj.xmy.common.persistence.dao.SysUserRoleMapper;
import com.zfj.xmy.common.persistence.pojo.SysRole;
import com.zfj.xmy.sys.service.SysRoleService;

/**
 * @author lij
 */
@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper ;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper ;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper ;
	/**
	 * 添加单个角色信息
	 */
	@Override
	public void add(SysRole sysRole) {
		//sysRole.setStatus(BaseConstant.STATUS_ENABLE) ;
		Date curr = new Date() ;
		sysRole.setCreateTime(curr) ;
		sysRole.setUpdateTime(curr) ;
		sysRoleMapper.insertSelective(sysRole) ;
	}
	/**
	 * 修改角色信息
	 */
	@Override
	public void update(SysRole sysRole) {
		SysRole old = sysRoleMapper.selectByPrimaryKey(sysRole.getId()) ;
		BeanUtils.copyProperties(sysRole,old,"id","creator","updator","createTime","updateTime") ;
		old.setUpdateTime(new Date()) ;
		sysRoleMapper.updateByPrimaryKeySelective(old) ;
		//如果角色被禁用了删除用户对应的角色信息
		if(old.getStatus() == SystemConstant.SYS_USER.STATUS_DISABLED){
			ReqData reqData = new ReqData();
			reqData.putValue("role_id", sysRole.getId(), SystemConstant.REQ_PARAMETER_EQ);
			sysUserRoleMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		}
	}
	/**
	 * 根据ID删除角色信息
	 */
	@Override
	public void delete(Long id) {
		//物理删除角色
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		//1、删除用户角色信息
		criteria.equalTo("role_id",id) ;
		sysUserRoleMapper.deleteByExample(parameter) ;
		//2、删除角色资源信息
		sysRoleMenuMapper.deleteByExample(parameter) ;
		//3、删除角色信息
		sysRoleMapper.deleteByPrimaryKey(id) ;
		
	}

	@Override
	public void deleteBatch(List<Object> idList) {
		//物理删除角色
		CriteriaParameter parameter = new CriteriaParameter() ;
		Criteria criteria = parameter.createCriteria() ;
		//1、删除用户角色信息
		criteria.in("role_id",idList) ;
		sysUserRoleMapper.deleteByExample(parameter) ;
		//2、删除角色资源信息
		sysRoleMenuMapper.deleteByExample(parameter) ;
		//3、删除角色信息
		criteria.clearCriteria() ;
		criteria.in("id",idList) ;
		sysRoleMapper.deleteByExample(parameter) ;
	}
	/**
	 * 根据条件分页查询角色信息
	 */
	@Override
	public List<SysRole> find(ReqData reqData) {
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		List<SysRole> sysRoleList = sysRoleMapper.selectByExampleAndPage(parameter,reqData.getRowBounds()) ;
		return sysRoleList ;
	}
	/**
	 * 查询角色总条数
	 */
	@Override
	public int countByParameter(ReqData reqData) {
		CriteriaParameter parameter = ReqUtil.reqParameterToCriteriaParameter(reqData) ;
		return sysRoleMapper.countByExample(parameter) ;
	}
	/**
	 * 添加单个角色后返回添加的这个角色
	 */
	@Override
	public SysRole addRole(SysRole sysRole) {
		Date curr = new Date() ;
		sysRole.setCreateTime(curr) ;
		sysRole.setUpdateTime(curr) ;
		sysRoleMapper.insert(sysRole);
		ReqData reqData = new ReqData();
		reqData.putValue("name", sysRole.getName(),SystemConstant.REQ_PARAMETER_EQ );
		List<SysRole> selectByExample = sysRoleMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		SysRole role = sysRoleMapper.selectByPrimaryKey(selectByExample.get(0).getId());
		return role;
	}

}
