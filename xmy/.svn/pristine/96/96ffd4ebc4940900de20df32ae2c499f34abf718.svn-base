package com.zfj.xmy.user.service.common;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.MsgPushInfo;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.AppUserInDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.MobilePhoneDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.UserLoginInDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.UserLoginOutDto;
import com.zfj.xmy.user.persistence.pojo.dto.app.UserRegisterInDto;
import com.zfj.xmy.user.persistence.pojo.dto.common.PushLabelUserDto;

public interface UserService {
	/**
	 * 
	 * @Description 查询UserInfo、User的扩展信息
	 * @param para
	 * @param rowBound
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日上午10:54:49
	 */
	List<UserInfoDto> findUserInfoEx(CriteriaParameter para, RowBounds rowBound);
	/**
	 * 
	 * @Description 更新User信息
	 * @param reqData
	 * @param userInfoDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日上午10:54:31
	 */
	int updateUser(ReqData reqData,UserInfoDto userInfoDto);
	/**
	 * 
	 * @Description 更新User的状态。（禁用，启用）
	 * @param idArry
	 * @param status
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日下午3:11:04
	 */
	int updateUserStatus(String[] idArry, String status);
	/**
	 * 批量删除用户信息
	 * @Description 
	 * @param idArry
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日下午4:10:59
	 */
	int deleteUsers(String[] idArry);
	
	/**
	 * 用户注册
	 * @param registerInDto
	 * @Description 
	 * @date 2017年7月18日  上午10:45:57
	 * @author wy
	 * 2017
	 * @return void
	 */
	void register(UserRegisterInDto registerInDto);
	/**
	 * 用户登录
	 * @param loginInDto
	 * @return
	 * @Description 
	 * @date 2017年7月18日  上午11:10:13
	 * @author wy
	 * 2017
	 * @return User
	 */
	UserLoginOutDto login(UserLoginInDto loginInDto);
	/**
	 * 获取单条消息
	 * @param msgid
	 * @return
	 * @Description 
	 * @date 2017年7月31日  下午1:42:47
	 * @author wy
	 * 2017
	 * @return MsgPushInfo
	 */
	MsgPushInfo getMsgPushById(Long msgid);
	/**
	 * 根据用户id 获取 消息列表数据
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2017年7月31日  下午1:43:11
	 * @author wy
	 * 2017
	 * @return List<TMsgPushInfo>
	 */
     List<MsgPushInfo> findMsgInfoByUser(Long userid);
     /**
 	 * 根据用户id 获取未读 消息列表数据
 	 * @param userid
 	 * @return
 	 * @Description 
 	 * @date 2017年7月31日  下午1:43:11
 	 * @author wy
 	 * 2017
 	 * @return List<TMsgPushInfo>
 	 */
      List<MsgPushInfo> findNotReadMsgInfo(Long userid);
     /**
      * 设置消息为已读
      * @param userid 
      * @param msgid
      * @Description 
      * @date 2017年7月31日  下午4:10:04
      * @author wy
      * 2017
      * @return void
      */
	void makeMsgRead(Long userid, Long msgid);
	/**
	 * 找回密码
	 * @param retrieveInDto
	 * @Description 
	 * @date 2017年8月3日  上午9:35:58
	 * @author wy
	 * 2017
	 * @return void
	 */
	void retrievePass(UserRegisterInDto retrieveInDto);
	/**
	 * 发送短信
	 * @param phoneDto
	 * @Description 
	 * @date 2017年8月7日  下午4:39:11
	 * @author wy
	 * 2017
	 * @return void
	 */
	void sendsms(MobilePhoneDto phoneDto);
	
	/**
	 * 换绑手机号
	 * @param bindNewPhoneVo
	 * @param userid
	 * @Description 
	 * @date 2017年9月5日  下午5:49:13
	 * @author wy
	 * 2017
	 * @return void
	 */
	void bindNewPhone(UserRegisterInDto bindNewPhoneVo, Long userid);
	/**
	 * 修改密码
	 * @Description 
	 * @param user
	 * @Author liuw
	 * @Date 2017年9月5日下午5:46:41
	 */
	void modifyPassword(AppUserInDto user);
	/**
	 * 发送邮箱绑定邮件
	 * @param email
	 * @Description 
	 * @date 2017年9月7日  下午4:23:57
	 * @author wy
	 * 2017
	 * @return void
	 */
	void sendBindinEmail(String email);
	
	/**
	 * 确认扫码登录
	 * @param userId
	 * @param code 扫到的码
	 * @Description 
	 * @date 2017年9月29日  上午9:52:22
	 * @author wy
	 * 2017
	 * @return void
	 */
	void updateScancode(Long userId, String code);
	
	/**
	 * 校验改手机号是否已被绑定
	 * @Description 
	 * @param mobilePhone
	 * @Author hexw
	 * @Date 2017年11月21日下午3:53:35
	 */
	void verifyPhone(String mobilePhone);
	
	/**
	 * 校验旧密码
	 * @param userId
	 * @param oldPassWord    
	 * @return void    
	 * Date:2017年11月25日 下午1:57:04 
	 */
	void verifyOldPassword(Long userId, String oldPassword);
	/**
	 * 推送标签用户列表
	 * @param parameter
	 * @param pageBean
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午5:07:03
	 * @author wy
	 * 2018
	 * @return List<PushLabelUserDto>
	 */
	@Deprecated
	List<PushLabelUserDto> findPushLabelUserList(CriteriaParameter parameter,
			PageBean pageBean);
	/**
	 * 根据名称查询用户是否存在
	 * @param userName
	 * @return List<User>
	 * @author ***
	 * @date 2018年1月24日 下午2:55:22
	 */
	User getUser(String userName,String password,Boolean isPwd);
	
	/**
	 * 检查用户名是否被绑定过用户电话
	 * @param name
	 * @return    
	 * @return boolean    
	 * Date:2018年2月8日 下午3:33:58 
	 * @author hexw
	 */
	boolean validUserName(String name);
}
