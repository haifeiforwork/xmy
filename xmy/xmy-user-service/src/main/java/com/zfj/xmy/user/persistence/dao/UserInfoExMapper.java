package com.zfj.xmy.user.persistence.dao;

import java.util.List;







import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.persistence.pojo.dto.common.PushLabelUserDto;
/**
 * 
 * @Description 
 * @Author liuw
 * @Date 2017年6月29日下午4:00:48
 */
public interface UserInfoExMapper extends UserInfoMapper{
	/**
	 * 按条件分页查询
	 * 
	 * @param para
	 *            条件
	 * @param rowBound
	 *            分页RowBounds对象
	 * @return
	 */
	List<UserInfoDto> findUserInfoEx(CriteriaParameter para, RowBounds rowBound);
	
	
	
	
	/**
	 * 
	 * @Description 按条件查询用户信息，不分页
	 * @param para
	 * @return
	 * @Author liuw
	 * @Date 2017年7月26日下午8:51:48
	 */
	List<UserInfoDto> findUserInfoExNoPageBean(CriteriaParameter para);
	/**
	 * 查询扩展后的单条用户记录
	 * @Description 
	 * @param para
	 * @return 扩展后的实体
	 * @Author liuw
	 * @Date 2017年6月30日下午2:48:37
	 */
	UserInfoDto findUserInfoEx2(CriteriaParameter para);
	
	/**
	 * 根据用户Id查询出用户信息（APP端口）
	 * @Description 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月19日下午7:47:21
	 */
	UserInfoDto getUserInfo(CriteriaParameter para);
	
	/**
	 * 根据邮箱查询是否已经存在此邮箱，返回与此邮箱相同记录的总数
	 *@author mgy
	 * @param email
	 * @return
	 */
	Integer getEmailCount(String email);
	/**
	 * 推送标签用户列表
	 * @param parameter
	 * @param rowBounds
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午5:13:07
	 * @author wy
	 * 2018
	 * @return List<PushLabelUserDto>
	 */
	List<PushLabelUserDto> findPushLabelUserList(CriteriaParameter parameter,RowBounds rowBounds);
}
