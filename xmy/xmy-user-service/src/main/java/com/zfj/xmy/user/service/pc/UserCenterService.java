package com.zfj.xmy.user.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.Feedback;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserAnswer;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserQuestion;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;

public interface UserCenterService {
	/**
	 * @Description 根据id查询用户详情
	 * @param id
	 * @return UserInfo
	 * @author zhangh
	 * @Date 2017年8月2日上午10:54:49
	 */
	UserInfo findUserinfo(Long id);
	
	/**
	 * @Description 用户详情修改
	 * @param userInfo
	 * @return int
	 * @author zhangh
	 * @Date 2017年8月2日上午10:54:49
	 */
	int updateUserInfo(UserInfo userInfo);
	
	/**
	 * @Description 根据id查询用户详情
	 * @param id
	 * @return User
	 * @author zhangh
	 * @Date 2017年8月3日下午2:54:49
	 */
	User findUser(Long id);
	
	/**
	 * @Description 修改用户密码
	 * @param user
	 * @return int
	 * @author zhangh
	 * @Date 2017年8月3日下午2:54:49
	 */
	int updateUser(User user);
	
	/**
	 * @Description 根绝分类查找问题
	 * @param int
	 * @return List<UserQuestion>
	 * @author zhangh
	 * @Date 2017年8月4日下午2:54:49
	 */
	List<UserQuestion> findQuestion(int type);
	
	/**
	 * @Description 插入安全问题答案
	 * @param userAnswer
	 * @author zhangh
	 * @Date 2017年8月4日下午2:54:49
	 */
	void insertAnswer(UserAnswer userAnswer);
	
	/**
	 * @Description 手机验证
	 * @param userAnswer
	 * @author zhangh
	 * @Date 2017年8月4日下午2:54:49
	 */
	void updatePhone(long id,String phone,String code);
	
	/**
	 * @Description 查找用户安全问题
	 * @param Long
	 * @return List<UserAnswer>
	 * @author zhangh
	 * @Date 2017年8月4日下午2:54:49
	 */
	List<UserAnswer> findAnswer(Long id);
	
	/**
	 * @Description 查询用户消费记录
	 * @param Long
	 * @return List<UserSpendPoints>
	 * @author zhangh
	 * @Date 2017年8月9日上午11:44:49
	 */
	List<UserSpendPoints> findRecord(Long id);
	
	/**
	 * @Description 查询用户消费记录分页
	 * @param Long
	 * @author zhangh
	 * @Date 2017年8月9日上午11:44:49
	 */
	void findRecordByPage(Long id,PageBean pageBean,int length);
	
	/**
	 * @Description 查询用户商品收藏
	 * @param Long
	 * @author zhangh
	 * @Date 2017年8月9日上午11:44:49
	 */
	List<Goods> findGoodsCollect(Long id);
	
	/**
	 * @Description 分页查询用户商品收藏
	 * @param Long
	 * @param pageBean
	 * @author zhangh
	 * @Date 2017年8月9日上午11:44:49
	 */
	void findGoodsCollectPage(Long id,PageBean pageBean,String Category,String goodsName);
	
	/**
	 * @Description 分页查询用户投诉与建议
	 * @param Long
	 * @param pageBean
	 * @author zhangh
	 * @Date 2017年8月12日上午11:44:49
	 */
	void findAdviseByPage(Long id,PageBean pageBean);
	
	/**
	 * @Description 添加用户投诉与建议
	 * @param feedback
	 * @author zhangh
	 * @Date 2017年8月12日下午2:44:49
	 */
	void addAdvise(Feedback feedback);
	
	/**
	 * @Description 根据id删除投诉与建议
	 * @param id
	 * @author zhangh
	 * @Date 2017年8月12日下午3:44:49
	 */
	void delAdvise(Long id);
	
	/**
	 * @Description 根据id分页查询用户购物卡
	 * @param id
	 * @author zhangh
	 * @Date 2017年8月15日上午10:44:49
	 */
	void findShopCard(long id,PageBean pageBean,int status);
	
	/**
	 * 远程校验用户名或者手机号码是否已经被注册
	 * @author dengq
	 * @param type 0：验证用户名，1：验证手机号码
	 * @param key 接受的信息
	 * @return
	 */
	public boolean validUserNameOrMobilePhone(String key,Integer type) ;
	
	/**
	 * @Description 更新用户头像
	 * @param userInfo
	 * @author zhangh
	 * @Date 2017年8月17日上午10:44:49
	 */
	void updateImg(UserInfo userInfo);
	
	/**
	 * @Description 添加用户足迹
	 * @param userId
	 * @param goodsId
	 * @author zhangh
	 * @Date 2017年8月25日下午5:44:49
	 */
	void addFootprint(long userId,long goodsId);
	
	/**
	 * 发送邮件
	 * @author zhangh
	 * @param email
	 */
	String sendEmail(String email);

	/**
	 * 批量删除收藏商品
	 * @param idStr
	 * @param userId    
	 * @return void    
	 * Date:2017年11月20日 下午5:22:18 
	 * @author hexw
	 */
	void deleteCollectionGoods(String idStr, Long userId);
	
	/**
	 * 校验手机号是否已被绑定
	 * @param phone
	 * @return    
	 * @return int    
	 * Date:2017年11月30日 下午5:48:23 
	 * @author hexw
	 */
	int voildMobilePhone(String phone);
	
}
