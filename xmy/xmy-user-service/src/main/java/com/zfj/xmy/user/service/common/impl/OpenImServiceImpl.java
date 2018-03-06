package com.zfj.xmy.user.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.api.domain.Userinfos;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.enu.BaseProp;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.openim.OpenImManager;
import com.zfj.xmy.user.service.common.OpenImService;

@Service
public class OpenImServiceImpl implements OpenImService {


	@Autowired
	private OpenImManager openImManager;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Object getOpenImUsers(Long userid){
		//1.检查用户
		User user = userMapper.selectByPrimaryKey(userid);
		if (ObjectUtil.isNull(user)) {
			throw new BusinessException("用户不存在");
		}
//		if (StringUtil.isEmpty(user.getAppToken())) {
//			throw new BusinessException("用户信息获取错误");
//		}
		
		//2.生成云旺用户id 并添加用户
		if (StringUtil.isEmpty(user.getOpenimUserid())) {
			user.setOpenimUserid(UUIDUtil.generateToken());
			userMapper.updateByPrimaryKeySelective(user);
		}
		addOrUpdateUser(userid);
		//3.获取用户
		com.xiaoleilu.hutool.json.JSONObject jo = new com.xiaoleilu.hutool.json.JSONObject(openImManager.getUser(userid)) ;
		System.out.println(">>>>>>>>>>>>>>>>>>>>jo:"+jo);
		JSONArray userarray = jo.getJSONArray("userinfos");
		if (ObjectUtil.isNull(userarray)) {
			throw new BusinessException("获取用户失败");
		}
		if (userarray.isEmpty()) {
			throw new BusinessException("获取用户失败");
		}
		return userarray.get(0);
	}
	
	 @Override
	 public  String addOrUpdateUser(Long userid){
		User user = userMapper.selectByPrimaryKey(userid);
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userid);
		if (ObjectUtil.isNull(user)) {
			throw new BusinessException("用户不存在");
		}
		if (ObjectUtil.isNull(userInfo)) {
			throw new BusinessException("用户信息不完整");
		}
		Userinfos userinfos = openImManager.genarateOpenImUseridAndPass(userid);
		userinfos.setName(user.getName());
		userinfos.setNick(user.getName()); //昵称名字都先设置成手机号
		String avatar = SystemConstant.USER.HTYP_XMY_APP + "common/headimage/"+userInfo.getAvatar(); //头像路径
		userinfos.setIconUrl(avatar);
		String addUserRet = openImManager.addUser(userinfos);
		
		JSONObject addUserRetJO = new JSONObject(addUserRet);
		JSONObject fail_msgs = addUserRetJO.getJSONObject("openim_users_add_response").getJSONObject("fail_msg");
		if (ObjectUtil.isNull(fail_msgs.getJSONArray("string"))) {
			return addUserRet;
		}
		if ("data exist".equals(fail_msgs.getJSONArray("string").get(0))) { //
			//如果提示已经存在，则更新数据
			return openImManager.updateUser(userinfos);
		}else {
			return addUserRet;
		}
	 }
	
	@Override
	public String getOpenImAppkey(){
		return OpenImManager.getAppkey();
	}
	
	@Override
	public String getOpenImCustomid(){
		return OpenImManager.getCustomtaobaoid();
	}
	
}
