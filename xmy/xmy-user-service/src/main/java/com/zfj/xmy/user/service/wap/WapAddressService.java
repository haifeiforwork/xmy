package com.zfj.xmy.user.service.wap;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.UserAddrees;

/**
 * @author cj
 *用户地址service
 */
public interface WapAddressService {
	
	/**
	 *@author cj
	 * @param userId
	 * 根据用户查出所有地址
	 * @return
	 */
	List<UserAddrees> getUserAddressByUserId(Long userId);
	
	
	/**
	 *@author cj
	 * @param id
	 * 根据user_address表主键修改默认状态
	 * @return
	 */
	Integer updateDefault(Long id);

	/**
	 * 
	 *@author cj
	 * @param address 传入地址类
	 * 
	 * @return
	 */
	int add(UserAddrees address);


	/**
	 *@author cj
	 * @param address
	 * 修改地址
	 * @return
	 */
	int updateAddress(UserAddrees address);


	/**
	 *@author cj
	 * @param id
	 * @return
	 * 根据主键查询地址
	 */
	UserAddrees getAddress(Long id);


	/**
	 *@author cj
	 * @param addressId
	 * 根据主键删除地址
	 */
	int del(Long addressId);
}
