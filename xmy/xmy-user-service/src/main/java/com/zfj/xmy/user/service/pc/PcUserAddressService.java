package com.zfj.xmy.user.service.pc;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.UserAddrees;

/**
 * @author lij
 */
public interface PcUserAddressService {
	/**
	 * @param userId
	 * @return List<UserAddrees>
	 * @author lij
	 * @date 2017年8月3日 上午11:09:55
	 * 根据用户id查询用户收货地址
	 */
	List<UserAddrees> findAddressByUserId(long userId);
	
	/**
	 * @param userAddrees
	 * @author zhangh
	 * @date 2017年8月7日 下午4:09:55
	 * 添加用户收货地址
	 */
	void addUserAddrees(UserAddrees userAddrees);
	
	/**
	 * @param id
	 * @author zhangh
	 * @date 2017年8月7日 下午7:20:55
	 * 根据id删除用户收货地址
	 */
	void deleteAddrees(Long id);
	
	/**
	 * @param id
	 * @author zhangh
	 * @date 2017年8月8日 上午10:20:55
	 * 根据地址id查询单个收货地址
	 */
	UserAddrees findAddrees(Long id);
	
	/**
	 * @param id
	 * @author zhangh
	 * @date 2017年8月8日 上午10:20:55
	 * 根据地址id查询单个收货地址
	 */
	void updateAddrees(UserAddrees userAddrees);
	
	/**
	 * @param id
	 * @author zhangh
	 * @date 2017年8月8日 下午3:20:55
	 * 设置默认收货地址
	 */
	void defaultAddress(Long aId,Long uId);
}
