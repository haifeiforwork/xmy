package com.zfj.xmy.user.service.pc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.UserAddreesMapper;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.user.service.pc.PcUserAddressService;
@Service
public class PcUserAddressServiceImpl extends BaseService implements PcUserAddressService{

	@Autowired
	private UserAddreesMapper addreesMapper;
	
	//默认收货地址 0.是  1.否
	private final static int IS_DEFAULT=0;
	private final static int NOT_DEFAULT=1;
	
	/**
	 * 查询用户收货地址
	 */
	@Override
	public List<UserAddrees> findAddressByUserId(long userId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("type", 0, SystemConstant.REQ_PARAMETER_EQ);//用户收货地址：type 0：用户收货地址 1：配送人信息\
		reqData.setSortname("is_default");
		reqData.setSortorder("asc");
		List<UserAddrees> selectByExample = addreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}

	@Override
	public void addUserAddrees(UserAddrees userAddrees) {
		userAddrees.setType(0);
		if (!StringUtil.isEmpty(userAddrees.getArea()) && userAddrees.getArea().equals("市、县级市")) {
			userAddrees.setArea(null);
		}
		if (!StringUtil.isEmpty(userAddrees.getCity()) && userAddrees.getCity().equals("市、县级市")) {
			userAddrees.setCity(null);
		}
		addreesMapper.insertSelective(userAddrees);
		
		ReqData reqData= new ReqData();
		reqData.putValue("user_id", userAddrees.getUserId(),SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_default", 0,SystemConstant.REQ_PARAMETER_EQ);
		List<UserAddrees> list=addreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(list.size()==0){
			reqData.clearValue();
			reqData.putValue("user_id", userAddrees.getUserId(),SystemConstant.REQ_PARAMETER_EQ);
			list=addreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			UserAddrees addrees=list.get(0);
			addrees.setIsDefault(0);
			addreesMapper.updateByPrimaryKeySelective(addrees);
		}
	}

	@Override
	public void deleteAddrees(Long id) {
		addreesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UserAddrees findAddrees(Long id) {
		return addreesMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateAddrees(UserAddrees userAddrees) {
		if (!StringUtil.isEmpty(userAddrees.getArea()) && userAddrees.getArea().equals("市、县级市")) {
			userAddrees.setArea(null);
		}
		if (!StringUtil.isEmpty(userAddrees.getCity()) && userAddrees.getCity().equals("市、县级市")) {
			userAddrees.setCity(null);
		}
		addreesMapper.updateByPrimaryKeySelective(userAddrees);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void defaultAddress(Long aid,Long uId) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", uId,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("is_default", IS_DEFAULT,SystemConstant.REQ_PARAMETER_EQ);
		List<UserAddrees> list=addreesMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(!list.isEmpty()){
			for(UserAddrees address:list){
				address.setIsDefault(NOT_DEFAULT);
				addreesMapper.updateByPrimaryKey(address);
			}
		}
		UserAddrees userAddrees=addreesMapper.selectByPrimaryKey(aid);
		if(userAddrees!=null){
			userAddrees.setIsDefault(IS_DEFAULT);
			addreesMapper.updateByPrimaryKeySelective(userAddrees);
		}
	}

}
