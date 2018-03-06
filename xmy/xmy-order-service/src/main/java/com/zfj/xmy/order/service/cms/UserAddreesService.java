package com.zfj.xmy.order.service.cms;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;

public interface UserAddreesService {
	/**
	 * 
	 * @param reqData
	 * @return List<UserAddrees>
	 * @author ljie
	 * @date 2017年6月22日 下午3:32:19
	 * 查询全部配送人信息
	 */
	List<UserAddrees> finAllUserAddrees(ReqData reqData);
	
	/**
	 * 
	 * @param reqData
	 * @return UserAddrees
	 * @author ljie
	 * @date 2017年6月22日 下午4:13:17
	 * 查询单个配送人信息
	 */
	UserAddrees getUserAddrees(ReqData reqData);

	/**
	 * 
	 * @Description 插入单条用户地址
	 * @param userAddrees
	 * @Author liuw
	 * @Date 2017年7月27日上午11:40:46
	 */
	void insertUserAddress(UserAddrees userAddrees);

	/**
	 * 
	 * @Description 更新用户地址单条记录
	 * @param userAddrees
	 * @Author liuw
	 * @Date 2017年7月27日下午3:30:38
	 */
	void updateUserAddress(UserAddrees userAddrees);

	/**
	 * 
	 * @Description 根据收货地址的id，删除单条收货地址记录
	 * @param id
	 * @Author liuw
	 * @Date 2017年7月27日下午4:16:29
	 */
	void delUserAddress(UserAddrees userAddrees);

	/**
	 * 
	 * @Description 设置新的默认收货地址（同时把之前的默认收货地址标志去掉）
	 * @param newDefaultUserAddress
	 * @Author liuw
	 * @Date 2017年7月27日下午7:02:32
	 */
	void updateSetDefaultUserAddress(UserAddrees newDefaultUserAddress);
}
