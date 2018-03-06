package com.zfj.xmy.order.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.SystemConstant.USERADDRESS;
import com.zfj.xmy.common.persistence.dao.UserAddreesMapper;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.order.service.cms.UserAddreesService;
/**
 * 
 * 
 * @author ljie
 *6.22
 */
@Service
public class UserAddreesServiceImpl extends BaseService implements UserAddreesService {

	@Autowired
	private UserAddreesMapper userAddreesMapper;
	 

	@Override
	public List<UserAddrees> finAllUserAddrees(ReqData reqData) {
		List<UserAddrees> list = userAddreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}


	@Override
	public UserAddrees getUserAddrees(ReqData reqData) {
		return userAddreesMapper.selectByPrimaryKey(reqData.getValue("id"));
	}
	@Override
	public void insertUserAddress(UserAddrees userAddrees){
		userAddreesMapper.insert(userAddrees);
	}
	
	@Override
	public void updateUserAddress(UserAddrees userAddrees){
		userAddreesMapper.updateByPrimaryKeySelective(userAddrees);
	}
	@Override
	public void delUserAddress(UserAddrees userAddrees){
		if(StringUtil.isEmpty(userAddrees.getId())){
			throw new BusinessException("请携带需要删除的收货地址id");
		}else{
			UserAddrees selectByPrimaryKey = userAddreesMapper.selectByPrimaryKey(userAddrees.getId());
			if(ObjectUtils.isEmpty(selectByPrimaryKey)||!selectByPrimaryKey.getUserId().equals(userAddrees.getUserId())){
				throw new BusinessException("此地址不是你的，或者说地址id不存在！！您携带的地址id为"+userAddrees.getId());
			}else{
				userAddreesMapper.deleteByPrimaryKey(userAddrees.getId());
			}
		}
		
	}
	@Override
	public void updateSetDefaultUserAddress(UserAddrees newDefaultUserAddress){
		//1 先把设置了默认地址的全部改为非默认地址。（其实应该就至多只有一条记录的）
		ReqData reqData=new ReqData();
		//设置当前用户id进去，作为查询条件
		reqData.putValue(SystemConstant.USERADDRESS.USER_ID,newDefaultUserAddress.getUserId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue(SystemConstant.USERADDRESS.IS_DEFAULT_S, SystemConstant.USERADDRESS.IS_DEFAULT_AADDRESS, SystemConstant.REQ_PARAMETER_EQ);
		List<UserAddrees> selectByExample = userAddreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (UserAddrees userAddrees : selectByExample) {
			userAddrees.setIsDefault(SystemConstant.USERADDRESS.IS_NOT_DEFAULT_AADDRESS);
			userAddreesMapper.updateByPrimaryKeySelective(userAddrees);
		}
		// 2 把设置的新的默认地址设置为默认地址
		newDefaultUserAddress.setIsDefault(SystemConstant.USERADDRESS.IS_DEFAULT_AADDRESS);
		userAddreesMapper.updateByPrimaryKeySelective(newDefaultUserAddress);
	}
}
