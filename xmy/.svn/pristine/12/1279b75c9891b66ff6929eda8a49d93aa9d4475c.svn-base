package com.zfj.xmy.user.service.common;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.EmailLog;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;

public interface UserInfoService {
	void findUserInfo(ReqData reqData,PageBean pageBean);
	int getCountUserInfo(ReqData reqData);
	/**
	 * 
	 * @Description 获取的是扩展类的UserInfoDto
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日上午11:16:24
	 */
	UserInfoDto findUserInfo(ReqData reqData);

	/**
	 * 批量删除用户信息
	 * @Description 
	 * @param idArry
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日下午4:10:59
	 */
	int deleteUsersInfo(String[] idArry);
	/**
	 * 
	 * @Description App端口根据ID查询用户信息
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月19日下午7:43:32
	 */
	UserInfoDto getUserInfoDto(Long id);
	/**
	 * 
	 * @Description 根据原ID和旧密码，更新新密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @Author liuw
	 * @Date 2017年7月20日上午10:15:16
	 */
	void updatePassword(Long id, String oldPassword, String newPassword);
	/**
	 * 
	 * @Description 更新手机号码
	 * @param id
	 * @param oldPhone
	 * @param newPhone
	 * @Author liuw
	 * @Date 2017年7月20日上午11:21:09
	 */
	void updatePhone(Long id, String oldPhone, String newPhone);
	/**
	 * 
	 * @Description 更新邮箱号码
	 * @param id
	 * @param oldEmail
	 * @param newEmail
	 * @Author liuw
	 * @Date 2017年7月20日上午11:26:48
	 */
	void updateEmail(UserInfo userInfo,EmailLog emailLog);
	/**
	 * 
	 * @Description 根据条件有有选择的更新UserInfo的值
	 * @param reqData
	 * @param userInfoDto
	 * @Author liuw
	 * @Date 2017年7月20日下午1:57:09
	 */
	void updateUserInfo(ReqData reqData, UserInfoDto userInfoDto);
	/**
	 * 
	 * @Description 根据Id查询user的收货地址列表 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月20日下午2:46:36
	 */
	List<UserAddrees> getUserAddress(Long id);
	/**
	 * 
	 * @Description 插入用户收货地址
	 * @param userAddrees
	 * @Author liuw
	 * @Date 2017年7月20日下午3:34:01
	 */
	void insertUserAddress(UserAddrees userAddrees);
	/**
	 * 
	 * @Description 更新用户的收货地址
	 * @param userAddrees
	 * @Author liuw
	 * @Date 2017年7月20日下午4:57:21
	 */
	void updateUserAddress(UserAddrees userAddrees);
	/**
	 * 
	 * @Description 设置默认收货地址，同时去掉之前的默认收货地址标识
	 * @param oldUserAddrees
	 * @param newUserAddresAddrees
	 * @Author liuw
	 * @Date 2017年7月20日下午8:09:41
	 */
	void updateUserDefaultAddress(UserAddrees oldUserAddrees,UserAddrees newUserAddresAddrees);
	/**
	 * 
	 * @Description 根据用户地址的id，删除单条用户地址
	 * @param id
	 * @Author liuw
	 * @Date 2017年7月21日上午11:08:06
	 */
	void delUserAddress(Long id);
	/**
	 * 
	 * @Description 按照条件查询所有符合条件的记录（不分页）
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月26日下午8:53:12
	 */
	List<UserInfoDto> findUserInfoNoPageBean(ReqData reqData);
	/**
	 * 
	 * @Description 更新用户信息，有选择性的按照值更新
	 * @param userInfoDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月27日下午4:35:18
	 */
	int updateUserInfo(UserInfoDto userInfoDto);
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return    
	 * @return UserInfo    
	 * Date:2017年10月23日 下午8:54:59 
	 * @author hexw
	 */
	UserInfo getUserInfo(Long id);
	
	/**
	 * 推送标签页面用户列表
	 * @param reqData
	 * @param pageBean
	 * @Description 
	 * @date 2018年1月23日  上午11:11:37
	 * @author wy
	 * 2018
	 * @return void
	 */
	void findPushLabelUserList(ReqData reqData, PageBean pageBean);
}
